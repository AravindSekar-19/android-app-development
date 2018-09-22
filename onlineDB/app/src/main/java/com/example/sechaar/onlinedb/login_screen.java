package com.example.sechaar.onlinedb;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class login_screen extends AppCompatActivity {


    private static final int SELECT_PHOTO = 100;
    CollapsingToolbarLayout scroll_bar_picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        scroll_bar_picture = (CollapsingToolbarLayout)findViewById(R.id.collape_id);
        Uri imageUri = getIntent().getData();


    }
}
