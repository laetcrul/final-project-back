package be.digitalcity.laetitia.finalproject.models.dtos;

import be.digitalcity.laetitia.finalproject.models.entities.Team;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {
    private Long id;
    private String label;
    private List<Team> teams;
}
