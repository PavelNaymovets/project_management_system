package com.digdes.pms.service.employee.converter;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.service.util.converter.Converter;

public interface EmployeeConverter extends Converter<Employee, EmployeeDto> {
    @Override
    Employee convertToEntity(EmployeeDto employeeDto);

    @Override
    EmployeeDto convertToDto(Employee employee);
}
