package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.RoleDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    }
}