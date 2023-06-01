package com.digdes.pms.controller.employee;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.employee.EmployeeFilterDto;
import com.digdes.pms.service.employee.sevice.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@Tag(name = "Контроллер работника", description = "Содержит endpoints для работы с сущностью Employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(summary = "Создание сотрудника",
            responses = {
                    @ApiResponse(description = "Сотрудник успешно создан. В качестве ответа возвращается EmployeeDto созданного сотрудника",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto create(@RequestBody
                          @Parameter(description = "Объект EmployeeDto - содержит параметры для создания сотрудника.", required = true)
                          EmployeeDto employeeDto) {

        return employeeService.create(employeeDto);
    }

    @Operation(summary = "Обновление сотрудника",
            responses = {
                    @ApiResponse(description = "Сотрудник успешно обновлен. В качестве ответа возвращается EmployeeDto обновленного сотрудника",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))
                    )
            }
    )
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto update(@RequestBody
                          @Parameter(description = "Объект EmployeeDto - содержит параметры для обновления сотрудника.", required = true)
                          EmployeeDto employeeDto) {
        return employeeService.update(employeeDto);
    }

    @Operation(summary = "Получение сотрудника по id",
            responses = {
                    @ApiResponse(description = "Сотрудник получен. В качестве ответа возвращается EmployeeDto найденного сотрудника",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto getById(@PathVariable
                           @Parameter(description = "Идентификатор сотрудника.", required = true)
                           Long id) {
        return employeeService.findById(id);
    }

    @Operation(summary = "Получение сотрудника по логину",
            responses = {
                    @ApiResponse(description = "Сотрудник получен. В качестве ответа возвращается EmployeeDto найденного сотрудника",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))
                    )
            }
    )
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto getByLogin(@RequestParam(name = "login", required = true)
                               @Parameter(description = "Логин сотрудника.", required = true)
                               String login) {
        return employeeService.findByLogin(login);
    }

    @Operation(summary = "Получение всех сотрудников согласно фильтру параметров. В качестве ответа возвращается список из EmployeeDto",
            responses = {
                    @ApiResponse(description = "Список сотрудников получен",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))
                    )
            }
    )
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> getAll(@RequestBody
                                @Parameter(description = "Объект EmployeeFilterDto - содержит параметры для поиска сотрудников.", required = true)
                                EmployeeFilterDto filter) {
        return employeeService.findAllByFilter(filter);
    }

    @Operation(summary = "Удаление сотрудника по id",
            responses = {
                    @ApiResponse(description = "Сотрудник удален. В качестве ответа возвращается EmployeeDto удаленного сотрудника",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EmployeeDto.class))
                    )
            }
    )
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto deleteById(@PathVariable
                              @Parameter(description = "Идентификатор сотрудника.", required = true)
                              Long id) {
        return employeeService.deleteById(id);
    }
}
