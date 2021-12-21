package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.exceptions.models.UsernamePasswordInvalidException;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.forms.LoginForm;
import be.digitalcity.laetitia.finalproject.services.impl.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginForm form) {
        return ResponseEntity.ok(service.login(form));
    }

    @ExceptionHandler(UsernamePasswordInvalidException.class)
    public ResponseEntity<String> handleInvalidCredentials(
            UsernamePasswordInvalidException e
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Wrong username/password combination");
    }
}