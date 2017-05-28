package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

//보호대상자를 선택했을 때 보호대상자 선택을 확인하는 액티비티
public class ChoiceSilver extends AppCompatActivity {
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_silver);
    }
    public void ok(View view) {

        intent = new Intent(this, AgreeData.class);
        intent.putExtra("androidID",getIntent().getStringExtra("androidID"));
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {
        intent = new Intent(this, ChoiceRole.class);
        intent.putExtra("androidID",getIntent().getStringExtra("androidID"));
        startActivity(intent);

        finish();
    }
}
