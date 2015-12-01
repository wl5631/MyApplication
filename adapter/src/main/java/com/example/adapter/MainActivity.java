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


        Integer[] posterID = {R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,
                R.drawable.poster6,R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,
                R.drawable.poster6,R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,
                R.drawable.poster6,R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4,
                R.drawable.poster6};

        String[] posterName = {"Sunny", "wanduk","Monster","RadioStar","King's man"};

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
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);

            imageview.setImageResource(posterID[position]);


            final int pos = position;
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle("Big Poster");
                    //아이콘넣자
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("Close", null);
                    dlg.show();
                }
            });

            return imageview;
        }
    }
}
