package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    private final TeamMapper teamMapper;
    private final RoleMapper roleMapper;
    private final GroupMapper groupMapper;

    public UserMapper(TeamMapper teamMapper, RoleMapper roleMapper, GroupMapper groupMapper) {
        this.teamMapper = teamMapper;
        this.roleMapper = roleMapper;
        this.groupMapper = groupMapper;
    }

    public UserDTO toDTO(User entity){
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .image(entity.getImage())
                .team(teamMapper.toDTO(entity.getTeam()))
                .group(groupMapper.toDTO(entity.getGroup()))
                .roles(roleMapper.toDTOs(entity.getRoles()))
                .isAccountNotExpired(entity.isAccountNonExpired())
                .isNonLocked(entity.isNonLocked())
                .isCredentialsNonExpired(entity.isCredentialsNonExpired())
                .isEnabled(entity.isEnabled())
                .build();
    }

    public List<UserDTO> toDTOs(List<User> entities){
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
