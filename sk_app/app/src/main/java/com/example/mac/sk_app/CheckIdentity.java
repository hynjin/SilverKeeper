package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

//인증번호 확인을 위한 액티비티
public class CheckIdentity extends AppCompatActivity {
    String silverID;
    String identifyNumber;
    TextView idNumView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_identity);
        silverID=getIntent().getStringExtra("silverID");
        identifyNumber=getIntent().getStringExtra("identifyNumber");
        idNumView=(TextView)findViewById(R.id.textView6);
        idNumView.setText(identifyNumber);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, ViewData.class);
        intent.putExtra("silverID",silverID);
        intent.putExtra("identifyNumber",identifyNumber);
        startActivity(intent);

        finish();
    }

}
