package com.digdes.pms.service.employee.validator;

import com.digdes.pms.dto.employee.EmployeeDto;
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
public class EmployeeValidatorImpl implements EmployeeValidator {
    private final MessageSource messageSource;

    @Override
    public void validate(EmployeeDto employeeDto) {
        List<String> errorMessage = new ArrayList<>();

        if (ObjectUtils.isEmpty(employeeDto.getPersonalNumber()) || employeeDto.getPersonalNumber().isBlank()) {
            errorMessage.add(messageSource.getMessage("employee.field.personalNumber.not.filled", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(employeeDto.getLastName()) || employeeDto.getLastName().isBlank()) {
            errorMessage.add(messageSource.getMessage("employee.field.lastName.not.filled", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(employeeDto.getFirstName()) || employeeDto.getFirstName().isBlank()) {
            errorMessage.add(messageSource.getMessage("employee.field.firstName.not.filled", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(employeeDto.getLogin()) || employeeDto.getLogin().isBlank()) {
            errorMessage.add(messageSource.getMessage("employee.field.login.not.filled", null, Locale.ENGLISH));
        }
        if (ObjectUtils.isEmpty(employeeDto.getPassword()) || employeeDto.getPassword().isBlank()) {
            errorMessage.add(messageSource.getMessage("employee.field.password.not.filled", null, Locale.ENGLISH));
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
