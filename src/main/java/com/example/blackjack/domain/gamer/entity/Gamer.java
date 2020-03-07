package com.example.blackjack.domain.gamer.entity;

import com.example.blackjack.domain.card.entity.Card;
import com.example.blackjack.domain.card.entity.GameRule;
import com.example.blackjack.domain.card.entity.GamerCards;

import java.util.List;

public interface Gamer extends Comparable<Gamer> {
    void hit(final Card card);
    int total(final GameRule gameRule);
    int numberOfAce();
    String getName();
    GamerCards getGamerCards();
    List<Card> getCards();
    int order();
}
