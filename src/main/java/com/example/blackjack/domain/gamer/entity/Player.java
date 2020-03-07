package com.example.blackjack.domain.gamer.entity;

import com.example.blackjack.domain.card.entity.Card;
import com.example.blackjack.domain.card.entity.GameRule;
import com.example.blackjack.domain.card.entity.GamerCards;
import com.example.blackjack.util.StringUtils;

import java.util.List;
import java.util.Objects;

public class Player implements Gamer {

    private final String name;
    private final GamerCards gamerCards;

    public Player(final String name, final Card... cards) {
        validateName(name);
        validateCards(cards);
        this.name = name;
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
        return Integer.MIN_VALUE;
    }

    @Override
    public String getName() {
        return name;
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
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(gamerCards, player.gamerCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gamerCards);
    }

    private void validateName(final String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("이름은 필수입니다");
        }
    }

    private void validateCards(final Card[] cards) {
        Objects.requireNonNull(cards, "플레이어에게 카드를 나눠주세요");
    }
}
