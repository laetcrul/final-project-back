package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class GroupDTO {
    private Long id;
    private String label;
    private Set<RoleDTO> roles;
}
