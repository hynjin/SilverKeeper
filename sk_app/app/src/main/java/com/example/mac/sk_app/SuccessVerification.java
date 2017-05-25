package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 차민광01027370165 on 2017-05-23.
 */

//키퍼가 인증을 완료해서 실버와의 연결을 성공했음을 알리는 액티비티
public class SuccessVerification extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_verification);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, ViewKdata.class);
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, InputKeeper.class);
        startActivity(intent);

        finish();
    }
}
