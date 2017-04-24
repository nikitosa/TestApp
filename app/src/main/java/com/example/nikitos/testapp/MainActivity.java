package com.example.nikitos.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {

    private FieldClass fieldClass;
    private Brick brick;

    private Button setArguments;
    private Button addBrick;
    private EditText fieldHeight;
    private EditText fieldWidth;
    private EditText editBrickHeight;
    private EditText editBrickWidth;
    private EditText editBrickNumb;
    private TextView heightText;
    private TextView widthText;
    private TextView numberText;

    private int height;
    private int width;

    private int brickHeight;
    private int brickWidth;
    private int brickHeight1;
    private int brickWidth1;
    private int brickNumb;

    private boolean build;

    private ArrayList<Brick> bricks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldClass=new FieldClass();
        bricks=new ArrayList<>();

        fieldHeight = (EditText)findViewById(R.id.field_height);
        fieldWidth = (EditText)findViewById(R.id.field_width);
        editBrickHeight = (EditText)findViewById(R.id.set_brick_height) ;
        editBrickWidth = (EditText)findViewById(R.id.set_brick_width);
        editBrickNumb =(EditText)findViewById(R.id.set_brick_numb);

        heightText=(TextView)findViewById(R.id.height);
        widthText=(TextView)findViewById(R.id.width);
        numberText=(TextView)findViewById(R.id.number);

        setArguments=(Button)findViewById(R.id.set_arg);
        addBrick=(Button)findViewById(R.id.add_brick);

        View.OnClickListener onClick1=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editBrickHeight.getText().toString().equals("")||editBrickWidth.getText().toString().equals("")||editBrickNumb.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, R.string.toast_text1, Toast.LENGTH_SHORT).show();
                }
                else{
                    brickHeight=Integer.parseInt(editBrickHeight.getText().toString());
                    brickWidth=Integer.parseInt(editBrickWidth.getText().toString());
                    brickNumb=Integer.parseInt(editBrickNumb.getText().toString());
                    heightText.setText(heightText.getText()+"\n"+brickHeight);
                    widthText.setText(widthText.getText()+"\n"+brickWidth);
                    numberText.setText(numberText.getText()+"\n"+brickNumb);
                    if (brickHeight >= brickWidth) {
                        brickHeight1=brickHeight;
                        brickWidth1=brickWidth;
                    } else {
                        brickHeight1=brickWidth;
                        brickWidth1=brickHeight;
                    }
                    Brick brick= new Brick(brickHeight*brickWidth,brickHeight1,brickWidth1,brickNumb);
                    bricks.add(brick);
                    editBrickHeight.setText("");
                    editBrickWidth.setText("");
                    editBrickNumb.setText("");

                }

            }
        };
        addBrick.setOnClickListener(onClick1);

        View.OnClickListener onClick2=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fieldHeight.getText().toString().equals("") || fieldWidth.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, R.string.toast_text1, Toast.LENGTH_SHORT).show();
                } else {
                    if (bricks.size()==0) {
                        Toast.makeText(MainActivity.this, R.string.toast_text2, Toast.LENGTH_SHORT).show();
                    } else {
                        height = Integer.parseInt(fieldHeight.getText().toString());
                        width = Integer.parseInt(fieldWidth.getText().toString());
                        if (height >= width) {
                            fieldClass.setHeight(height);
                            fieldClass.setWidth(width);
                        } else {
                            fieldClass.setHeight(width);
                            fieldClass.setWidth(height);
                        }
                        Brick.brickType = bricks.size();
                        Brick.brickColorType = Brick.brickType;
                        brick = new Brick(brickHeight * brickWidth, brickHeight, brickWidth, brickNumb);
                        Collections.sort(bricks, new Comparator<Brick>() {
                            @Override
                            public int compare(Brick o1, Brick o2) {
                                return o2.getSquare() - o1.getSquare();
                            }
                        });

                        FieldClass.field=new int[FieldClass.height][FieldClass.width];
                        for (int j = 0; j < bricks.size(); j++) {
                            brick = bricks.get(j);
                            Logic logic = new Logic(brick);
                            logic.start();
                            Brick.brickType--;
                            build = logic.isBuild();
                            if (!build) {
                                break;
                            }
                        }
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("build", build);
                        startActivity(intent);
                    }
                }
            }
        };
        setArguments.setOnClickListener(onClick2);
    }
}
