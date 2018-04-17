package com.example.dingu.smartcradle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


public class VideoStreamingScreen extends AppCompatActivity {
    WebView webView;
    Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_streaming);
        connectButton = findViewById(R.id.connect);
        webView = (WebView) findViewById(R.id.webview);
        final String ip = getIntent().getExtras().getString(MyConstants.CACHED_IP);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(ip);
                Toast.makeText(getApplicationContext(),"Connecting to "+ip,Toast.LENGTH_LONG).show();
            }
        });




    }
}
