package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.dto.task.TaskFilterDto;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.task.Task;
import com.digdes.pms.model.task.TaskStatus;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.task.TaskRepository;
import com.digdes.pms.repository.task.specification.TaskSpecification;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.project.converter.ProjectConverter;
import com.digdes.pms.service.task.converter.TaskConverter;
import com.digdes.pms.service.task.validator.TaskValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final ProjectConverter projectConverter;
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final TaskValidator taskValidator;
    private final MessageSource messageSource;

    @Override
    public TaskDto create(TaskDto taskDto, String login) {
        taskValidator.validate(taskDto);
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));
        taskDto.setAuthor(employeeConverter.convertToDto(employee));
        Task task = taskConverter.convertToEntity(taskDto);
        task.setStatus(TaskStatus.NEW.getStatus());
        Task createdTask = taskRepository.save(task);

        return taskConverter.convertToDto(createdTask);
    }

    @Override
    public TaskDto update(TaskDto taskDto, String login) {
        Task task = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + taskDto.getId()));
        checkUpdatableFields(taskDto, task);
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));
        task.setAuthor(employee);
        Task updatedTask = taskRepository.save(task);

        return taskConverter.convertToDto(updatedTask);
    }

    @Override
    public TaskDto findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + id));

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
        Task deletedTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + id));
        taskRepository.deleteById(id);

        return taskConverter.convertToDto(deletedTask);
    }

    @Transactional
    @Override
    public void updateStatus(Long id, String status, String login) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + id));
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));
        task.setAuthor(employee);
        task.setStatus(status);
    }

    @Override
    public List<TaskDto> findAllByFilter(TaskFilterDto filter) {
        Specification<Task> spec = Specification.where(null);
        String sortParam = "createdAt";

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

        return taskRepository.findAll(spec, Sort.by(sortParam).descending()).stream()
                .map(taskConverter::convertToDto)
                .toList();
    }

    private void checkUpdatableFields(TaskDto taskDto, Task task) {
        if (!ObjectUtils.isEmpty(taskDto.getName()) && !taskDto.getName().isBlank()) {
            task.setName(taskDto.getName());
        }

        if (!ObjectUtils.isEmpty(taskDto.getDescription()) && !taskDto.getDescription().isBlank()) {
            task.setDescription(taskDto.getDescription());
        }

        if (!ObjectUtils.isEmpty(taskDto.getProject())) {
            task.setProject(projectConverter.convertToEntity(taskDto.getProject()));
        }

        if (!ObjectUtils.isEmpty(taskDto.getEmployee())) {
            task.setEmployee(employeeConverter.convertToEntity(taskDto.getEmployee()));
        }

        if (!ObjectUtils.isEmpty(taskDto.getLaborCosts())) {
            task.setLaborCosts(taskDto.getLaborCosts());
        }

        if (!ObjectUtils.isEmpty(taskDto.getDeadline())) {
            task.setDeadline(taskDto.getDeadline());
        }
    }
}
