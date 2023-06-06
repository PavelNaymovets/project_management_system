package com.digdes.pms.service.employee.sevice;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.employee.EmployeeFilterDto;
import com.digdes.pms.exception.FieldIncorrectException;
import com.digdes.pms.exception.HasDeletedStatusException;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.employee.EmployeeStatus;
import com.digdes.pms.model.employee.Role;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.employee.RoleRepository;
import com.digdes.pms.repository.employee.specification.EmployeeSpecification;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.employee.validator.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Locale;

import static com.digdes.pms.model.employee.EmployeeStatus.ACTIVE;
import static com.digdes.pms.model.employee.EmployeeStatus.REMOTE;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger serviceLog = LoggerFactory.getLogger("service-log");
    private final EmployeeValidator employeeValidator;
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        employeeValidator.validate(employeeDto);
        Employee employee = employeeConverter.convertToEntity(employeeDto);
        employee.setLogin(employeeDto.getLogin());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setStatus(ACTIVE.getStatus());
        Role role = roleRepository.findById(1L).get();
        employee.setRoles(List.of(role));
        Employee createdEmployee = employeeRepository.save(employee);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("employee.created", null, Locale.ENGLISH), createdEmployee.getId(), createdEmployee.getLogin()));

        return employeeConverter.convertToDto(createdEmployee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + employeeDto.getId()));

        if (employee.getStatus().equals(REMOTE.getStatus())) {
            throw new HasDeletedStatusException(
                    messageSource.getMessage("employee.has.deleted.status", null, Locale.ENGLISH));
        }

        checkUpdatableFields(employeeDto, employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("employee.updated", null, Locale.ENGLISH), updatedEmployee.getId(), updatedEmployee.getLogin()));

        return employeeConverter.convertToDto(updatedEmployee);
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + id));
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("employee.find", null, Locale.ENGLISH), id, employee.getLogin()));

        return employeeConverter.convertToDto(employee);
    }

    @Override
    public EmployeeDto findByLogin(String login) {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("employee.find.by.login", null, Locale.ENGLISH), employee.getId(), login));

        return employeeConverter.convertToDto(employee);
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

        if (!ObjectUtils.isEmpty(filter.getStatus())) {
            checkStatus(filter.getStatus());
            spec = spec.and(EmployeeSpecification.statusLike(filter.getStatus()));
        } else {
            spec = spec.and(EmployeeSpecification.statusLike(ACTIVE.getStatus()));
        }

        serviceLog.debug(messageSource.getMessage("employee.find.by.filter", null, Locale.ENGLISH));

        return employeeRepository.findAll(spec).stream()
                .map(employeeConverter::convertToDto)
                .toList();
    }

    @Override
    public EmployeeDto deleteById(Long id) {
        Employee deletedEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + id));

        if (deletedEmployee.getStatus().equals(REMOTE.getStatus())) {
            throw new HasDeletedStatusException(
                    messageSource.getMessage("employee.has.deleted.status", null, Locale.ENGLISH));
        }

        employeeRepository.delete(deletedEmployee);
        deletedEmployee.setStatus(REMOTE.getStatus());
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("employee.deleted", null, Locale.ENGLISH), id, deletedEmployee.getLogin()));

        return employeeConverter.convertToDto(deletedEmployee);
    }

    private void checkUpdatableFields(EmployeeDto employeeDto, Employee employee) {
        if (!ObjectUtils.isEmpty(employeeDto.getStatus())) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("employee.field.status.not.updatable", null, Locale.ENGLISH));
        }
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

    private void checkStatus(String status) {
        if (EmployeeStatus.check(status) == null) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("employee.field.status.incorrect", null, Locale.ENGLISH));
        }
    }

}
