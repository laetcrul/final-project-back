package be.digitalcity.laetitia.finalproject.models.forms;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EventForm {
    private Long topicId;
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    private Long addressId;
    private AddressForm addressForm;    //either Id or new address
    private Long creatorId;
    private boolean limitedToTeam;
    private boolean limitedToDepartment;
}