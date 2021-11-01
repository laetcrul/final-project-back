package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private Long id;
    private String street;
    private int number;
    private int postcode;
    private String city;
    private String country;
}
