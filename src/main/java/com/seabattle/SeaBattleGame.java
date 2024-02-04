package com.seabattle;
import java.io.IOException;
import java.util.Scanner;
public class SeaBattleGame {
    public static void main(String[] args) {
        System.out.println("Выберите режим игры:");
        System.out.println("1. Игра с другом.");
        System.out.println("2. Игра с компьютером.");
        Scanner scanner = new Scanner(System.in);
        int gameMode = scanner.nextInt();

        if (gameMode == 1) {
            TwoPlayersGame twoPlayersGame = new TwoPlayersGame();
            twoPlayersGame.play();
        } else if (gameMode == 2) {
            ComputerPlayerGame computerPlayerGame = new ComputerPlayerGame();
            computerPlayerGame.play();
        } else {
            System.out.println("Неверный режим. Завершение программы.");
        }
    }
}
