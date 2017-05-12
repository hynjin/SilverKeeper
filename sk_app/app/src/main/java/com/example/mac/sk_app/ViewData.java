package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.util.Log;
import java.sql.Date;

/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

public class ViewData extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);

        Button test = (Button) findViewById(R.id.test); //button to send


        test.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SilverVO silverData = new SilverVO();
                silverData.setHeartRate(11);
                silverData.setWalkCount(222);
                silverData.setIdentifyNumber(333);
                silverData.setCurrentTime(new Date(System.currentTimeMillis()));
                silverData.setCheckMiBand(Boolean.valueOf("true").booleanValue());
                String param = silverData.toString();
                        //"heartRate=123&walkCount=444&identifyNumber=6666&currentTime=2017-05-13&checkMiBand=true";

                Log.v("??",param);
                AppClient appClient = new AppClient();

                appClient.execute(param);

            }
        });

    }
    public void ok(View view) {
        Intent intent = new Intent(this, CheckIdentity.class);
        startActivity(intent);

        finish();
    }
}
