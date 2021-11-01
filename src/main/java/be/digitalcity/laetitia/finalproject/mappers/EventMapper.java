package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Event;
import be.digitalcity.laetitia.finalproject.models.forms.EventForm;
import be.digitalcity.laetitia.finalproject.services.impl.AddressService;
import be.digitalcity.laetitia.finalproject.services.impl.TopicService;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class EventMapper {
    private final TopicMapper topicMapper;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final AddressService addressService;
    private final TopicService topicService;

    public EventMapper(TopicMapper topicMapper, AddressMapper addressMapper, UserMapper userMapper, UserService userService, AddressService addressService, TopicService topicService) {
        this.topicMapper = topicMapper;
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.addressService = addressService;
        this.topicService = topicService;
    }

    public EventDTO toDTO(Event entity) {
        return EventDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .date(entity.getDate())
                .topic(topicMapper.toDTO(entity.getTopic()))
                .address(addressMapper.toDTO(entity.getAddress()))
                .creationDate(entity.getCreationDate())
                .creator(userMapper.toDTO(entity.getCreator()))
                .participants(userMapper.toDTOs(entity.getParticipants()))
                .limitedToTeam(entity.isLimitedToTeam())
                .limitedToDepartment(entity.isLimitedToDepartment())
                .build();
    }

    public Event toEntity(EventForm form) {
        Event entity = new Event();
        entity.setTopic(topicMapper.toEntity(topicService.findById(form.getTopicId())));
        entity.setName(form.getName());
        entity.setDescription(form.getDescription());
        entity.setImage(form.getImage());
        entity.setDate(form.getDate());
        entity.setAddress(addressMapper.toEntity(addressService.findById(form.getAddressId())));
        entity.setCreator(userMapper.toEntity(userService.findById(form.getCreatorId())));
        entity.setLimitedToTeam(form.isLimitedToTeam());
        entity.setLimitedToDepartment(form.isLimitedToDepartment());
        return entity;
    }

    public Event toEntity(EventDTO dto) {
        Event entity = new Event();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setDate(dto.getDate());
        entity.setTopic(topicMapper.toEntity(dto.getTopic()));
        entity.setAddress(addressMapper.toEntity(dto.getAddress()));
        entity.setCreator(userMapper.toEntity(dto.getCreator()));
        entity.setParticipants(userMapper.toEntities(dto.getParticipants()));
        entity.setLimitedToTeam(dto.isLimitedToTeam());
        entity.setLimitedToDepartment(dto.isLimitedToDepartment());

        return entity;
    }
}
