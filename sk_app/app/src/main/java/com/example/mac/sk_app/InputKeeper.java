package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

//인증번호와 키퍼의 이름을 입력하는 액티비티
public class InputKeeper extends AppCompatActivity {
    String initIdentifyNumber="1234";         //초기화된 인증번호
    String initKeeperName="Jackson";         //초기화된 보호대상자의 이름
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_keeper);
    }
    public void ok(View view) {
        EditText identifyNumber = (EditText) findViewById(R.id.identifyNumber); //인증번호를 레이아웃에서 불러오기
        String sidentifyNumber=identifyNumber.getText().toString();
        EditText keeperName = (EditText) findViewById(R.id.keeperName); //키퍼이름을 레이아웃에서 불러오기
        String skeeperName=keeperName.getText().toString();

        if(!sidentifyNumber.equals(initIdentifyNumber)) {               //인증번호를 잘못 입력했을 때 인증번호 오류를 알리는 액티비티로 이동
            Intent intent = new Intent(this, ErrorIdentity.class);
            startActivity(intent);
            finish();
        } else if(!skeeperName.equals(initKeeperName)) {                //키퍼이름을 잘못 입력했을 때 보호자가 연결을 거부했다고 알리는 액티비티로 이동
            Intent intent = new Intent(this, RejectKeeper.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, SuccessVerification.class);
            startActivity(intent);
            finish();

        }

    }
    public void cancel(View view) {
        Intent intent = new Intent(this, ChoiceKeeper.class);
        startActivity(intent);

        finish();
    }

}