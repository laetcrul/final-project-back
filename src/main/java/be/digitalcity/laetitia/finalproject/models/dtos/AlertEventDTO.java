package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlertEventDTO extends AlertDTO{
    private EventDTO event;
}