package com.example.blackjack.domain.card.entity;

import com.example.blackjack.domain.gamer.entity.Gamer;

public interface GameRule {

    boolean isWin(final Gamer gamer);
    int total(final Gamer gamer);
}
