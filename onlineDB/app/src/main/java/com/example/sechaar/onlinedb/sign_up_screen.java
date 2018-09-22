package com.example.sechaar.onlinedb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class sign_up_screen extends AppCompatActivity  {

    Bitmap bitmap;
    CircleImageView views;
    EditText us,pass,confpass,email;
    CardView clickToLogin;
    private static final int SELECT_PHOTO = 1;
   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        us = (EditText)findViewById(R.id.userNames);
        pass = (EditText)findViewById(R.id.passwordz);
        confpass = (EditText)findViewById(R.id.con) ;
        email = (EditText)findViewById(R.id.e_mail) ;

        views = (CircleImageView)findViewById(R.id.profile_images) ;
        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickAImage();
            }
        });

        clickToLogin = (CardView)findViewById(R.id.card);
        clickToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(us.getText().toString().length() < 1) {
                us.setError("Must be filled");
                us.requestFocus();
                }
                if(pass.getText().toString().length() < 1 ){
                    pass.setError("Must be filled");
                    pass.requestFocus();
                }
                if( pass.getText().toString().length() <5){
                    pass.setError("Your password must be atleast 6 characters in length");
                }
                if(confpass.getText().toString().equals(pass.getText().toString())){
                    confpass.setError("password and confirm password didn't match");
                }else {
                   uploadImage();
                    Toast.makeText(getApplicationContext(),"Profile picture saved",Toast.LENGTH_SHORT).show();
                    Intent ins  = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(ins);
                }
            }
        });
    }

    //     Pick an Image From SD Card Gallery
    public void pickAImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    views.setImageURI(selectedImage);

                }
        }
    }

    private void uploadImage(){
        StringRequest sr = new StringRequest(Request.Method.POST, "https://geopolitical-charac.000webhostapp.com/phpfile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent ins = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ins);
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();

                String userName = us.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String emailid = email.getText().toString();
                map.put("username",userName);
              //  map.put("password",password);
                map.put("mobile",emailid);
                map.put("img",imageToString(bitmap));

                return map;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }

    private  String imageToString(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }










}
