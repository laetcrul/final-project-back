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

/**
 * Service to get, add, edit, delete addresses. Always returns them as DTOs
 */
@Service
public class AddressService implements AddressServiceInterface {
    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressService(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
    * returns the address with the given ID
    * @param id the id of the address to find
    * @return AddressDTO
    * @throws IllegalArgumentException if no address has been found
    */
    public AddressDTO findById(Long id) {
        if (id == null) {
            return null;
        }

        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("No active address for this id"));
    }

    /**
     * returns a list of all active addresses
     * @return a list of AddressDTO
     */
    public List<AddressDTO> findAll() {
        return mapper.toDTOs(this.repository.findAll());
    }

    /**
     * creates a new address if not yet existing
     * @param form addressForm
     */
    public void insert(AddressForm form) {
        this.findAddressByFields(form).ifPresentOrElse(
                (address) -> {
                },
                () -> this.repository.save(this.mapper.toEntity(form))
        );
    }

    /**
     * deactivates the address with the given ID if existing
     * @param id
     */
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        this.repository.deleteById(id);
    }

    /**
     * updates the address corresponding to given id with fields of given form
     * @param id
     * @param form address form
     * @throws IllegalArgumentException if no address was found for given id
     */
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

    /**
     * returns the address matching the form fields if already existing
     * @param form a form containing all Address fields except id
     * @return optional addressDTO
     **/
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