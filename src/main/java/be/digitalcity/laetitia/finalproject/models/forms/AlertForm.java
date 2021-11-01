package be.digitalcity.laetitia.finalproject.models.forms;

import be.digitalcity.laetitia.finalproject.util.enums.ALERT_CATEGORY;
import lombok.Data;

@Data
public abstract class AlertForm {
    private String message;
    private Long creatorId;
    private ALERT_CATEGORY category;
}