package com.example.calc2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText edit_num1, edit_num2;
    Button bnt_add, bnt_sub, bnt_mul, bnt_div;
    TextView tv_result;
    String num1, num2;
    int result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.button_num0,R.id.button_num1,R.id.button_num2,R.id.button_num3,
            R.id.button_num4,R.id.button_num5,R.id.button_num6,R.id.button_num7,R.id.button_num8, R.id.button_num9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        setTitle(R.string.v_title);
        edit_num1 = (EditText) findViewById(R.id.editText_num1);
        edit_num2 = (EditText) findViewById(R.id.editText_num2);
        bnt_add = (Button) findViewById(R.id.button_add);
        bnt_sub = (Button) findViewById(R.id.button_sub);
        bnt_mul = (Button) findViewById(R.id.button_mul);
        bnt_div = (Button) findViewById(R.id.button_div);
        tv_result = (TextView) findViewById(R.id.textView_result);

        for(i =0; i<numBtnIDs.length; i++){
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        for(i=0; i<numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit_num1.isFocused() == true) {
                        num1 = edit_num1.getText().toString() + numButtons[index].getText().toString();
                        edit_num1.setText(num1);
                    } else if (edit_num2.isFocused() == true) {
                        num2 = edit_num2.getText().toString() + numButtons[index].getText().toString();
                        edit_num2.setText(num2);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.v_select, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        bnt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();
                if(num1.getBytes().length <= 0 || num2.getBytes().length<=0){
                    Toast.makeText(MainActivity.this, "Input a number", Toast.LENGTH_SHORT).show();
                }else {
                    result = Integer.parseInt(num1) + Integer.parseInt(num2);
                    tv_result.setText("Result :" + result);
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
                    result = Integer.parseInt(num1) - Integer.parseInt(num2);
                    tv_result.setText("Result :" + result);
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
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                    tv_result.setText("Result :" + result);
                }
            }
        });
        bnt_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit_num1.getText().toString();
                num2 = edit_num2.getText().toString();
                if(num1.getBytes().length <= 0 || num2.getBytes().length<=0){
                    Toast.makeText(MainActivity.this, "Input a number", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Integer.parseInt(num1) / Integer.parseInt(num2);
                    tv_result.setText("Result :" + result);
                }
            }
        });
    }
}
