package com.example.sechaar.onlinedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class main_screen extends AppCompatActivity {

    ImageView login,sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        login = (ImageView)findViewById(R.id.login_View);
        sign_up = (ImageView)findViewById(R.id.sign_up_View);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ins  = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ins);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ins = new Intent(getApplicationContext(), sign_up_screen.class);
                startActivity(ins);
            }
        });
    }
}
