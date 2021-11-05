package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Event extends BaseEntity<Long>{

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 250)
    private String image;

    private LocalDate date;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Address address;

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

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
