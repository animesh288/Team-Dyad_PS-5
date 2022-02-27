package com.android.meandme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.meandme.databinding.ActivitySignupBinding;
import com.android.meandme.fragments.GuardianLogin;
import com.android.meandme.fragments.StudentLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private StudentLogin studentFrag;
    private FragmentTransaction sFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference();

        studentFrag=new StudentLogin();

        sFragmentTransaction=getSupportFragmentManager().beginTransaction();
        sFragmentTransaction.replace(R.id.cardview,studentFrag);
        sFragmentTransaction.commit();

//        binding.gBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.gBtn.setBackgroundColor(Color.rgb(93, 27, 158));
//                binding.sBtn.setBackgroundColor(Color.rgb(251, 188, 255));
//                gFragmentTransaction=getSupportFragmentManager().beginTransaction();
//                gFragmentTransaction.replace(R.id.cardview,gaurdianFrag);
//                gFragmentTransaction.commit();
//            }
//        });
//        binding.sBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.sBtn.setBackgroundColor(Color.rgb(93, 27, 158));
//                binding.gBtn.setBackgroundColor(Color.rgb(251, 188, 255));
//                sFragmentTransaction=getSupportFragmentManager().beginTransaction();
//                sFragmentTransaction.replace(R.id.cardview,studentFrag);
//                sFragmentTransaction.commit();
//            }
//        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
        });


    }
}