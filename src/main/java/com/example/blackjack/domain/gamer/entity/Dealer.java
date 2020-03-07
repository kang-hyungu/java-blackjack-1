package com.example.blackjack.domain.gamer.entity;

import com.example.blackjack.domain.card.entity.Card;
import com.example.blackjack.domain.card.entity.GameRule;
import com.example.blackjack.domain.card.entity.GamerCards;

import java.util.List;
import java.util.Objects;

public class Dealer implements Gamer {

    private static final String KOREAN_NAME = "딜러";

    private final GamerCards gamerCards;

    public Dealer(final Card... cards) {
        validateCards(cards);
        this.gamerCards = new GamerCards(cards);
    }

    @Override
    public void hit(final Card card) {
        if (card == null) {
            return;
        }
        gamerCards.hit(card);
    }

    @Override
    public int total(final GameRule gameRule) {
        return gameRule.total(this);
    }

    @Override
    public int numberOfAce() {
        return gamerCards.numberOfAce();
    }

    @Override
    public int order() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getName() {
        return KOREAN_NAME;
    }

    @Override
    public GamerCards getGamerCards() {
        return gamerCards;
    }

    @Override
    public List<Card> getCards() {
        return gamerCards.getCards();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(final Gamer gamer) {
        return order() - gamer.order();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(gamerCards, dealer.gamerCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamerCards);
    }

    private void validateCards(final Card[] cards) {
        Objects.requireNonNull(cards, String.format("%s에게 카드를 나눠주세요", getName()));
    }
}
