package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.dto.task.TaskFilterDto;
import com.digdes.pms.service.util.service.ServiceCRUD;
import com.digdes.pms.service.util.service.ServiceUpdateStatus;

import java.util.List;

public interface TaskService extends ServiceCRUD<TaskDto, TaskFilterDto>, ServiceUpdateStatus {
    @Override
    TaskDto create(TaskDto taskDto);

    @Override
    TaskDto update(TaskDto taskDto);

    @Override
    TaskDto deleteById(Long id);

    @Override
    TaskDto findById(Long id);

    @Override
    List<TaskDto> findAllByFilter(TaskFilterDto filter);

    @Override
    void updateStatus(Long id, String status);
}
