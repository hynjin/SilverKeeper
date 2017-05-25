package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

//인증번호를 잘못 입력했음을 알리는 액티비티
public class ErrorIdentity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_identity);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, InputKeeper.class);
        startActivity(intent);
        finish();
    }

}
