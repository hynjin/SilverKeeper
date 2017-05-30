package com.example.mac.sk_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class ViewStreamingActivity extends AppCompatActivity {
    Button returnBtn;
    String keeperID,raspIP;
    Context context;
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_streaming);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        context=this;
        raspIP=getIntent().getStringExtra("raspIP");
        keeperID=getIntent().getStringExtra("keeperID");
        returnBtn=(Button)findViewById(R.id.returnViewKData);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ViewKdata.class);
                intent.putExtra("keeperID",keeperID);
                startActivity(intent);
                finish();

            }
        });
        web=(WebView)findViewById(R.id.viewStreaming);
        web.setWebViewClient(new WebViewClient());
        WebSettings setting=web.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setBuiltInZoomControls(true);
        web.loadUrl("http://"+raspIP);

        System.out.println("raspIP:"+raspIP);
    }

}
