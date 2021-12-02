package be.digitalcity.laetitia.finalproject.repositories;

import be.digitalcity.laetitia.finalproject.models.entities.AlertTopic;
import be.digitalcity.laetitia.finalproject.models.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertTopicRepository extends JpaRepository<AlertTopic, Long> {
    List<AlertTopic> findAllByTopic(Topic topic);
}
