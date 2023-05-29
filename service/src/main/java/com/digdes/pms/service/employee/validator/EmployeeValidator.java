package com.digdes.pms.service.employee.validator;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.service.validator.Validator;

public interface EmployeeValidator extends Validator<EmployeeDto> {
    @Override
    void validate(EmployeeDto employeeDto);
}
