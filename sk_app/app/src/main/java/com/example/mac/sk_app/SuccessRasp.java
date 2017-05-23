package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

//라즈베리파이의 맥주소 인증 성공을 알리는 액티비티
public class SuccessRasp extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_rasp);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, ViewData.class);
        startActivity(intent);
        finish();
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, InputRasp.class);
        startActivity(intent);

        finish();
    }
}
