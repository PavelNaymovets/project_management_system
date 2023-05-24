package com.digdes.pms.controller;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.repository.task.TaskRepository;
import com.digdes.pms.service.task.filter.TaskFilter;
import com.digdes.pms.service.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = {"com.digdes.pms.service"})
@EnableJpaRepositories(basePackages = "com.digdes.pms.repository")
@EntityScan(basePackages = "com.digdes.pms.model")
public class ProjectManagementSystemApplication implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementSystemApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        ProjectDto projectDto = ProjectDto.builder()
                .id(1L)
                .code(1220680L)
                .name("тестовый 'система управления проектами'")
                .description("тестовое 'Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт'")
                .status("в разработке")
                .build();

        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(3L)
                .personalNumber("384f508c-3016-3bf1-9334-6ccc1ca21c36")
                .lastName("Прохорова")
                .firstName("Инна")
                .middleName("Андреевна")
                .position("инженер ui тестирования")
                .email("vil534@example.com")
                .build();

        TaskDto taskDto = TaskDto.builder()
                .id(9L)
                .name("тестовая задача")
                .description("тестовое описание")
                .project(projectDto)
                .employee(employeeDto)
                .laborCosts(10L)
                .deadline(LocalDateTime.now())
                .status("тест")
                .author(employeeDto)
                .build();

        //create task
//        System.out.println(taskService.create(taskDto));

        //update task
//        taskDto.setName("New task");
//        System.out.println(taskService.update(taskDto));

        //find by id task
//        System.out.println(taskService.findById(9L));

        //find all task
//        System.out.println(taskService.findAll());

        //delete by id task
//        System.out.println(taskService.deleteById(2L));

        //find by filter task
        TaskFilter filter = new TaskFilter();
//        filter.setName("New task");
//        filter.setStatus("несуществующий");
//        filter.setDeadlineMin(LocalDateTime.now());
//        filter.setDeadlineMax(LocalDateTime.now());
//        filter.setCreatedAtMin(LocalDateTime.now());
//        filter.setCreatedAtMax(LocalDateTime.now());
//        filter.setEmployee(employeeDto);
//        filter.setAuthor(employeeDto);
        System.out.println(taskService.findAllByFilter(filter));

        //update status task
//        taskService.updateStatus(9L, "In progress");
//        System.out.println(taskService.findById(9L));
    }
}
