package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Address;
import be.digitalcity.laetitia.finalproject.models.forms.AddressForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressMapper {
    public AddressDTO toDTO(Address entity) {

        if (entity == null) {
            return null;
        }

        return AddressDTO.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .postcode(entity.getPostCode())
                .city(entity.getCity())
                .country(entity.getCountry())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public List<AddressDTO> toDTOs(List<Address> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public Address toEntity(AddressForm form) {
        if (form == null) {
            return null;
        }

        Address entity = new Address();
        entity.setStreet(form.getStreet());
        entity.setNumber(form.getNumber());
        entity.setCity(form.getCity());
        entity.setPostCode(form.getPostcode());
        entity.setCountry(form.getCountry());

        return entity;
    }

    public Address toEntity(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());
        entity.setCity(dto.getCity());
        entity.setPostCode(dto.getPostcode());
        entity.setCountry(dto.getCountry());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        return entity;
    }
}
