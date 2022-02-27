package com.android.meandme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.meandme.databinding.ActivityHomeBinding;
import com.android.meandme.databinding.ActivityLoginBinding;
import com.android.meandme.fragments.ProfileFragment;
import com.android.meandme.fragments.QuestionFragment;
import com.android.meandme.fragments.TaskFragment;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    Fragment selectorFragment;
    DatabaseReference db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference();


        db.child("Students").child(auth.getCurrentUser().getUid()).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.greeting.setText("Hello, "+snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db.child("Students").child(auth.getCurrentUser().getUid()).child("Streak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.streak.setText("STREAK : "+snapshot.getValue().toString()+" DAYS");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        selectorFragment=new ProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectorFragment).commit();

        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_question:
                        startActivity(new Intent(HomeActivity.this,DailyCheckInActivity.class));
                        break;
                    case R.id.nav_profile:
                        selectorFragment=new ProfileFragment();
                        break;
                    case R.id.nav_task:
                        selectorFragment=new TaskFragment();
                        break;
                }
                if(selectorFragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectorFragment).commit();
                }
                return true;
            }
        });



    }
}