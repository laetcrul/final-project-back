package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.forms.AddressForm;
import be.digitalcity.laetitia.finalproject.services.impl.AddressService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> create(@RequestBody AddressForm form){
        this.service.insert(form);
        return ResponseEntity.ok("Address saved");
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_CREATE_ADDRESS"})
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody AddressForm form) {
        this.service.update(id, form);
        return ResponseEntity.ok("Address updated");
    }


    @DeleteMapping("/{id}")
    @Secured({"ROLE_DELETE_ADDRESS"})
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok("Address deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException e
    ){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}