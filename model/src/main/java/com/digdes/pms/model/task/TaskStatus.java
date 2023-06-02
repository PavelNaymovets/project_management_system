package com.digdes.pms.model.task;

import java.util.stream.Stream;

public enum TaskStatus {
    NEW("новая"),
    IN_PROGRESS("в работе"),
    COMPLETED("выполнена"),
    CLOSED("закрыта");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public static TaskStatus check(final String status) {
        return Stream.of(TaskStatus.values())
                .filter(targetEnum -> targetEnum.status.equals(status))
                .findFirst()
                .orElse(null);
    }

    public String getStatus() {
        return status;
    }
}
