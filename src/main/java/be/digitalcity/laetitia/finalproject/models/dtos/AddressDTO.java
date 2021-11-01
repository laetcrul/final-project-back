package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String street;
    private int number;
    private int postcode;
    private String city;
    private String country;
}
