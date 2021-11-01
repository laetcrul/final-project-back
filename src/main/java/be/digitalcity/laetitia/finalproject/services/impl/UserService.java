package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.UserMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.repositories.UserRepository;
import be.digitalcity.laetitia.finalproject.services.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDTO findById(Long id){
        if (id == null) {
            return null;
        }

        if(this.repository.findById(id).isPresent()){
            return mapper.toDTO(this.repository.findById(id).get());
        } else throw new NoSuchElementException("no user found for this id");
    }
}
