package com.digdes.pms.model.team;

import java.util.stream.Stream;

public enum TeamMemberRole {
    PROJECT_MANAGE("руководитель проекта"),
    ANALYST("аналитик"),
    DEVELOPER("разработчик"),
    TESTER("тестировщик");

    private final String role;

    TeamMemberRole(String role) {
        this.role = role;
    }

    public static TeamMemberRole check(final String role) {
        return Stream.of(TeamMemberRole.values())
                .filter(targetEnum -> targetEnum.role.equals(role))
                .findFirst()
                .orElse(null);
    }

    public String getStatus() {
        return role;
    }
}
