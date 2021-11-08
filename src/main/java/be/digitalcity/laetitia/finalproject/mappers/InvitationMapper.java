package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.InvitationDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Invitation;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationForm;
import be.digitalcity.laetitia.finalproject.services.impl.EventService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if (dto == null) {
            return null;
        }

        Invitation entity = new Invitation();

        entity.setId(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setRecipient(userMapper.toEntity(dto.getRecipient()));
        entity.setSender(userMapper.toEntity(dto.getSender()));
        entity.setEvent(eventMapper.toEntity(dto.getEvent()));
        entity.setStatus(dto.getStatus());
        entity.setResponseMessage(dto.getResponseMessage());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setActive(dto.isActive());

        return entity;
    }

    public InvitationDTO toDTO(Invitation entity) {
        if (entity == null) {
            return null;
        }

        return InvitationDTO.builder()
                .id(entity.getId())
                .message(entity.getMessage())
                .recipient(userMapper.toDTO(entity.getRecipient()))
                .sender(userMapper.toDTO(entity.getSender()))
                .event(eventMapper.toDTO(entity.getEvent()))
                .status(entity.getStatus())
                .responseMessage(entity.getResponseMessage())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .isActive(entity.isActive())
                .build();
    }

    public List<InvitationDTO> toDTOs(List<Invitation> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Invitation toEntity(InvitationForm form) {
        if (form == null) {
            return null;
        }

        Invitation entity = new Invitation();
        entity.setRecipient(userMapper.toEntity(userService.findById(form.getRecipientId())));
        entity.setEvent(eventMapper.toEntity(eventService.findById(form.getEventId())));
        entity.setMessage(form.getMessage());

        return entity;
    }
}
