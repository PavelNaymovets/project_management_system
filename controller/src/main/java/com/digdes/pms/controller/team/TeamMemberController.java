package com.digdes.pms.controller.team;

import com.digdes.pms.dto.team.TeamMemberDto;
import com.digdes.pms.dto.team.TeamMemberFilterDto;
import com.digdes.pms.service.team.service.TeamMemberService;
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
@RequestMapping("/api/v1/team/member")
@RequiredArgsConstructor
@Tag(name = "Контроллер участников команды", description = "Содержит endpoints для работы с сущностью TeamMember")
public class TeamMemberController {
    private final TeamMemberService teamMemberService;

    @Operation(summary = "Создание участника команды",
            responses = {
                    @ApiResponse(description = "Участник команды успешно создан. В качестве ответа возвращается TeamMemberDto созданного участника команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamMemberDto.class))
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamMemberDto create(@RequestBody
                          @Parameter(description = "Объект TeamMemberDto - содержит параметры для создания участника команды.", required = true)
                          TeamMemberDto teamMemberDto) {
        return teamMemberService.create(teamMemberDto);
    }

    @Operation(summary = "Обновление участника команды",
            responses = {
                    @ApiResponse(description = "Участник команды успешно обновлен. В качестве ответа возвращается TeamMemberDto обновленного участника команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamMemberDto.class))
                    )
            }
    )
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamMemberDto update(@RequestBody
                          @Parameter(description = "Объект TeamMemberDto - содержит параметры для обновления участника команды.", required = true)
                          TeamMemberDto teamMemberDto) {
        return teamMemberService.update(teamMemberDto);
    }

    @Operation(summary = "Получение участника команды по id",
            responses = {
                    @ApiResponse(description = "Участник команды получен. В качестве ответа возвращается TeamMemberDto участника команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamMemberDto.class))
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamMemberDto getById(@PathVariable
                           @Parameter(description = "Идентификатор участника команды.", required = true)
                           Long id) {
        return teamMemberService.findById(id);
    }

    @Operation(summary = "Получение списка участников команды согласно фильтру параметров. В качестве ответа возвращается список TeamMemberDto",
            responses = {
                    @ApiResponse(description = "Участники команды получены",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamMemberDto.class))
                    )
            }
    )
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeamMemberDto> getAll(@RequestBody
                                @Parameter(description = "Объект TeamMemberFilterDto - содержит параметры для поиска участников команды.", required = true)
                                TeamMemberFilterDto filter) {
        return teamMemberService.findAllByFilter(filter);
    }

    @Operation(summary = "Удаление участника команды по id",
            responses = {
                    @ApiResponse(description = "Участник команды удален. В качестве ответа возвращается TeamMemberDto удаленного участника команды",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamMemberDto.class))
                    )
            }
    )
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamMemberDto deleteById(@PathVariable
                              @Parameter(description = "Идентификатор участника команды.", required = true)
                              Long id) {
        return teamMemberService.deleteById(id);
    }

    @Operation(summary = "Обновление роли участника команды по id")
    @PutMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestParam(name = "id", required = true)
                             @Parameter(description = "Идентификатор участника команды.", required = true)
                             Long id,
                             @RequestParam(name = "status", required = true)
                             @Parameter(description = "Роль участника команды.", required = true)
                             String role) {
        teamMemberService.updateStatus(id, role);
    }
}
