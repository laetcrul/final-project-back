package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class AddressDTO {
    private Long id;
    private String street;
    private int number;
    private int postcode;
    private String city;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO)) return false;
        AddressDTO that = (AddressDTO) o;
        return number == that.number && postcode == that.postcode && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(country, that.country);
    }
}
