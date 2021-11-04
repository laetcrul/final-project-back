package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.InvitationMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.InvitationDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Invitation;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationForm;
import be.digitalcity.laetitia.finalproject.models.forms.InvitationResponseForm;
import be.digitalcity.laetitia.finalproject.repositories.InvitationRepository;
import be.digitalcity.laetitia.finalproject.services.InvitationServiceInterface;
import be.digitalcity.laetitia.finalproject.util.enums.STATUS;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationService implements InvitationServiceInterface {
    private final InvitationRepository repository;
    private final InvitationMapper mapper;
    private final UserService userService;

    public InvitationService(InvitationRepository repository, InvitationMapper mapper, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public List<InvitationDTO> findAll() {
        return this.mapper.toDTOs(this.repository.findAll());
    }

    public InvitationDTO findById(Long id) {
        if (id == null) {
            return null;
        }

        return this.mapper.toDTO(this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No invitation for this id")));
    }

    public List<InvitationDTO> findAllByRecipient(Long id) {
        if (id == null) {
            return null;
        }
        UserDTO recipient = this.userService.findById(id);
        return this.findAll().stream()
                .filter(invitation -> invitation.getRecipient().equals(recipient))
                .collect(Collectors.toList());
    }

    public List<InvitationDTO> findAllBySender(Long id) {
        if (id == null) {
            return null;
        }
        UserDTO sender = this.userService.findById(id);
        return this.findAll().stream()
                .filter(invitation -> invitation.getSender().equals(sender))
                .collect(Collectors.toList());
    }

    public void insert(InvitationForm form) {
        if (form == null) {
            return;
        }
        System.out.println(form);
        Invitation toSave = this.mapper.toEntity(form);
        toSave.setStatus(STATUS.PENDING);
        this.repository.save(toSave);
    }

    public void respond(InvitationResponseForm form) {
        if (form == null) {
            return;
        }

        InvitationDTO invitation = this.findById(form.getInvitationId());
        invitation.setMessage(form.getResponseMessage());
        invitation.setStatus(form.getStatus());
    }

    public void delete(Long id) {
        if (id == null) {
            return;
        }
        this.repository.deleteById(id);
    }
}
