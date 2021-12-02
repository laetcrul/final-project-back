package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private Long id;
    private String label;
    public String getAuthority() {
        return this.label;
    } //check if necessary
}