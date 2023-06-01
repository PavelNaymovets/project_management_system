package com.digdes.pms.service.task.converter;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.model.task.Task;
import com.digdes.pms.service.util.converter.Converter;

public interface TaskConverter extends Converter<Task, TaskDto> {
    @Override
    Task convertToEntity(TaskDto taskDto);

    @Override
    TaskDto convertToDto(Task task);
}
