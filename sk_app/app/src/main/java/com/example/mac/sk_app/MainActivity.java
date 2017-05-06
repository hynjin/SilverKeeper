package com.example.mac.sk_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;


public class MainActivity extends AppCompatActivity{

    Button cnt;
    TextView text;
    EditText silver;
    EditText heart;
    EditText step;
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cnt = (Button) findViewById(R.id.cnt);
        text = (TextView) findViewById(R.id.log);
        silver = (EditText) findViewById(R.id.silverID);
        heart = (EditText) findViewById(R.id.heartRate);
        step = (EditText) findViewById(R.id.stepCnt);
        send = (Button) findViewById(R.id.send); //button to send


        cnt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                text.append("try to connect...\n");

                AppClient appClient = new AppClient();
                appClient.execute();

                text.append("success to connect...\n");
            }
        });

        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                silver.setText("");
                heart.setText("");
                step.setText("");
            }
        });
    }

}







