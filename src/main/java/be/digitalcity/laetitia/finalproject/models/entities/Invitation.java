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

    @Column(length = 500)
    private String message;

    @ManyToOne
    private User recipient;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Event event;

    private STATUS status;

    @Column(length = 500)
    private String responseMessage;
}
