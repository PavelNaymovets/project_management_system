package com.digdes.pms.repository.project;

import com.digdes.pms.model.project.Project;
import com.digdes.pms.repository.project.util.DataBaseConnector;
import com.digdes.pms.repository.project.util.filter.ProjectFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDao implements ProjectRepository {

    @Override
    public boolean create(Project project) {
        try (Connection connection = DataBaseConnector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO project (code, name, description, status) VALUES(?,?,?,?)");
            ps.setLong(1, project.getCode());
            ps.setString(2, project.getName());
            ps.setString(3, project.getDescription());
            ps.setString(4, project.getStatus());
            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                System.out.println("ProjectDao: Операция create выполнена. 0 строк создано.");

                return false;
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Project project) {
        Project oldProjectData = findById(project.getId());

        try (Connection connection = DataBaseConnector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE project SET code = ?, name = ?, description = ?, status = ? where id = ?");

            validateProjectFields(project, oldProjectData, ps);

            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                System.out.println("ProjectDao: Операция update выполнена. 0 строк обновлено.");

                return false;
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project findById(Long id) {
        try (Connection connection = DataBaseConnector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM project WHERE id = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                throw new RuntimeException("ProjectDao: Операция findById выполнена. 0 строк найдено.");
            } else {
                resultSet.beforeFirst();
                Project project = new Project();

                while (resultSet.next()) {
                    project.setId(resultSet.getLong("id"));
                    project.setCode(resultSet.getLong("code"));
                    project.setName(resultSet.getString("name"));
                    project.setDescription(resultSet.getString("description"));
                    project.setStatus(resultSet.getString("status"));
                }

                return project;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> findAll() {
        try (Connection connection = DataBaseConnector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM project",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                throw new RuntimeException("ProjectDao: Операция findAll выполнена. 0 строк найдено.");
            } else {
                resultSet.beforeFirst();
                List<Project> list = new ArrayList<>();

                while (resultSet.next()) {
                    list.add(Project.builder()
                            .id(resultSet.getLong("id"))
                            .code(resultSet.getLong("code"))
                            .name(resultSet.getString("name"))
                            .description(resultSet.getString("description"))
                            .status(resultSet.getString("status"))
                            .build());
                }

                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = DataBaseConnector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM project WHERE id = ?");
            ps.setLong(1, id);
            int updatedRows = ps.executeUpdate();

            if (updatedRows == 0) {
                System.out.println("ProjectDao: Операция deleteById выполнена. 0 строк удалено.");

                return false;
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> searchByFilter(ProjectFilter filter) {
        String searchQuery =
                """
                        SELECT
                            p.id,
                            p.code,
                            p.name,
                            p.description,
                            p.status
                        FROM team
                            JOIN project p
                                ON team.project_id = p.id
                            JOIN team_member tm
                                ON team.id = tm.team_id
                            JOIN employee e
                                ON tm.employee_id = e.id
                        WHERE 1 = 1""";
        Map<Integer, String> parameterMap = new HashMap<>();
        int indexParameter = 1;

        if (filter.getLastName() != null) {
            searchQuery += " AND e.last_name = ?";
            parameterMap.put(indexParameter++, filter.getLastName());
        }

        if (filter.getRole() != null) {
            searchQuery += " AND tm.role = ?";
            parameterMap.put(indexParameter++, filter.getRole());
        }

        if (parameterMap.size() == 0) {
            return findAll();
        }

        try (Connection connection = DataBaseConnector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    searchQuery,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            for (Map.Entry<Integer, String> entry : parameterMap.entrySet()) {
                ps.setObject(entry.getKey(), entry.getValue());
            }

            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                throw new RuntimeException("ProjectDao: Операция searchByFilter выполнена. 0 строк найдено.");
            } else {
                resultSet.beforeFirst();
                List<Project> projects = new ArrayList<>();

                while (resultSet.next()) {
                    projects.add(Project.builder()
                            .id(resultSet.getLong("id"))
                            .code(resultSet.getLong("code"))
                            .name(resultSet.getString("name"))
                            .description(resultSet.getString("description"))
                            .status(resultSet.getString("status"))
                            .build());
                }

                return projects;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateProjectFields(Project project, Project oldProjectData, PreparedStatement ps) throws SQLException {
        if (project.getCode() == null)
            ps.setLong(1, oldProjectData.getCode());
        else
            ps.setLong(1, project.getCode());

        if (project.getName() == null)
            ps.setString(2, oldProjectData.getName());
        else
            ps.setString(2, project.getName());

        if (project.getDescription() == null)
            ps.setString(3, oldProjectData.getDescription());
        else
            ps.setString(3, project.getDescription());

        if (project.getStatus() == null)
            ps.setString(4, oldProjectData.getStatus());
        else
            ps.setString(4, project.getStatus());

        if (project.getId() == null)
            ps.setLong(5, oldProjectData.getId());
        else
            ps.setLong(5, project.getId());
    }
}
