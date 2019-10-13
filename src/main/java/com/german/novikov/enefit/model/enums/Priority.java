package com.german.novikov.enefit.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
public enum Priority {
    LOWEST("Lowest", 0),
    LOW("Low", 1),
    NORMAL("Normal", 2),
    HIGH("High", 3),
    CRITICAL("Critical", 4);

    private final String name;
    private final int level;

    Priority(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static Priority getByLevel(int level){
        return Arrays.stream(Priority.values())
                .filter(priority -> priority.level == level )
                .findFirst()
                .orElse(null);

    }

    public static Stream<Priority> stream() {
        return Arrays.stream(Priority.values());
    }

}
