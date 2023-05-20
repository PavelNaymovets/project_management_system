package com.digdes.pms.service.employee.converter;

import com.digdes.pms.api.dto.employee.EmployeeDto;
import com.digdes.pms.model.employee.Employee;

public class EmployeeConverterImpl implements EmployeeConverter {
    @Override
    public Employee convertToEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .middleName(employeeDto.getMiddleName())
                .position(employeeDto.getPosition())
                .email(employeeDto.getEmail())
                .build();
    }

    @Override
    public EmployeeDto convertToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .middleName(employee.getMiddleName())
                .position(employee.getPosition())
                .email(employee.getEmail())
                .build();
    }
}
