package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.TeamMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.TeamDTO;
import be.digitalcity.laetitia.finalproject.repositories.TeamRepository;
import be.digitalcity.laetitia.finalproject.services.TeamServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class TeamService implements TeamServiceInterface {
    private final TeamRepository repository;
    private final TeamMapper mapper;

    public TeamService(TeamRepository repository, TeamMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TeamDTO findById(Long id) {
        return mapper.toDTO(this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existing team for this id")));
    }
}
