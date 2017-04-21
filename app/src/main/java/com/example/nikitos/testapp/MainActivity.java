package com.example.nikitos.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nikitos.testapp.Bricks.LargeBrickClass;
import com.example.nikitos.testapp.Bricks.MediumBrickClass;
import com.example.nikitos.testapp.Bricks.SmallBrickClass;



public class MainActivity extends AppCompatActivity {

    private FieldClass fieldClass;
    private SmallBrickClass smallBrickClass;
    private MediumBrickClass mediumBrickClass;
    private LargeBrickClass largeBrickClass;

    private Button setArguments;
    private EditText fieldHeight;
    private EditText fieldWidth;
    private EditText smallBricks;
    private EditText mediumBricks;
    private EditText largeBricks;

    private int height;
    private int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldClass=new FieldClass();
        smallBrickClass =new SmallBrickClass();
        mediumBrickClass = new MediumBrickClass();
        largeBrickClass =new LargeBrickClass();

        fieldHeight = (EditText)findViewById(R.id.field_height);
        fieldWidth = (EditText)findViewById(R.id.field_width);
        smallBricks = (EditText)findViewById(R.id.set_small_bricks_numb) ;
        mediumBricks = (EditText)findViewById(R.id.set_medium_bricks_numb);
        largeBricks =(EditText)findViewById(R.id.set_big_bricks_numb);

        setArguments=(Button)findViewById(R.id.set_arg);

        View.OnClickListener onClick=new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(fieldHeight.getText().toString().equals("")||fieldWidth.getText().toString().equals("")||smallBricks.getText().toString().equals("")||mediumBricks.getText().toString().equals("")||largeBricks.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, R.string.toast_text, Toast.LENGTH_SHORT).show();
                }
                else {
                    height = Integer.parseInt(fieldHeight.getText().toString());
                    width = Integer.parseInt(fieldWidth.getText().toString());
                    if (height >= width) {
                        fieldClass.setHeight(height);
                        fieldClass.setWidth(width);
                    } else {
                        fieldClass.setHeight(width);
                        fieldClass.setWidth(height);
                    }

                    smallBrickClass.setBricksNumb(Integer.parseInt(smallBricks.getText().toString()));
                    mediumBrickClass.setBricksNumb(Integer.parseInt(mediumBricks.getText().toString()));
                    largeBrickClass.setBricksNumb(Integer.parseInt(largeBricks.getText().toString()));
                    LogicClass logic = new LogicClass(fieldClass, smallBrickClass, mediumBrickClass, largeBrickClass);
                    logic.startLogic();
                    boolean build = logic.isBuild();
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("build", build);
                    startActivity(intent);
                }
            }
        };
        setArguments.setOnClickListener(onClick);
    }
}
