package com.example.blackjack.view;

import com.example.blackjack.domain.blackjack.usecase.BlackJackGameRule;
import com.example.blackjack.domain.blackjack.usecase.BlackJack;
import com.example.blackjack.domain.card.entity.Card;
import com.example.blackjack.domain.gamer.entity.Gamer;

import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";

    public static void startByDealing(final BlackJack blackJack) {
        final Gamer dealer = blackJack.getDealer();
        final String playerNames = playerNames(blackJack);
        final int countStartingCards = blackJack.countStartingCards();

        System.out.println();
        System.out.println(String.format("%s와 %s에게 카드를 %d장씩 나누었습니다.", dealer, playerNames, countStartingCards));
        blackJack.getGamers()
                .forEach(OutputView::printGamerCards);
        System.out.println();
    }

    private static String playerNames(final BlackJack blackJack) {
        return blackJack.getOnlyPlayers()
                .stream()
                .map(Gamer::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    public static void printGamerCards(final Gamer gamer) {
        final String gamerCardNames = gameCardNames(gamer);
        System.out.println(String.format("%s: %s", gamer, gamerCardNames));
    }

    private static String gameCardNames(final Gamer gamer) {
        return gamer.getCards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    public static void printDealerNotStay(final BlackJack blackJack) {
        final Gamer dealer = blackJack.getDealer();
        if (blackJack.mustStay(dealer)) {
            return;
        }
        System.out.println();
        System.out.println(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.", dealer, BlackJackGameRule.numberOfMustNotStay()));
    }

    public static void printSumOfTheCardValues(final BlackJack blackJack) {
        System.out.println();
        blackJack.getGamers()
                .forEach(gamer -> System.out.println(String.format("%s 카드: %s - 결과: %d", gamer, gameCardNames(gamer), blackJack.total(gamer))));
    }

    public static void printGameReport(final BlackJack blackJack) {
        final Gamer dealer = blackJack.getDealer();

        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println(String.format("%s: %d승 %d패", dealer, blackJack.numberOfWinOfDealer(), blackJack.numberOfLossOfDealer()));
        blackJack.getOnlyPlayers()
                .forEach(player -> {
                    final String isWin = blackJack.isWin(player) ? "승" : "패";
                    System.out.println(String.format("%s: %s", player, isWin));
                });
    }

    private OutputView() {}
}
