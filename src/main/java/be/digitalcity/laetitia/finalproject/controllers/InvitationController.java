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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/received")
    @Secured({"ROLE_SEE_INVITATIONS"})
    public ResponseEntity<List<InvitationDTO>> findAllByRecipient() {
        User currentUser = contextService.getCurrentUser();

        return ResponseEntity.ok(this.service.findAllByRecipient(currentUser.getId()));
    }

    @GetMapping("/sent")
    @Secured({"ROLE_SEE_INVITATIONS"})
    public ResponseEntity<List<InvitationDTO>> findAllBySender() {
        User currentUser = contextService.getCurrentUser();
        return ResponseEntity.ok(this.service.findAllBySender(currentUser.getId()));
    }

    @PostMapping("")
    @Secured({"ROLE_CREATE_INVITATION"})
    public void create(@RequestBody InvitationForm form) {
        User currentUser = this.contextService.getCurrentUser();
        this.service.insert(form, currentUser);
    }

    @PutMapping("/respond/{invitation}")
    @Secured({"ROLE_RESPOND_TO_INVITATION"})
    public void respond(@RequestBody InvitationResponseForm form, @PathVariable Invitation invitation) {
        UserDetails currentUser = contextService.getCurrentUserDetails();

        if (!invitation.getRecipient().equals(currentUser)) {
            return;
        }

        this.service.respond(invitation.getId(), form);
    }

    @DeleteMapping("/{invitation}")
    @Secured({"ROLE_MANAGE_OWNED_ELEMENTS"})
    public void delete(@PathVariable Invitation invitation) {
        UserDetails currentUser = contextService.getCurrentUserDetails();
        if (!invitation.getSender().equals(currentUser)) {
            return;
        }
        this.service.delete(invitation.getId());
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
