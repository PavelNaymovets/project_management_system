package com.digdes.pms.repository.employee.specification;

import com.digdes.pms.model.employee.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {
    public static Specification<Employee> lastNameLike(String lastName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("lastName"), String.format("%%%s%%", lastName));
    }

    public static Specification<Employee> firstNameLike(String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstName"), String.format("%%%s%%", firstName));
    }

    public static Specification<Employee> middleNameLike(String middleName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("middleName"), String.format("%%%s%%", middleName));
    }

    public static Specification<Employee> loginLike(String login) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("login"), login);
    }

    public static Specification<Employee> emailLike(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), email);
    }

    public static Specification<Employee> statusEqual(Boolean status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}
