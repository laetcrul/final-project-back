package be.digitalcity.laetitia.finalproject.repositories;

import be.digitalcity.laetitia.finalproject.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
