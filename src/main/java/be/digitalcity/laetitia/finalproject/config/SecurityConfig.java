package be.digitalcity.laetitia.finalproject.config;

import be.digitalcity.laetitia.finalproject.config.jwt.JwtAuthenticationFilter;
import be.digitalcity.laetitia.finalproject.config.jwt.JwtTokenProvider;
import be.digitalcity.laetitia.finalproject.services.UserServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
@EnableTransactionManagement
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final UserServiceInterface userServiceInterface;
    private final JwtTokenProvider tokenProvider;


    public SecurityConfig(UserServiceInterface userServiceInterface, JwtTokenProvider tokenProvider) {
        this.userServiceInterface = userServiceInterface;
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().disable()
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userServiceInterface)
                .passwordEncoder(encoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
