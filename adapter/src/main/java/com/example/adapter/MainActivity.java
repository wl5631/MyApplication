package com.example.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GridView Movie Poster");

        final GridView gv = (GridView) findViewById(R.id.gridView);
        MyGridAdapter gAdapter= new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

    }

    public class MyGridAdapter extends BaseAdapter {

        Context context;

        public MyGridAdapter(Context c) {
            context = c;
        }
        String[] posterName = {"Sunny","Punch","The Host","Radio Star"," King and the clown","Maundy Thursday","The Prists","The Beauty Inside",
                "Sunny","Punch","The Host","Radio Star"," King and the clown","Maundy Thursday","The Prists","The Beauty Inside",
                "Sunny","Punch","The Host","Radio Star"," King and the clown","Maundy Thursday","The Prists","The Beauty Inside"};

        Integer[] posterID = {R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,
                R.drawable.poster6,R.drawable.poster5, R.drawable.poster7, R.drawable.poster9,
                R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,
                R.drawable.poster6,R.drawable.poster5, R.drawable.poster7, R.drawable.poster9,
                R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,
                R.drawable.poster6,R.drawable.poster5, R.drawable.poster7, R.drawable.poster9
             };

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View grid = getLayoutInflater().inflate(R.layout.gridview, null);
            ImageView image = (ImageView) grid.findViewById(R.id.imageView);
            TextView tv = (TextView) grid.findViewById(R.id.textView);
            image.setImageResource(posterID[position]);
            tv.setText(posterName[position]);

            final int pos = position;
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("Close", null);
                    dlg.show();
                }
            });

            return grid;
        }
    }
}
