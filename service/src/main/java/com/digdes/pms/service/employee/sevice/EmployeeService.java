package com.digdes.pms.service.employee.sevice;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.employee.EmployeeFilterDto;
import com.digdes.pms.service.util.service.ServiceCRUD;

import java.util.List;

public interface EmployeeService extends ServiceCRUD<EmployeeDto, EmployeeFilterDto> {
    @Override
    EmployeeDto create(EmployeeDto employeeDto);

    @Override
    EmployeeDto update(EmployeeDto employeeDto);

    @Override
    EmployeeDto deleteById(Long id);

    @Override
    EmployeeDto findById(Long id);

    @Override
    List<EmployeeDto> findAllByFilter(EmployeeFilterDto filter);

    EmployeeDto findByLogin(String login);
}
