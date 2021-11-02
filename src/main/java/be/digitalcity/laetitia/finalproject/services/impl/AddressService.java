package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.AddressMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Address;
import be.digitalcity.laetitia.finalproject.models.forms.AddressForm;
import be.digitalcity.laetitia.finalproject.repositories.AddressRepository;
import be.digitalcity.laetitia.finalproject.services.AddressServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressServiceInterface {
    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressService(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AddressDTO findById(Long id) {
        if (id == null) {
            return null;
        }
        if (this.repository.findById(id).isPresent()) {
            return mapper.toDTO(this.repository.findById(id).get());
        } else return null;
    }


    public List<AddressDTO> findAll() {
        return mapper.toDTOs(this.repository.findAll());
    }

    public void insert(AddressForm form) {
        Address toSave = this.mapper.toEntity(form);
        this.findAll().stream()
                .map(mapper::toEntity)
                .filter(address -> address.equals(toSave))
                .findFirst()
                .ifPresentOrElse(
                        (address) -> {
                        },
                        () -> this.repository.save(toSave)
                );
    }

    public void delete(Long id) {
        AddressDTO toDelete = this.findById(id);
        if (toDelete != null) {
            this.repository.deleteById(toDelete.getId());
        }
    }

    public void update(Long id, AddressForm form) {
        Address toUpdate = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No address for this id"));

        toUpdate.setNumber(form.getNumber());
        toUpdate.setStreet(form.getStreet());
        toUpdate.setCity(form.getCity());
        toUpdate.setPostCode(form.getPostcode());
        toUpdate.setCountry(form.getCountry());

        this.repository.save(toUpdate);
    }
}