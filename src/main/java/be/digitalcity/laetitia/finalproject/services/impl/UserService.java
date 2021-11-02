package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.UserMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.DepartmentDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.TeamDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.UserDTO;
import be.digitalcity.laetitia.finalproject.models.entities.Role;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import be.digitalcity.laetitia.finalproject.repositories.RoleRepository;
import be.digitalcity.laetitia.finalproject.repositories.UserRepository;
import be.digitalcity.laetitia.finalproject.services.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final DepartmentService departmentService;
    private final TeamService teamService;
    private final RoleRepository roleRepository;

    public UserService(UserRepository repository, UserMapper mapper, DepartmentService departmentService, TeamService teamService, RoleRepository roleRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.departmentService = departmentService;
        this.teamService = teamService;
        this.roleRepository = roleRepository;
    }

    public List<UserDTO> findAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public UserDTO findById(Long id){
        if (id == null) {
            return null;
        }

        if(this.repository.findById(id).isPresent()){
            return mapper.toDTO(this.repository.findById(id).get());
        } else throw new NoSuchElementException("no user found for this id");
    }

    public List<UserDTO> findALlByDepartment(Long id){
        DepartmentDTO department = departmentService.findById(id);
        return this.findAll().stream()
                .filter(user -> user.getTeam().getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public List<UserDTO> findAllByTeam(Long id) {
        TeamDTO team = teamService.findById(id);

        return this.findAll().stream()
                .filter(user -> user.getTeam().equals(team))
                .collect(Collectors.toList());
    }

    public void disable(Long id) {
        User user = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        user.setEnabled(false);
    }

    public void enable(Long id) {
        User user = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        user.setEnabled(true);
    }

    public void addRole(Long userId, Long roleId){
        User user = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));

        if(user.getRoles().contains(role)){
            return;
        }
        List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);

        repository.save(user);
    }

    public void removeRole(Long userId, Long roleId) {
        User user = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));

        if(!user.getRoles().contains(role)){
            return;
        }

        List<Role> roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);

        repository.save(user);
    }
}