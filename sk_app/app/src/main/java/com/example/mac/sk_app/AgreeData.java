package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 차민광01027370165 on 2017-05-10.
 */
public class AgreeData extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agree_data);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, InputRasp.class);
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, ChoiceRole.class);
        startActivity(intent);

        finish();
    }
}
