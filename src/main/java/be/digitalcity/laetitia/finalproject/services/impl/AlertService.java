package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.AlertEventMapper;
import be.digitalcity.laetitia.finalproject.mappers.AlertTopicMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.AlertDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.AlertEventDTO;
import be.digitalcity.laetitia.finalproject.models.dtos.AlertTopicDTO;
import be.digitalcity.laetitia.finalproject.models.entities.*;
import be.digitalcity.laetitia.finalproject.models.forms.AlertEventForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertTopicForm;
import be.digitalcity.laetitia.finalproject.repositories.*;
import be.digitalcity.laetitia.finalproject.services.AlertServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertService implements AlertServiceInterface {
    private final AlertEventRepository alertEventRepository;
    private final AlertTopicRepository alertTopicRepository;
    private final AlertEventMapper alertEventMapper;
    private final AlertTopicMapper alertTopicMapper;
    private final TopicRepository topicRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public AlertService(AlertEventRepository alertEventRepository, AlertTopicRepository AlertTopicRepository, AlertEventMapper alertEventMapper, AlertTopicMapper alertTopicMapper, TopicRepository topicRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.alertEventRepository = alertEventRepository;
        this.alertTopicRepository = AlertTopicRepository;
        this.alertEventMapper = alertEventMapper;
        this.alertTopicMapper = alertTopicMapper;
        this.topicRepository = topicRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<AlertDTO> findAllAlerts(){
        List<AlertDTO> alerts = new ArrayList<>();
        alerts.addAll(alertTopicMapper.toDTOs(this.alertTopicRepository.findAll()));
        alerts.addAll(alertEventMapper.toDTOs(this.alertEventRepository.findAll()));

        return alerts;
    }

    public AlertDTO findById(Long id){
        if (id == null) {
            return null;
        }
        Optional<AlertEvent> eventOptional = alertEventRepository.findById(id);
        Optional<AlertTopic> topicOptional = alertTopicRepository.findById(id);

        if (eventOptional.isPresent()) {
            return alertEventMapper.toDTO(eventOptional.get());
        }

        return topicOptional.map(alertTopicMapper::toDTO).orElse(null);
    }

    public List<AlertTopicDTO> findAllByTopic(Long topicId) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        return optionalTopic
                .map(topic -> alertTopicMapper
                        .toDTOs(alertTopicRepository.findAllByTopic(topic)))
                .orElse(null);
    }

    public List<AlertEventDTO> findAllByEvent(Long eventId) {
        Optional<Event> optionalEvent = this.eventRepository.findById(eventId);
        return optionalEvent
                .map(event -> alertEventMapper
                        .toDTOs(alertEventRepository.findAllByEvent(event)))
                .orElse(null);
    }

    public List<AlertDTO> findAllByCreator(Long creatorId){
        Optional<User> optionalCreator = this.userRepository.findById(creatorId);
        if(optionalCreator.isPresent()){
            return this.findAllAlerts().stream()
                    .filter(alert -> alert.getCreator().getId() == creatorId)
                    .collect(Collectors.toList());
        }
        else return null;
    }

    public void insertEventAlert(AlertEventForm form){
        if (form == null) {
            return;
        }
        this.alertEventRepository.save(alertEventMapper.toEntity(form));
    }

    public void insertTopicAlert(AlertTopicForm form){

    }
}
