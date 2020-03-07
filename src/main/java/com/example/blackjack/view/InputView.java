package com.example.blackjack.view;

import com.example.blackjack.domain.blackjack.entity.Choice;
import com.example.blackjack.domain.gamer.entity.Gamer;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            final String players = scanner.nextLine();
            return Arrays.stream(players.split(DELIMITER))
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (NoSuchElementException | IllegalStateException e) {
            throw new RuntimeException("입력 값을 다시 확인해주세요");
        }
    }

    public static Choice choose(final Gamer player) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player));
        try {
            final String choice = scanner.nextLine();
            return Choice.of(choice);
        } catch (NoSuchElementException | IllegalStateException e) {
            throw new RuntimeException("입력 값을 다시 확인해주세요");
        }
    }

    private InputView() {}
}
