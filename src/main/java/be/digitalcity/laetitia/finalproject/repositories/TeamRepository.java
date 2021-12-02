package be.digitalcity.laetitia.finalproject.repositories;

import be.digitalcity.laetitia.finalproject.models.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
