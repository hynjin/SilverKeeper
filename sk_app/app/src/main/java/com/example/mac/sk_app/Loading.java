package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

//로딩 화면
public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        LoadingActivity();
    }
    private void LoadingActivity() {
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(Loading.this, ChoiceRole.class));  //3초 동안 화면에 나타난 후에 역할 선택 액티비티로 이동
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);       //3초 동안 화면에 나타냄
    }
}
