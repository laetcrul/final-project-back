package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Address;
import be.digitalcity.laetitia.finalproject.models.forms.AddressForm;
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

    public Address toEntity(AddressForm form) {
        Address entity = new Address();
        entity.setStreet(form.getStreet());
        entity.setNumber(form.getNumber());
        entity.setCity(form.getCity());
        entity.setPostCode(form.getPostcode());
        entity.setCountry(form.getCountry());

        return entity;
    }

    public Address toEntity(AddressDTO dto) {
        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());
        entity.setCity(dto.getCity());
        entity.setPostCode(dto.getPostcode());
        entity.setCountry(dto.getCountry());

        return entity;
    }
}
