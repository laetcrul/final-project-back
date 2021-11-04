package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.EventMapper;
import be.digitalcity.laetitia.finalproject.mappers.UserMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Event;
import be.digitalcity.laetitia.finalproject.models.forms.AddressForm;
import be.digitalcity.laetitia.finalproject.models.forms.EventForm;
import be.digitalcity.laetitia.finalproject.repositories.EventRepository;
import be.digitalcity.laetitia.finalproject.services.EventServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements EventServiceInterface {
    private final EventRepository repository;
    private final EventMapper mapper;
    private final AddressService addressService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TopicService topicService;

    public EventService(EventRepository repository, EventMapper mapper, AddressService addressService, UserService userService, UserMapper userMapper, TopicService topicService) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressService = addressService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.topicService = topicService;
    }

    public EventDTO findById(Long id) {
        if (id == null) {
            return null;
        }
        Event event = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No event with this id"));

        return mapper.toDTO(event);
    }

    public List<EventDTO> findAll() {
        return this.mapper.toDTOs(this.repository.findAll());
    }

    public List<EventDTO> findBySubscribedUser(Long id) {
        if (id == null) {
            return null;
        }
        UserDTO user = this.userService.findById(id);

        return this.findAll().stream()
                .filter(event -> event.getParticipants().contains(user))
                .collect(Collectors.toList());
    }

    public List<EventDTO> findByCreator(Long id) {
        if (id == null) {
            return null;
        }
        UserDTO creator = this.userService.findById(id);
        return this.findAll().stream()
                .filter(event -> event.getCreator().equals(creator))
                .collect(Collectors.toList());
    }

    public List<EventDTO> findByTopic(Long id) {
        if (id == null) {
            return null;
        }

        TopicDTO topic = this.topicService.findById(id);
        return this.findAll().stream()
                .filter(event -> event.getTopic().equals(topic))
                .collect(Collectors.toList());
    }

    public void insert(EventForm form) {
        if (form == null) {
            return;
        }

        EventForm formWithAddressId = this.addAddressIdToForm(form);

        Event toSave = this.mapper.toEntity(formWithAddressId);
        toSave.setCreationDate(Date.valueOf(LocalDate.now()));
        this.repository.save(toSave);
    }

    public void update(Long id, EventForm form) {
        if (form == null) {
            return;
        }
        EventForm finalForm = this.addAddressIdToForm(form);
        Event toUpdate = this.mapper.toEntity(finalForm);
        EventDTO oldDTO = this.findById(id);
        toUpdate.setCreationDate(oldDTO.getCreationDate());
        toUpdate.setParticipants(this.userMapper.toEntities(oldDTO.getParticipants()));
        toUpdate.setId(id);

        this.repository.save(toUpdate);
    }

    public void delete(Long id) {
        if (id == null) {
            return;
        }
        this.repository.deleteById(id);
    }

    private EventForm addAddressIdToForm(EventForm form) {
        if (form == null) {
            return null;
        }

        if (form.getAddressId() == null) {
            AddressForm newAddress = form.getAddressForm();

            if (newAddress != null) {
                this.addressService.insert(newAddress);
                if (this.addressService.findAddressByFields(newAddress).isPresent()) {
                    Long addressId = this.addressService.findAddressByFields(newAddress).get().getId();
                    form.setAddressId(addressId);
                }
            }
        }
        return form;
    }

    public void register(Long userId, Long eventId) {
        if (userId == null || eventId == null) {
            return;
        }
        EventDTO event = this.findById(eventId);
        UserDTO user = this.userService.findById(userId);

        if (!event.getParticipants().contains(user)) {
            List<UserDTO> updatedList = event.getParticipants();
            updatedList.add(user);
            event.setParticipants(updatedList);
            this.repository.save(this.mapper.toEntity(event));
        }
    }

    public void unregister(Long userId, Long eventId) {
        if (userId == null || eventId == null) {
            return;
        }

        EventDTO event = this.findById(eventId);
        UserDTO user = this.userService.findById(userId);

        if (event.getParticipants().contains(user)) {
            List<UserDTO> updatedList = event.getParticipants();
            updatedList.remove(user);
            event.setParticipants(updatedList);
            this.repository.save(this.mapper.toEntity(event));
        }
    }
}