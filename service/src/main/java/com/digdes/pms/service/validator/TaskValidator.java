package com.digdes.pms.service.validator;

import com.digdes.pms.dto.task.TaskDto;

public interface TaskValidator extends Validator<TaskDto>{
    @Override
    void validate(TaskDto taskDto);
}
