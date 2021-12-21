package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.AlertDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.AlertEventDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.AlertTopicDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Alert;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.AlertEventForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertResponseForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertTopicForm;
import be.digitalcity.laetitia.finalproject.services.impl.AlertService;
import be.digitalcity.laetitia.finalproject.services.impl.ContextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private final AlertService service;
    private final ContextService contextService;

    public AlertController(AlertService service, ContextService contextService) {
        this.service = service;
        this.contextService = contextService;
    }

    @GetMapping("")
    @Secured({"ROLE_MANAGE_ALERTS"})
    public ResponseEntity<List<AlertDTO>> findAll() {
        return ResponseEntity.ok(service.findAllAlerts());
    }

    @GetMapping("/owned")
    @Secured({"ROLE_MANAGE_OWNED_ELEMENTS"})
    public ResponseEntity<List<AlertDTO>> findByCreator() {
        User currentUser = contextService.getCurrentUser();

        return ResponseEntity.ok(this.service.findAllByCreator(currentUser.getId()));
    }

    @GetMapping("/by_topic/{id}")
    @Secured({"ROLE_MANAGE_ALERTS"})
    public ResponseEntity<List<AlertTopicDTO>> findByTopic(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByTopic(id));
    }

    @GetMapping("/by_event/{id}")
    @Secured({"ROLE_MANAGE_ALERTS"})
    public ResponseEntity<List<AlertEventDTO>> findByEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByEvent(id));
    }

    @PostMapping("/event")
    @Secured({"ROLE_RAISE_ALERT"})
    public void createEventAlert(@RequestBody AlertEventForm form) {
        User user = this.contextService.getCurrentUser();
        this.service.insertEventAlert(form, user);
    }

    @PostMapping("/topic")
    @Secured({"ROLE_RAISE_ALERT"})
    public void createTopicAlert(@RequestBody AlertTopicForm form) {
        User user = this.contextService.getCurrentUser();
        this.service.insertTopicAlert(form, user);
    }

    @PutMapping("/{alert}")
    @Secured({"ROLE_RAISE_ALERT"})
    public void updateAlert(@PathVariable Alert alert, @RequestBody AlertForm form) {
        UserDetails user = contextService.getCurrentUserDetails();
        if(!alert.getCreator().equals(user)){
            return;
        }
        this.service.updateAlert(alert.getId(), form);
    }

    @PutMapping("/response")
    @Secured({"ROLE_MANAGE_ALERTS"})
    public void respond(@RequestBody AlertResponseForm form) {
        this.service.respondToAlert(form);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_MANAGE_ALERTS"})
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
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
