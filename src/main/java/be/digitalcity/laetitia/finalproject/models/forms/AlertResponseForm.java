package be.digitalcity.laetitia.finalproject.models.forms;

import lombok.Data;

@Data
public class AlertResponseForm {
    private Long alertId;
    private String responseMessage;
    private boolean handled;
}