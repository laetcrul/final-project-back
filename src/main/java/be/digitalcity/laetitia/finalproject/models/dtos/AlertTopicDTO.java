package be.digitalcity.laetitia.finalproject.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlertTopicDTO extends AlertDTO{
    private TopicDTO topic;
}
