package com.example.blackjack.domain.blackjack.usecase;

import com.example.blackjack.domain.card.entity.GameRule;
import com.example.blackjack.domain.card.entity.GamerCards;
import com.example.blackjack.domain.gamer.entity.Dealer;
import com.example.blackjack.domain.gamer.entity.Gamer;

import java.util.stream.IntStream;

public class BlackJackGameRule implements GameRule {

    private static final int TWENTY_ONE = 21;
    private static final int SEVENTEEN = 17;

    private final Dealer dealer;

    public BlackJackGameRule(final Dealer dealer) {
        this.dealer = dealer;
    }

    public boolean mustStay(final Gamer gamer) {
        if (isDealer(gamer)) {
            return total(gamer) >= SEVENTEEN;
        }
        return isBurst(gamer) || isTwentyOne(gamer);
    }

    @Override
    public boolean isWin(final Gamer gamer) {
        if (isBurst(gamer)) {
            return false;
        }

        if (isTwentyOne(gamer) || isBurst(dealer)) {
            return true;
        }

        // 21에 가까운 사람이 이긴다
        return (TWENTY_ONE - total(dealer)) > (TWENTY_ONE - total(gamer));
    }

    public int total(final Gamer gamer) {
        final GamerCards gamerCards = gamer.getGamerCards();
        final int numberOfAce = gamer.numberOfAce();
        if (notExceedWithAce(gamerCards, numberOfAce)) {
            return sumOfAceAsEleven(gamerCards, numberOfAce);
        }
        return gamerCards.total();
    }

    public static int numberOfMustNotStay() {
        return SEVENTEEN - 1;
    }

    private boolean isBurst(final Gamer gamer) {
        return total(gamer) > TWENTY_ONE;
    }

    private static boolean isDealer(final Gamer gamer) {
        return Dealer.class.equals(gamer.getClass());
    }

    private static boolean isTwentyOne(final Gamer gamer) {
        final GamerCards gamerCards = gamer.getGamerCards();
        if (notExceedWithAce(gamerCards, gamer.numberOfAce())) {
            return true;
        }
        return gamerCards.total() == TWENTY_ONE;
    }

    private static boolean notExceedWithAce(final GamerCards gamerCards, int numberOfAce) {
        // Ace를 11로 바꿔서 합계가 21 이하면 true
        return IntStream.rangeClosed(1, numberOfAce)
                .anyMatch(number -> notExceedTwentyOneWithAceAsEleven(gamerCards, number));
    }

    private static int sumOfAceAsEleven(final GamerCards gamerCards, int numberOfAce) {
        final long numberOfAceAsEleven = IntStream.rangeClosed(1, numberOfAce)
                .filter(number -> notExceedTwentyOneWithAceAsEleven(gamerCards, number))
                .count();

        return sumOfAceAsEleven(gamerCards.total(), (int) numberOfAceAsEleven);
    }

    private static boolean notExceedTwentyOneWithAceAsEleven(final GamerCards gamerCards, final int numberOfAce) {
        return sumOfAceAsEleven(gamerCards.total(), numberOfAce) <= TWENTY_ONE;
    }

    private static int sumOfAceAsEleven(int total, int numberOfAce) {
        final int tenValue = 10;

        // Ace를 1 대신 11로 계산한다
        return total + (numberOfAce * tenValue);
    }
}
