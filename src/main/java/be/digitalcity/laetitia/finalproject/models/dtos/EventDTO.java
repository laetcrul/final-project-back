package be.digitalcity.laetitia.finalproject.models.dtos;

import be.digitalcity.laetitia.finalproject.models.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Date date;
    private TopicDTO topic;
    private AddressDTO address;
    private Date creationDate;
    private UserDTO creator;
    private List<User> participants;
    private boolean limitedToTeam;
    private boolean limitedToDepartment;

    public TeamDTO getCreatorTeam(){
        return this.creator.getTeam();
    }

    public DepartmentDTO getCreatorDepartment(){
        return this.getCreatorTeam().getDepartment();
    }
}
