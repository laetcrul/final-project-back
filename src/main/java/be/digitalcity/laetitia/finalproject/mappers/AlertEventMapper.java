package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.AlertEventDTO;
import be.digitalcity.laetitia.finalproject.models.entities.AlertEvent;
import be.digitalcity.laetitia.finalproject.models.forms.AlertEventForm;
import be.digitalcity.laetitia.finalproject.services.impl.EventService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class AlertEventMapper {
    private final UserMapper userMapper;
    private final EventMapper eventMapper;
    private final UserService userService;
    private final EventService eventService;

    public AlertEventMapper(UserMapper userMapper, EventMapper eventMapper, UserService userService, EventService eventService) {
        this.userMapper = userMapper;
        this.eventMapper = eventMapper;
        this.userService = userService;
        this.eventService = eventService;
    }

    public AlertEventDTO toDTO(AlertEvent entity) {
        AlertEventDTO dto = new AlertEventDTO();

        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setCreator(userMapper.toDTO(entity.getCreator()));
        dto.setCategory(entity.getCategory());
        dto.setHandled(entity.isHandled());
        dto.setResponseMessage(entity.getResponseMessage());
        dto.setEvent(eventMapper.toDTO(entity.getEvent()));

        return dto;
    }

    public AlertEvent toEntity(AlertEventDTO dto) {
        AlertEvent entity = new AlertEvent();
        entity.setId(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setCreator(userMapper.toEntity(dto.getCreator()));
        entity.setCategory(dto.getCategory());
        entity.setHandled(dto.isHandled());
        entity.setResponseMessage(dto.getResponseMessage());
        entity.setEvent(eventMapper.toEntity(dto.getEvent()));

        return entity;
    }

    public AlertEvent toEntity(AlertEventForm form) {
        AlertEvent entity = new AlertEvent();
        entity.setMessage(form.getMessage());
        entity.setCreator(userMapper.toEntity(userService.findById(form.getCreatorId())));
        entity.setCategory(form.getCategory());
        entity.setEvent(eventMapper.toEntity(eventService.findById(form.getEventId())));

        return entity;
    }
}
