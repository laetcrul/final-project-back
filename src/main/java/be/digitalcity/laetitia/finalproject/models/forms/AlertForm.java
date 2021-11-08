package be.digitalcity.laetitia.finalproject.models.forms;

import be.digitalcity.laetitia.finalproject.util.enums.ALERT_CATEGORY;
import lombok.Data;

@Data
public class AlertForm {
    private String message;
    private ALERT_CATEGORY category;
}