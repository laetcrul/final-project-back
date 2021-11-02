package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.DepartmentDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper {

    public DepartmentDTO toDTO(Department entity){
        if (entity == null) {
            return null;
        }

        return DepartmentDTO.builder()
                .id((entity.getId()))
                .label(entity.getLabel())
                .build();
    }

    public Department toEntity(DepartmentDTO dto){
        if (dto == null) {
            return null;
        }

        Department entity = new Department();
        entity.setId(dto.getId());
        entity.setLabel(dto.getLabel());

        return entity;
    }
}