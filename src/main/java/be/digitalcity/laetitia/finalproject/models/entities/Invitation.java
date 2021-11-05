package be.digitalcity.laetitia.finalproject.models.entities;

import be.digitalcity.laetitia.finalproject.util.enums.STATUS;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Invitation extends BaseEntity<Long>{

    @Column(length = 500)
    private String message;

    @ManyToOne
    private User recipient;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Event event;

    private STATUS status;

    @Column(length = 500)
    private String responseMessage;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
