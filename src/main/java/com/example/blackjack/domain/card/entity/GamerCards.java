package com.example.blackjack.domain.card.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GamerCards {

    private final List<Card> cards;

    public GamerCards(final Card... cards) {
        this.cards = Arrays.stream(cards)
                .collect(Collectors.toList());
    }

    public void hit(final Card card) {
        if (card == null) {
            return;
        }
        cards.add(card);
    }

    public int total() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }

    public int numberOfAce() {
        return (int) cards.stream()
                .filter(card -> card.has(CardNumber.ACE))
                .count();
    }

    public List<Card> getCards() {
        return cards;
    }
}
