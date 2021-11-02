package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.GroupDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Group;
import org.springframework.stereotype.Service;

@Service
public class GroupMapper {
    private final RoleMapper roleMapper;

    public GroupMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public GroupDTO toDTO(Group entity) {
        if (entity == null) {
            return null;
        }

        return GroupDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .roles(roleMapper.toDTOs(entity.getRoles()))
                .build();
    }

    public Group toEntity(GroupDTO dto){
        if (dto == null) {
            return null;
        }

        Group entity = new Group();
        entity.setId(dto.getId());
        entity.setLabel(dto.getLabel());
        entity.setRoles(roleMapper.toEntities(dto.getRoles()));

        return entity;
    }
}