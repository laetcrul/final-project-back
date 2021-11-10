package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String image;
    private TeamDTO team;
    private List<String> roles;
    private boolean isAccountNotExpired;
    private boolean isNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private String token;
}
