package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.forms.EventForm;
import be.digitalcity.laetitia.finalproject.services.impl.EventService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/registered/{id}")
    public ResponseEntity<List<EventDTO>> findBySubscribedUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findBySubscribedUser(id));
    }

    @GetMapping("/owned/{id}")
    public ResponseEntity<List<EventDTO>> findByCreator(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findByCreator(id));
    }

    @GetMapping("/by_topic/{id}")
    public ResponseEntity<List<EventDTO>> findByTopic(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findByTopic(id));
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody EventForm form) {
        service.insert(form);
        return ResponseEntity.ok("Event created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody EventForm form) {
        this.service.update(id, form);
        return ResponseEntity.ok("Event updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok("Event deleted");
    }

    @PutMapping("/register")
    public ResponseEntity<String> register(@Param("userId") Long userId, @Param("eventId") Long eventId) {
        this.service.register(userId, eventId);
        return ResponseEntity.ok("User was registered to event");
    }

    @PutMapping("/unregister")
    public ResponseEntity<String> unregister(@Param("userId") Long userId, @Param("eventId") Long eventId) {
        this.service.unregister(userId, eventId);
        return ResponseEntity.ok("User was unregistered");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}