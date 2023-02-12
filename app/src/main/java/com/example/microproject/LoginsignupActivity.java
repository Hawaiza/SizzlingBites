package com.example.microproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginsignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsignup);

        Button bt1= (Button) findViewById(R.id.btn);
        Button bt2= (Button) findViewById(R.id.btn1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(LoginsignupActivity.this, MainActivity1.class);
                startActivity(login);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(LoginsignupActivity.this, Add_Users.class);
                startActivity(signup);
                finish();
            }
        });

    }
}