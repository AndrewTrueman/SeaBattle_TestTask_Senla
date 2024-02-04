package com.seabattle;

class Cell {
    // Класс обьекты которого используются в массиве в качестве полей
    private int value;  // 0: empty, 1: ship, 2: hit, 3: miss

    public Cell() {
        this.value = 0; // Default value is empty
    }

    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }
}