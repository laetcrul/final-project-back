package be.digitalcity.laetitia.finalproject.repositories;

import be.digitalcity.laetitia.finalproject.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByTeamId(Long teamId);

    Optional<User> findByUsername(String username);
}