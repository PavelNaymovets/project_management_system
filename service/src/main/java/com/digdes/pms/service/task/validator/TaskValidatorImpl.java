package com.digdes.pms.service.task.validator;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class TaskValidatorImpl implements TaskValidator {
    private final MessageSource messageSource;

    @Override
    public void validate(TaskDto taskDto) {
        List<String> errorMessage = new ArrayList<>();

        if (!ObjectUtils.isEmpty(taskDto.getId())) {
            errorMessage.add(messageSource.getMessage("task.field.id.autofill", null, Locale.ENGLISH));
        }
        if (!ObjectUtils.isEmpty(taskDto.getStatus())) {
            errorMessage.add(messageSource.getMessage("task.field.status.autofill", null, Locale.ENGLISH));
        }
        if (!ObjectUtils.isEmpty(taskDto.getAuthor())) {
            errorMessage.add(messageSource.getMessage("task.field.author.autofill", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(taskDto.getName()) || taskDto.getName().isBlank()) {
            errorMessage.add(messageSource.getMessage("task.field.name.not.filled", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(taskDto.getLaborCosts())) {
            errorMessage.add(messageSource.getMessage("task.field.laborCost.not.filled", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(taskDto.getDeadline())) {
            errorMessage.add(messageSource.getMessage("task.field.deadline.not.filled", null, Locale.ENGLISH));
        }
        if (!ObjectUtils.isEmpty(taskDto.getDeadline())) {
            long days = taskDto.getLaborCosts()/24;
            LocalDate minDeadLine = LocalDate.now().plusDays(days);

            if(taskDto.getDeadline().isBefore(minDeadLine)) {
                errorMessage.add(messageSource.getMessage("task.field.deadline.lessThan.laborCost", null, Locale.ENGLISH));
            }
        }
        if (ObjectUtils.isEmpty(taskDto.getProject())) {
            errorMessage.add(messageSource.getMessage("task.field.project.not.filled", null, Locale.ENGLISH));
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
