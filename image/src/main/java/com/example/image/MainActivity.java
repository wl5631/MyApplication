package com.example.image;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button bnt_exit, bnt_restart;
    ImageView i_image;
    Switch switch_agree;
    RadioGroup rGroup;
    RadioButton  rd_jelly, rd_kit, rd_lolli;
    LinearLayout linear1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnt_exit = (Button) findViewById(R.id.button_exit);
        bnt_restart = (Button) findViewById(R.id.button_restart);
        i_image = (ImageView) findViewById(R.id.imageView);
        switch_agree = (Switch) findViewById(R.id.switch1);
        rGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        rd_jelly = (RadioButton) findViewById(R.id.radioButton_jelly);
        rd_kit = (RadioButton) findViewById(R.id.radioButton_kitkat);
        rd_lolli = (RadioButton) findViewById(R.id.radioButton_lolli);
        linear1 = (LinearLayout) findViewById(R.id.linear1);

        switch_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_agree.isChecked() == true) {
                    linear1.setVisibility(View.VISIBLE);
                } else {
                    linear1.setVisibility(View.INVISIBLE);
                }
            }
        });

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_jelly:
                        i_image.setImageResource(R.drawable.jellybean_);
                        break;
                    case R.id.radioButton_kitkat:
                        i_image.setImageResource(R.drawable.kitkat_);
                        break;
                    case R.id.radioButton_lolli:
                        i_image.setImageResource(R.drawable.lollipop_);
                        break;
                }
            }
        });
    }

    public void OnClick(View v){
        switch(v.getId()){
            case R.id.button_exit:
                finish();
                break;
            case R.id.button_restart:
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
