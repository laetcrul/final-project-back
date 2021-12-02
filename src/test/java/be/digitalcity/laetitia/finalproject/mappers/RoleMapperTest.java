package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.RoleDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

class RoleMapperTest {
    private final RoleMapper roleMapper = new RoleMapper();

    @Test
    void toDTO() {
        Role entity = new Role();
        entity.setId(2L);
        entity.setLabel("test");
        RoleDTO dto = roleMapper.toDTO(entity);

        Assertions.assertEquals("test", dto.getLabel());
        Assertions.assertEquals(2L, dto.getId());
    }

    @Test
    void toDTOs() {
        Role entity1 = new Role();
        entity1.setId(4L);
        entity1.setLabel("test1");
        Role entity2 = new Role();
        entity2.setId(3L);
        entity2.setLabel("test2");
        List<Role> entities = List.of(
             entity1, entity2
        );

        Set<RoleDTO> dtos = roleMapper.toDTOs(entities);

        Optional<RoleDTO> optional1 = dtos.stream().filter(dto -> dto.getId().equals(4L)).findFirst();
        Optional<RoleDTO> optional2 = dtos.stream().filter(dto -> dto.getId().equals(3L)).findFirst();

        Assertions.assertTrue(optional1.isPresent());
        Assertions.assertTrue(optional2.isPresent());

        RoleDTO dto1 = optional1.get();
        RoleDTO dto2 = optional2.get();

        Assertions.assertEquals(dto1.getLabel(), entity1.getLabel());
        Assertions.assertEquals(dto2.getLabel(), entity2.getLabel());

        Assertions.assertEquals(dto1.getAuthority(), entity1.getAuthority());
        Assertions.assertEquals(dto2.getAuthority(), entity2.getAuthority());
    }
}