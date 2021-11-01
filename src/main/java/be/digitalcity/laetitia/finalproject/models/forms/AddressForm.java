package be.digitalcity.laetitia.finalproject.models.forms;

import lombok.Data;

@Data
public class AddressForm {
    private String street;
    private int number;
    private String city;
    private int postcode;
    private String country;
}