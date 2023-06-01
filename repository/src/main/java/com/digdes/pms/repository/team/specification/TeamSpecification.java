package com.digdes.pms.repository.team.specification;

import com.digdes.pms.model.team.Team;
import org.springframework.data.jpa.domain.Specification;

public class TeamSpecification {
    public static Specification<Team> projectIdEqual(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("project"), id);
    }
}
