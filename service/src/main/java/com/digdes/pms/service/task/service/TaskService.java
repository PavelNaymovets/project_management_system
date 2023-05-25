package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.dto.task.TaskFilterDto;

import java.util.List;

public interface TaskService {
    TaskDto create(TaskDto taskDto, String login);

    TaskDto update(TaskDto taskDto, String login);

    TaskDto findById(Long id);

    List<TaskDto> findAll();

    TaskDto deleteById(Long id);
    void updateStatus(Long id, String status, String login);
    List<TaskDto> findAllByFilter(TaskFilterDto filter);
}
