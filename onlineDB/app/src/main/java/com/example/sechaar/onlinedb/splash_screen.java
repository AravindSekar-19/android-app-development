package com.example.sechaar.onlinedb;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash_screen extends AppCompatActivity {

    private static  int Splash_Time_Out = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ins = new Intent(getApplicationContext(), main_screen.class);
                startActivity(ins);
                finish();
            }
        },Splash_Time_Out);
    }
}
