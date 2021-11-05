package be.digitalcity.laetitia.finalproject.models.entities;

import be.digitalcity.laetitia.finalproject.util.enums.ALERT_CATEGORY;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance
@Data
public class Alert extends BaseEntity<Long>{

    @Column(length = 500)
    private String message;

    @ManyToOne
    private User creator;

    private ALERT_CATEGORY category;

    private boolean isHandled;

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
