package be.digitalcity.laetitia.finalproject.models.forms;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventForm {
    private Long topicId;
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    private Long addressId;
    private AddressForm address;    //either Id or new address
    private boolean limitedToTeam;
    private boolean limitedToDepartment;
}