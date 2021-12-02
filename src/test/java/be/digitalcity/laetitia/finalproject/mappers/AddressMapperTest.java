package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {
    private final AddressMapper addressMapper = new AddressMapper();

    @Test
    void toDTO() {
        Address entity = new Address();
        entity.setId(4L);
        entity.setStreet("Rue Sans Souci");
        entity.setNumber(15);
        entity.setPostCode(1050);
        entity.setCity("Brussels");
        entity.setCountry("Belgium");

        AddressDTO dto = addressMapper.toDTO(entity);

        Assertions.assertEquals(4L, dto.getId());
        Assertions.assertEquals("Rue Sans Souci", dto.getStreet());
        Assertions.assertEquals(15, dto.getNumber());
        Assertions.assertEquals(1050, dto.getPostcode());
        Assertions.assertEquals("Brussels", dto.getCity());
        Assertions.assertEquals("Belgium", dto.getCountry());
    }
}