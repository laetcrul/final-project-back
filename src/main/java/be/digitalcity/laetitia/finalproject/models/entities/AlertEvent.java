package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class AlertEvent extends Alert{
    @ManyToOne
    private Event event;
}