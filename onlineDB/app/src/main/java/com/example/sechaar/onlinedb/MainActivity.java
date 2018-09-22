package com.example.sechaar.onlinedb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 1;
    ImageView profile_Picture_View;
    EditText editText,editText2;
    CardView cardViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_Picture_View = (ImageView)findViewById(R.id.profile_image) ;
        cardViewLogin = (CardView) findViewById(R.id.card_Login_View);
        editText = (EditText)findViewById(R.id.user_name);
        editText2 = (EditText)findViewById(R.id.password);
        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ins = new Intent(getApplicationContext(), login_screen.class);
                startActivity(ins);
            }
        });

//        SharedPreferences sharedPreferences = getSharedPreferences("tokens", Context.MODE_PRIVATE);
//        final String  values = sharedPreferences.getString("name", null);
//        Toast.makeText(getApplicationContext(),values,Toast.LENGTH_LONG).show();



//        cardViewLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),values,Toast.LENGTH_LONG).show();
//            }
//        });
//        cardViewLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userDefinedMethod();
//                Toast.makeText(getApplicationContext(),"values saved",Toast.LENGTH_LONG).show();
//            }
//        });
    }

     //Pick an Image From SD Card Gallery
public void pickAImage(View view) {
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
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                   // Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    profile_Picture_View.setImageURI(selectedImage);// To display selected image in image view

                    //sending image to another activity...
                    Intent ins  = new Intent();
                    ins.setData(selectedImage);
                      ins.putExtra("imageUri",selectedImage.toString());
                    //startActivity(ins);
                }
        }
    }
//
//
//
//
//    // Online DataBase Connectivity code
//
//    public void userDefinedMethod(){
//        StringRequest sr = new StringRequest(Request.Method.POST, "https://geopolitical-charac.000webhostapp.com/phpfile.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map = new HashMap<String, String>();
//
//                String us = editText.getText().toString();
//                String pass = editText2.getText().toString();
//                map.put("username",us);
//                map.put("password",pass);
//                return map;
//            }
//        };
//        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
//        rq.add(sr);
//    }

}
