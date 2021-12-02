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
        if (entity == null) {
            return null;
        }

        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .image(entity.getImage())
                .team(teamMapper.toDTO(entity.getTeam()))
                .roles(roleMapper.toDTOs(entity.getRoles()))
                .group(groupMapper.toDTO(entity.getGroup()))
                .isAccountNotExpired(entity.isAccountNonExpired())
                .isNonLocked(entity.isNonLocked())
                .isCredentialsNonExpired(entity.isCredentialsNonExpired())
                .isEnabled(entity.isEnabled())
                .build();
    }

    public List<UserDTO> toDTOs(List<User> entities){
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setImage(dto.getImage());
        entity.setTeam(teamMapper.toEntity(dto.getTeam()));
        entity.setAccountNonExpired(dto.isAccountNotExpired());
        entity.setNonLocked(dto.isNonLocked());
        entity.setCredentialsNonExpired(dto.isCredentialsNonExpired());
        entity.setEnabled(dto.isEnabled());

        return entity;
    }

    public List<User> toEntities(List<UserDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
