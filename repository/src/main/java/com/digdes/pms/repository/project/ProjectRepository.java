package com.digdes.pms.repository.project;

import com.digdes.pms.model.project.Project;
import com.digdes.pms.repository.project.util.filter.ProjectFilter;

import java.util.List;

public interface ProjectRepository {
    boolean create(Project project);

    boolean update(Project project);

    Project findById(Long id);

    List<Project> findAll();

    boolean deleteById(Long id);

    Project searchByFilter(ProjectFilter filter);
}
