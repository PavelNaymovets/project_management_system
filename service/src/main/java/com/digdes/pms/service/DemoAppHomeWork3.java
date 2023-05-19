package com.digdes.pms.service;

import com.digdes.pms.api.dto.employee.EmployeeDto;
import com.digdes.pms.service.employee.sevice.EmployeeService;
import com.digdes.pms.service.employee.sevice.EmployeeServiceImpl;

public class DemoAppHomeWork3 {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();

        EmployeeDto employeeDto = EmployeeDto.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .middleName("Ivanovich")
                .position("computer programmer")
                .email("ivanov@mail.ru")
                .build();

        //Создание сотрудника
        employeeService.create(employeeDto);

        //Поиск сотрудника по id
        employeeDto = employeeService.findById(1L);
        employeeDto.setName("Roman");

        //Обновление сотрудника
        employeeService.update(employeeDto);
        System.out.println(employeeService.findById(1L));

        //Поиск всех сотрудников
        System.out.println(employeeService.findAll());

        //Удаление сотрудника
//        System.out.println(employeeService.deleteById(1L));
//        System.out.println(employeeService.findAll());

    }
}
