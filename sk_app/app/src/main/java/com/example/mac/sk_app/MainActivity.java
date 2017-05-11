/*package com.example.mac.sk_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;


public class MainActivity extends AppCompatActivity{

    TextView text;
    EditText silver;
    EditText heart;
    EditText step;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.log);
        silver = (EditText) findViewById(R.id.silverID);
        heart = (EditText) findViewById(R.id.heartRate);
        step = (EditText) findViewById(R.id.stepCnt);
        send = (Button) findViewById(R.id.send); //button to send


        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String param ="id=app&passwd=zzz&";
                param =  param +"silverID="+ silver.getText().toString()
                        +"&heartRate="+ heart.getText().toString()
                        +"&stepCnt="+ step.getText().toString();

                text.append("try to connect...\n");

                AppClient appClient = new AppClient();

                appClient.execute(param);
                Log.v("??",param);

                text.append("success to connect...\n");

                silver.setText("");
                heart.setText("");
                step.setText("");
            }
        });
    }

}




*/


