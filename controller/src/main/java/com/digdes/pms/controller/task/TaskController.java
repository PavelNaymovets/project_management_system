package com.digdes.pms.controller.task;

import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.model.task.Task;
import com.digdes.pms.service.task.filter.TaskFilter;
import com.digdes.pms.service.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto create(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto update(@RequestBody TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getAll(@RequestBody TaskFilter filter) {
        return taskService.findAllByFilter(filter);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto deleteById(@PathVariable Long id) {
        return taskService.deleteById(id);
    }

    @PutMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestParam(name = "id", required = true) Long id,
                             @RequestParam(name = "status", required = true) String status)
    {
        taskService.updateStatus(id, status);
    }
}
