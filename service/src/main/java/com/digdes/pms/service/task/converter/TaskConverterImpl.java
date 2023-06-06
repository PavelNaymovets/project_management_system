package com.digdes.pms.service.task.converter;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.model.task.Task;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.project.converter.ProjectConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@RequiredArgsConstructor
public class TaskConverterImpl implements TaskConverter {
    private final ProjectConverter projectConverter;
    private final EmployeeConverter employeeConverter;

    @Override
    public Task convertToEntity(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .project(ObjectUtils.isEmpty(taskDto.getProject()) ? null : projectConverter.convertToEntity(taskDto.getProject()))
                .employee(ObjectUtils.isEmpty(taskDto.getEmployee()) ? null : employeeConverter.convertToEntity(taskDto.getEmployee()))
                .laborCosts(taskDto.getLaborCosts())
                .deadline(taskDto.getDeadline())
                .status(taskDto.getStatus())
                .author(ObjectUtils.isEmpty(taskDto.getAuthor()) ? null : employeeConverter.convertToEntity(taskDto.getAuthor()))
                .build();
    }

    @Override
    public TaskDto convertToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .project(ObjectUtils.isEmpty(task.getProject()) ? null : projectConverter.convertToDto(task.getProject()))
                .employee(ObjectUtils.isEmpty(task.getEmployee()) ? null : employeeConverter.convertToDto(task.getEmployee()))
                .laborCosts(task.getLaborCosts())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .author(ObjectUtils.isEmpty(task.getAuthor()) ? null : employeeConverter.convertToDto(task.getAuthor()))
                .build();
    }

}
