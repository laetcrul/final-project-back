package be.digitalcity.laetitia.finalproject.models.dtos;

import be.digitalcity.laetitia.finalproject.models.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class TeamDTO {
    private Long id;
    private String label;
    private DepartmentDTO department;
    private List<User> members;
}
