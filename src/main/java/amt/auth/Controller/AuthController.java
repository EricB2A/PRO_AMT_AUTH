package amt.auth.Controller;

import amt.auth.DTO.AccountDTO;
import amt.auth.DTO.TokenDTO;
import amt.auth.Service.AuthService;
import amt.auth.DTO.CredentialDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint de création de compte
     * @param credentialDTO crédential du nouvel utilisateur
     * @return ResponseEntity<AccountDTO>
     */
    @PostMapping(value = "/accounts/register")
    public ResponseEntity<AccountDTO> signup(@RequestBody CredentialDTO credentialDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(credentialDTO));
    }

    /**
     * Endpoint de connexion à un compte existant
     * @param credentialDTO credential de l'utilisateur
     * @return ResponseEntity<TokenDTO>
     */
    @PostMapping(value = "/auth/login")
    public ResponseEntity<TokenDTO> signin(@RequestBody CredentialDTO credentialDTO) {
        return ResponseEntity.ok(authService.signin(credentialDTO));
    }
}
