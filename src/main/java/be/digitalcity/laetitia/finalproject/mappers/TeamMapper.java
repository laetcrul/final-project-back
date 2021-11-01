package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.TeamDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamMapper {
    private final UserMapper userMapper;
    private final DepartmentMapper departmentMapper;

    public TeamMapper(UserMapper userMapper, DepartmentMapper departmentMapper) {
        this.userMapper = userMapper;
        this.departmentMapper = departmentMapper;
    }

    public TeamDTO toDTO(Team entity){
        return TeamDTO.builder()
                .id(entity.getId())
                .department(departmentMapper.toDTO(entity.getDepartment()))
                .label(entity.getLabel())
                .members(userMapper.toDTOs(entity.getMembers()))
                .build();
    }

    public List<TeamDTO> toDTOs(List<Team> entities){
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
