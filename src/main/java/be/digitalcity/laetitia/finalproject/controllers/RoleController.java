package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.RoleDTO;
import be.digitalcity.laetitia.finalproject.services.impl.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping("")
    @Secured("ROLE_MANAGE_USERS")
    public ResponseEntity<Set<RoleDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(
            AccessDeniedException e
    ) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied");
    }
}
