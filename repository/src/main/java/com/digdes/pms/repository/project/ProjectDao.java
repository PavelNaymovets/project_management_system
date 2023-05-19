package com.digdes.pms.repository.project;

import com.digdes.pms.model.project.Project;
import com.digdes.pms.repository.project.util.DataBaseConnector;
import com.digdes.pms.repository.project.util.filter.ProjectFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProjectDao implements ProjectRepository {

    @Override
    public boolean create(Project project) {
        try (Connection connection = DataBaseConnector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO project (name, duration) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getName());
            ps.setInt(2, 10);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean update(Project project) {
        return false;
    }

    @Override
    public Project findById(Long id) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Project searchByFilter(ProjectFilter filter) {
        //1:58:40

        return null;
    }
}
