package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.project.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {


    @Override
    public boolean create(ProjectDto projectDto) {
        return false;
    }

    @Override
    public boolean update(ProjectDto projectDto) {
        return false;
    }

    @Override
    public ProjectDto findById(Long id) {
        return null;
    }

    @Override
    public List<ProjectDto> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
