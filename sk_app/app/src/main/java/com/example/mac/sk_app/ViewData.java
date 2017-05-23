package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

//보호자가 생체정보를 보는 화면
public class ViewData extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, CheckIdentity.class);
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {             //처음으로 돌아가기
        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);

        finish();
    }
}
