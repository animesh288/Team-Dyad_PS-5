package com.android.meandme;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddictionActivity extends AppCompatActivity {

    HashMap<String ,String > map;

    Button submit;
    CheckBox marijuana_use,cocaine_use,heroine_use,meth_use,inject_drugs,rehab_program;
    Slider marijuana_per_month,cocaine_number_uses,cocaine_per_month,heronine_per_month,meth_number_uses,meth_per_month,
            current_smoker,previous_cigarettes_per_day,current_cigarettes_per_day,days_quit_smoking,household_smokers,
            drinks_per_occasion,drinks_past_year;
    EditText start_smoking_age;

    DatabaseReference db;
    FirebaseAuth auth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.addiction_layout);

        map= (HashMap<String, String>) getIntent().getSerializableExtra("map");
        db= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();

        marijuana_use=findViewById(R.id.marijuana_use);
        cocaine_use=findViewById(R.id.cocaine_use);
        heroine_use=findViewById(R.id.heroine_use);
        meth_use=findViewById(R.id.meth_use);
        inject_drugs=findViewById(R.id.inject_drugs);
        rehab_program=findViewById(R.id.rehab_program);

        submit=findViewById(R.id.submit_btn);

        start_smoking_age=findViewById(R.id.start_smoking_age);

//        marijuana_per_month=findViewById(R.id.marijuana_per_month);
//        cocaine_per_month=findViewById(R.id.cocaine_per_month);
        cocaine_number_uses=findViewById(R.id.cocaine_number_uses);
//        heronine_per_month=findViewById(R.id.heronine_per_month);
        meth_number_uses=findViewById(R.id.meth_number_uses);
//        meth_per_month=findViewById(R.id.meth_per_month);
        previous_cigarettes_per_day=findViewById(R.id.previous_cigarettes_per_day);
        current_cigarettes_per_day=findViewById(R.id.current_cigarettes_per_day);
//        days_quit_smoking=findViewById(R.id.days_quit_smoking);
//        household_smokers=findViewById(R.id.household_smokers);
        drinks_per_occasion=findViewById(R.id.drinks_per_occasion);
//        drinks_past_year=findViewById(R.id.drinks_past_year);

        drinks_per_occasion.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("drinks_per_occasion",(int)value+"");
            }
        });

        previous_cigarettes_per_day.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("previous_cigarettes_per_day",(int)value+"");
            }
        });
        current_cigarettes_per_day
                .addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("current_cigarettes_per_day",(int)value+"");
            }
        });
        cocaine_number_uses.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("cocaine_number_uses",(int)value+"");
            }
        });
        meth_number_uses.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("meth_number_uses",(int)value+"");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("marijuana_use",marijuana_use.isChecked()?"1":"0");
                map.put("cocaine_use",cocaine_use.isChecked()?"1":"0");
                map.put("heroine_use",heroine_use.isChecked()?"1":"0");
                map.put("meth_use",meth_use.isChecked()?"1":"0");
                map.put("inject_drugs",inject_drugs.isChecked()?"1":"0");
                map.put("rehab_program",rehab_program.isChecked()?"1":"0");

                if(!map.containsKey("cocaine_number_uses")) map.put("cocaine_number_uses","0");
                if(!map.containsKey("meth_number_uses")) map.put("meth_number_uses","0");
                map.put("current_smoker","1.5");

                String sval=start_smoking_age.getText().toString();
                if((sval == null) || (sval.length()==0) || Integer.parseInt(sval)<=0 ||Integer.parseInt(sval)>76) map.put("start_smoking_age","76");
                else map.put("start_smoking_age",sval);

                if(!map.containsKey("previous_cigarettes_per_day")) map.put("previous_cigarettes_per_day","0");
                if(!map.containsKey("current_cigarettes_per_day")) map.put("current_cigarettes_per_day","0");

                if(!map.containsKey("drinks_per_occasion")) map.put("drinks_per_occasion","0");

                db.child("Students").child(auth.getCurrentUser().getUid()).child("Details").child("varied").setValue(map);

                startActivity(new Intent(AddictionActivity.this,HomeActivity.class));
                finish();
            }
        });










    }
}
