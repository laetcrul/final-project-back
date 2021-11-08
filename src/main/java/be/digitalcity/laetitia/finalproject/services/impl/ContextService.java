package be.digitalcity.laetitia.finalproject.services.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ContextService {
    private final UserService service;

    public ContextService(UserService service) {
        this.service = service;
    }

    public UserDetails getCurrentUser() {
        return service.loadUserByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
