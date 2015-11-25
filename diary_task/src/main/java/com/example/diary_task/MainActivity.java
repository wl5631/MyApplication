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

        checkDir(sdPath); //mydiary 폴더 존재 확인.

        tv_date.setText(Integer.toString(cYear) + "/" + Integer.toString(cMonth + 1) + "/" + Integer.toString(cDay));

        fileName =Integer.toString(cYear) + "_" + Integer.toString(cMonth+1) + "_" +Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName); //ReadDiary
        edit_diary.setText(str); //일기를 EditText에 보여준다.

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
                    if(!Diary.exists()){ //해당 날짜 일기가 존재하지 않으면
                        Diary.createNewFile(); // 파일 생성
                    }
                    String str = edit_diary.getText().toString();
                    outFs.write(str.getBytes()); //파일 쓰기
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " has been saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });
    }

    public void checkDir(String path) { // mydiary 폴더가 존재하지 않을 경우 폴더 생성
        final File mydiary = new File(path + "/mydiary");
       if (!mydiary.exists())
               mydiary.mkdir();
        }

    public String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = new FileInputStream("/sdcard/mydiary/"+fileName); // 파일 입력스트림을 생성하고 경로를 설정한다.
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();

        }catch (IOException e){
            Toast.makeText(getApplicationContext(), "No such File " + fileName, Toast.LENGTH_SHORT).show();
        }
        return diaryStr; //일기 내용을 리턴한다.

    }
    public void showDatePicker(){

        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, dateListener, cYear, cMonth, cDay); //DatePaickerDialog 생성
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", dialog); //확인 버튼 설정
        dialog.show();

        dateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                cYear = year;
                cMonth = monthOfYear;
                cDay = dayOfMonth;

                tv_date.setText(Integer.toString(year) + "/" + Integer.toString(monthOfYear + 1) + "/" + Integer.toString(dayOfMonth)); // 선택한 날짜로 TextView 설정

                fileName =Integer.toString(cYear) + "_" + Integer.toString(cMonth+1) + "_" +Integer.toString(cDay) + ".txt";
                String str = readDiary(fileName); //해당 날짜 일기 불러오기
                    edit_diary.setText(str); // 일기 내용 보여주기
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
                    dialog.setMessage("Do you want to delete this diary");
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
