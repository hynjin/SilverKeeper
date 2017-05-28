package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

//실버가 키퍼의 연결을 거부했음을 알리는 액티비티
public class RejectKeeper extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reject_keeper);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, InputKeeper.class);
        startActivity(intent);

        finish();
    }

}
