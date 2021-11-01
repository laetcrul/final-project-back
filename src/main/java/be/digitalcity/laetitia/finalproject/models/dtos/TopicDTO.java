package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class TopicDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private UserDTO creator;
    private Date creationDate;
    private List<UserDTO> subscribedUsers;
}
