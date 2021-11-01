package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 250)
    private String image;

    private Date date;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Address address;

    private Date creationDate;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<User> participants;

    private boolean limitedToTeam;

    private boolean limitedToDepartment;

    public Team getCreatorTeam(){
        return this.creator.getTeam();
    }

    public Department getCreatorDepartment(){
        return this.getCreatorTeam().getDepartment();
    }
}
