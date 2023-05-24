package com.digdes.pms.service.validator;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskValidatorImpl implements TaskValidator {
    @Override
    public void validate(TaskDto taskDto) {
        List<String> errorMessage = new ArrayList<>();
        if (taskDto.getName() == null) {
            errorMessage.add("Не заполнено обязательное поле - Наименование задачи");
        }
        if (taskDto.getLaborCosts() == null) {
            errorMessage.add("Не заполнено обязательное поле - Трудозатраты");
        }
        if (taskDto.getDeadline() == null) {
            errorMessage.add("Не заполнено обязательное поле - Крайний срок");
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
