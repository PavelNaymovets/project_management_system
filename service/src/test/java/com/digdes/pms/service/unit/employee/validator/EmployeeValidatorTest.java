package com.digdes.pms.service.unit.employee.validator;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.exception.ValidationException;
import com.digdes.pms.service.employee.validator.EmployeeValidatorImpl;
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
public class EmployeeValidatorTest {
    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private EmployeeValidatorImpl employeeValidator;

    private EmployeeDto employeeDto;

    @BeforeEach
    public void setup(){
        when(messageSource.getMessage(any(), any(), any())).thenReturn("Some message");
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
    }

    @DisplayName("Employee validator Dto test")
    @Test
    public void givenEmployee_whenEmployeeValidate_thenDoesNotThrowException() {
        Assertions.assertDoesNotThrow(() -> employeeValidator.validate(employeeDto));
    }

    @DisplayName("Employee validator Dto null test")
    @Test
    public void givenNull_whenEmployeeValidateObjectNull_thenDoesNotThrowException() {
        employeeDto = null;
        ResourceNotFoundException validationException = Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeValidator.validate(employeeDto));
    }

    @DisplayName("Employee validator Dto empty object test")
    @Test
    public void givenNull_whenEmployeeValidateEmptyObject_thenDoesNotThrowException() {
        employeeDto = EmployeeDto.builder().build();
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> employeeValidator.validate(employeeDto));
    }

    @DisplayName("Employee validator Dto required personalNumber field null test")
    @Test
    public void givenEmployee_whenEmployeeValidateWithNullPersonalNumber_thenDoesNotThrowException() {
        employeeDto.setPersonalNumber(null);
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> employeeValidator.validate(employeeDto));
    }

    @DisplayName("Employee validator Dto required lastName field null test")
    @Test
    public void givenEmployee_whenEmployeeValidateWithNullLastName_thenDoesNotThrowException() {
        employeeDto.setLastName(null);
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> employeeValidator.validate(employeeDto));
    }

    @DisplayName("Employee validator Dto required firstName field null test")
    @Test
    public void givenEmployee_whenEmployeeValidateWithNullFirstName_thenDoesNotThrowException() {
        employeeDto.setFirstName(null);
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> employeeValidator.validate(employeeDto));
    }

    @DisplayName("Employee validator Dto required login field null test")
    @Test
    public void givenEmployee_whenEmployeeValidateWithNullLogin_thenDoesNotThrowException() {
        employeeDto.setLogin(null);
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> employeeValidator.validate(employeeDto));
    }

    @DisplayName("Employee validator Dto required password field null test")
    @Test
    public void givenEmployee_whenEmployeeValidateWithNullPassword_thenDoesNotThrowException() {
        employeeDto.setPassword(null);
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> employeeValidator.validate(employeeDto));
    }
}
