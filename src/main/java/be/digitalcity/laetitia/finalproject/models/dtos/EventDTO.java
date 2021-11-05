package be.digitalcity.laetitia.finalproject.models.dtos;

import be.digitalcity.laetitia.finalproject.models.entities.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    private TopicDTO topic;
    private AddressDTO address;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private UserDTO creator;
    private List<UserDTO> participants;
    private boolean limitedToTeam;
    private boolean limitedToDepartment;

    public TeamDTO getCreatorTeam(){
        return this.creator.getTeam();
    }

    public DepartmentDTO getCreatorDepartment(){
        return this.getCreatorTeam().getDepartment();
    }
}
