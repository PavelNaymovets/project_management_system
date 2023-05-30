package com.digdes.pms.model.employee;

import java.util.stream.Stream;

public enum EmployeeStatus {
    ACTIVE("активный"),
    REMOTE("удаленный");

    private final String status;

    EmployeeStatus(String status) {
        this.status = status;
    }

    public static EmployeeStatus check(final String status) {
        return Stream.of(EmployeeStatus.values())
                .filter(targetEnum -> targetEnum.status.equals(status))
                .findFirst()
                .orElse(null);
    }

    public String getStatus() {
        return status;
    }
}
