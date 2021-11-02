package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String street;

    private int number;

    private int postCode;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number && postCode == address.postCode && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(country, address.country);
    }
}
