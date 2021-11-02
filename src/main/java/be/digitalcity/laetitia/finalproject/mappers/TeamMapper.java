package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.TeamDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamMapper {
    private final DepartmentMapper departmentMapper;

    public TeamMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public TeamDTO toDTO(Team entity){
        if (entity == null) {
            return null;
        }

        return TeamDTO.builder()
                .id(entity.getId())
                .department(departmentMapper.toDTO(entity.getDepartment()))
                .label(entity.getLabel())
                .build();
    }

    public List<TeamDTO> toDTOs(List<Team> entities){
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Team toEntity(TeamDTO dto) {
        if (dto == null) {
            return null;
        }

        Team entity = new Team();
        entity.setId(dto.getId());
        entity.setLabel(dto.getLabel());
        entity.setDepartment(departmentMapper.toEntity(dto.getDepartment()));

        return entity;
    }
}
