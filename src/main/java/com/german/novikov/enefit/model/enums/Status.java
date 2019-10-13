package com.german.novikov.enefit.model.enums;

import java.util.Arrays;

public enum Status {
    OPEN("OPEN", 0),
    IN_PROGRES("IN_PROGRES",1);

    private final String name;
    private final int id;

    Status(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static Status getById(int id) {
        return Arrays.stream(Status.values())
                .filter(status -> status.id == id)
                .findFirst()
                .orElse(null);
    }
}
