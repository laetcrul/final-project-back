package be.digitalcity.laetitia.finalproject.config;

import be.digitalcity.laetitia.finalproject.services.UserServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {
    private final UserServiceInterface userServiceInterface;

    public SecurityConfig(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
