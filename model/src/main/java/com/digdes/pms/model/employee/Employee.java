package com.digdes.pms.model.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Builder
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    //TODO добавить в Employee поля со списком task, team при необходимости

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "position")
    private String position;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private boolean status; //1 - активный, 0 - удаленный.

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
