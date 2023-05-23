package com.digdes.pms.service.task.converter;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.model.task.Task;

public interface TaskConverter {
    Task convertToEntity(TaskDto taskDto);
    TaskDto convertToDto(Task task);
}
