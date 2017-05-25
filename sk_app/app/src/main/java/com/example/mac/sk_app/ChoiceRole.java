package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//역할을 선택하는 액티비티
public class ChoiceRole extends AppCompatActivity {

    RadioGroup role;                                      //선택된 역할을 찾기위해 RadioGroup 변수 생성
    RadioButton silver, keeper;                         //실버와 키퍼를 구별하기 위한 RadioButton

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_role);
    }

    public void ok(View view) {
        role=(RadioGroup)findViewById(R.id.role);           //라디오그룹 객체 위젯에서  불러오기
        silver=(RadioButton) findViewById(R.id.silver);    //보호자 버튼을 위젯에서 불러오기
        keeper=(RadioButton) findViewById(R.id.keeper);    //보호대상자 버튼을 위젯에서 불러오기
        RadioButton choosedRole = (RadioButton) findViewById(role.getCheckedRadioButtonId());   //선택된 역할을 choosedRole에 저장하기
        String str_Role = choosedRole.getText().toString();     //선택된 역할의 문자열을 저장하기

        Toast.makeText(getApplicationContext(), str_Role+" 선택됨", Toast.LENGTH_SHORT).show();    //역할이 선택되었음을 토스트를 통해 화면에 나타냄
        if(choosedRole==silver) {
            Intent intent = new Intent(this, ChoiceSilver.class);       //보호자를 선택했을 때 보호자 선택확인을 위한 액티비티로 이동
            startActivity(intent);
        }else if(choosedRole==keeper){
            Intent intent = new Intent(this, ChoiceKeeper.class);       //보호대상자를 선택했을 때 보호대상자 선택확인을 위한 액티비티로 이동
            startActivity(intent);
        }

        finish();
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, ChoiceRole.class);
        startActivity(intent);
        finish();
    }
}

