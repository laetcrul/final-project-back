package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    public AddressDTO toDTO(Address entity) {
        return AddressDTO.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .postcode(entity.getPostCode())
                .city(entity.getCity())
                .country(entity.getCountry())
                .build();
    }
}
