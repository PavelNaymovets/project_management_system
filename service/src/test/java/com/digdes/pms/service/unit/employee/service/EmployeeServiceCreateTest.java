package com.digdes.pms.service.unit.employee.service;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.employee.Role;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.employee.RoleRepository;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.employee.sevice.EmployeeServiceImpl;
import com.digdes.pms.service.employee.validator.EmployeeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceCreateTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeValidator employeeValidator;

    @Mock
    private EmployeeConverter employeeConverter;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    private EmployeeDto employeeDto;
    private Employee employee;
    private Employee dbEmployee;
    private Employee convertedToEntityEmployee;
    private EmployeeDto convertedToDtoEmployee;

    @BeforeEach()
    public void setup() {
        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName("ROLE_ADMIN");
        when(messageSource.getMessage(any(), any(), any())).thenReturn("User created");
        when(passwordEncoder.encode(any())).thenReturn("password cash");
        when(roleRepository.findById(any())).thenReturn(Optional.of(userRole));
        employeeDto = EmployeeDto.builder()
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Анна")
                .middleName("Ивановна")
                .position("Разработчик")
                .email("ema@il.ru")
                .login("log")
                .password("100")
                .build();
        employee = Employee.builder()
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Анна")
                .middleName("Ивановна")
                .position("Разработчик")
                .email("ema@il.ru")
                .login("log")
                .password("100")
                .build();
        dbEmployee = Employee.builder()
                .id(1L)
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Анна")
                .middleName("Ивановна")
                .position("Разработчик")
                .email("ema@il.ru")
                .status("активный")
                .roles(List.of(userRole))
                .login("log")
                .password("password cash")
                .build();
        convertedToEntityEmployee = Employee.builder()
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Анна")
                .middleName("Ивановна")
                .position("Разработчик")
                .email("ema@il.ru")
                .build();
        convertedToDtoEmployee = EmployeeDto.builder()
                .id(1L)
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Анна")
                .middleName("Ивановна")
                .position("Разработчик")
                .email("ema@il.ru")
                .build();
        when(employeeConverter.convertToEntity(any())).thenReturn(convertedToEntityEmployee);
        when(employeeConverter.convertToDto(any())).thenReturn(convertedToDtoEmployee);
        when(employeeRepository.save(any())).thenReturn(dbEmployee);
    }

    @DisplayName("Employee create test")
    @Test
    public void givenEmployee_whenEmployeeCreate_thenReturnCreatedEmployeeDto() {
        EmployeeDto createdEmployee = employeeService.create(employeeDto);
        Assertions.assertNotNull(createdEmployee);
        Assertions.assertNotNull(createdEmployee.getId());
        Assertions.assertEquals(convertedToDtoEmployee, createdEmployee);
        Assertions.assertEquals(1L, createdEmployee.getId());
    }

}
