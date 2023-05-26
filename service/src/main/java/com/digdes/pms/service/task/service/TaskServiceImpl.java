package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.dto.task.TaskFilterDto;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.task.Task;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.task.TaskRepository;
import com.digdes.pms.repository.task.specification.TaskSpecification;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.project.converter.ProjectConverter;
import com.digdes.pms.service.task.converter.TaskConverter;
import com.digdes.pms.service.task.validator.TaskValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final ProjectConverter projectConverter;
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final TaskValidator taskValidator;

    @Override
    public TaskDto create(TaskDto taskDto, String login) {
        taskValidator.validate(taskDto);
        Employee employee = employeeRepository.findByLogin(login).get();
        taskDto.setAuthor(employeeConverter.convertToDto(employee));
        Task task = taskConverter.convertToEntity(taskDto);
        task.setStatus("новая");
        Task createdTask = taskRepository.save(task);

        return taskConverter.convertToDto(createdTask);
    }

    @Override
    public TaskDto update(TaskDto taskDto, String login) {
        Task task = taskRepository.findById(taskDto.getId()).get();
        checkUpdatableFields(taskDto, task);
        Employee employee = employeeRepository.findByLogin(login).get();
        task.setAuthor(employee);
        Task updatedTask = taskRepository.save(task);

        return taskConverter.convertToDto(updatedTask);
    }

    @Override
    public TaskDto findById(Long id) {
        Task task = taskRepository.findById(id).get();

        return taskConverter.convertToDto(task);
    }

    @Override
    public List<TaskDto> findAll() {
        List<Task> list = taskRepository.findAll();

        return list.stream()
                .map(taskConverter::convertToDto)
                .toList();
    }

    @Override
    public TaskDto deleteById(Long id) {
        Task deletedTask = taskRepository.findById(id).get();
        taskRepository.deleteById(id);

        return taskConverter.convertToDto(deletedTask);
    }

    @Transactional
    @Override
    public void updateStatus(Long id, String status, String login) {
        Task task = taskRepository.findById(id).get();
        Employee employee = employeeRepository.findByLogin(login).get();
        task.setAuthor(employee);
        task.setStatus(status);
    }

    @Override
    public List<TaskDto> findAllByFilter(TaskFilterDto filter) {
        Specification<Task> spec = Specification.where(null);

        if(!ObjectUtils.isEmpty(filter.getName())) {
            spec = spec.and(TaskSpecification.nameLike(filter.getName()));
        }

        if(!ObjectUtils.isEmpty(filter.getStatus())) {
            spec = spec.and(TaskSpecification.statusLike(filter.getStatus()));
        }

        if(!ObjectUtils.isEmpty(filter.getEmployee())) {
            Long id = filter.getEmployee().getId();
            spec = spec.and(TaskSpecification.employeeIdEqual(id));
        }

        if (!ObjectUtils.isEmpty(filter.getAuthor())) {
            Long id = filter.getAuthor().getId();
            spec = spec.and(TaskSpecification.authorIdEqual(id));
        }

        if (!ObjectUtils.isEmpty(filter.getDeadlineMin())) {
            spec = spec.and(TaskSpecification.deadlineMin(filter.getDeadlineMin()));
        }

        if (!ObjectUtils.isEmpty(filter.getDeadlineMax())) {
            spec = spec.and(TaskSpecification.deadlineMax(filter.getDeadlineMax()));
        }

        if (!ObjectUtils.isEmpty(filter.getCreatedAtMin())) {
            spec = spec.and(TaskSpecification.createdAtMin(filter.getCreatedAtMin()));
        }

        if (!ObjectUtils.isEmpty(filter.getCreatedAtMax())) {
            spec = spec.and(TaskSpecification.createdAtMax(filter.getCreatedAtMax()));
        }

        return taskRepository.findAll(spec, Sort.by("createdAt").descending()).stream()
                .map(taskConverter::convertToDto)
                .toList();
    }

    private void checkUpdatableFields(TaskDto taskDto, Task task) {
        if (taskDto.getName() != null && !taskDto.getName().isEmpty()) {
            task.setName(taskDto.getName());
        }

        if (taskDto.getDescription() != null && !taskDto.getDescription().isEmpty()) {
            task.setDescription(taskDto.getDescription());
        }

        if (taskDto.getProject() != null) {
            task.setProject(projectConverter.convertToEntity(taskDto.getProject()));
        }

        if (taskDto.getEmployee() != null) {
            task.setEmployee(employeeConverter.convertToEntity(taskDto.getEmployee()));
        }

        if (taskDto.getLaborCosts() != null) {
            task.setLaborCosts(taskDto.getLaborCosts());
        }

        if (taskDto.getDeadline() != null) {
            task.setDeadline(taskDto.getDeadline());
        }
    }
}
