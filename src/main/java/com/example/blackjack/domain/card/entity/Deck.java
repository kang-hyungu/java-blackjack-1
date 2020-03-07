package com.example.blackjack.domain.card.entity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Deck {

    private static final Map<String, Card> deck = new HashMap<>();

    static {
        deck.putAll(createDeck());
    }

    public static Card pickUp(final CardNumber number, final CardSuit suit) {
        return deck.getOrDefault(Card.name(number, suit), new Card(number, suit));
    }

    public static List<Card> all() {
        return new ArrayList<>(deck.values());
    }

    private static Map<String, Card> createDeck() {
        return CardNumber.getValues()
                .stream()
                .map(Deck::createCards)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Card::toString, Function.identity()));
    }

    private static List<Card> createCards(final CardNumber cardNumber) {
        return CardSuit.getValues()
                .stream()
                .map(cardSuit -> Card.of(cardNumber, cardSuit))
                .collect(Collectors.toList());
    }

    private Deck() {}
}
