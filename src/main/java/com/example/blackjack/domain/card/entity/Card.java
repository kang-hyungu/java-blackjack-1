package com.example.blackjack.domain.card.entity;

import java.util.Objects;

public class Card {

    private final CardNumber number;
    private final CardSuit symbol;

    public static Card of(final CardNumber number, final CardSuit symbol) {
        return Deck.pickUp(number, symbol);
    }

    Card(final CardNumber number, final CardSuit symbol) {
        Objects.requireNonNull(number, "number는 필수 입니다.");
        Objects.requireNonNull(symbol, "symbol는 필수 입니다.");
        this.number = number;
        this.symbol = symbol;
    }

    public static String name(final CardNumber number, final CardSuit symbol) {
        return String.format("%s%s", number, symbol);
    }

    public int value() {
        return number.value();
    }

    public boolean has(final CardNumber cardNumber) {
        return number.equals(cardNumber);
    }

    @Override
    public String toString() {
        return name(number, symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number &&
                symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, symbol);
    }
}
