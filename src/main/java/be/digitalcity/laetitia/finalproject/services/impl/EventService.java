package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.EventMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.EventDTO;
import be.digitalcity.laetitia.finalproject.repositories.EventRepository;
import be.digitalcity.laetitia.finalproject.services.EventServiceInterface;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
        if(this.repository.findById(id).isPresent()){
            return mapper.toDTO(this.repository.findById(id).get());
        } else throw new NoSuchElementException("no address found for this id");
    }
}
