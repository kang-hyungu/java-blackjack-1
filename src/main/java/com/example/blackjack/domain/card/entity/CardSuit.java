package com.example.blackjack.domain.card.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CardSuit {

    CLUB("♣", "클로버", CardColor.BLACK),
    DIAMOND("♦", "다이아몬드", CardColor.RED),
    HEART("♥", "하트", CardColor.RED),
    SPADE("♠", "스페이드", CardColor.BLACK),
    NONE("", "", CardColor.NONE)
    ;

    private final String symbol;
    private final String koreanName;
    private final CardColor color;

    CardSuit(final String symbol, final String koreanName, CardColor color) {
        this.symbol = symbol;
        this.koreanName = koreanName;
        this.color = color;
    }

    public String symbol() {
        return symbol;
    }

    public static List<CardSuit> getValues() {
        return Arrays.stream(CardSuit.values())
                .filter(value -> value != NONE)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return koreanName;
    }
}
