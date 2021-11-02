package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Topic;
import be.digitalcity.laetitia.finalproject.models.forms.TopicForm;
import be.digitalcity.laetitia.finalproject.services.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class TopicMapper {
    private final UserMapper userMapper;
    private final UserService userService;

    public TopicMapper(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    public TopicDTO toDTO(Topic entity) {
        if (entity == null) {
            return null;
        }

        return TopicDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .creator(userMapper.toDTO(entity.getCreator()))
                .creationDate(entity.getCreationDate())
                .subscribedUsers(userMapper.toDTOs(entity.getSubscribedUsers()))
                .build();
    }

    public Topic toEntity(TopicForm form) {
        if (form == null) {
            return null;
        }

        Topic entity = new Topic();
        entity.setName(form.getName());
        entity.setDescription(form.getDescription());
        entity.setImage(form.getImage());
        entity.setCreator(userMapper.toEntity(userService.findById(form.getCreatorId())));

        return entity;
    }

    public Topic toEntity(TopicDTO dto){
        if (dto == null) {
            return null;
        }

        Topic entity = new Topic();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setCreator(userMapper.toEntity(userService.findById(dto.getCreator().getId())));
        entity.setCreationDate(dto.getCreationDate());
        entity.setSubscribedUsers(userMapper.toEntities(dto.getSubscribedUsers()));
        return entity;
    }
}