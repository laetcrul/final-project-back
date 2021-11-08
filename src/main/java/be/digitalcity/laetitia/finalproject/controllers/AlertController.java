package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.AlertDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.AlertEventDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.AlertTopicDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Alert;
import be.digitalcity.laetitia.finalproject.models.forms.AlertEventForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertResponseForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertTopicForm;
import be.digitalcity.laetitia.finalproject.services.impl.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }

    @GetMapping("")
    @Secured({"ROLE_MANAGE_ALERTS"})
    public ResponseEntity<List<AlertDTO>> findAll() {
        return ResponseEntity.ok(service.findAllAlerts());
    }

    @GetMapping("/created_by/{id}")
    @Secured({"ROLE_MANAGE_OWNED_ELEMENTS"})
    public ResponseEntity<List<AlertDTO>> findByCreator(@PathVariable Alert alert) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!alert.getCreator().equals(user)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok(this.service.findAllByCreator(alert.getId()));
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<List<AlertTopicDTO>> findByTopic(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByTopic(id));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<AlertEventDTO>> findByEvent(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByEvent(id));
    }

    @PostMapping("/event")
    public ResponseEntity<String> createEventAlert(@RequestBody AlertEventForm form) {
        this.service.insertEventAlert(form);
        return ResponseEntity.ok("Event alert created");
    }

    @PostMapping("/topic")
    public ResponseEntity<String> createTopicAlert(@RequestBody AlertTopicForm form) {
        this.service.insertTopicAlert(form);
        return ResponseEntity.ok("Topic alert created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAlert(@PathVariable Long id, @RequestBody AlertForm form) {
        this.service.updateAlert(id, form);
        return ResponseEntity.ok("Alert was updated");
    }

    @PutMapping("/response")
    public ResponseEntity<String> respond(@RequestBody AlertResponseForm form) {
        this.service.respondToAlert(form);
        return ResponseEntity.ok("Response sent");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok("Alert was deleted");
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
