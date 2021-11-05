package be.digitalcity.laetitia.finalproject.models.entities;

import be.digitalcity.laetitia.finalproject.util.enums.ALERT_CATEGORY;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance
@Data
@SQLDelete(sql = "UPDATE alert SET is_active = false WHERE id=?")
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
