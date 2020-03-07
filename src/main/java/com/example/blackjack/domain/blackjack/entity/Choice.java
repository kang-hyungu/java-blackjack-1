package com.example.blackjack.domain.blackjack.entity;

import com.example.blackjack.util.StringUtils;

import java.util.Arrays;

public enum Choice {

    HIT("y", true),
    STAND("n", false)
    ;

    private final String name;
    private final boolean choice;

    Choice(final String name, boolean choice) {
        this.name = name;
        this.choice = choice;
    }

    public static Choice of(final String choice) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(StringUtils.toLowerCase(choice)))
                .findFirst()
                .orElse(STAND);
    }

    public boolean isHit() {
        return choice;
    }
}
