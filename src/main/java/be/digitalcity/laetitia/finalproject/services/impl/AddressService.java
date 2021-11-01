package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.AddressMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.repositories.AddressRepository;
import be.digitalcity.laetitia.finalproject.services.AddressServiceInterface;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AddressService implements AddressServiceInterface {
    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressService(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AddressDTO findById(Long id) {
        if (id == null){
            return null;
        }
        if(this.repository.findById(id).isPresent()){
            return mapper.toDTO(this.repository.findById(id).get());
        } else throw new NoSuchElementException("no address found for this id");
    }
}
