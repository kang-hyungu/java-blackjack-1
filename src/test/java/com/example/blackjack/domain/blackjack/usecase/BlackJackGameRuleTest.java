package com.example.blackjack.domain.blackjack.usecase;

import com.example.blackjack.domain.card.entity.Card;
import com.example.blackjack.domain.card.entity.CardNumber;
import com.example.blackjack.domain.card.entity.CardSuit;
import com.example.blackjack.domain.gamer.entity.Dealer;
import com.example.blackjack.domain.gamer.entity.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameRuleTest {

    @Test
    void 모든_플레이어_패배() {
        Dealer dealer = new Dealer(
                Card.of(CardNumber.FOUR, CardSuit.DIAMOND),
                Card.of(CardNumber.EIGHT, CardSuit.DIAMOND),
                Card.of(CardNumber.SEVEN, CardSuit.HEART));

        Player pobi = new Player("pobi",
                Card.of(CardNumber.TWO, CardSuit.DIAMOND),
                Card.of(CardNumber.TWO, CardSuit.SPADE),
                Card.of(CardNumber.FIVE, CardSuit.CLUB),
                Card.of(CardNumber.NINE, CardSuit.SPADE),
                Card.of(CardNumber.SEVEN, CardSuit.DIAMOND));

        Player json = new Player("json",
                Card.of(CardNumber.SIX, CardSuit.HEART),
                Card.of(CardNumber.QUEEN, CardSuit.CLUB));

        BlackJackGameRule blackJackGameRule = new BlackJackGameRule(dealer);

        assertThat(blackJackGameRule.total(dealer)).isEqualTo(19);
        assertThat(blackJackGameRule.total(pobi)).isEqualTo(25);
        assertThat(blackJackGameRule.total(json)).isEqualTo(16);
        assertThat(blackJackGameRule.isWin(pobi)).isFalse();
        assertThat(blackJackGameRule.isWin(json)).isFalse();
    }

    @Test
    void 딜러가_블랙잭이면_플레이어_패배() {
        Dealer dealer = new Dealer(
                Card.of(CardNumber.QUEEN, CardSuit.SPADE),
                Card.of(CardNumber.ACE, CardSuit.HEART));

        Player pobi = new Player("pobi",
                Card.of(CardNumber.SIX, CardSuit.SPADE),
                Card.of(CardNumber.NINE, CardSuit.HEART));

        Player json = new Player("json",
                Card.of(CardNumber.QUEEN, CardSuit.CLUB),
                Card.of(CardNumber.THREE, CardSuit.DIAMOND));

        BlackJackGameRule blackJackGameRule = new BlackJackGameRule(dealer);

        assertThat(blackJackGameRule.total(dealer)).isEqualTo(21);
        assertThat(blackJackGameRule.total(pobi)).isEqualTo(15);
        assertThat(blackJackGameRule.total(json)).isEqualTo(13);
        assertThat(blackJackGameRule.isWin(pobi)).isFalse();
        assertThat(blackJackGameRule.isWin(json)).isFalse();
    }

    @Test
    void 플레이어_totla이_21_이하면_ACE를_10으로_계산한다() {
        Dealer dealer = new Dealer(
                Card.of(CardNumber.QUEEN, CardSuit.SPADE),
                Card.of(CardNumber.EIGHT, CardSuit.HEART));

        Player pobi = new Player("pobi",
                Card.of(CardNumber.FIVE, CardSuit.CLUB),
                Card.of(CardNumber.THREE, CardSuit.SPADE),
                Card.of(CardNumber.ACE, CardSuit.HEART));

        BlackJackGameRule blackJackGameRule = new BlackJackGameRule(dealer);

        assertThat(blackJackGameRule.total(dealer)).isEqualTo(18);
        assertThat(blackJackGameRule.total(pobi)).isEqualTo(19);
        assertThat(blackJackGameRule.isWin(pobi)).isTrue();
    }
}