package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Event;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.EventForm;
import be.digitalcity.laetitia.finalproject.services.impl.ContextService;
import be.digitalcity.laetitia.finalproject.services.impl.EventService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService service;
    private final ContextService contextService;

    public EventController(EventService service, ContextService contextService) {
        this.service = service;
        this.contextService = contextService;
    }

    @GetMapping("")
    @Secured(value = {"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/registered/{user}")
    @Secured({"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findBySubscribedUser(@PathVariable User user) {
        UserDetails currentUser = contextService.getCurrentUser();
        if(!user.equals(currentUser)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok(this.service.findBySubscribedUser(user.getId()));
    }

    @GetMapping("/owned/{user}")
    @Secured({"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findByCreator(@PathVariable User user) {
        UserDetails currentUser = contextService.getCurrentUser();
        if(!currentUser.equals(user)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok(this.service.findByCreator(user.getId()));
    }

    @GetMapping("/by_topic/{id}")
    @Secured({"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findByTopic(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findByTopic(id));
    }

    @PostMapping("")
    @Secured({"ROLE_CREATE_EVENT"})
    public ResponseEntity<String> create(@RequestBody EventForm form) {
        service.insert(form);
        return ResponseEntity.ok("Event created");
    }

    @PutMapping("/{event}")
    @Secured({"ROLE_CREATE_EVENT"})
    public ResponseEntity<String> update(@PathVariable Event event, @RequestBody EventForm form) {
        UserDetails user = contextService.getCurrentUser();

        if (!event.getCreator().getUsername().equals(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized - user does not own the event");
        }

        this.service.update(event.getId(), form);
        return ResponseEntity.ok("Event updated");
    }

    @DeleteMapping("/{event}")
    @Secured({"ROLE_MANAGE_EVENTS", "ROLE_MANAGE_OWNED_ELEMENTS"})
    public ResponseEntity<String> delete(@PathVariable Event event) {
        UserDetails currentUser = this.contextService.getCurrentUser();

        if(!currentUser.equals(event.getCreator())
                && currentUser.getAuthorities().stream()
                .noneMatch(r -> r.getAuthority().equals("ROLE_MANAGE_TOPICS"))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin or creator can delete topic");
        }

        this.service.delete(event.getId());
        return ResponseEntity.ok("Event deleted");
    }

    @PutMapping("/register")
    @Secured({"ROLE_SUBSCRIBE_TO_EVENT"})
    public ResponseEntity<String> register(@Param("userId") Long userId, @Param("eventId") Long eventId) {
        this.service.register(userId, eventId);
        return ResponseEntity.ok("User was registered to event");
    }

    @PutMapping("/unregister")
    @Secured({"ROLE_SUBSCRIBE_TO_EVENT"})
    public ResponseEntity<String> unregister(@Param("userId") User user, @Param("eventId") Long eventId) {
        UserDetails currentUser = contextService.getCurrentUser();
        if(!currentUser.equals(user)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User can only unregister oneself");
        }
        this.service.unregister(user.getId(), eventId);
        return ResponseEntity.ok("User was successfully unregistered");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}