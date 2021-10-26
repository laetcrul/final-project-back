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

    @Column(length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 250)
    private String image;

    @ManyToOne
    private User creator;

    private Date creationDate;

    @ManyToMany
    private List<User> subscribedUsers;
}
