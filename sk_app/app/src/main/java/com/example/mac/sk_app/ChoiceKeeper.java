package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

//보호자를 선택했을 때 보호자 선택을 확인하는 액티비티
public class ChoiceKeeper extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_keeper);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, InputKeeper.class);
        intent.putExtra("androidID",getIntent().getStringExtra("androidID"));
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, ChoiceRole.class);
        intent.putExtra("androidID",getIntent().getStringExtra("androidID"));
        startActivity(intent);

        finish();
    }
}

