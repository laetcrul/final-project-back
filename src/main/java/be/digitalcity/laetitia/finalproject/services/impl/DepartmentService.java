package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.DepartmentMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.DepartmentDTO;
import be.digitalcity.laetitia.finalproject.repositories.DepartmentRepository;
import be.digitalcity.laetitia.finalproject.services.DepartmentServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public DepartmentService(DepartmentRepository repository, DepartmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DepartmentDTO> findAll() {
        return mapper.toDTOs(this.repository.findAll());
    }

    public DepartmentDTO findById(Long id) {
        return mapper.toDTO(this.repository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("No department with this id")));
    }
}