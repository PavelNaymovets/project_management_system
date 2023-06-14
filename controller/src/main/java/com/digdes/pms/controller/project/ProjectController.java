package com.digdes.pms.controller.project;

import com.digdes.pms.auth.util.IsAdmin;
import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.dto.project.ProjectFilterDto;
import com.digdes.pms.service.project.service.ProjectService;
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
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@Tag(name = "Контроллер проекта", description = "Содержит endpoints для работы с сущностью Project")
public class ProjectController {
    private final ProjectService projectService;

    @Operation(summary = "Создание проекта",
            responses = {
                    @ApiResponse(description = "Проект успешно создан. В качестве ответа возвращается ProjectDto созданного проекта",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProjectDto.class))
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto create(@RequestBody
                              @Parameter(description = "Объект ProjectDto - содержит параметры для создания проекта.", required = true)
                              ProjectDto projectDto) {
        return projectService.create(projectDto);
    }

    @Operation(summary = "Обновление проекта",
            responses = {
                    @ApiResponse(description = "Проект успешно обновлен. В качестве ответа возвращается ProjectDto обновленного проекта",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProjectDto.class))
                    )
            }
    )
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto update(@RequestBody
                          @Parameter(description = "Объект ProjectDto - содержит параметры для обновления проекта.", required = true)
                          ProjectDto projectDto) {
        return projectService.update(projectDto);
    }

    @Operation(summary = "Получение проекта по id",
            responses = {
                    @ApiResponse(description = "Проект получен. В качестве ответа возвращается ProjectDto проекта",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProjectDto.class))
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto getById(@PathVariable
                           @Parameter(description = "Идентификатор проекта.", required = true)
                           Long id) {
        return projectService.findById(id);
    }

    @Operation(summary = "Получение списка проектов согласно фильтру параметров. В качестве ответа возвращается список из ProjectDto",
            responses = {
                    @ApiResponse(description = "Список проектов получен",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProjectDto.class))
                    )
            }
    )
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDto> getAll(@RequestBody
                                @Parameter(description = "Объект ProjectFilterDto - содержит параметры для поиска проектов.", required = true)
                                ProjectFilterDto filter) {
        return projectService.findAllByFilter(filter);
    }

    @Operation(summary = "Удаление проекта по id",
            responses = {
                    @ApiResponse(description = "Проект удален. В качестве ответа возвращается ProjectDto удаленного проекта",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProjectDto.class))
                    )
            }
    )
    @IsAdmin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto deleteById(@PathVariable
                              @Parameter(description = "Идентификатор проекта.", required = true)
                              Long id) {
        return projectService.deleteById(id);
    }

    @Operation(summary = "Обновление статуса проекта по id")
    @PutMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestParam(name = "id", required = true)
                             @Parameter(description = "Идентификатор проекта.", required = true)
                             Long id,
                             @RequestParam(name = "status", required = true)
                             @Parameter(description = "Статус проекта.", required = true)
                             String status) {
        projectService.updateStatus(id, status);
    }
}
