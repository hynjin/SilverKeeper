package com.example.mac.sk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChoiceRole extends AppCompatActivity {

    RadioGroup role;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_role);
    }

    public void ok(View view) {
        role=(RadioGroup)findViewById(R.id.role);
        RadioButton radioButton = (RadioButton) findViewById(role.getCheckedRadioButtonId());
        String str_Role = radioButton.getText().toString();
        String str_silver= "보호대상자";
        String str_keeper= "보호자";
        Toast.makeText(getApplicationContext(), str_Role+" 선택됨", Toast.LENGTH_SHORT).show();
        if(str_silver.equals(str_Role)) {
            Intent intent = new Intent(this, ChoiceSilver.class);
            startActivity(intent);
        }else if(str_keeper.equals(str_Role)){
            Intent intent = new Intent(this, ChoiceKeeper.class);
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
