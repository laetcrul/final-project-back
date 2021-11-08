package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Topic;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.TopicForm;
import be.digitalcity.laetitia.finalproject.services.impl.ContextService;
import be.digitalcity.laetitia.finalproject.services.impl.TopicService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topic")
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

    @GetMapping("/by_creator/{user}")
    @Secured({"ROLE_SEE_TOPICS"})
    public ResponseEntity<List<TopicDTO>> findByCreator(@PathVariable User user) {
        UserDetails currentUser = this.contextService.getCurrentUser();

        if (!currentUser.equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok(this.service.findAllByCreatorId(user.getId()));
    }

    @GetMapping("/registered/{user}")
    @Secured({"ROLE_SEE_TOPICS"})
    public ResponseEntity<List<TopicDTO>> findByRegisteredUser(@PathVariable User user) {
        UserDetails currentUser = this.contextService.getCurrentUser();
        if (!currentUser.equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok(this.service.findByRegisteredUser(user.getId()));
    }

    @PostMapping("")
    @Secured({"ROLE_CREATE_TOPIC"})
    public ResponseEntity<String> create(@RequestBody TopicForm form) {
        this.service.insert(form);
        return ResponseEntity.ok("Topic created");
    }

    @PutMapping("/{topic}")
    @Secured({"ROLE_CREATE_TOPIC"})
    public ResponseEntity<String> update(@PathVariable Topic topic, @RequestBody TopicForm form) {
        UserDetails currentUser = this.contextService.getCurrentUser();
        if (!topic.getCreator().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only allowed to update owned topics");
        }
        this.service.update(topic.getId(), form);
        return ResponseEntity.ok("Topic updated");
    }

    @PutMapping("/subscribe")
    @Secured({"ROLE_SUBSCRIBE_TO_TOPIC"})
    public ResponseEntity<String> subscribe(@Param("userId") User user, @Param("topicId") Long topicId) {
        UserDetails currentUser = this.contextService.getCurrentUser();
        if (!user.equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("can only subscribe oneself");
        }

        this.service.subscribe(user.getId(), topicId);
        return ResponseEntity.ok("Subscribed");
    }

    @PutMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@Param("userId") User user, @Param("topicId") Long topicId) {
        UserDetails currentUser = this.contextService.getCurrentUser();
        if (!user.equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("can only subscribe oneself");
        }

        this.service.unsubscribe(user.getId(), topicId);
        return ResponseEntity.ok("Unsubscribed");
    }

    @DeleteMapping("/{topic}")
    @Secured({"ROLE_MANAGE_OWNED_ELEMENTS", "ROLE_MANAGE_TOPICS"})
    public ResponseEntity<String> delete(@PathVariable Topic topic) {
        UserDetails currentUser = this.contextService.getCurrentUser();

        if(!currentUser.equals(topic.getCreator())
                && currentUser.getAuthorities().stream()
                .noneMatch(r -> r.getAuthority().equals("ROLE_MANAGE_TOPICS"))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin or creator can delete topic");
        }
        this.service.delete(topic.getId());
        return ResponseEntity.ok("Topic deleted");
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
