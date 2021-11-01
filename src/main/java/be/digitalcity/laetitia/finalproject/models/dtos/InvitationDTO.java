package be.digitalcity.laetitia.finalproject.models.dtos;

import be.digitalcity.laetitia.finalproject.util.enums.STATUS;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvitationDTO {
    private Long id;
    private String message;
    private UserDTO recipient;
    private UserDTO sender;
    private EventDTO event;
    private STATUS status;
    private String responseMessage;
}