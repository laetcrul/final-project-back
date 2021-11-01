package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.DepartmentDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper {
    private final TeamMapper teamMapper;

    public DepartmentMapper(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    public DepartmentDTO toDTO(Department entity){
        return DepartmentDTO.builder()
                .id((entity.getId()))
                .label(entity.getLabel())
                .teams(teamMapper.toDTOs(entity.getTeams()))
                .build();
    }
}