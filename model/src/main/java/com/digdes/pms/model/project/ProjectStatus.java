package com.digdes.pms.model.project;

import java.util.stream.Stream;

public enum ProjectStatus {

    DRAFT("черновик"),
    IN_DEVELOPMENT("в разработке"),
    IN_TESTING("в тестировании"),
    COMPLETED("завершен");

    private final String status;

    ProjectStatus(String status) {
        this.status = status;
    }

    public static ProjectStatus check(final String status) {
        return Stream.of(ProjectStatus.values())
                .filter(targetEnum -> targetEnum.status.equals(status))
                .findFirst()
                .orElse(null);
    }

    public String getStatus() {
        return status;
    }
}
