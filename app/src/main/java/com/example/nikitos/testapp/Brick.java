package com.example.nikitos.testapp;


public class Brick {

    private int square;
    private int brickHeight;
    private int brickWidth;
    private int brickNumb;

    public static int brickType;
    public static int brickColorType;


    public Brick(int square, int brickHeight, int brickWidth, int brickNumb) {
        this.brickHeight = brickHeight;
        this.brickWidth = brickWidth;
        this.brickNumb = brickNumb;
        this.square=square;
    }

    public int getSquare() {
        return square;
    }

    public int getBrickHeight() {
        return brickHeight;
    }

    public void setBrickHeight(int brickHeight) {
        this.brickHeight = brickHeight;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public void setBrickWidth(int brickWidth) {
        this.brickWidth = brickWidth;
    }

    public int getBrickNumb() {
        return brickNumb;
    }

    public void setBrickNumb(int brickNumb) {
        this.brickNumb = brickNumb;
    }
}
