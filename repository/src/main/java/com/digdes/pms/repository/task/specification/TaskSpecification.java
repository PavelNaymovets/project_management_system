package com.digdes.pms.repository.task.specification;

import com.digdes.pms.model.task.Task;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {
    public static Specification<Task> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
    }

    public static Specification<Task> statusLike(String status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("status"), status);
    }

    public static Specification<Task> employeeIdEqual(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee"), id);
    }

    public static Specification<Task> authorIdEqual(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), id);
    }

    public static Specification<Task> deadlineMin(LocalDateTime deadline) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("deadline"), deadline);
    }

    public static Specification<Task> deadlineMax(LocalDateTime deadline) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("deadline"), deadline);
    }

    public static Specification<Task> createdAtMin(LocalDateTime createdAt) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), createdAt);
    }

    public static Specification<Task> createdAtMax(LocalDateTime createdAt) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdAt);
    }
}
