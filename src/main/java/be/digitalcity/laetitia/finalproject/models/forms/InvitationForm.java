package be.digitalcity.laetitia.finalproject.models.forms;

import lombok.Data;

@Data
public class InvitationForm {
    private String message;
    private Long recipientId;
    private Long eventId;
}