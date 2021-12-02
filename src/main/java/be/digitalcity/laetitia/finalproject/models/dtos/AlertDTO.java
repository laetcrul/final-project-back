package be.digitalcity.laetitia.finalproject.models.dtos;

import be.digitalcity.laetitia.finalproject.util.enums.ALERT_CATEGORY;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public abstract class AlertDTO {
    private Long id;
    private String message;
    private UserDTO creator;
    private ALERT_CATEGORY category;
    private boolean isHandled;
    private String responseMessage;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean isActive;
}