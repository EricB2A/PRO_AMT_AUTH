package amt.auth.Service;

import amt.auth.DTO.AccountDTO;
import amt.auth.DTO.CredentialDTO;
import amt.auth.DTO.TokenDTO;
import amt.auth.Exception.UserAlreadyExistException;
import amt.auth.Model.User;
import amt.auth.Model.UserRepository;
import amt.auth.Util.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.StandardCharsets;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final String secret;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public AuthService(UserRepository userRepository, @Value("${com.example.amt_demo.config.jwt.secret}") String secret) {
        this.userRepository = userRepository;
        this.secret = secret;
    }

    /**
     * Methode de login retournant un TokenDTO si l'utilisateur est valide.
     * @param credential de l'utilisateur
     * @return TokenDTO contenant les info de l'utilisateur ainsi que le JWT
     */
    public TokenDTO signin(CredentialDTO credential) {
        User user = userRepository.findByUsername(credential.getUsername());
        passwordEncoder.encode(credential.getPassword());

        // If the password doesn't match
        // DPE - Lancer une exception http au niveau du service casse un peu la séparation en layer de l'application.
        // Je lancerais une exception "custom" et pour la catcher dans le controller, et ça serait la tâche du controlleur de lancer l'exception http
        if (user == null || !passwordEncoder.matches(credential.getPassword(), user.getPassword())) {
            throw HttpClientErrorException.create(HttpStatus.FORBIDDEN,
                    "Les informations de connexion fournies sont incorrectes",
                    new HttpHeaders(),
                    "".getBytes(StandardCharsets.UTF_8),
                    null);
        }
        return new TokenDTO(JWTUtils.generateJWT(user, secret), new AccountDTO(user));
    }

    /**
     * Méthode de création d'un compte
     * @param credentialDTO du nouvel utilisateur
     * @return AccountDTO
     */
    public AccountDTO signup(CredentialDTO credentialDTO) {

        User newUser = null;
        try{
            newUser = new User(credentialDTO.getUsername(), passwordEncoder.encode(credentialDTO.getPassword()), "user");
            userRepository.save(newUser);
        }catch(DataIntegrityViolationException exception){
            throw new UserAlreadyExistException("Ce nom d'utilisateur existe déjà");
        }
        return new AccountDTO(newUser);
    }
}
