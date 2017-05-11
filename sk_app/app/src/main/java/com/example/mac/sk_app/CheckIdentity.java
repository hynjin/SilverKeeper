package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CheckIdentity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_identity);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, ViewData.class);
        startActivity(intent);

        finish();
    }
}