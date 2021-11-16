package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SQLDelete(sql = "UPDATE topic SET is_active = false WHERE id=?")
@Where(clause = "is_active=true")
public class Topic extends BaseEntity<Long>{

    @Column(length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 250)
    private String image;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<User> subscribedUsers = new ArrayList();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
