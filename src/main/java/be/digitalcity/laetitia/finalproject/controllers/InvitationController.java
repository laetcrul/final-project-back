package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.InvitationDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Invitation;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationForm;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationResponseForm;
import be.digitalcity.laetitia.finalproject.services.impl.ContextService;
import be.digitalcity.laetitia.finalproject.services.impl.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invitation")
public class InvitationController {
    private final InvitationService service;
    private final ContextService contextService;
    private final InvitationService invitationService;

    public InvitationController(InvitationService service, ContextService contextService, InvitationService invitationService) {
        this.service = service;
        this.contextService = contextService;
        this.invitationService = invitationService;
    }

    @GetMapping("/received/{user}")
    @Secured({"ROLE_SEE_INVITATIONS"})
    public ResponseEntity<List<InvitationDTO>> findAllByRecipient(@PathVariable User user) {
        UserDetails currentUser = contextService.getCurrentUser();
        if(!currentUser.equals(user)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok(this.service.findAllByRecipient(user.getId()));
    }

    @GetMapping("/sent/{user}")
    @Secured({"ROLE_SEE_INVITATIONS"})
    public ResponseEntity<List<InvitationDTO>> findAllBySender(@PathVariable User user) {
        UserDetails currentUser = contextService.getCurrentUser();
        if(!currentUser.equals(user)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok(this.service.findAllBySender(user.getId()));
    }

    @PostMapping("")
    @Secured({"ROLE_CREATE_INVITATION"})
    public ResponseEntity<String> create(@RequestBody InvitationForm form) {
        this.service.insert(form);
        return ResponseEntity.ok("Invitation sent");
    }

    @PutMapping("/respond/{invitation}")
    @Secured({"ROLE_RESPOND_TO_INVITATION"})
    public ResponseEntity<String> respond(@RequestBody InvitationResponseForm form, @PathVariable Invitation invitation) {
        UserDetails currentUser = contextService.getCurrentUser();

        if (!invitation.getRecipient().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized - user is not recipient of invitation");
        }

        this.service.respond(invitation.getId(), form);
        return ResponseEntity.ok("Answer sent");
    }

    @DeleteMapping("/{invitation}")
    @Secured({"ROLE_MANAGE_OWNED_ELEMENTS"})
    public ResponseEntity<String> delete(@PathVariable Invitation invitation) {
        UserDetails currentUser = contextService.getCurrentUser();
        if (!invitation.getSender().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can only be deleted by sender");
        }
        this.service.delete(invitation.getId());
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
