package com.digdes.pms.controller.project;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@Tag(name = "Контроллер проекта", description = "Содержит endpoints для работы с сущностью Project")
public class ProjectController {
}
