package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    @Secured({"ROLE_SEE_USERS"})
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/department/{id}")
    @Secured({"ROLE_SEE_USERS"})
    public ResponseEntity<List<UserDTO>> findAllByDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findALlByDepartment(id));
    }

    @GetMapping("/team/{id}")
    @Secured({"ROLE_SEE_USERS"})
    public ResponseEntity<List<UserDTO>> findAllByTeam(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByTeam(id));
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
    public ResponseEntity<String> addRole(@Param("userId") Long userId, @Param("roleId") Long roleId){
        this.service.addRole(userId, roleId);
        return ResponseEntity.ok("Role added");
    }

    @PutMapping("/remove_role")
    @Secured({"ROLE_MANAGE_USERS"})
    public ResponseEntity<String> removeRole(@Param("userId") Long userId, @Param("roleId") Long roleId){
        this.service.removeRole(userId, roleId);
        return ResponseEntity.ok("Role removed");
    }
}