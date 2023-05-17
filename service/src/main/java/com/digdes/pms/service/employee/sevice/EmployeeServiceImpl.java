package com.digdes.pms.service.employee.sevice;

import com.digdes.pms.api.dto.employee.EmployeeDto;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.repository.employee.DataStorage;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.employee.converter.EmployeeConverterImpl;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    public EmployeeServiceImpl() {
        this.employeeRepository = new DataStorage();
        this.employeeConverter = new EmployeeConverterImpl();
    }

    @Override
    public boolean create(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.convertToEntity(employeeDto);

        return employeeRepository.create(employee);
    }

    @Override
    public boolean update(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.convertToEntity(employeeDto);

        return employeeRepository.update(employee);
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id);

        return employeeConverter.convertToDto(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeConverter::convertToDto)
                .toList();
    }

    @Override
    public boolean deleteById(Long id) {
        return employeeRepository.deleteById(id);
    }
}
