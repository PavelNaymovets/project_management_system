package com.digdes.pms.service.team.validator;

import com.digdes.pms.dto.team.TeamMemberDto;
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
public class TeamMemberValidatorImpl implements TeamMemberValidator {
    private final MessageSource messageSource;

    @Override
    public void validate(TeamMemberDto teamMemberDto) {
        List<String> errorMessage = new ArrayList<>();

        if (!ObjectUtils.isEmpty(teamMemberDto.getId())) {
            errorMessage.add(messageSource.getMessage("teamMember.field.id.autofill", null, Locale.ENGLISH));
        }

        if (ObjectUtils.isEmpty(teamMemberDto.getTeam()) || teamMemberDto.getTeam().getId() == null) {
            errorMessage.add(messageSource.getMessage("teamMember.field.team.not.filled", null, Locale.ENGLISH));
        }

        if (ObjectUtils.isEmpty(teamMemberDto.getEmployee()) || teamMemberDto.getEmployee().getId() == null) {
            errorMessage.add(messageSource.getMessage("teamMember.field.employee.not.filled", null, Locale.ENGLISH));
        }

        if (ObjectUtils.isEmpty(teamMemberDto.getRole()) || teamMemberDto.getRole().isBlank()) {
            errorMessage.add(messageSource.getMessage("teamMember.field.role.not.filled", null, Locale.ENGLISH));
        }

        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
