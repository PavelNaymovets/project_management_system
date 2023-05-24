package com.digdes.pms.repository.project;

import com.digdes.pms.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Employee, Long> {
}
