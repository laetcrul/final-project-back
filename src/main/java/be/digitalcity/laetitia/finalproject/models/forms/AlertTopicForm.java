package be.digitalcity.laetitia.finalproject.models.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlertTopicForm extends AlertForm{
    private Long topicId;
}