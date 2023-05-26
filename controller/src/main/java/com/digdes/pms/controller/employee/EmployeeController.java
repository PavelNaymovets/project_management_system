package com.digdes.pms.controller.employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@Tag(name = "Контроллер работника", description = "Содержит endpoints для работы с сущностью Employee")
public class EmployeeController {
}
