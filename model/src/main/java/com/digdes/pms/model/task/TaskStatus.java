package com.digdes.pms.model.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    //Метод будет использоваться для десериализации кода, поступающего из тела запроса.
    @JsonCreator
    public static TaskStatus decode(final String status) {
        return Stream.of(TaskStatus.values())
                .filter(targetEnum -> targetEnum.status.equals(status))
                .findFirst()
                .orElse(null);
    }

    //Возвращает строку, которая будет помещена в качестве значения в соответствующее поле JSON объекта.
    @JsonValue
    public String getStatus() {
        return status;
    }
}
