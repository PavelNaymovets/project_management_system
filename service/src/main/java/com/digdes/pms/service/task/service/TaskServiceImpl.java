package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.dto.task.TaskFilterDto;
import com.digdes.pms.exception.*;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.project.Project;
import com.digdes.pms.model.task.Task;
import com.digdes.pms.model.task.TaskStatus;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.project.ProjectRepository;
import com.digdes.pms.repository.task.TaskRepository;
import com.digdes.pms.repository.task.specification.TaskSpecification;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.task.converter.TaskConverter;
import com.digdes.pms.service.task.email.service.TaskServiceEmail;
import com.digdes.pms.service.task.queue.TaskEmailQueueSupplier;
import com.digdes.pms.service.task.validator.TaskValidator;
import com.digdes.pms.service.team.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static com.digdes.pms.model.employee.EmployeeStatus.REMOTE;
import static com.digdes.pms.model.task.TaskStatus.NEW;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private static final Logger serviceLog = LoggerFactory.getLogger("service-log");
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final TaskValidator taskValidator;
    private final TeamMemberService teamMemberService;
    private final TaskEmailQueueSupplier taskEmailQueueSupplier;
    private final MessageSource messageSource;

    @Override
    public TaskDto create(TaskDto taskDto) {
        taskValidator.validate(taskDto);
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee author = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));
        Project project = projectRepository.findById(taskDto.getProject().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("project.not.found.id", null, Locale.ENGLISH) + taskDto.getProject().getId()));
        Long projectId = project.getId();
        Long authorId = author.getId();

        if (!teamMemberService.isEmployeeProjectMember(projectId, authorId)) {
            throw new NotProjectMemberException(
                    messageSource.getMessage("task.author.not.project.member", null, Locale.ENGLISH) + login);
        }

        taskDto.setAuthor(employeeConverter.convertToDto(author));
        Task task = taskConverter.convertToEntity(taskDto);
        task.setStatus(NEW.getStatus());
        Task createdTask = taskRepository.save(task);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("task.created", null, Locale.ENGLISH), createdTask.getId(), login));

        return taskConverter.convertToDto(createdTask);
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        if (ObjectUtils.isEmpty(taskDto.getId())) {
            throw new NotSpecifiedIdException(
                    messageSource.getMessage("task.field.id.null", null, Locale.ENGLISH));
        }

        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee author = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));
        Task task = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + taskDto.getId()));

        Long projectId = task.getProject().getId();
        Long authorId = author.getId();

        if (!teamMemberService.isEmployeeProjectMember(projectId, authorId)) {
            throw new NotProjectMemberException(
                    messageSource.getMessage("task.author.not.project.member", null, Locale.ENGLISH) + login);
        }

        checkUpdatableFields(taskDto, task);
        task.setAuthor(author);
        Task updatedTask = taskRepository.save(task);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("task.updated", null, Locale.ENGLISH), updatedTask.getId(), login));

        return taskConverter.convertToDto(updatedTask);
    }

    @Override
    public TaskDto findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + id));
        serviceLog.debug(messageSource.getMessage("task.find", null, Locale.ENGLISH) + id);

        return taskConverter.convertToDto(task);
    }

    @Override
    public TaskDto deleteById(Long id) {
        Task deletedTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + id));
        taskRepository.delete(deletedTask);
        serviceLog.debug(messageSource.getMessage("task.delete", null, Locale.ENGLISH) + id);

        return taskConverter.convertToDto(deletedTask);
    }

    @Transactional
    @Override
    public void updateStatus(Long id, String status) {
        checkStatus(status);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + id));
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee author = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));

        Long projectId = task.getProject().getId();
        Long authorId = author.getId();

        if (!teamMemberService.isEmployeeProjectMember(projectId, authorId)) {
            throw new NotProjectMemberException(
                    messageSource.getMessage("task.author.not.project.member", null, Locale.ENGLISH) + login);
        }

        task.setAuthor(author);
        task.setStatus(status);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("task.update.status", null, Locale.ENGLISH), id, status, login));
    }

    @Override
    public TaskDto appointAnEmployee(Long taskId, Long employeeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("task.not.found.id", null, Locale.ENGLISH) + taskId));

        if (ObjectUtils.isEmpty(task.getEmployee()) || !ObjectUtils.nullSafeEquals(task.getEmployee().getId(), employeeId)) {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + employeeId));

            if (employee.getStatus().equals(REMOTE.getStatus())) {
                throw new HasDeletedStatusException(
                        messageSource.getMessage("employee.has.deleted.status", null, Locale.ENGLISH));
            }

            Long projectId = task.getProject().getId();

            if (!teamMemberService.isEmployeeProjectMember(projectId, employeeId)) {
                throw new NotProjectMemberException(
                        messageSource.getMessage("task.employee.not.project.member", null, Locale.ENGLISH) + employee.getLogin());
            }

            String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Employee author = employeeRepository.findByLogin(login)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messageSource.getMessage("employee.not.found.login", null, Locale.ENGLISH) + login));
            Long authorId = author.getId();

            if (!teamMemberService.isEmployeeProjectMember(projectId, authorId)) {
                throw new NotProjectMemberException(
                        messageSource.getMessage("task.author.not.project.member", null, Locale.ENGLISH) + login);
            }

            task.setEmployee(employee);
            task.setAuthor(author);
            taskEmailQueueSupplier.send(employee.getId() + "_" + task.getId());
            taskRepository.save(task);
            serviceLog.debug(
                    String.format(
                            messageSource.getMessage("task.employee.appoint", null, Locale.ENGLISH), taskId, employee.getLogin(), login));

            return taskConverter.convertToDto(task);
        } else {
            serviceLog.debug(
                    String.format(
                            messageSource.getMessage("task.employee.appoint.same", null, Locale.ENGLISH), taskId, task.getEmployee().getLogin()));

            return taskConverter.convertToDto(task);
        }
    }

    @Override
    public List<TaskDto> findAllByFilter(TaskFilterDto filter) {
        Specification<Task> spec = Specification.where(null);
        String sortParam = "createdAt";

        if(!ObjectUtils.isEmpty(filter.getName())) {
            spec = spec.and(TaskSpecification.nameLike(filter.getName()));
        }

        if(!ObjectUtils.isEmpty(filter.getStatus())) {
            checkStatus(filter.getStatus());
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

        serviceLog.debug(messageSource.getMessage("task.find.by.filter", null, Locale.ENGLISH) + sortParam);

        return taskRepository.findAll(spec, Sort.by(sortParam).descending()).stream()
                .map(taskConverter::convertToDto)
                .toList();
    }

    private void checkUpdatableFields(TaskDto taskDto, Task task) {
        if (!ObjectUtils.isEmpty(taskDto.getStatus())) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("task.field.status.not.updatable", null, Locale.ENGLISH));
        }

        if (!ObjectUtils.isEmpty(taskDto.getEmployee())) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("task.field.employee.not.updatable", null, Locale.ENGLISH));
        }

        if (!ObjectUtils.isEmpty(taskDto.getAuthor())) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("task.field.author.not.updatable", null, Locale.ENGLISH));
        }

        if (!ObjectUtils.isEmpty(taskDto.getName()) && !taskDto.getName().isBlank()) {
            task.setName(taskDto.getName());
        }

        if (!ObjectUtils.isEmpty(taskDto.getDescription()) && !taskDto.getDescription().isBlank()) {
            task.setDescription(taskDto.getDescription());
        }

        if (!ObjectUtils.isEmpty(taskDto.getProject())) {
            Long id = taskDto.getProject().getId();
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messageSource.getMessage("project.not.found.id", null, Locale.ENGLISH) + id));

            if (!ObjectUtils.isEmpty(taskDto.getProject().getCode()) ||
                !ObjectUtils.isEmpty(taskDto.getProject().getName()) ||
                !ObjectUtils.isEmpty(taskDto.getProject().getDescription()) ||
                !ObjectUtils.isEmpty(taskDto.getProject().getStatus())) {
                throw new FieldIncorrectException(
                        messageSource.getMessage("project.not.updatable.here", null, Locale.ENGLISH));
            }

            task.setProject(project);
        }

        if (!ObjectUtils.isEmpty(taskDto.getLaborCosts())) {
            task.setLaborCosts(taskDto.getLaborCosts());
        }

        if (!ObjectUtils.isEmpty(taskDto.getDeadline())) {
            Long laborCost = task.getLaborCosts();

            if (!ObjectUtils.isEmpty(taskDto.getLaborCosts())) {
                laborCost = taskDto.getLaborCosts();
            }

            long days = laborCost/24;
            LocalDate minDeadLine = task.getCreatedAt().toLocalDate().plusDays(days);

            if (taskDto.getDeadline().isBefore(minDeadLine)) {
                throw new FieldIncorrectException(
                        messageSource.getMessage("task.field.deadline.lessThan.laborCost", null, Locale.ENGLISH));
            }

            task.setDeadline(taskDto.getDeadline());
        }
    }

    private void checkStatus(String status) {
        if (TaskStatus.check(status) == null) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("task.field.status.incorrect", null, Locale.ENGLISH));
        }
    }
}
