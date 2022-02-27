package com.android.meandme.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.meandme.GraphActivity;
import com.android.meandme.HomeActivity;
import com.android.meandme.R;
import com.android.meandme.RegistrationActivity;
import com.android.meandme.databinding.FragmentGeneralRegistrationBinding;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class GeneralRegistrationFragment extends Fragment {

    FragmentGeneralRegistrationBinding binding;

    String sex[]={"Male","Female"};
    String education[]={"Primary School","High School","Diploma","Undergraduate","Graduate","Postgraduate / Master"};
    ChronicRegistrationFragment fragment;
    FragmentTransaction fragmentTransaction;
//    HashMap<String ,String > map;
    DatabaseReference db;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentGeneralRegistrationBinding.inflate(getLayoutInflater(),container,false);
        View view=binding.getRoot();

//        map= RegistrationActivity.map;
        db= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();


        ArrayAdapter<String> sexAdapter;
        ArrayAdapter<String> educationAdapter;

        sexAdapter=new ArrayAdapter<>(getActivity(),R.layout.dropbox_list_item,sex);
        educationAdapter=new ArrayAdapter<>(getActivity(),R.layout.dropbox_list_item,education);

        binding.sex.setAdapter(sexAdapter);
        binding.education.setAdapter(educationAdapter);


        binding.sex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RegistrationActivity.map.put("gender",position+"");
            }
        });
        binding.education.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RegistrationActivity.map.put("education_level",""+position);
            }
        });

        binding.prescriptionSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                RegistrationActivity.map.put("prescriptions_count",(int)value+"");
            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(binding.age.getText()) || TextUtils.isEmpty(binding.height.getText()) ||
                TextUtils.isEmpty(binding.weight.getText()) || TextUtils.isEmpty(binding.familySize.getText()) ||
                TextUtils.isEmpty(binding.familyIncome.getText()) || !RegistrationActivity.map.containsKey("gender") ||!RegistrationActivity.map.containsKey("education_level")){
                    Toast.makeText(getActivity(), "Enter details", Toast.LENGTH_SHORT).show();
                    return;
                }

                RegistrationActivity.map.put("age",getAge());
                RegistrationActivity.map.put("household_size",getSize());
                RegistrationActivity.map.put("household_income",getIncome());
                RegistrationActivity.map.put("BMI",getBmi());
                RegistrationActivity.map.put("trouble_sleeping_history",binding.troubleSleep.isChecked()?1+"":0+"");
                RegistrationActivity.map.put("ever_overweight",binding.overweight.isChecked()?1+"":0+"");
                RegistrationActivity.map.put("memory_problems",binding.memory.isChecked()?1+"":0+"");
                RegistrationActivity.map.put("lifetime_alcohol_consumption",binding.lifetimeAlcoholConsumption.isChecked()?1+"":-1+"");
                RegistrationActivity.map.put("cant_work",binding.cantWork.isChecked()?1+"":0+"");
                RegistrationActivity.map.put("limited_work",binding.limitedWork.isChecked()?1+"":0+"");

                if(!RegistrationActivity.map.containsKey("prescriptions_count")) RegistrationActivity.map.put("prescriptions_count","0");

                db.child("Students").child(auth.getCurrentUser().getUid()).child("Details").child("fixed").setValue(RegistrationActivity.map);

                if(binding.chronic.isChecked()) {
                    fragment = new ChronicRegistrationFragment();
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.ll, fragment);
                    fragmentTransaction.commit();
                }else{

                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                }
            }
        });


        return view;
    }

    private String getAge() {
        return binding.age.getText().toString();
    }

    private String getBmi() {
        int weight=Integer.parseInt(binding.weight.getText().toString());
        int height=Integer.parseInt(binding.height.getText().toString());
        height/=100;
        height*=height;

        if(height==0) return weight+"";

        return (weight/height)+"";
    }

    private String getSize() {
        int household_size=Integer.parseInt(binding.familySize.getText().toString());
        if(household_size<1) household_size=1;
        else if(household_size>7) household_size=7;
        return household_size+"";
    }
    private String getIncome(){
        int income=Integer.parseInt(binding.familyIncome.getText().toString());
        if(income<10000) return 0+"";
        if(income<20000) return 1+"";
        if(income<30000) return 2+"";
        if(income<40000) return 3+"";
        if(income<50000) return 4+"";
        if(income<60000) return 5+"";
        if(income<70000) return 6+"";
        if(income<80000) return 7+"";
        if(income<90000) return 8+"";
        if(income<100000) return 9+"";
        if(income<110000) return 10+"";
        if(income<120000) return 11+"";
        return ""+12;
    }
}