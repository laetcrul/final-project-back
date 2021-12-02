package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.RoleMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.RoleDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Group;
import be.digitalcity.laetitia.finalproject.models.entities.Role;
import be.digitalcity.laetitia.finalproject.repositories.GroupRepository;
import be.digitalcity.laetitia.finalproject.repositories.RoleRepository;
import be.digitalcity.laetitia.finalproject.services.RoleServiceInterface;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService implements RoleServiceInterface {
    private final RoleRepository repository;
    private final RoleMapper mapper;
    private final UserService userService;
    private final GroupRepository groupRepository;

    public RoleService(RoleRepository repository, RoleMapper mapper, UserService userService, GroupRepository groupRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.groupRepository = groupRepository;
    }

    public Set<RoleDTO> findAll() {
        return this.mapper.toDTOs(this.repository.findAll());
    }


    public Set<RoleDTO> findAllAdmin() {
        Group user = this.groupRepository.findById(1L).orElseThrow();
        Group admin = this.groupRepository.findById(2L).orElseThrow();

        Set<Role> roles = admin.getRoles();
        roles.removeAll(user.getRoles());
        return mapper.toDTOs(roles);
    }


}
