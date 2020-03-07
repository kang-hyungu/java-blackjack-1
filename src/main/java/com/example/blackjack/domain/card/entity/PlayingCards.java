package com.example.blackjack.domain.card.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlayingCards {

    private static final int NUMBER_OF_STARTING_CARDS = 2;

    private final Queue<Card> cards;

    public PlayingCards() {
        final List<Card> deck = Deck.all();
        Collections.shuffle(deck);
        cards = new LinkedList<>(deck);
    }

    public Card[] startByDealing() {
        return new Card[]{ cards.poll(), cards.poll() };
    }

    public int countStartingCards() {
        return NUMBER_OF_STARTING_CARDS;
    }

    public Card deal() {
        return cards.poll();
    }
}
