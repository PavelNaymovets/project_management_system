package com.digdes.pms.service.team.validator;

import com.digdes.pms.dto.team.TeamDto;
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
public class TeamValidatorImpl implements TeamValidator {
    private final MessageSource messageSource;

    @Override
    public void validate(TeamDto teamDto) {
        List<String> errorMessage = new ArrayList<>();

        if (!ObjectUtils.isEmpty(teamDto.getId())) {
            errorMessage.add(messageSource.getMessage("team.field.id.autofill", null, Locale.ENGLISH));
        }

        if (ObjectUtils.isEmpty(teamDto.getProject()) || teamDto.getProject().getId() == null) {
            errorMessage.add(messageSource.getMessage("team.field.project.not.filled", null, Locale.ENGLISH));
        }

        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
