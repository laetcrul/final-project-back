package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.AddressMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.AddressDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Address;
import be.digitalcity.laetitia.finalproject.models.entities.BaseEntity;
import be.digitalcity.laetitia.finalproject.models.forms.AddressForm;
import be.digitalcity.laetitia.finalproject.repositories.AddressRepository;
import be.digitalcity.laetitia.finalproject.services.AddressServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("No active address for this id"));
    }

    public List<AddressDTO> findAll() {
        return mapper.toDTOs(this.repository.findAll());
    }

    public void insert(AddressForm form) {
        this.findAddressByFields(form).ifPresentOrElse(
                (address) -> {
                },
                () -> this.repository.save(this.mapper.toEntity(form))
        );
    }

    public void delete(Long id) {
        if (id == null) {
            return;
        }
        this.repository.deleteById(id);
    }

    public void update(Long id, AddressForm form) {
        Address toUpdate = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No address for this id"));

        this.findAddressByFields(form).ifPresent((address) -> {throw new IllegalArgumentException("This address already exists");});

        toUpdate.setNumber(form.getNumber());
        toUpdate.setStreet(form.getStreet());
        toUpdate.setCity(form.getCity());
        toUpdate.setPostCode(form.getPostcode());
        toUpdate.setCountry(form.getCountry());

        this.repository.save(toUpdate);
    }

    public Optional<AddressDTO> findAddressByFields(AddressForm form) {
        if (form == null) {
            return Optional.empty();
        }

        Address toFind = this.mapper.toEntity(form);
        AddressDTO toFindDTO = this.mapper.toDTO(toFind);

        return this.findAll().stream()
                .filter(address -> address.equals(toFindDTO))
                .findFirst();
    }
}