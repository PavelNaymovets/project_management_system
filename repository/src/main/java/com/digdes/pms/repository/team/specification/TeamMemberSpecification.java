package com.digdes.pms.repository.team.specification;

import com.digdes.pms.model.team.TeamMember;
import org.springframework.data.jpa.domain.Specification;

public class TeamMemberSpecification {
    public static Specification<TeamMember> teamIdEqual(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("team"), id);
    }

    public static Specification<TeamMember> employeeIdEqual(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee"), id);
    }

    public static Specification<TeamMember> roleLike(String role) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("role"), role);
    }
}
