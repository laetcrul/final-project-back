package be.digitalcity.laetitia.finalproject.mappers;

import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Address;
import be.digitalcity.laetitia.finalproject.models.entities.Event;
import be.digitalcity.laetitia.finalproject.models.entities.Topic;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventMapperTest {

    @Mock
    private TopicMapper topicMapper;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    EventMapper eventMapper;

    @Test
    void toDTO() {
        User userEntity = new User();
        Address addressEntity = new Address();
        Topic topicEntity = new Topic();
        Date date = new Date();

        Event entity = new Event();
        entity.setId(42L);
        entity.setName("eventName");
        entity.setDescription("eventDescription");
        entity.setImage("image");
        entity.setDate(date);
        entity.setTopic(topicEntity);
        entity.setAddress(addressEntity);
        entity.setCreationDate(date);
        entity.setCreator(userEntity);
        entity.setParticipants(List.of(userEntity));
        entity.setLimitedToTeam(true);
        entity.setLimitedToDepartment(false);

        TopicDTO topicDTO = TopicDTO.builder().build();
        AddressDTO addressDTO = AddressDTO.builder().build();
        UserDTO userDTO = UserDTO.builder().build();

        when(this.topicMapper.toDTO(topicEntity)).thenReturn(topicDTO);
        when(this.addressMapper.toDTO(addressEntity)).thenReturn(addressDTO);
        when(this.userMapper.toDTO(userEntity)).thenReturn(userDTO);
        when(this.userMapper.toDTOs(List.of(userEntity))).thenReturn(List.of(userDTO));

        EventDTO eventDTO = this.eventMapper.toDTO(entity);

        assertEquals(42L, eventDTO.getId());
        assertEquals("eventName", eventDTO.getName());
        assertEquals("eventDescription", eventDTO.getDescription());
        assertEquals("image", eventDTO.getImage());
        assertEquals(date, eventDTO.getDate());
        assertEquals(topicDTO, eventDTO.getTopic());
        assertEquals(addressDTO, eventDTO.getAddress());
        assertEquals(date, eventDTO.getCreationDate());
        assertEquals(userDTO, eventDTO.getCreator());
        assertEquals(1, eventDTO.getParticipants().size());
        assertTrue(eventDTO.isLimitedToTeam());
        assertFalse(eventDTO.isLimitedToDepartment());
    }

    @Test
    void toEntity() {
    }

    @Test
    void testToEntity() {
    }
}