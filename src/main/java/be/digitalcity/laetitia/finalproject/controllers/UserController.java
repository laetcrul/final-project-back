package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.services.impl.ContextService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    private final ContextService contextService;

    public UserController(UserService service, ContextService contextService) {
        this.service = service;
        this.contextService = contextService;
    }

    @GetMapping("")
    @Secured({"ROLE_SEE_USERS"})
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/department")
    @Secured({"ROLE_SEE_USERS"})
    public ResponseEntity<List<UserDTO>> findAllByDepartment() {
        User currentUser = this.contextService.getCurrentUser();
        return ResponseEntity.ok(this.service.findALlByDepartment(currentUser.getTeam().getDepartment().getId()));
    }

    @GetMapping("/team")
    @Secured({"ROLE_SEE_USERS"})
    public ResponseEntity<List<UserDTO>> findAllByTeam() {
        User currentUser = this.contextService.getCurrentUser();
        return ResponseEntity.ok(this.service.findAllByTeam(currentUser.getTeam().getId()));
    }

    @PutMapping("/disable/{id}")
    @Secured({"ROLE_MANAGE_USERS"})
    public ResponseEntity<String> disable(@PathVariable Long id) {
        this.service.disable(id);
        return ResponseEntity.ok("User account disabled");
    }

    @PutMapping("/enable/{id}")
    @Secured({"ROLE_MANAGE_USERS"})
    public ResponseEntity<String> enable(@PathVariable Long id) {
        this.service.enable(id);
        return ResponseEntity.ok("User account enabled");
    }

    @PutMapping("/add_role")
    @Secured({"ROLE_MANAGE_USERS"})
    public ResponseEntity<String> addRole(@Param("userId") Long userId, @Param("roleId") Long roleId) {
        this.service.addRole(userId, roleId);
        return ResponseEntity.ok("Role added");
    }

    @PutMapping("/remove_role")
    @Secured({"ROLE_MANAGE_USERS"})
    public ResponseEntity<String> removeRole(@Param("userId") Long userId, @Param("roleId") Long roleId) {
        this.service.removeRole(userId, roleId);
        return ResponseEntity.ok("Role removed");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException e
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
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