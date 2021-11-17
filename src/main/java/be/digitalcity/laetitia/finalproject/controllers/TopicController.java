package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Topic;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.TopicForm;
import be.digitalcity.laetitia.finalproject.services.impl.ContextService;
import be.digitalcity.laetitia.finalproject.services.impl.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
@CrossOrigin
public class TopicController {
    private final TopicService service;
    private final ContextService contextService;

    public TopicController(TopicService service, ContextService contextService) {
        this.service = service;
        this.contextService = contextService;
    }

    @GetMapping("")
    @Secured({"ROLE_SEE_TOPICS"})
    public ResponseEntity<List<TopicDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/owned")
    @Secured({"ROLE_SEE_TOPICS"})
    public ResponseEntity<List<TopicDTO>> findByCreator() {
        User currentUser = this.contextService.getCurrentUser();

        return ResponseEntity.ok(this.service.findAllByCreatorId(currentUser.getId()));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_SEE_TOPICS"})
    public ResponseEntity<TopicDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping("/registered")
    @Secured({"ROLE_SEE_TOPICS"})
    public ResponseEntity<List<TopicDTO>> findByRegisteredUser() {
        User currentUser = this.contextService.getCurrentUser();
        return ResponseEntity.ok(this.service.findByRegisteredUser(currentUser.getId()));
    }

    @PostMapping("")
    @Secured({"ROLE_CREATE_TOPIC"})
    public void create(@RequestBody TopicForm form) {
        User currentUser = this.contextService.getCurrentUser();
        this.service.insert(form, currentUser);
    }

    @PutMapping("/edit/{topic}")
    @Secured({"ROLE_CREATE_TOPIC"})
    public void update(@PathVariable Topic topic, @RequestBody TopicForm form) {
        UserDetails currentUser = this.contextService.getCurrentUserDetails();
        if (!topic.getCreator().equals(currentUser)) {
            return;
        }
        this.service.update(topic.getId(), form);
    }

    @PutMapping("/register/{topicId}")
    @Secured({"ROLE_SUBSCRIBE_TO_TOPIC"})
    public void subscribe(@PathVariable Long topicId) {
        User currentUser = this.contextService.getCurrentUser();
        this.service.subscribe(currentUser.getId(), topicId);
    }

    @PutMapping("/unregister/{topicId}")
    public void unsubscribe(@PathVariable Long topicId) {
        User currentUser = this.contextService.getCurrentUser();
        this.service.unsubscribe(currentUser.getId(), topicId);
    }

    @DeleteMapping("/{topic}")
    @Secured({"ROLE_MANAGE_OWNED_ELEMENTS", "ROLE_MANAGE_TOPICS"})
    public void delete(@PathVariable Topic topic) {
        User currentUser = this.contextService.getCurrentUser();

        if (!currentUser.equals(topic.getCreator())
                && currentUser.getAuthorities().stream()
                .noneMatch(r -> r.getAuthority().equals("ROLE_MANAGE_TOPICS"))) {
            return;
        }
        this.service.delete(topic.getId());
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
