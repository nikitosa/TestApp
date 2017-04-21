package com.example.nikitos.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView result;
    private Button repeatBtn;
    private Button gResultBtn;

    private boolean build;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        build=intent.getBooleanExtra("build", true);

        result=(TextView)findViewById(R.id.result_text);
        repeatBtn=(Button)findViewById(R.id.try_again_btn);
        View.OnClickListener onClick1=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        };
        repeatBtn.setOnClickListener(onClick1);

        gResultBtn=(Button)findViewById(R.id.grafical_result);
        View.OnClickListener onClick2=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this, GraphicResultActivity.class);
                startActivity(intent);
            }
        };
        gResultBtn.setOnClickListener(onClick2);
        if(!build){
            result.setText(R.string.negative_result);
            gResultBtn.setEnabled(false);

        }
        else{
            result.setText(R.string.positive_result);
        }
    }
}
