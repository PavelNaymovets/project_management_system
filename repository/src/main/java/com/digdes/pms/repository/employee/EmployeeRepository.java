package com.digdes.pms.repository.employee;

import com.digdes.pms.model.employee.Employee;

import java.util.List;

public interface EmployeeRepository {
    boolean create(Employee employee);

    boolean update(Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    boolean deleteById(Long id);
}
