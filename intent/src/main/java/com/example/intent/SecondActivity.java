package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Float result;
    TextView tv_result;
    Button bnt_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_result = (TextView) findViewById(R.id.textView_result);
        bnt_intent = (Button) findViewById(R.id.button_second);

        Intent inIntent = getIntent();
        String mark = inIntent.getStringExtra("mark");
        float num1 = inIntent.getFloatExtra("Num1", 0);
        float num2 = inIntent.getFloatExtra("Num2", 0);

        Button bnt_ok = (Button) findViewById(R.id.button_second);

        switch (mark) {
            case "add":
                result = num1 + num2;
                break;
            case "sub":
                result = num1 - num2;
                break;
            case "mul":
                result = num1 * num2;
                break;
            case "div":
                result = num1 / num2;
                break;
        }

        tv_result.setText("Result : " + result);


        bnt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("result", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });


    }


}
