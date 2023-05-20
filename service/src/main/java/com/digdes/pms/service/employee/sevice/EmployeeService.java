package com.digdes.pms.service.employee.sevice;

import com.digdes.pms.api.dto.employee.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    boolean create(EmployeeDto employee);

    boolean update(EmployeeDto employee);

    EmployeeDto findById(Long id);

    List<EmployeeDto> findAll();

    boolean deleteById(Long id);
}
