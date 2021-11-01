package be.digitalcity.laetitia.finalproject.models.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlertEventForm extends AlertForm {
    private Long eventId;
}