package com.digdes.pms.service.task.converter;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.model.task.Task;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.project.converter.ProjectConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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
                .project(projectConverter.convertToEntity(taskDto.getProject()))
                .employee(employeeConverter.convertToEntity(taskDto.getEmployee()))
                .laborCosts(taskDto.getLaborCosts())
                .deadline(taskDto.getDeadline())
                .status(taskDto.getStatus())
                .author(employeeConverter.convertToEntity(taskDto.getAuthor()))
                .build();
    }

    @Override
    public TaskDto convertToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .project(projectConverter.convertToDto(task.getProject()))
                .employee(employeeConverter.convertToDto(task.getEmployee()))
                .laborCosts(task.getLaborCosts())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .author(employeeConverter.convertToDto(task.getAuthor()))
                .build();
    }
}
