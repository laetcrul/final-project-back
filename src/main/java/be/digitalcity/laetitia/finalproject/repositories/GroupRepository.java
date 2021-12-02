package be.digitalcity.laetitia.finalproject.repositories;

import be.digitalcity.laetitia.finalproject.models.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
