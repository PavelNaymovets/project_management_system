package com.digdes.pms.service.task.validator;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.service.validator.Validator;

public interface TaskValidator extends Validator<TaskDto> {
    @Override
    void validate(TaskDto taskDto);
}
