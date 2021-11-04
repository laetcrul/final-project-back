package be.digitalcity.laetitia.finalproject.controllers;

import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.models.forms.TopicForm;
import be.digitalcity.laetitia.finalproject.services.impl.TopicService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class Topic {
    private final TopicService service;

    public Topic(TopicService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<TopicDTO>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/by_creator/{id}")
    public ResponseEntity<List<TopicDTO>> findByCreator(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findAllByCreatorId(id));
    }

    @GetMapping("/registered/{id}")
    public ResponseEntity<List<TopicDTO>> findByRegisteredUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findByRegisteredUser(id));
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody TopicForm form) {
        this.service.insert(form);
        return ResponseEntity.ok("Topic created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody TopicForm form) {
        this.service.update(id, form);
        return ResponseEntity.ok("Topic updated");
    }

    @PutMapping("/subscribe")
    public ResponseEntity<String> subscribe(@Param("userId") Long userId, @Param("topicId") Long topicId){
        this.service.subscribe(userId, topicId);
        return ResponseEntity.ok("Subscribed");
    }

    @PutMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@Param("userId") Long userId, @Param("topicId") Long topicId) {
        this.service.unsubscribe(userId, topicId);
        return ResponseEntity.ok("Unsubscribed");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok("Topic deleted");
    }
}
