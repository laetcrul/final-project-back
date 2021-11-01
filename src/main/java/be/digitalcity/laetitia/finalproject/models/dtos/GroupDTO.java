package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupDTO {
    private Long id;
    private String label;
    private List<RoleDTO> roles;
}
