package com.digdes.pms.service.task.validator;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskValidatorImpl implements TaskValidator {
    @Override
    public void validate(TaskDto taskDto) {
        List<String> errorMessage = new ArrayList<>();
        if (ObjectUtils.isEmpty(taskDto.getName()) || taskDto.getName().isBlank()) {
            errorMessage.add("Не заполнено обязательное поле - Наименование задачи");
        }
        if (ObjectUtils.isEmpty(taskDto.getLaborCosts())) {
            errorMessage.add("Не заполнено обязательное поле - Трудозатраты");
        }
        if (ObjectUtils.isEmpty(taskDto.getDeadline())) {
            errorMessage.add("Не заполнено обязательное поле - Крайний срок");
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
