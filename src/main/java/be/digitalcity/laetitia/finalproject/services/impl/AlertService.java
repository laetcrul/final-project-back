package be.digitalcity.laetitia.finalproject.services.impl;

import be.digitalcity.laetitia.finalproject.mappers.AlertEventMapper;
import be.digitalcity.laetitia.finalproject.mappers.AlertTopicMapper;
import be.digitalcity.laetitia.finalproject.mappers.EventMapper;
import be.digitalcity.laetitia.finalproject.mappers.TopicMapper;
import be.digitalcity.laetitia.finalproject.models.dtos.*;
import be.digitalcity.laetitia.finalproject.models.entities.*;
import be.digitalcity.laetitia.finalproject.models.forms.AlertEventForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertForm;
import be.digitalcity.laetitia.finalproject.models.forms.AlertResponseForm;
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
    private final TopicService topicService;
    private final EventService eventService;
    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final EventMapper eventMapper;
    private final TopicMapper topicMapper;

    public AlertService(AlertEventRepository alertEventRepository, AlertTopicRepository AlertTopicRepository, AlertEventMapper alertEventMapper, AlertTopicMapper alertTopicMapper, TopicService topicService, EventService eventService, UserRepository userRepository, AlertRepository alertRepository, EventMapper eventMapper, TopicMapper topicMapper) {
        this.alertEventRepository = alertEventRepository;
        this.alertTopicRepository = AlertTopicRepository;
        this.alertEventMapper = alertEventMapper;
        this.alertTopicMapper = alertTopicMapper;
        this.topicService = topicService;
        this.eventService = eventService;
        this.userRepository = userRepository;
        this.alertRepository = alertRepository;
        this.eventMapper = eventMapper;
        this.topicMapper = topicMapper;
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

        if (topicOptional.isPresent()) {
            return alertTopicMapper.toDTO(topicOptional.get());
        }

        else throw new IllegalArgumentException("No alert for this id");
    }

    public List<AlertTopicDTO> findAllByTopic(Long topicId) {
        TopicDTO topic = this.topicService.findById(topicId);

        return this.alertTopicMapper.toDTOs(this.alertTopicRepository.findAllByTopic(this.topicMapper.toEntity(topic)));
    }

    public List<AlertEventDTO> findAllByEvent(Long eventId) {
        if (eventId == null) {
            return null;
        }
        EventDTO event = this.eventService.findById(eventId);

        return this.alertEventMapper.toDTOs(
                this.alertEventRepository.findAllByEvent(this.eventMapper.toEntity(event)));
    }

    public List<AlertDTO> findAllByCreator(Long creatorId){
        Optional<User> optionalCreator = this.userRepository.findById(creatorId);
        if(optionalCreator.isPresent()){
            return this.findAllAlerts().stream()
                    .filter(AlertDTO::isActive)
                    .filter(alert -> alert.getCreator().getId().equals(creatorId))
                    .collect(Collectors.toList());
        }
        else return null;
    }

    public void insertEventAlert(AlertEventForm form, User creator){
        if (form == null) {
            return;
        }
        AlertEvent toSave = this.alertEventMapper.toEntity(form);
        toSave.setCreator(creator);
        this.alertEventRepository.save(this.alertEventMapper.toEntity(form));
    }

    public void insertTopicAlert(AlertTopicForm form, User creator){
        if (form == null) {
            return;
        }
        AlertTopic toSave = this.alertTopicMapper.toEntity(form);
        toSave.setCreator(creator);
        this.alertTopicRepository.save(alertTopicMapper.toEntity(form));
    }

    public void updateAlert(Long id, AlertForm form) {
        if (form == null) {
            return;
        }

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existing alert for this id"));

        alert.setMessage(form.getMessage());
        alert.setCategory(form.getCategory());

        this.alertRepository.save(alert);
    }

    public void respondToAlert(AlertResponseForm form) {
        Alert alert = alertRepository.findById(form.getAlertId())
                .orElseThrow(() -> new IllegalArgumentException("No alert for this id"));

        alert.setResponseMessage(form.getResponseMessage());
        alert.setHandled(form.isHandled());
        System.out.println(form.isHandled());

        alertRepository.save(alert);
    }

    public void delete(Long id) {
        if (id == null) {
            return;
        }
        this.findById(id);
        alertRepository.deleteById(id);
    }
}
