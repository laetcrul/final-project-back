package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.forms.AddressForm;
import be.digitalcity.laetitia.finalproject.services.impl.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<AddressDTO>> getAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @PostMapping("")
    @Secured({"ROLE_CREATE_ADDRESS"})
    public void create(@RequestBody AddressForm form){
        this.service.insert(form);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_CREATE_ADDRESS"})
    public void update(@PathVariable Long id, @RequestBody AddressForm form) {
        this.service.update(id, form);
    }


    @DeleteMapping("/{id}")
    @Secured({"ROLE_DELETE_ADDRESS"})
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){

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