package com.example.nikitos.testapp;


public class FieldClass {

    public static int height;
    public static int width;

    public static int [][]field;

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}

