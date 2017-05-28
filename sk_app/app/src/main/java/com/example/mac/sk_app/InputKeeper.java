package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InputKeeper extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_keeper);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, ErrorIdentity.class);
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, RejectKeeper.class);
        startActivity(intent);

        finish();
    }

}