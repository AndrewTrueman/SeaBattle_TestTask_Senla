package com.seabattle;

import java.util.Scanner;

public class ComputerPlayerGame {
    private final Player humanPlayer;
    private final Player computerPlayer;

    public ComputerPlayerGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя:");
        String playerName = scanner.next();

        humanPlayer = new Player(playerName);
        computerPlayer = new ComputerPlayer();
    }

    public void play() {
        Cell[][] battlefield1 = new Cell[16][16];
        Cell[][] battlefield2 = new Cell[16][16];
        Cell[][] monitor1 = new Cell[16][16];
        Cell[][] monitor2 = new Cell[16][16];

        Operation(battlefield1, battlefield2);
        Operation(monitor1, monitor2);

        placeShips(humanPlayer.getName(), battlefield1);
        placeShips(computerPlayer.getName(), battlefield2);

        while (true) {
            makeTurn(humanPlayer, monitor1, battlefield2);
            if (isWinCondition(monitor1)) {
                break;
            }

            makeComputerTurn(computerPlayer.getName(), monitor2, battlefield1);
            if (isWinCondition(monitor2)) {
                break;
            }
        }
    }

    private void Operation(Cell[][] battlefield1, Cell[][] battlefield2) {
        for (int i = 0; i < battlefield1.length; i++) {
            for (int j = 0; j < battlefield1[0].length; j++) {
                battlefield1[i][j] = new Cell();
            }
        }

        for (int i = 0; i < battlefield2.length; i++) {
            for (int j = 0; j < battlefield2[0].length; j++) {
                battlefield2[i][j] = new Cell();
            }
        }
    }

    private void placeShips(String playerName, Cell[][] battlefield) {
        System.out.println(playerName + ", выберите режим размещения кораблей:");
        System.out.println("1. Ручной.");
        System.out.println("2. Автоматический.");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();

        if (mode == 1) {
            manualPlaceShips(playerName, battlefield);
        } else if (mode == 2) {
            placeShipsOfType(6, 1, playerName, battlefield);
            placeShipsOfType(5, 2, playerName, battlefield);
            placeShipsOfType(4, 3, playerName, battlefield);
            placeShipsOfType(3, 4, playerName, battlefield);
            placeShipsOfType(2, 5, playerName, battlefield);
            placeShipsOfType(1, 6, playerName, battlefield);
            System.out.println("Поле после автоматического размещения кораблей:");
            drawField(battlefield);
        } else {
            System.out.println("Неверный режим. Выбран ручной режим по умолчанию.");
            manualPlaceShips(playerName, battlefield);
        }
    }

    private void manualPlaceShips(String playerName, Cell[][] battlefield) {
        int deck = 4;
        Scanner scanner = new Scanner(System.in);

        while (deck >= 1) {
            System.out.println();
            System.out.println(playerName + ", разместите ваш " + deck + "-палубный корабль на поле:");
            System.out.println();

            drawField(battlefield);

            System.out.println("Введите координату X:");
            int x = scanner.nextInt();
            System.out.println("Введите координату Y:");
            int y = scanner.nextInt();
            System.out.println("Выберите направление:");
            System.out.println("1. Вертикальное.");
            System.out.println("2. Горизонтальное.");
            int direction = scanner.nextInt();

            if (!isAvailable(x, y, deck, direction, battlefield)) {
                System.out.println("Неверные координаты!");
                continue;
            }

            for (int i = 0; i < deck; i++) {
                if (direction == 1) {
                    battlefield[x][y + i].setValue(1);
                } else {
                    battlefield[x + i][y].setValue(1);
                }
            }

            deck--;
            clearScreen();
        }
    }

    private void placeShipsOfType(int deck, int count, String playerName, Cell[][] battlefield) {
        for (int i = 0; i < count; i++) {
            placeShipOfType(deck, playerName, battlefield);
        }
    }

    private void placeShipOfType(int deck, String playerName, Cell[][] battlefield) {
        while (true) {
            int x = (int) (Math.random() * 16);
            int y = (int) (Math.random() * 16);
            int direction = (int) (Math.random() * 2) + 1; // 1 for vertical, 2 for horizontal

            if (isAvailable(x, y, deck, direction, battlefield)) {
                for (int i = 0; i < deck; i++) {
                    if (direction == 1) {
                        battlefield[x][y + i].setValue(1);
                    } else {
                        battlefield[x + i][y].setValue(1);
                    }
                }
                break;
            }
        }
    }

    private void drawField(Cell[][] battlefield) {
        FieldCreation(battlefield);
    }

    static void FieldCreation(Cell[][] battlefield) {
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

    private void makeTurn(Player player, Cell[][] monitor, Cell[][] battlefield) {
        Scanner scanner = new Scanner(System.in);

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

    private void makeComputerTurn(String playerName, Cell[][] monitor, Cell[][] battlefield) {
        System.out.println(playerName + ", компьютер делает свой ход.");

        int x = (int) (Math.random() * 16);
        int y = (int) (Math.random() * 16);

        if (battlefield[x][y].getValue() == 1) {
            System.out.println("Компьютер попал в корабль!");
            monitor[x][y].setValue(2);
            makeComputerTurn(playerName, monitor, battlefield); // Добавлен вызов метода для дополнительного хода
        } else {
            System.out.println("Компьютер промахнулся.");
            monitor[x][y].setValue(1);
        }

        drawField(monitor);
    }

    private boolean isWinCondition(Cell[][] monitor) {
        int counter = 0;
        for (int i = 0; i < monitor.length; i++) {
            for (int j = 0; j < monitor[i].length; j++) {
                if (monitor[i][j].getValue() == 2) {
                    counter++;
                }
            }
        }

        if (counter >= 10) {
            System.out.println(humanPlayer.getName() + " выиграл(а)!!!");
            return true;
        }
        return false;
    }

    private boolean isAvailable(int x, int y, int deck, int rotation, Cell[][] battlefield) {
        if (rotation == 1) {
            if (y + deck > battlefield.length) {
                return false;
            }
        }
        if (rotation == 2) {
            if (x + deck > battlefield[0].length) {
                return false;
            }
        }

        while (deck != 0) {
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (rotation == 1) {
                    yi = i;
                } else {
                    xi = i;
                }

                if (x + 1 + xi < battlefield.length && x + 1 + xi >= 0) {
                    if (battlefield[x + 1 + xi][y + yi].getValue() != 0) {
                        return false;
                    }
                }
                if (x - 1 + xi < battlefield.length && x - 1 + xi >= 0) {
                    if (battlefield[x - 1 + xi][y + yi].getValue() != 0) {
                        return false;
                    }
                }
                if (y + 1 + yi < battlefield.length && y + 1 + yi >= 0) {
                    if (battlefield[x + xi][y + 1 + yi].getValue() != 0) {
                        return false;
                    }
                }
                if (y - 1 + yi < battlefield.length && y - 1 + yi >= 0) {
                    if (battlefield[x + xi][y - 1 + yi].getValue() != 0) {
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }

    private void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

