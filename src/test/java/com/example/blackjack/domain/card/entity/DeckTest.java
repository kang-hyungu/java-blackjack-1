package com.example.blackjack.domain.card.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @DisplayName("Deck은 카드 48장을 가지고 있다")
    @Test
    void deckSize() {
        final List<Card> deck = Deck.all();

        assertThat(deck.size()).isEqualTo(48);
    }

    @DisplayName("모든 카드는 이름이 있다")
    @Test
    void notBlackCardName() {
        final List<Card> deck = Deck.all();

        deck.forEach(card -> assertThat(card.toString()).isNotBlank());
    }
}