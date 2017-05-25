package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

//라즈베리파이의 맥주소를 입력하는 액티비티
public class InputRasp extends AppCompatActivity {

    String init="12345678";         //초기화한 라즈베리파이의 맥주소
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_rasp);
    }
    public void ok(View view) {

        EditText raspMac = (EditText) findViewById(R.id.raspMAC);
        String sraspMac=raspMac.getText().toString();

        if(sraspMac.equals(init)) {                            //라즈베리파이의 맥주소를 올바르게 입력했을 때 라즈베리 맥주소 인증 성공을 알리는 액티비티로 이동
            Intent intent = new Intent(this, SuccessRasp.class);
            startActivity(intent);
            finish();
        }
        else {                                                  //라즈베리파이의 맥주소를 잘못 입력했을 때 맥주소 입력 오류를 알리는 액티비티로 이동
            Intent intent = new Intent(this, ErrorRasp.class);
            startActivity(intent);
            finish();
        }
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, AgreeData.class);
        startActivity(intent);

        finish();
    }


}