package com.digdes.pms.service.employee.sevice;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.employee.EmployeeFilterDto;
import com.digdes.pms.exception.EmployeeHasDeletedStatusException;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.employee.specification.EmployeeSpecification;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.employee.validator.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeValidator employeeValidator;
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        employeeValidator.validate(employeeDto);
        Employee employee = employeeConverter.convertToEntity(employeeDto);
        employee.setLogin(employeeDto.getLogin());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setStatus(true);
        Employee createdEmployee = employeeRepository.save(employee);

        return employeeConverter.convertToDto(createdEmployee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Сотрудник не найден. Id: " + employeeDto.getId()));

        if (employee.isStatus() == false) {
            throw new EmployeeHasDeletedStatusException("Сотрудник имеет статус - удаленный.");
        }

        checkUpdatableFields(employeeDto, employee);
        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeConverter.convertToDto(updatedEmployee);
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Сотрудник не найден. Id: " + id));

        return employeeConverter.convertToDto(employee);
    }

    @Override
    public EmployeeDto findByLogin(String login) {
        Employee employee = employeeRepository.findByLogin(login).orElseThrow(() -> new ResourceNotFoundException("Сотрудник не найден. Логин: " + login));

        return employeeConverter.convertToDto(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> list = employeeRepository.findAll();

        return list.stream()
                .map(employeeConverter::convertToDto)
                .toList();
    }

    @Override
    public List<EmployeeDto> findAllByFilter(EmployeeFilterDto filter) {
        Specification<Employee> spec = Specification.where(null);

        if (!ObjectUtils.isEmpty(filter.getLastName())) {
            spec = spec.and(EmployeeSpecification.lastNameLike(filter.getLastName()));
        }

        if (!ObjectUtils.isEmpty(filter.getFirstName())) {
            spec = spec.and(EmployeeSpecification.firstNameLike(filter.getFirstName()));
        }

        if (!ObjectUtils.isEmpty(filter.getMiddleName())) {
            spec = spec.and(EmployeeSpecification.middleNameLike(filter.getMiddleName()));
        }

        if (!ObjectUtils.isEmpty(filter.getLogin())) {
            spec = spec.and(EmployeeSpecification.loginLike(filter.getLogin()));
        }

        if (!ObjectUtils.isEmpty(filter.getEmail())) {
            spec = spec.and(EmployeeSpecification.emailLike(filter.getEmail()));
        }

        spec = spec.and(EmployeeSpecification.statusEqual(filter.isStatus()));

        return employeeRepository.findAll(spec).stream()
                .map(employeeConverter::convertToDto)
                .toList();
    }

    @Override
    public EmployeeDto deleteById(Long id) {
        Employee deletedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Сотрудник не найден. Id: " + id));

        if (deletedEmployee.isStatus() == false) {
            throw new EmployeeHasDeletedStatusException("Сотрудник имеет статус - удаленный.");
        }

        employeeRepository.delete(deletedEmployee);

        return employeeConverter.convertToDto(deletedEmployee);
    }

    private void checkUpdatableFields(EmployeeDto employeeDto, Employee employee) {
        if (!ObjectUtils.isEmpty(employeeDto.getPersonalNumber()) && !employeeDto.getPersonalNumber().isBlank()) {
            employee.setPersonalNumber(employeeDto.getPersonalNumber());
        }
        if (!ObjectUtils.isEmpty(employeeDto.getLastName()) && !employeeDto.getLastName().isBlank()) {
            employee.setLastName(employeeDto.getLastName());
        }
        if (!ObjectUtils.isEmpty(employeeDto.getFirstName()) && !employeeDto.getFirstName().isBlank()) {
            employee.setFirstName(employeeDto.getFirstName());
        }
        if (!ObjectUtils.isEmpty(employeeDto.getMiddleName()) && !employeeDto.getMiddleName().isBlank()) {
            employee.setMiddleName(employeeDto.getMiddleName());
        }
        if (!ObjectUtils.isEmpty(employeeDto.getPosition()) && !employeeDto.getPosition().isBlank()) {
            employee.setPosition(employeeDto.getPosition());
        }
        if (!ObjectUtils.isEmpty(employeeDto.getEmail()) && !employeeDto.getEmail().isBlank()) {
            employee.setEmail(employeeDto.getEmail());
        }
        if (!ObjectUtils.isEmpty(employeeDto.getLogin()) && !employeeDto.getLogin().isBlank()) {
            employee.setLogin(employeeDto.getLogin());
        }
        if (!ObjectUtils.isEmpty(employeeDto.getPassword()) && !employeeDto.getPassword().isBlank()) {
            employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        }
    }
}
