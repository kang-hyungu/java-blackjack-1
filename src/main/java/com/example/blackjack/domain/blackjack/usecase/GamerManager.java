package com.example.blackjack.domain.blackjack.usecase;

import com.example.blackjack.domain.gamer.entity.Dealer;
import com.example.blackjack.domain.gamer.entity.Gamer;
import com.example.blackjack.domain.gamer.entity.Player;
import com.example.blackjack.util.CollectionUtils;

import java.util.*;

class GamerManager implements Iterable<Player> {

    private final Dealer dealer;
    private final List<Player> players;
    private final List<Gamer> gamers; // dealer + players

    public GamerManager(final Dealer dealer, final List<Player> players) {
        validateDealer(dealer);
        validatePlayers(players);
        this.dealer = dealer;
        this.players = players;
        this.gamers = createGamers(dealer, players);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    public long numberOfPlayer() {
        return players.size();
    }

    public List<Gamer> getGamers() {
        return gamers;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void validateDealer(final Gamer dealer) {
        Objects.requireNonNull(dealer, "딜러는 필수입니다");
    }

    private void validatePlayers(final List<Player> players) {
        if (CollectionUtils.isEmpty(players)) {
            throw new IllegalArgumentException("플레이어는 필수입니다");
        }
    }

    private List<Gamer> createGamers(final Dealer dealer, final List<Player> players) {
        final List<Gamer> gamers = new ArrayList<>(players);
        gamers.add(dealer);
        Collections.sort(gamers);
        return gamers;
    }
}
