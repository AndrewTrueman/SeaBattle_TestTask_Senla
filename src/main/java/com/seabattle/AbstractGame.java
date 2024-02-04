package com.seabattle;

import java.util.Scanner;

public class AbstractGame { //Класс для выноса ходов игроков отдельно, чтобы избежать дублирования.
    //будет реализовано в будущем.

    protected Scanner scanner = new Scanner(System.in);

    protected void makeTurn(Player player, Cell[][] monitor, Cell[][] battlefield) {
        while (true) {
            System.out.println(player.getName() + ", ваш ход.");
            System.out.println("  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
            for (int i = 0; i < monitor.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < monitor[1].length; j++) {
                    if (monitor[j][i].getValue() == 0) {
                        System.out.print("- ");
                    } else if (monitor[j][i].getValue() == 1) {
                        System.out.print("* ");
                    } else {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
            System.out.println("Введите координату X:");
            int x = scanner.nextInt();
            System.out.println("Введите координату Y:");
            int y = scanner.nextInt();

            if (battlefield[x][y].getValue() == 1) {
                System.out.println("Попадание! Сделайте ход еще раз!");
                monitor[x][y].setValue(2);
            } else {
                System.out.println("Промах! Ходит ваш оппонент!");
                monitor[x][y].setValue(1);
                break;
            }
            clearScreen();
        }
    }

    protected void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | java.io.IOException e) {
            e.printStackTrace();
        }
    }

    protected void drawField(Cell[][] battlefield) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
        for (int i = 0; i < battlefield.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < battlefield[1].length; j++) {
                if (battlefield[j][i].getValue() == 0) {
                    System.out.print("- ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
