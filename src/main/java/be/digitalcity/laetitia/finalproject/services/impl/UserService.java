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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return this.mapper.toDTOs(this.repository.findAll());
    }

    public UserDTO findById(Long id){
        if (id == null) {
            return null;
        }

        if(this.repository.findById(id).isPresent()){
            return this.mapper.toDTO(this.repository.findById(id).get());
        } else throw new IllegalArgumentException("No user found for this id");
    }

    public List<UserDTO> findALlByDepartment(Long id){
        if (id == null) {
            return null;
        }
        DepartmentDTO department = this.departmentService.findById(id);
        return this.findAll().stream()
                .filter(user -> user.getTeam().getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public List<UserDTO> findAllByTeam(Long id) {
        if (id == null) {
            return null;
        }
        TeamDTO team = this.teamService.findById(id);

        return this.findAll().stream()
                .filter(user -> user.getTeam().equals(team))
                .collect(Collectors.toList());
    }

    public void disable(Long id) {
        if (id == null) {
            return;
        }
        User user = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        user.setEnabled(false);

        this.repository.save(user);
    }

    public void enable(Long id) {
        if (id == null) {
            return;
        }
        User user = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        user.setEnabled(true);

        this.repository.save(user);
    }

    public void addRole(Long userId, Long roleId){
        if (userId == null || roleId == null) {
            return;
        }
        User user = this.repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));

        if(user.getRoles().contains(role) || user.getGroup().getRoles().contains(role)){
            return;
        }
        List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);

        this.repository.save(user);
    }

    public void removeRole(Long userId, Long roleId) {
        if (userId == null || roleId == null) {
            return;
        }
        User user = this.repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));
        Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("No user for this ID"));

        if(!user.getRoles().contains(role)){
            return;
        }

        List<Role> roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);

        this.repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null) {
            return null;
        }
        return this.repository.findByUsername(s).orElseThrow(() -> new IllegalArgumentException("no user for this username"));
    }
}