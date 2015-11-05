package com.example.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit_name, edit_email, dlg_edit_name, dlg_edit_email;
    Button bnt_click;
    TextView toast_text;
    View view_dialog, view_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_name = (EditText) findViewById(R.id.editText_name);
        edit_email = (EditText) findViewById(R.id.editText_email);
        bnt_click = (Button) findViewById(R.id.button_click);

        bnt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_dialog = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle(R.string.v_user_info);
                dlg.setView(view_dialog);
                dlg.setPositiveButton(R.string.v_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dlg_edit_name = (EditText) view_dialog.findViewById(R.id.d_edit_name);
                        dlg_edit_email = (EditText) view_dialog.findViewById(R.id.d_edit_eamil);

                       edit_name.setText(dlg_edit_name.getText().toString());
                       edit_email.setText(dlg_edit_email.getText().toString());
                    }
                });
                dlg.setNegativeButton(R.string.v_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          Toast toast = new Toast(MainActivity.this);
                        view_toast = (View) View.inflate(MainActivity.this, R.layout.toast, null);
                        toast_text = (TextView) view_toast.findViewById(R.id.toast_text);
                        toast_text.setText("It has been cancled");
                        toast.setView(view_toast);
                        toast.setGravity(Gravity.RIGHT, 100, 100 );
                        toast.show();
                    }
                });
                dlg.show();
            }
        });
    }


}
