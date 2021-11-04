package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.InvitationDTO;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationForm;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationResponseForm;
import be.digitalcity.laetitia.finalproject.services.impl.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {
    private final InvitationService service;

    public InvitationController(InvitationService service) {
        this.service = service;
    }

    @GetMapping("/received/{id}")
    public ResponseEntity<List<InvitationDTO>> findAllByRecipient(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByRecipient(id));
    }

    @GetMapping("/sent/{id}")
    public ResponseEntity<List<InvitationDTO>> findAllBySender(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllBySender(id));
    }

    @PostMapping("")
    public ResponseEntity<String> create(InvitationForm form) {
        this.service.insert(form);
        return ResponseEntity.ok("Invitation sent");
    }

    @PutMapping("/respond")
    public ResponseEntity<String> respond(@RequestBody InvitationResponseForm form) {
        this.service.respond(form);
        return ResponseEntity.ok("Answer sent");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok("Invitation deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException e
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
