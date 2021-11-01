package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.TopicMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.TopicDTO;
import be.digitalcity.laetitia.finalproject.repositories.TopicRepository;
import be.digitalcity.laetitia.finalproject.services.TopicServiceInterface;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TopicService implements TopicServiceInterface {
    private final TopicRepository repository;
    private final TopicMapper mapper;

    public TopicService(TopicRepository repository, TopicMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TopicDTO findById(Long id) {
        if (id == null){
            return null;
        }
        if(this.repository.findById(id).isPresent()){
            return mapper.toDTO(this.repository.findById(id).get());
        } else throw new NoSuchElementException("no topic found for this id");
    }
}
