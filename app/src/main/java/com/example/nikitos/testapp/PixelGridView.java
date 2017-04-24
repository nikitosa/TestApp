package com.example.nikitos.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

public class PixelGridView extends View {

    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private Paint paint = new Paint();

    public int[][] field;
    private FieldClass fieldClass;

    private ArrayList<Integer> colors;


    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setColor(Color.BLACK);
        fieldClass=new FieldClass();
        colors=new ArrayList<>();
        for(int i=0; i<Brick.brickColorType;i++){ //adding set of random colors, his size is a number of brick's types
            Random rnd = new Random();
            colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        }
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if (numColumns == 0 || numRows == 0) {
            return;
        }
        int width = getWidth();
        int height = getHeight();

        field=fieldClass.getField();
        //taking value from every cell, and setting color? for every type of bricks
        for (int y = 0; y < numColumns; y++) {
            for (int x = 0; x < numRows; x++) {
                for (int q=Brick.brickColorType; q>0;q--){
                    if(field[x][y]==q){
                        paint.setColor(colors.get(q-1));
                        canvas.drawRect(y * cellWidth, x * cellHeight,
                                (y + 1) * cellWidth, (x + 1) * cellHeight,
                                paint);
                    }
                }

            }
        }
        for (int i = 1; i < numColumns; i++) { //Setting vertical lines of the grid
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
        }

        for (int i = 1; i < numRows; i++) { //Setting horizontal lines of the grid
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
        }
    }

}