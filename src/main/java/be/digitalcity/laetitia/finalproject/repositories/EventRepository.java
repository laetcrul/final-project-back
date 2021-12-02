package be.digitalcity.laetitia.finalproject.repositories;

import be.digitalcity.laetitia.finalproject.models.entities.Event;
import be.digitalcity.laetitia.finalproject.models.entities.Topic;
import be.digitalcity.laetitia.finalproject.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByAddress_City(String city);
    List<Event> findAllByTopic(Topic topic);
    List<Event> findAllByCreator(User creator);
    List<Event> findAllByDate(Date date);
}
