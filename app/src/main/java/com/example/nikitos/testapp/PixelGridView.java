package com.example.nikitos.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PixelGridView extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private Paint bluePaint = new Paint();
    private Paint redPaint = new Paint();
    private Paint yellowPaint = new Paint();

    public static int[][] field;
    private FieldClass fieldClass;

    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bluePaint.setColor(getResources().getColor(R.color.colorBlue));
        redPaint.setColor(getResources().getColor(R.color.colorRed));
        yellowPaint.setColor(getResources().getColor(R.color.colorYellow));
        blackPaint.setColor(getResources().getColor(R.color.colorBlack));
        fieldClass=new FieldClass();
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

        for (int i = 1; i < numColumns; i++) { //Setting vertical lines of the grid
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
        }

        for (int i = 1; i < numRows; i++) { //Setting horizontal lines of the grid
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
        }

        field=fieldClass.getField();

        //Filling each cell with colour
        //Large bricks - blue colour
        //Medium bricks - yellow colour
        //Small bricks - red colour
        for (int y = 0; y < numColumns; y++) {
            for (int x = 0; x < numRows; x++) {
                if(field[x][y]==3){
                    canvas.drawRect(y * cellWidth, x * cellHeight,
                            (y + 1) * cellWidth, (x + 1) * cellHeight,
                            bluePaint);
                }
                    if(field[x][y]==2){
                        canvas.drawRect(y * cellWidth, x * cellHeight,
                                (y + 1) * cellWidth, (x + 1) * cellHeight,
                                yellowPaint);
                    }
                        if(field[x][y]==1){
                            canvas.drawRect(y * cellWidth, x * cellHeight,
                                    (y + 1) * cellWidth, (x + 1) * cellHeight,
                                    redPaint);
                        }
            }
        }
    }

}