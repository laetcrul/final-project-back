package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.RoleDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMapper {

    public RoleDTO toDTO(Role entity){
        return RoleDTO.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .build();
    }

    public List<RoleDTO> toDTOs(List<Role> entities){
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Role toEntity(RoleDTO dto) {
        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setLabel(dto.getLabel());

        return entity;
    }

    public List<Role> toEntities(List<RoleDTO> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}