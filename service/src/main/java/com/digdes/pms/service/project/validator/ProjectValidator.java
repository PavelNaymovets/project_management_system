package com.digdes.pms.service.project.validator;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.service.validator.Validator;

public interface ProjectValidator extends Validator<ProjectDto> {
    @Override
    void validate(ProjectDto projectDto);
}
