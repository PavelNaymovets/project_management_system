package com.digdes.pms.service.employee.validator;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeValidatorImpl implements EmployeeValidator {
    @Override
    public void validate(EmployeeDto employeeDto) {
        List<String> errorMessage = new ArrayList<>();

        if (ObjectUtils.isEmpty(employeeDto.getPersonalNumber()) || employeeDto.getPersonalNumber().isBlank()) {
            errorMessage.add("Не заполнено обязательное поле - Код сотрудника");
        }
        if (ObjectUtils.isEmpty(employeeDto.getLastName()) || employeeDto.getLastName().isBlank()) {
            errorMessage.add("Не заполнено обязательное поле - Фамилия сотрудника");
        }
        if (ObjectUtils.isEmpty(employeeDto.getFirstName()) || employeeDto.getFirstName().isBlank()) {
            errorMessage.add("Не заполнено обязательное поле - Имя сотрудника");
        }
        if (ObjectUtils.isEmpty(employeeDto.getLogin()) || employeeDto.getLogin().isBlank()) {
            errorMessage.add("Не заполнено обязательное поле - Логин");
        }
        if (ObjectUtils.isEmpty(employeeDto.getPassword()) || employeeDto.getPassword().isBlank()) {
            errorMessage.add("Не заполнено обязательное поле - Пароль");
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }
}
