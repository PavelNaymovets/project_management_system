package com.digdes.pms.service.employee.converter;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.model.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class EmployeeConverterImpl implements EmployeeConverter {
    private final MessageSource messageSource;

    @Override
    public Employee convertToEntity(EmployeeDto employeeDto) {
        if (ObjectUtils.isEmpty(employeeDto)) {
            throw new ResourceNotFoundException(
                    messageSource.getMessage("employee.null", null, Locale.ENGLISH));
        }

        return Employee.builder()
                .id(employeeDto.getId())
                .personalNumber(employeeDto.getPersonalNumber())
                .lastName(employeeDto.getLastName())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .position(employeeDto.getPosition())
                .email(employeeDto.getEmail())
                .status(employeeDto.getStatus())
                .build();
    }

    @Override
    public EmployeeDto convertToDto(Employee employee) {
        if (ObjectUtils.isEmpty(employee)) {
            throw new ResourceNotFoundException(
                    messageSource.getMessage("employee.null", null, Locale.ENGLISH));
        }

        return EmployeeDto.builder()
                .id(employee.getId())
                .personalNumber(employee.getPersonalNumber())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .middleName(employee.getMiddleName())
                .position(employee.getPosition())
                .email(employee.getEmail())
                .status(employee.getStatus())
                .build();
    }
}
