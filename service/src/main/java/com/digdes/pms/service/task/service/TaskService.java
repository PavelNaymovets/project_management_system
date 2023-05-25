package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.service.task.filter.TaskFilter;

import java.util.List;

public interface TaskService {
    TaskDto create(TaskDto taskDto);

    TaskDto update(TaskDto taskDto);

    TaskDto findById(Long id);

    List<TaskDto> findAll();

    TaskDto deleteById(Long id);
    void updateStatus(Long id, String status);
    List<TaskDto> findAllByFilter(TaskFilter filter);
}
