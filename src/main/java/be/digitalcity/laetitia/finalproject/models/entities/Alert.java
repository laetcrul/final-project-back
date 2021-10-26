package be.digitalcity.laetitia.finalproject.models.entities;

import be.digitalcity.laetitia.finalproject.util.enums.ALERT_CATEGORY;

import javax.persistence.*;

@Entity
@Inheritance
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String message;

    private ALERT_CATEGORY category;

    private boolean isHandled;

    @Column(length = 500)
    private String responseMessage;
}
