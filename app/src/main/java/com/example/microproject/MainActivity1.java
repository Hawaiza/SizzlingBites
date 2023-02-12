package com.example.microproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity1 extends AppCompatActivity {
    public static UsersDBHelper db;
    public String userMobile;
    EditText email, password;
    public String pswd;
    Button login;
    String emailAddress;
    ToggleButton admin_but, user_but;
    public String phone;
    TextView forgotpass;
    ImageButton back1;

    private Boolean validateEmail(EditText email) {
        emailAddress = email.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        email = (EditText) findViewById(R.id.et1);
        password = (EditText) findViewById(R.id.et2);
        login = (Button) findViewById(R.id.button);
        forgotpass = (TextView) findViewById(R.id.forgotpass);
        back1 = (ImageButton)findViewById(R.id.back1);



        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lv = new Intent(MainActivity1.this,LoginsignupActivity.class);
                startActivity(lv);
            }
        });

        forgotpass.setVisibility(View.INVISIBLE);

        //if (admin_but.isChecked()==false){

        // }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean a = validateEmail(email);
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity1.this, "Enter Required Data!", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().contains(" ")) {
                    Toast.makeText(MainActivity1.this, "Email Contains Spaces!", Toast.LENGTH_SHORT).show();
                } else if (a != true) {
                    Toast.makeText(MainActivity1.this, "Enter Valid Email Address! ", Toast.LENGTH_SHORT).show();
                } else if (password.getText().length() < 8) {
                    Toast.makeText(MainActivity1.this, "Password cannot be less than 8 characters", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().contains(" ")) {
                    Toast.makeText(MainActivity1.this, "Password Contains Spaces!", Toast.LENGTH_SHORT).show();
                } else {
                    forgotpass.setVisibility(View.INVISIBLE);

                    db = new UsersDBHelper(MainActivity1.this);
                    Cursor cursor = db.GetUsersLogin(email.getText().toString());
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        userMobile = (cursor.getString(2));
                        phone = "+91" + userMobile;
                        pswd = (cursor.getString(5));

                        forgotpass.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sendSMS(phone, pswd);
                            }
                        });

                        if (password.getText().toString().equals(pswd)) {
                            Toast.makeText(MainActivity1.this, "Login Successful! ", Toast.LENGTH_SHORT).show();
                            email.setText("");
                            password.setText("");

                                Intent userHome = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(userHome);

                        } else {
                            Toast.makeText(MainActivity1.this, "Login Denied! ", Toast.LENGTH_SHORT).show();
                            forgotpass.setVisibility(View.VISIBLE);
                        }


                    }
                }
            }
        });
    }



    public void sendSMS(String phone, String pswd) {
        SmsManager smsManager = SmsManager.getDefault();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, 1);
            } else {
                smsManager.sendTextMessage(phone, null, "Your Sizzling Bites password is \n" + pswd + "\nDo not share this with anyone.", null, null);
                Toast t = Toast.makeText(getApplicationContext(), "An SMS has been sent to your Registered Mobile No.", Toast.LENGTH_LONG);
                t.show();
            }
        }
    }
}