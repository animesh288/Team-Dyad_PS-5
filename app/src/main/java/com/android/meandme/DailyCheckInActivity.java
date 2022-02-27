package com.android.meandme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.meandme.databinding.ActivityDailyCheckInBinding;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DailyCheckInActivity extends AppCompatActivity {

    ActivityDailyCheckInBinding binding;
    String[] ans={"NO","YES"};
    String[] pulse={"Low","Normal","High"};
    HashMap<String ,String> map;

    DatabaseReference db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDailyCheckInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        map=new HashMap<>();
        db= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();

        ArrayAdapter<String> ansAdapter;
        ArrayAdapter<String> pulseAdapter;
        ansAdapter=new ArrayAdapter<>(this,R.layout.dropbox_list_item,ans);
        pulseAdapter=new ArrayAdapter<>(this,R.layout.dropbox_list_item,pulse);

        binding.pulse.setAdapter(pulseAdapter);
        binding.vrecreation.setAdapter(ansAdapter);
        binding.mrecreation.setAdapter(ansAdapter);

        binding.vrecreation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) map.put("vigorous_recreation",-1+"");
                else if(position==1) map.put("vigorous_recreation",1+"");
                else map.put("vigorous_recreation",0+"");
            }
        });
        binding.mrecreation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) map.put("moderate_recreation",-1+"");
                else if(position==1) map.put("moderate_recreation",1+"");
                else map.put("moderate_recreation",0+"");
            }
        });


        binding.pulse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pulse="";
                if(position==0) pulse="64";
                if(position==1) pulse="72";
                if(position==2) pulse="804";
                map.put("pulse",pulse);
            }
        });

        binding.sleepSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("sleep_hours",value+"");
            }
        });


        binding.sedentaryTime.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("sedentary_time",(int)value+"");
            }
        });


        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!map.containsKey("vigorous_recreation")) map.put("vigorous_recreation",0+"");
                if(!map.containsKey("moderate_recreation")) map.put("vigorous_recreation",0+"");
                if(!map.containsKey("sleep_hours")||!map.containsKey("sedentary_time")||!map.containsKey("pulse")){
                    Toast.makeText(DailyCheckInActivity.this, "Enter details", Toast.LENGTH_SHORT).show();
                    return;
                }

                map.put("vigorous_work",binding.vwork.isChecked()?"1":"0");
                map.put("moderate_work",binding.mwork.isChecked()?"1":"0");



                if(binding.sdd.isChecked()) {
                    Intent intent=new Intent(DailyCheckInActivity.this, AddictionActivity.class);
                    intent.putExtra("map",map);
                    startActivity(intent);
                    finish();
                }else{
                    map.put("marijuana_use","0");
                    map.put("cocaine_use","0");
                    map.put("heroine_use","0");
                    map.put("meth_use","0");
                    map.put("inject_drugs","0");
                    map.put("rehab_program","0");
                    if(!map.containsKey("cocaine_number_uses")) map.put("cocaine_number_uses","0");
                    if(!map.containsKey("meth_number_uses")) map.put("meth_number_uses","0");
                    map.put("current_smoker","1.5");
                    map.put("start_smoking_age","76");
                    if(!map.containsKey("previous_cigarettes_per_day")) map.put("previous_cigarettes_per_day","0");
                    if(!map.containsKey("current_cigarettes_per_day")) map.put("current_cigarettes_per_day","0");

                    if(!map.containsKey("drinks_per_occasion")) map.put("drinks_per_occasion","0");

                    db.child("Students").child(auth.getCurrentUser().getUid()).child("Details").child("varied").setValue(map);


                    startActivity(new Intent(DailyCheckInActivity.this, HomeActivity.class));
                    finish();
                }
            }
        });

    }
}