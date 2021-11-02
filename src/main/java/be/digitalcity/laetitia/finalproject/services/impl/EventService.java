package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.EventMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Event;
import be.digitalcity.laetitia.finalproject.repositories.EventRepository;
import be.digitalcity.laetitia.finalproject.services.EventServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EventServiceInterface {
    private final EventRepository repository;
    private final EventMapper mapper;

    public EventService(EventRepository repository, EventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EventDTO findById(Long id) {
        if (id == null){
            return null;
        }
        Event event = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no event with this id"));

        return mapper.toDTO(event);
    }
}