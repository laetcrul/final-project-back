package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "security_group")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @ManyToMany(targetEntity = Role.class)
    private List<Role> roles;
}