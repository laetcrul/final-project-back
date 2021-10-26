package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String image;

    @ManyToOne
    private User creator;

    private Date creationDate;

    @ManyToMany
    private List<User> subscribedUsers;
}
