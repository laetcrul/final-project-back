package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String image;
    private TeamDTO team;
    private GroupDTO group;
    private List<RoleDTO> roles;
    private boolean isAccountNotExpired;
    private boolean isNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private String token;
}
