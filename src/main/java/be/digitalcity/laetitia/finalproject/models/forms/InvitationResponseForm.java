package be.digitalcity.laetitia.finalproject.models.forms;

import be.digitalcity.laetitia.finalproject.util.enums.STATUS;
import lombok.Data;

@Data
public class InvitationResponseForm {
    private Long invitationId;
    private STATUS status;
    private String responseMessage;
}
