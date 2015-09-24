package com.example.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit_num1, edit_num2;
    Button bnt_add, bnt_sub, bnt_mul, bnt_div, bnt_rem;
    TextView tv_result;
    String num1, num2;
    Float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_num1 = (EditText) findViewById(R.id.editText_num1);
        edit_num2 = (EditText) findViewById(R.id.editText_num2);
        bnt_add = (Button) findViewById(R.id.button_add);
        bnt_sub = (Button) findViewById(R.id.button_sub);
        bnt_mul = (Button) findViewById(R.id.button_mul);
        bnt_div = (Button) findViewById(R.id.button_div);
        bnt_rem = (Button) findViewById(R.id.button_rem);
        tv_result = (TextView) findViewById(R.id.textView_result);

        bnt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();
                if(num1.getBytes().length <= 0 || num2.getBytes().length<=0){
                    Toast.makeText(MainActivity.this, "Input a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) + Float.parseFloat(num2);
                    tv_result.setText("result : " + result);
                }
            }
        });

        bnt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();

                if(num1.getBytes().length <= 0 || num2.getBytes().length<=0){
                    Toast.makeText(MainActivity.this, "Input a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) - Float.parseFloat(num2);
                    tv_result.setText("result : " + result);
                }

            }
        });

        bnt_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();
                if(num1.getBytes().length <= 0 || num2.getBytes().length<=0){
                    Toast.makeText(MainActivity.this, "Input a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) * Float.parseFloat(num2);
                    tv_result.setText("result : " + result);
                }
            }

        });

        bnt_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();
                if (num1.getBytes().length <= 0 || num2.getBytes().length <= 0) {
                    Toast.makeText(MainActivity.this, "Input a number", Toast.LENGTH_SHORT).show();
                }else if(num2.equals("0")) {
                    Toast.makeText(MainActivity.this, "Input a number above 0", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) / Float.parseFloat(num2);
                    tv_result.setText("result : " + result);
                }
            }
        });

        bnt_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();
                if (num1.getBytes().length <= 0 || num2.getBytes().length <= 0) {
                    Toast.makeText(MainActivity.this, "Input a number", Toast.LENGTH_SHORT).show();
                }else if(num2.equals("0")) {
                    Toast.makeText(MainActivity.this, "Input a number above 0", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) % Float.parseFloat(num2);
                    tv_result.setText("result : " + result);
                }
            }
        });

    }


}
