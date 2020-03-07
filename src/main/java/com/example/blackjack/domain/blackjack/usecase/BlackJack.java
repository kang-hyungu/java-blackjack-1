package com.example.blackjack.domain.blackjack.usecase;

import com.example.blackjack.domain.blackjack.entity.PlayerDecisions;
import com.example.blackjack.domain.card.entity.PlayingCards;
import com.example.blackjack.domain.gamer.entity.Dealer;
import com.example.blackjack.domain.gamer.entity.Gamer;
import com.example.blackjack.domain.gamer.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {

    private final PlayingCards playingCards;
    private final GamerManager gamerManager;
    private final BlackJackGameRule blackJackGameRule;

    public BlackJack(final List<String> playerNames) {
        validatePlayerNames(playerNames);
        this.playingCards = new PlayingCards();
        final Dealer dealer = new Dealer(playingCards.startByDealing());
        this.gamerManager = new GamerManager(dealer, createPlayers(playerNames));
        this.blackJackGameRule = new BlackJackGameRule(dealer);
    }

    public void play(final PlayerDecisions playerDecisions) {
        for (final Gamer gamer : gamerManager) {
            final Player player = (Player) gamer;
            turn(playerDecisions, player);
            playerDecisions.hands(player);
        }
        turn(gamerManager.getDealer());
    }

    private void turn(final PlayerDecisions playerDecisions, final Player player) {
        while (playerDecisions.isHit(player) && !blackJackGameRule.mustStay(player)) {
            turn(player);
        }
    }

    private void turn(final Gamer gamer) {
        if (blackJackGameRule.mustStay(gamer)) {
            return;
        }
        gamer.hit(playingCards.deal());
    }

    public boolean mustStay(final Gamer dealer) {
        return blackJackGameRule.mustStay(dealer);
    }

    public int total(final Gamer gamer) {
        return gamer.total(blackJackGameRule);
    }

    public boolean isWin(final Gamer gamer) {
        return blackJackGameRule.isWin(gamer);
    }

    public long numberOfLossOfDealer() {
        return gamerManager.getPlayers()
                .stream()
                .filter(blackJackGameRule::isWin)
                .count();
    }

    public long numberOfWinOfDealer() {
        return gamerManager.numberOfPlayer() - numberOfLossOfDealer();
    }

    public int countStartingCards() {
        return playingCards.countStartingCards();
    }

    public List<Gamer> getGamers() {
        return gamerManager.getGamers();
    }

    public Dealer getDealer() {
        return gamerManager.getDealer();
    }

    public List<Player> getOnlyPlayers() {
        return gamerManager.getPlayers();
    }

    private void validatePlayerNames(final List<String> playerNames) {
        if (playerNames == null || playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 필수입니다.");
        }
    }

    private List<Player> createPlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(player -> new Player(player, playingCards.startByDealing()))
                .collect(Collectors.toList());
    }
}
