package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Data;

@Data
public class RoleDTO {
    private long id;
    private String label;
    public String getAuthority() {
        return this.label;
    } //check if necessary
}