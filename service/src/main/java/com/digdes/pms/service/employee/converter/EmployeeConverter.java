package com.digdes.pms.service.employee.converter;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.model.employee.Employee;

public interface EmployeeConverter {
    Employee convertToEntity(EmployeeDto employeeDto);
    EmployeeDto convertToDto(Employee employee);
}
