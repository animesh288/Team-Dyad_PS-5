package com.android.meandme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sp=getApplication().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                boolean b = sp.getBoolean("key_name", false);
                if(b==true){
                    Intent i=new Intent(MainActivity.this,HomeActivity.class);

                    startActivity(i);

                    finish();

                }
                else{
                    Intent i=new Intent(MainActivity.this,LoginActivity.class);

                    startActivity(i);

                    finish();
                }
            }
        }, 1200);

    }
}