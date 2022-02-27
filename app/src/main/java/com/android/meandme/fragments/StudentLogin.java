package com.android.meandme.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.meandme.HomeActivity;
import com.android.meandme.R;
import com.android.meandme.RegistrationActivity;
import com.android.meandme.databinding.FragmentStudentLoginBinding;
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

public class StudentLogin extends Fragment {

    FirebaseAuth auth;
    DatabaseReference db;
    Button signup;
    EditText password,cpassword,email,name;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_student_login, container, false);

        auth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference();
        signup=view.findViewById(R.id.signup_btn);
        password=view.findViewById(R.id.password);
        cpassword=view.findViewById(R.id.con_password);
        email=view.findViewById(R.id.email);
        name=view.findViewById(R.id.name);

        progressDialog=new ProgressDialog(getActivity());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordt=password.getText().toString();
                String cpasswordt=cpassword.getText().toString();
                String namet=name.getText().toString();
                String emailt=email.getText().toString();

                if(!passwordt.equals(cpasswordt)){
                    Toast.makeText(getActivity(), "passwords dont match", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(emailt) || TextUtils.isEmpty(namet) || TextUtils.isEmpty(passwordt) || TextUtils.isEmpty(cpasswordt)) {
                    Toast.makeText(getActivity(), "Empty credentials", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(emailt,passwordt,namet);
                }
            }
        });

        return view;
    }
    private void registerUser(String emailTxt, String passwordTxt,String name) {
        auth.createUserWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.show(getActivity(),"","signing in",true);
                    createUser(name,emailTxt,passwordTxt);
                }else{
                    try
                    {
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException weakPassword)
                    {
                        Toast.makeText(getActivity(), "password too weak", Toast.LENGTH_SHORT).show();
                    }
                    catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                    {
                        Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                    catch (FirebaseAuthUserCollisionException existEmail)
                    {
                        Toast.makeText(getActivity(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void createUser(String name, String email, String password) {
        HashMap<String,String > map=new HashMap<>();

        map.put("Name",name);
        map.put("Email",email);
        map.put("Id",auth.getCurrentUser().getUid());
        map.put("IsParent","no");
        map.put("Streak","0");

        db.child("Students").child(auth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("key_name", true);
                editor.commit();
                progressDialog.dismiss();
                startActivity(new Intent(getActivity(), RegistrationActivity.class));
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }
}