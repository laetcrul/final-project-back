package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.AlertTopicDTO;
import be.digitalcity.laetitia.finalproject.models.entities.AlertTopic;
import be.digitalcity.laetitia.finalproject.models.forms.AlertTopicForm;
import be.digitalcity.laetitia.finalproject.services.impl.TopicService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class AlertTopicMapper {
    private final UserMapper userMapper;
    private final TopicMapper topicMapper;
    private final UserService userService;
    private final TopicService topicService;

    public AlertTopicMapper(UserMapper userMapper, TopicMapper topicMapper, UserService userService, TopicService topicService) {
        this.userMapper = userMapper;
        this.topicMapper = topicMapper;
        this.userService = userService;
        this.topicService = topicService;
    }

    public AlertTopicDTO toDTO(AlertTopic entity) {
        AlertTopicDTO dto = new AlertTopicDTO();

        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setCreator(userMapper.toDTO(entity.getCreator()));
        dto.setCategory(entity.getCategory());
        dto.setHandled(entity.isHandled());
        dto.setResponseMessage(entity.getResponseMessage());
        dto.setTopic(topicMapper.toDTO(entity.getTopic()));

        return dto;
    }

    public AlertTopic toEntity(AlertTopicDTO dto) {
        AlertTopic entity = new AlertTopic();

        entity.setId(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setCreator(userMapper.toEntity(dto.getCreator()));
        entity.setCategory(dto.getCategory());
        entity.setHandled(dto.isHandled());
        entity.setResponseMessage(dto.getResponseMessage());
        entity.setTopic(topicMapper.toEntity(dto.getTopic()));

        return entity;
    }

    public AlertTopic toEntity(AlertTopicForm form) {
        AlertTopic entity = new AlertTopic();

        entity.setMessage(form.getMessage());
        entity.setCreator(userMapper.toEntity(userService.findById(form.getCreatorId())));
        entity.setCategory(form.getCategory());
        entity.setTopic(topicMapper.toEntity(topicService.findById(form.getTopicId())));

        return entity;
    }
}