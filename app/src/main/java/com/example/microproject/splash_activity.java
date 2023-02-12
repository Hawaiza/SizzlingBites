package com.example.microproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class splash_activity extends AppCompatActivity {

    ImageView logo,splashImg;
    TextView name;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        logo = (ImageView) findViewById(R.id.logo);
        splashImg = (ImageView)findViewById(R.id.splashImg);
        name =(TextView) findViewById(R.id.name);

//        logo.animate().translationY(1400).setDuration(1000).setStartDelay(3000);
//        name.animate().translationY(1420).setDuration(1000).setStartDelay(3000);
//        splashImg.animate().translationX(-1000).setDuration(1000).setStartDelay(3000);

        top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation_logo);
        bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);

        splashImg.setAnimation(bottom);
        logo.setAnimation(top);
        name.setAnimation(top);


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(splash_activity.this, LoginsignupActivity.class);
               startActivity(intent);
               finish();
           }
       }, 3600);
    }
}