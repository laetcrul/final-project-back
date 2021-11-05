package be.digitalcity.laetitia.finalproject.models.entities;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@SQLDelete(sql = "UPDATE address SET is_active = false WHERE id=?")
@Where(clause = "is_active=true")
public class Address extends BaseEntity<Long>{

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), street, number, postCode, city, country);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
