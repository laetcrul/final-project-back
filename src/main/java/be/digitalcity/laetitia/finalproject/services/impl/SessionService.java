package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.config.jwt.JwtTokenProvider;
import be.digitalcity.laetitia.finalproject.mappers.UserMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.LoginForm;
import be.digitalcity.laetitia.finalproject.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
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

    public UserDTO login(LoginForm form) {
        try{
            User u = repository.findByUsername(form.getUsername())
                    .orElseThrow();

            manager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

            UserDTO dto = mapper.toDTO(u);
            dto.setToken( provider.createToken(u.getUsername(), u.getAllRolesAsString()) );
            return dto;

        }catch (Exception ex){
            throw new IllegalArgumentException();
        }

    }
}
