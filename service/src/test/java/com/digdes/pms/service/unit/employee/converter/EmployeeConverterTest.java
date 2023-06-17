package com.digdes.pms.service.unit.employee.converter;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.employee.EmployeeStatus;
import com.digdes.pms.service.employee.converter.EmployeeConverterImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeConverterTest {
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private EmployeeConverterImpl employeeConverter;
    private EmployeeDto employeeDto;
    private Employee employee;

    @BeforeEach
    public void setup(){
        when(messageSource.getMessage(any(), any(), any())).thenReturn("Employee = null.");
        employeeDto = EmployeeDto.builder()
                .id(1L)
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Анна")
                .middleName("Ивановна")
                .position("Разработчик")
                .email("ema@il.ru")
                .status(EmployeeStatus.ACTIVE.getStatus())
                .build();
        employee = Employee.builder()
                .id(1L)
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Анна")
                .middleName("Ивановна")
                .position("Разработчик")
                .email("ema@il.ru")
                .status(EmployeeStatus.ACTIVE.getStatus())
                .build();
    }

    @DisplayName("Employee convert to Dto test")
    @Test
    public void givenEmployee_whenConvertEmployeeToDto_thenReturnEmployeeDto() {
        EmployeeDto convertedEmployeeDto = employeeConverter.convertToDto(employee);
        Assertions.assertEquals(employeeDto, convertedEmployeeDto);
    }

    @DisplayName("Employee convert to Entity test")
    @Test
    public void givenEmployeeDto_whenConvertEmployeeToEntity_thenReturnEmployeeEntity() {
        Employee convertedEmployee = employeeConverter.convertToEntity(employeeDto);
        Assertions.assertEquals(employee, convertedEmployee);
    }

    @DisplayName("Employee convert to Entity null test")
    @Test
    public void givenNull_whenConvertEmployeeToEntity_thenReturnException() {
        employeeDto = null;
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeConverter.convertToEntity(employeeDto));
        Assertions.assertEquals("Employee = null.", resourceNotFoundException.getMessage());
    }

    @DisplayName("Employee convert to Dto null test")
    @Test
    public void givenNull_whenConvertEmployeeToDto_thenReturnException() {
        employee = null;
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeConverter.convertToDto(employee));
        Assertions.assertEquals("Employee = null.", resourceNotFoundException.getMessage());
    }
}
