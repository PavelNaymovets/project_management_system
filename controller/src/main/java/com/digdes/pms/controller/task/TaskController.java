package com.digdes.pms.controller.task;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.dto.task.TaskFilterDto;
import com.digdes.pms.service.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
@Tag(name = "Контроллер задач", description = "Содержит endpoints для работы с сущностью Task")
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Создание задачи",
            responses = {
                    @ApiResponse(description = "Задача успешно создана. В качестве ответа возвращается TaskDto созданной задачи",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto create(@RequestBody
                          @Parameter(description = "Объект TaskDto - содержит параметры для создания задачи.", required = true)
                          TaskDto taskDto) {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return taskService.create(taskDto, login);
    }

    @Operation(summary = "Обновление задачи",
            responses = {
                    @ApiResponse(description = "Задача успешно обновлена. В качестве ответа возвращается TaskDto обновленной задачи",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto update(@RequestBody
                          @Parameter(description = "Объект TaskDto - содержит параметры для обновления задачи.", required = true)
                          TaskDto taskDto) {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return taskService.update(taskDto, login);
    }

    @Operation(summary = "Получение задачи по id",
            responses = {
                    @ApiResponse(description = "Задача получена. В качестве ответа возвращается TaskDto задачи",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getById(@PathVariable
                           @Parameter(description = "Идентификатор задачи.", required = true)
                           Long id) {
        return taskService.findById(id);
    }

    @Operation(summary = "Получение всех задач согласно фильтру параметров. В качестве ответа возвращается список из TaskDto",
            responses = {
                    @ApiResponse(description = "Список задач получен",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getAll(@RequestBody
                                @Parameter(description = "Объект TaskFilterDto - содержит параметры для поиска задач.", required = true)
                                TaskFilterDto filter) {
        return taskService.findAllByFilter(filter);
    }

    @Operation(summary = "Удаление задачи по id",
            responses = {
                    @ApiResponse(description = "Задача удалена. В качестве ответа возвращается TaskDto удаленной задачи",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskDto.class))
                    )
            }
    )
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto deleteById(@PathVariable
                              @Parameter(description = "Идентификатор задачи.", required = true)
                              Long id) {
        return taskService.deleteById(id);
    }

    @Operation(summary = "Обновление статуса задачи по id")
    @PutMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestParam(name = "id", required = true)
                             @Parameter(description = "Идентификатор задачи.", required = true)
                             Long id,
                             @RequestParam(name = "status", required = true)
                             @Parameter(description = "Статус задачи.", required = true)
                             String status) {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskService.updateStatus(id, status, login);
    }
}
