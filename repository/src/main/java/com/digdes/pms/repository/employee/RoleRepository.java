package com.digdes.pms.repository.employee;

import com.digdes.pms.model.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
