package com.example.blackjack;

import com.example.blackjack.domain.blackjack.entity.PlayerDecisions;
import com.example.blackjack.domain.blackjack.usecase.BlackJack;
import com.example.blackjack.view.InputView;
import com.example.blackjack.view.OutputView;

import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();

        final BlackJack blackJack = new BlackJack(playerNames);
        OutputView.startByDealing(blackJack);

        final PlayerDecisions playerDecisions = new PlayerDecisions(InputView::choose, OutputView::printGamerCards);
        blackJack.play(playerDecisions);

        OutputView.printDealerNotStay(blackJack);
        OutputView.printSumOfTheCardValues(blackJack);
        OutputView.printGameReport(blackJack);
    }
}
