package com.example.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edit_url;
    Button bnt_go, bnt_previous;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_url = (EditText) findViewById(R.id.editText_url);
        bnt_go = (Button) findViewById(R.id.button_move);
        bnt_previous = (Button) findViewById(R.id.button_previous);
        web = (WebView) findViewById(R.id.webView);
        edit_url.setSingleLine();

        web.setWebViewClient(new CookWebViewClient());
        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);

        bnt_go.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                String url = edit_url.getText().toString();
                if(!url.startsWith("http://"))
                    url = "http://" + url;
                web.loadUrl(url);
                edit_url.setText(url);
            }
        }); 

        bnt_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.goBack();
            }
        });
    }

    class CookWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            edit_url.setText(url);
        }
    }

}
