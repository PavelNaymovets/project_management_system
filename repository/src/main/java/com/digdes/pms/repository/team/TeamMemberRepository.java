package com.digdes.pms.repository.team;

import com.digdes.pms.model.team.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>, JpaSpecificationExecutor<TeamMember> {
    @Query(value = "SELECT tm FROM TeamMember tm JOIN tm.team t WHERE t.project.id=:projectId AND tm.employee.id=:employeeId")
    Optional<TeamMember> getByProjectIdAndEmployeeId(@Param("projectId") Long projectId, @Param("employeeId") Long employeeId);
}
