package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.RoleDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleMapper {

    public RoleDTO toDTO(Role entity){
        if (entity == null) {
            return null;
        }

        return RoleDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .build();
    }

    public Set<RoleDTO> toDTOs(Set<Role> entities){
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<RoleDTO> toDTOs(List<Role> entities){
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }

        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setLabel(dto.getLabel());

        return entity;
    }

    public Set<Role> toEntities(Set<RoleDTO> dtos){
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
