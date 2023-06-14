package com.digdes.pms.controller.team;

import com.digdes.pms.auth.util.IsAdmin;
import com.digdes.pms.dto.team.TeamDto;
import com.digdes.pms.dto.team.TeamFilterDto;
import com.digdes.pms.service.team.service.TeamService;
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
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
@Tag(name = "Контроллер команды", description = "Содержит endpoints для работы с сущностью Team")
public class TeamController {
    private final TeamService teamService;

    @Operation(summary = "Создание команды",
            responses = {
                    @ApiResponse(description = "Команда успешно создана. В качестве ответа возвращается TeamDto созданной команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamDto create(@RequestBody
                          @Parameter(description = "Объект TeamDto - содержит параметры для создания команды.", required = true)
                          TeamDto teamDto) {
        return teamService.create(teamDto);
    }

    @Operation(summary = "Обновление команды",
            responses = {
                    @ApiResponse(description = "Команда успешно обновлена. В качестве ответа возвращается TeamDto обновленной команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))
                    )
            }
    )
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamDto update(@RequestBody
                          @Parameter(description = "Объект TeamDto - содержит параметры для обновления команды.", required = true)
                          TeamDto teamDto) {
        return teamService.update(teamDto);
    }

    @Operation(summary = "Получение команды по id",
            responses = {
                    @ApiResponse(description = "Команда получена. В качестве ответа возвращается TeamDto команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamDto getById(@PathVariable
                           @Parameter(description = "Идентификатор команды.", required = true)
                           Long id) {
        return teamService.findById(id);
    }

    @Operation(summary = "Получение команды согласно фильтру параметров. В качестве ответа возвращается TeamDto",
            responses = {
                    @ApiResponse(description = "Команда получена",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))
                    )
            }
    )
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeamDto> getAll(@RequestBody
                                @Parameter(description = "Объект TeamFilterDto - содержит параметры для поиска команды.", required = true)
                                TeamFilterDto filter) {
        return teamService.findAllByFilter(filter);
    }

    @Operation(summary = "Удаление команды по id",
            responses = {
                    @ApiResponse(description = "Команда удалена. В качестве ответа возвращается TeamDto удаленной команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))
                    )
            }
    )
    @IsAdmin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamDto deleteById(@PathVariable
                              @Parameter(description = "Идентификатор команды.", required = true)
                              Long id) {
        return teamService.deleteById(id);
    }
}