package com.digdes.pms.service.employee.converter;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.model.employee.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverterImpl implements EmployeeConverter {
    @Override
    public Employee convertToEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .personalNumber(employeeDto.getPersonalNumber())
                .lastName(employeeDto.getLastName())
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .position(employeeDto.getPosition())
                .email(employeeDto.getEmail())
                .build();
    }

    @Override
    public EmployeeDto convertToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .personalNumber(employee.getPersonalNumber())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .middleName(employee.getMiddleName())
                .position(employee.getPosition())
                .email(employee.getEmail())
                .build();
    }
}
