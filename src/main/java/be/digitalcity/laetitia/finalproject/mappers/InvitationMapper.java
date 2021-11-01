package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.InvitationDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Invitation;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationForm;
import be.digitalcity.laetitia.finalproject.services.impl.EventService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class InvitationMapper {
    private final EventMapper eventMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final EventService eventService;

    public InvitationMapper(EventMapper eventMapper, UserMapper userMapper, UserService userService, EventService eventService) {
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.eventService = eventService;
    }

    public Invitation toEntity(InvitationDTO dto) {
        Invitation entity = new Invitation();

        entity.setId(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setRecipient(userMapper.toEntity(dto.getRecipient()));
        entity.setSender(userMapper.toEntity(dto.getSender()));
        entity.setEvent(eventMapper.toEntity(dto.getEvent()));
        entity.setStatus(dto.getStatus());
        entity.setResponseMessage(dto.getResponseMessage());

        return entity;
    }

    public InvitationDTO toDTO(Invitation entity) {
        return InvitationDTO.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .recipient(userMapper.toDTO(entity.getRecipient()))
                .sender(userMapper.toDTO(entity.getSender()))
                .event(eventMapper.toDTO(entity.getEvent()))
                .status(entity.getStatus())
                .responseMessage(entity.getResponseMessage())
                .build();
    }

    public InvitationDTO toDTO(InvitationForm form) {
        return InvitationDTO.builder()
                .message(form.getMessage())
                .recipient(userService.findById(form.getRecipientId()))
                .sender(userService.findById(form.getSenderId()))
                .event(eventService.findById(form.getEventId()))
                .build();
    }
}
