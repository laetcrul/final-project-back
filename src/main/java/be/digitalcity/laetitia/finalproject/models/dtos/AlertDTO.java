package be.digitalcity.laetitia.finalproject.models.dtos;

import be.digitalcity.laetitia.finalproject.util.enums.ALERT_CATEGORY;
import lombok.Data;

@Data
public class AlertDTO {
    private Long id;
    private String message;
    private UserDTO creator;
    private ALERT_CATEGORY category;
    private boolean isHandled;
    private String responseMessage;
}