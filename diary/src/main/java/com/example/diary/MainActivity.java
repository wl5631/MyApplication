package com.example.diary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText edit_diary;
    Button bnt_write;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.v_title);

        dp = (DatePicker) findViewById(R.id.datePicker);
        edit_diary = (EditText) findViewById(R.id.editText);
        bnt_write = (Button) findViewById(R.id.button);
        bnt_write.setText(" ");

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName =Integer.toString(cYear) + "_" + Integer.toString(cMonth+1) + "_" +Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName);
        edit_diary.setText(str);
        bnt_write.setEnabled(true);



        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName =Integer.toString(year) + "_" + Integer.toString(monthOfYear+1) + "_" +Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);
                edit_diary.setText(str);
                bnt_write.setEnabled(true);

            }
        });

        bnt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v){

                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String str = edit_diary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " has been saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });

    }

    String readDiary(String fname){
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fname);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            bnt_write.setText(R.string.v_modify);
        }catch(IOException e){
            edit_diary.setHint(R.string.v_no_diary);
            bnt_write.setText(R.string.v_save);
        }
        return diaryStr;
    }
}
