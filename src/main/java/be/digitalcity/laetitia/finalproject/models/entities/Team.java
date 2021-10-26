package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Department.class)
    private Department department;
}
