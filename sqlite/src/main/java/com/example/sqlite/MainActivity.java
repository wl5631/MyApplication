package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText edit_name, edit_number, edit_name_result, edit_number_result;
    Button bnt_init, bnt_insert, bnt_select, bnt_edit, bnt_delete;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Management Group Sound");

        edit_name = (EditText) findViewById(R.id.editText_name);
        edit_number = (EditText) findViewById(R.id.editText_number);
        edit_name_result = (EditText) findViewById(R.id.editText_name_result);
        edit_number_result = (EditText) findViewById(R.id.editText_number_result);
        bnt_init = (Button) findViewById(R.id.button_reset);
        bnt_insert = (Button) findViewById(R.id.button_input);
        bnt_select = (Button) findViewById(R.id.button_inquiry);
        bnt_edit = (Button) findViewById(R.id.button_edit);
        bnt_delete = (Button) findViewById(R.id.button_delete);

        myHelper = new myDBHelper(this);

        bnt_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        bnt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('"+edit_name.getText().toString()+"','"+edit_number.getText().toString()+"');");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
            }
        });

        bnt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber="+edit_number.getText().toString()+" WHERE gName="+edit_name.getText().toString()+";");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });

        bnt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName="+edit_name.getText().toString()+";");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });


        bnt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String strNames = "Group Name" + "\r\n" + "-------" + "\r\n";
                String strNumbers = "Numbers" + "\r\n" + "-------" + "\r\n";

                while(cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                edit_name_result.setText(strNames);
                edit_number_result.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });


    }

    public class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
            }

        @Override

        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);

        }
    }
}
