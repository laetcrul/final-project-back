package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Event;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.EventForm;
import be.digitalcity.laetitia.finalproject.services.impl.ContextService;
import be.digitalcity.laetitia.finalproject.services.impl.EventService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService service;
    private final ContextService contextService;
    private final UserService userService;

    public EventController(EventService service, ContextService contextService, UserService userService) {
        this.service = service;
        this.contextService = contextService;
        this.userService = userService;
    }

    @GetMapping("")
    @Secured(value = {"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findAll() {
        User currentUser = contextService.getCurrentUser();
        UserDTO user = this.userService.findById(currentUser.getId());
        List<EventDTO> allEvents= this.service.findAll();
        List<EventDTO> events = new ArrayList<>();
        events.addAll(allEvents.stream()
                            .filter(event ->
                            (!event.isLimitedToTeam() && !event.isLimitedToDepartment())
                            || (event.isLimitedToTeam() && event.getCreatorTeam().equals(user.getTeam()))
                            || (event.isLimitedToDepartment() && event.getCreatorDepartment().equals(user.getTeam().getDepartment())))
                            .collect(Collectors.toList()));
        return ResponseEntity.ok(events);
    }

    @GetMapping("/admin")
    @Secured(value= {"ROLE_MANAGE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findAllAdmin(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    @Secured(value = {"ROLE_SEE_EVENTS"})
    public ResponseEntity<EventDTO> findOneById(@PathVariable Long id){
        User currentUser = contextService.getCurrentUser();
        EventDTO event = service.findById(id);
        if(event.isLimitedToDepartment() &&
                !currentUser.getTeam().getDepartment().getId().equals(event.getCreatorDepartment().getId())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        if (event.isLimitedToTeam() && !currentUser.getTeam().getId().equals(event.getCreatorTeam().getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(event);
        }

    @GetMapping("/registered")
    @Secured({"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findBySubscribedUser() {
        User currentUser = contextService.getCurrentUser();
        return ResponseEntity.ok(this.service.findBySubscribedUser(currentUser.getId()));
    }

    @GetMapping("/owned")
    @Secured({"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findByCreator() {
        User currentUser = this.contextService.getCurrentUser();

        return ResponseEntity.ok(this.service.findByCreator(currentUser.getId()));
    }

    @GetMapping("/by_topic/{id}")
    @Secured({"ROLE_SEE_EVENTS"})
    public ResponseEntity<List<EventDTO>> findByTopic(@PathVariable Long id) {
        User user = contextService.getCurrentUser();

        List<EventDTO> filteredEvents;
        List<EventDTO> events = new ArrayList<>(this.service.findByTopic(id));

        filteredEvents = events.stream()
                .filter(event ->
                        (!event.isLimitedToTeam() && !event.isLimitedToDepartment())
                                || (event.isLimitedToTeam() && event.getCreatorTeam().getId().equals(user.getTeam().getId()))
                                || (event.isLimitedToDepartment() && event.getCreatorDepartment().getId().equals(user.getTeam().getDepartment().getId())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredEvents);
    }

    @PostMapping("")
    @Secured({"ROLE_CREATE_EVENT"})
    public void create(@RequestBody EventForm form) {
        User currentUser = this.contextService.getCurrentUser();
        service.insert(form, currentUser);
    }

    @PutMapping("edit/{event}")
    @Secured({"ROLE_CREATE_EVENT"})
    public void update(@PathVariable Event event, @RequestBody EventForm form) {
        UserDetails user = contextService.getCurrentUserDetails();

        if (!event.getCreator().getUsername().equals(user.getUsername())) {
            return;
        }

        this.service.update(event.getId(), form);
    }

    @DeleteMapping("/{event}")
    @Secured({"ROLE_MANAGE_EVENTS", "ROLE_MANAGE_OWNED_ELEMENTS"})
    public void delete(@PathVariable Event event) {
        UserDetails currentUser = this.contextService.getCurrentUserDetails();

        if(!currentUser.equals(event.getCreator())
                && currentUser.getAuthorities().stream()
                .noneMatch(r -> r.getAuthority().equals("ROLE_MANAGE_TOPICS"))){
            return;
        }

        this.service.delete(event.getId());
    }

    @PutMapping("/register/{id}")
    @Secured({"ROLE_SUBSCRIBE_TO_EVENT"})
    public void register(@PathVariable Long id) {
        User user = contextService.getCurrentUser();

        List<EventDTO> filteredEvents;
        List<EventDTO> events = new ArrayList<>(this.service.findAll());

        filteredEvents = events.stream()
                .filter(event ->
                        (!event.isLimitedToTeam() && !event.isLimitedToDepartment())
                                || (event.isLimitedToTeam() && event.getCreatorTeam().getId().equals(user.getTeam().getId()))
                                || (event.isLimitedToDepartment() && event.getCreatorDepartment().getId().equals(user.getTeam().getDepartment().getId())))
                .filter(event -> event.getId().equals(id))
                .collect(Collectors.toList());

        if(filteredEvents.stream().findAny().isPresent()){
            this.service.register(user.getId(), id);
        }
    }

    @PutMapping("/unregister/{id}")
    @Secured({"ROLE_SUBSCRIBE_TO_EVENT"})
    public void unregister(@PathVariable Long id) {
        User currentUser = contextService.getCurrentUser();
        this.service.unregister(currentUser.getId(), id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
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