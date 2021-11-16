package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.TopicMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Topic;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.models.forms.TopicForm;
import be.digitalcity.laetitia.finalproject.repositories.TopicRepository;
import be.digitalcity.laetitia.finalproject.services.TopicServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TopicService implements TopicServiceInterface {
    private final TopicRepository repository;
    private final TopicMapper mapper;
    private final UserService userService;

    public TopicService(TopicRepository repository, TopicMapper mapper, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public TopicDTO findById(Long id) {
        if (id == null) {
            return null;
        }
        if (this.repository.findById(id).isPresent()) {
            return mapper.toDTO(this.repository.findById(id).get());
        } else throw new NoSuchElementException("no topic found for this id");
    }

    public List<TopicDTO> findAll() {
        return this.mapper.toDTOs(this.repository.findAll());
    }

    public List<TopicDTO> findAllByCreatorId(Long id) {
        if (id == null) {
            return null;
        }
        this.userService.findById(id);
        return this.mapper.toDTOs(this.repository.findAll().stream()
                .filter(topic -> topic.getCreator().getId().equals(id))
                .collect(Collectors.toList()));
    }

    public void insert(TopicForm form, User creator) {
        if (form == null) {
            return;
        }
        Topic toSave = this.mapper.toEntity(form);
        toSave.setCreator(creator);
        toSave.getSubscribedUsers().add(creator);
        this.repository.save(toSave);
    }

    public void update(Long id, TopicForm form) {
        if (id == null) {
            return;
        }
        TopicDTO toSave = this.findById(id);

        toSave.setDescription(form.getDescription());
        toSave.setImage(form.getImage());
        toSave.setName(form.getName());

        repository.save(mapper.toEntity(toSave));
    }

    public void delete(Long id) {
        if (id == null) {
            return;
        }
        repository.deleteById(id);
    }

    public List<TopicDTO> findByRegisteredUser(Long id) {
        if (id == null) {
            return null;
        }
        UserDTO user = this.userService.findById(id);
        return this.findAll().stream()
                .filter(topic -> topic.getSubscribedUsers().contains(user))
                .collect(Collectors.toList());
    }

    public void subscribe(Long userId, Long topicId) {
        if (userId == null || topicId == null) {
            return;
        }
        UserDTO user = this.userService.findById(userId);
        TopicDTO topic = this.findById(topicId);

        if (!topic.getSubscribedUsers().contains(user)) {
            List<UserDTO> updatedList = topic.getSubscribedUsers();
            updatedList.add(user);
            topic.setSubscribedUsers(updatedList);
            this.repository.save(this.mapper.toEntity(topic));
        }
    }

    public void unsubscribe(Long userId, Long topicId) {
        if (userId == null || topicId == null) {
            return;
        }
        UserDTO user = this.userService.findById(userId);
        TopicDTO topic = this.findById(topicId);

        if (topic.getSubscribedUsers().contains(user)) {
            List<UserDTO> updatedList = topic.getSubscribedUsers();
            updatedList.remove(user);
            topic.setSubscribedUsers(updatedList);
            this.repository.save(mapper.toEntity(topic));
        }
    }
}