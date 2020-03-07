package com.example.blackjack.domain.card.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CardNumber {

    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    NONE("", 0)
    ;

    private final String name;
    private final int value;

    CardNumber(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static List<CardNumber> getValues() {
        return Arrays.stream(CardNumber.values())
                .filter(value -> value != NONE)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
