package com.digdes.pms.service.employee.sevice;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.employee.EmployeeFilterDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.dto.task.TaskFilterDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto);

    EmployeeDto update(EmployeeDto employeeDto);

    EmployeeDto findById(Long id);
    EmployeeDto findByLogin(String login);

    List<EmployeeDto> findAll();

    List<EmployeeDto> findAllByFilter(EmployeeFilterDto filter);
    EmployeeDto deleteById(Long id);
}
