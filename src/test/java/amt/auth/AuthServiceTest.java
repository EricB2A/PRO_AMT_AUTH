package amt.auth;

import amt.auth.DTO.AccountDTO;
import amt.auth.DTO.CredentialDTO;
import amt.auth.DTO.TokenDTO;
import amt.auth.Model.User;
import amt.auth.Model.UserRepository;
import amt.auth.Service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
/**
 * Tests unitaires du service d'auth. On est pas loin du assertTrue(true) mais avec des tests unitaires dur de faire mieux.
 */
public class AuthServiceTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mock
    public UserRepository userRepository;
    public AuthService service;

    @Test
    public void validSigninTest() {
        service = new AuthService(userRepository);
        CredentialDTO cred = new CredentialDTO("ilias", "Pa$$w0rd");
        when(userRepository.findByUsername("ilias")).thenReturn(new User(cred.getUsername(), passwordEncoder.encode("Pa$$w0rd"), "user"));
        AccountDTO account = new AccountDTO(1, cred.getUsername(), "user");
        Assertions.assertEquals(service.signin(cred).getAccount().getUsername(), account.getUsername());
        Assertions.assertEquals(service.signin(cred).getAccount().getRole(), account.getRole());
    }

    @Test
    public void invalidPasswordSigninTest() {
        Assertions.assertThrows(HttpClientErrorException.Forbidden.class, () -> {
            service = new AuthService(userRepository);
            CredentialDTO cred = new CredentialDTO("ilias", "Pa$$$$w0rdddd");
            when(userRepository.findByUsername("ilias")).thenReturn(new User(cred.getUsername(), passwordEncoder.encode("Pa$$w0rd"), "user"));
            AccountDTO account = new AccountDTO(1, cred.getUsername(), "user");
            TokenDTO tokenDTO = new TokenDTO("token", account);
            service.signin(cred);
        });
    }

    @Test
    public void validSignupTest() {
        service = new AuthService(userRepository);
        CredentialDTO cred = new CredentialDTO("ilias", "Pa$$w0rd");
        User newUser = new User(cred.getUsername(), "Pa$$w0rd_hash", "user");
        when(userRepository.save(Mockito.any(User.class))).thenReturn(newUser);
        AccountDTO res = service.signup(cred);
        Assertions.assertEquals(res.getRole(), newUser.getRole());
        Assertions.assertEquals(res.getUsername(), newUser.getUsername());
    }

    @Test
    public void UserAlreadyExistSignupTest() {
        service = new AuthService(userRepository);
        CredentialDTO cred = new CredentialDTO("ilias", "Pa$$$w0rd");
        when(userRepository.save(new User(cred.getUsername(), cred.getPassword(), "user"))).thenThrow(new DataIntegrityViolationException("Duplicate"));
        Assertions.assertThrows(MockitoException.class, () -> {
            service.signup(cred);
        });
    }
}
