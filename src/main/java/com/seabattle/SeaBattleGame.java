package com.seabattle;
import java.io.IOException;
import java.util.Scanner;
public class SeaBattleGame {
    public static void main(String[] args) {
        System.out.println("Выберите режим игры:");
        System.out.println("1. Игра с другом.");
        System.out.println("2. Игра с компьютером.");
        System.out.println("3. Завершить игру.");
        System.out.println("Вы всегда можете завершить игру написав команду Break");// пока не реализовано
        Scanner scanner = new Scanner(System.in);
        int gameMode = scanner.nextInt();

        if (gameMode == 1) {
            TwoPlayersGame twoPlayersGame = new TwoPlayersGame();
            twoPlayersGame.play();
        } else if (gameMode == 2) {
            ComputerPlayerGame computerPlayerGame = new ComputerPlayerGame();
            computerPlayerGame.play();
        } else if (gameMode == 3){
            System.out.println("Игра завершена. До свидания!");
            System.exit(0);  // Завершение программы
        }else  System.out.println("Неверная опция. Пожалуйста, выберите существующую опцию.");
    }
}
