package com.example.diary_task;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tv_date;
    Button bnt_save;
    EditText edit_diary;
    String fileName;

    int cYear, cMonth, cDay;


    private DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        tv_date = (TextView) findViewById(R.id.textView);
        bnt_save = (Button) findViewById(R.id.button);
        edit_diary = (EditText) findViewById(R.id.editText);

        Calendar cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH);
        cDay = cal.get(Calendar.DAY_OF_MONTH);

        checkDir(sdPath);

        tv_date.setText(Integer.toString(cYear) + "/" + Integer.toString(cMonth + 1) + "/" + Integer.toString(cDay));

        fileName =Integer.toString(cYear) + "_" + Integer.toString(cMonth+1) + "_" +Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName);
        edit_diary.setText(str);

        tv_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

       bnt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    File Diary = new File(sdPath + "/mydiary", fileName);
                    FileOutputStream outFs = new FileOutputStream(Diary);
                    if(!Diary.exists()){
                        Diary.createNewFile();
                    }
                    String str = edit_diary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " has been saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });
    }

    public void checkDir(String path) {
        final File mydiary = new File(path + "/mydiary");
       if (!mydiary.exists())
               mydiary.mkdir();
        }

    public String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = new FileInputStream("/sdcard/mydiary/"+fileName);
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();

        }catch (IOException e){
            Toast.makeText(getApplicationContext(), "No such File " + fileName, Toast.LENGTH_SHORT).show();
        }
        return diaryStr;

    }
    public void showDatePicker(){

        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, dateListener, cYear, cMonth, cDay);
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", dialog);
        dialog.show();

        dateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                cYear = year;
                cMonth = monthOfYear;
                cDay = dayOfMonth;

                tv_date.setText(Integer.toString(year) + "/" + Integer.toString(monthOfYear + 1) + "/" + Integer.toString(dayOfMonth));

                fileName =Integer.toString(cYear) + "_" + Integer.toString(cMonth+1) + "_" +Integer.toString(cDay) + ".txt";
                String str = readDiary(fileName);
                edit_diary.setText(str);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_reload:

                String str = readDiary(fileName);
                edit_diary.setText(str);
                return true;
            case R.id.menu_delete:
                File Diary = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mydiary", fileName);
                if(Diary.exists()){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Delete Diary");
                    dialog.setMessage("Do you want to delete this diary?");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            File Diary = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/mydiary", fileName);
                            Diary.delete();
                            edit_diary.setText(" ");
                            Toast.makeText(getApplicationContext(), fileName + " has been deleted", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext()," canceled", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                    }
                return true;

            case R.id.menu_large:
                edit_diary.setTextSize(30);
                return true;
            case R.id.menu_normal:
                edit_diary.setTextSize(20);
                return true;
            case R.id.menu_small:
                edit_diary.setTextSize(10);
                return true;
        }
        return false;

    }
}
