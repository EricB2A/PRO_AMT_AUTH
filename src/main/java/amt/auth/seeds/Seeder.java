

/**
 * @team AMT - Silkyroad
 * @author Bousbaa Eric, Fusi Noah, Goujgali Ilias, Maillefer Dalia, Teofanovic Stefan
 * @file Seeder.java
 *
 * @brief Class creating basic datas for User
 */
package amt.auth.seeds;

import amt.auth.Model.User;
import amt.auth.Model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class Seeder implements CommandLineRunner {

    final private Logger logger;
    final private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String password;
    @Autowired
    public Seeder(UserRepository userRepository, @Value("${com.example.amt_demo.config.admin.password}") String password) {
        this.userRepository = userRepository;
        this.password = password;
        logger = LoggerFactory.getLogger(Seeder.class);
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        createAdmin();
    }

    /**
     *
     */
    public void createAdmin() {
        logger.debug("Init data...");
        if(userRepository.findByUsername("silkyroad") == null){
            userRepository.save(new User("silkyroad", password, "admin"));
        }
        logger.debug("Init data done ! ...");
    }
}