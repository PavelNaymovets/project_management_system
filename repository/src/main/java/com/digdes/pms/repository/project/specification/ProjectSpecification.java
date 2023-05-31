package com.digdes.pms.repository.project.specification;

import com.digdes.pms.model.project.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {
    public static Specification<Project> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", name));
    }

    public static Specification<Project> codeEqual(Long code) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("code"), code);
    }

    public static Specification<Project> statusLike(String status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("status"), status);
    }
}
