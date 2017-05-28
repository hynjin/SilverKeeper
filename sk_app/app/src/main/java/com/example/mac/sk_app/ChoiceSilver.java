package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChoiceSilver extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_silver);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, AgreeData.class);
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, ChoiceRole.class);
        startActivity(intent);

        finish();
    }
}
