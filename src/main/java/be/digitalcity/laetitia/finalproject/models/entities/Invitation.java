package be.digitalcity.laetitia.finalproject.models.entities;

import be.digitalcity.laetitia.finalproject.util.enums.STATUS;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private User recipient;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Event event;

    private STATUS status;

    private String responseMessage;
}
