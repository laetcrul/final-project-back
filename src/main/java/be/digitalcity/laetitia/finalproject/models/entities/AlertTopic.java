package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SQLDelete(sql = "UPDATE alert SET is_active = false WHERE id=?")
@Where(clause = "is_active=true")
public class AlertTopic extends Alert{

    @ManyToOne
    private Topic topic;
}
