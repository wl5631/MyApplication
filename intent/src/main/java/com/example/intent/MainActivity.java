package com.example.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bnt_cal;
    EditText edit_num1, edit_num2;
    RadioGroup rg;
    RadioButton rd_add, rd_sub, rd_mul, rd_div;
    String mark, num1, num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnt_cal = (Button) findViewById(R.id.button);
        edit_num1 = (EditText) findViewById(R.id.editText);
        edit_num2 = (EditText) findViewById(R.id.editText2);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rd_add = (RadioButton) findViewById(R.id.radioButton_add);
        rd_sub = (RadioButton) findViewById(R.id.radioButton_sub);
        rd_div = (RadioButton) findViewById(R.id.radioButton_mul);
        rd_mul = (RadioButton) findViewById(R.id.radioButton_div);



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton_add:
                        mark = "add";
                        break;
                    case R.id.radioButton_sub:
                        mark = "sub";
                        break;
                    case R.id.radioButton_mul:
                        mark = "mul"; 
                        break;
                    case R.id.radioButton_div:
                        mark = "div";
                        break;
                }
            }
        });

        bnt_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();

                if(num1.getBytes().length <=0 || num2.getBytes().length <=0){
                    Toast.makeText(MainActivity.this, "Input number", Toast.LENGTH_SHORT).show();
                }

                else if(num2.equals("0")) {
                    if(mark.equals("mul") || mark.equals("div"))  {
                        Toast.makeText(MainActivity.this, "Input second number above 0", Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("Num1", Float.parseFloat(num1));
                    intent.putExtra("Num2", Float.parseFloat(num2));
                    intent.putExtra("mark", mark);

                    startActivityForResult(intent, 0);
                }

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Float result = data.getFloatExtra("result", 0);
            Toast.makeText(getApplicationContext(), "Result :" + result, Toast.LENGTH_SHORT).show();

        }
    }

}
