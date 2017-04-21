package com.example.nikitos.testapp;

import android.app.Activity;
import android.os.Bundle;

public class GraphicResultActivity extends Activity {

    private FieldClass field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        field=new FieldClass();
        PixelGridView pixelGrid = new PixelGridView(this);
        pixelGrid.setNumRows(field.getHeight());
        pixelGrid.setNumColumns(field.getWidth());
        setContentView(pixelGrid);
    }
}
