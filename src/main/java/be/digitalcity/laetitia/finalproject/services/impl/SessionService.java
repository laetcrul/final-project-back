package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.config.jwt.JwtTokenProvider;
import be.digitalcity.laetitia.finalproject.exceptions.models.UsernamePasswordInvalidException;
import be.digitalcity.laetitia.finalproject.mappers.UserMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.LoginForm;
import be.digitalcity.laetitia.finalproject.repositories.UserRepository;
import be.digitalcity.laetitia.finalproject.services.SessionServiceInterface;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SessionService implements SessionServiceInterface {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final AuthenticationManager manager;
    private final JwtTokenProvider provider;


    public SessionService(UserRepository repository, UserMapper mapper, AuthenticationManager manager, JwtTokenProvider provider) {
        this.repository = repository;
        this.mapper = mapper;
        this.manager = manager;
        this.provider = provider;
    }

    @Override
    public UserDTO login(LoginForm form) {
            User user = this.repository.findByUsername(form.getUsername()).orElseThrow(() -> new IllegalArgumentException("no user for this username"));
            this.manager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

            UserDTO dto = this.mapper.toDTO(user);
            System.out.println(user.getAuthorities());
            dto.setToken(this.provider.createToken(user.getUsername(), user.getAllRolesAsString()));
            return dto;
    }
}
