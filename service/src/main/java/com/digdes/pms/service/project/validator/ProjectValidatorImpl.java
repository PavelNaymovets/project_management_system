package com.digdes.pms.service.project.validator;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ProjectValidatorImpl implements ProjectValidator {
    private final MessageSource messageSource;

    @Override
    public void validate(ProjectDto projectDto) {
        List<String> errorMessage = new ArrayList<>();

        if (!ObjectUtils.isEmpty(projectDto.getId())) {
            errorMessage.add(messageSource.getMessage("project.field.id.autofill", null, Locale.ENGLISH));
        }
        if (!ObjectUtils.isEmpty(projectDto.getStatus())) {
            errorMessage.add(messageSource.getMessage("project.field.status.autofill", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(projectDto.getName()) || projectDto.getName().isBlank()) {
            errorMessage.add(messageSource.getMessage("project.field.name.not.filled", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(projectDto.getCode())) {
            errorMessage.add(messageSource.getMessage("project.field.code.not.filled", null, Locale.ENGLISH));
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
