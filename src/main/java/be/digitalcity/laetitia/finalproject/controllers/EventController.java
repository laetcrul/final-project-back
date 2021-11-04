package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.forms.EventForm;
import be.digitalcity.laetitia.finalproject.services.impl.EventService;
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

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody EventForm form) {
        service.insert(form);
        return ResponseEntity.ok("Event created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody EventForm form){
        this.service.update(id, form);
        return ResponseEntity.ok("Event updated");
    }

    @GetMapping("/subscribed/{id}")
    public ResponseEntity<List<EventDTO>> findBySubscribedUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findBySubscribedUser(id));
    }
}
