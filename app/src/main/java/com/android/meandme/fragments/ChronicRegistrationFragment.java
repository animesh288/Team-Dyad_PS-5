package com.android.meandme.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.android.meandme.GraphActivity;
import com.android.meandme.HomeActivity;
import com.android.meandme.R;
import com.android.meandme.RegistrationActivity;
import com.android.meandme.databinding.FragmentChronicRegistrationBinding;
import com.android.meandme.databinding.FragmentGeneralRegistrationBinding;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ChronicRegistrationFragment extends Fragment {

    FragmentChronicRegistrationBinding binding;
    String []diseases={"asthma","heart_failure","blood transfusion","anemia","angina","heart_disease","Bone/Joint problem",
    "stroke","bronchitis","emphysema","hay_fever","liver_condition","thyroid","walking equipment","cancer","healthcare equipment",
    "arthritis","Back/Neck problem","limitations","heart_attack"};

    String cancerTypes[]={"None","Colon","Other","Breast","Liver","Bladder","Lung","Melanoma","Skin Other","Mouth","Skin Non Melanoma",
    "Prostate","Cervical","Thyroid","Lymphoma","Uterine","Brain","Stomach","Rectal","Testicular","Kidney","Esophageal",
    "Larynx","Ovarian","Blood","Bone","Leukemia","Gallbladder","Pancreatic","Soft Tissue","Nervous System"};

    String arthritisTypes[]={"Missing","Osteoarthritis","Other","Rheumatoid","Psoriatic"};

    HashMap<String,String  > map;

    DatabaseReference db;
    FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentChronicRegistrationBinding.inflate(getLayoutInflater(),container,false);
        View view=binding.getRoot();

        map= RegistrationActivity.map;
        db= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();

        ArrayAdapter<String > cancer=new ArrayAdapter<>(getActivity(),R.layout.dropbox_list_item,cancerTypes);
        ArrayAdapter<String > arthritis=new ArrayAdapter<>(getActivity(),R.layout.dropbox_list_item,arthritisTypes);
        binding.c1t.setAdapter(cancer);
        binding.c2t.setAdapter(cancer);
        binding.c3t.setAdapter(cancer);
        binding.at.setAdapter(arthritis);

        binding.c1t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("first_cancer_type",position+"");
            }
        });

        binding.c2t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("second_cancer_type",position+"");
            }
        });

        binding.c3t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("third_cancer_type",position+"");
            }
        });
        binding.at.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.put("arthritis_type",position+"");
            }
        });

        binding.cocainePerMonth.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("cocaine_per_month",value+"");
            }
        });
        binding.daysQuitSmoking.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("days_quit_smoking",value+"");
            }
        });
        binding.cocainePerMonth.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("cocaine_per_month",value+"");
            }
        });
        binding.heroninePerMonth.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("heronine_per_month",value+"");
            }
        });
        binding.householdSmokers.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("household_smokers",value+"");
            }
        });
        binding.methPerMonth.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("meth_per_month",value+"");
            }
        });
        binding.marijuanaPerMonth.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                map.put("marijuana_per_month",value+"");
            }
        });








        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                map.put("health_problem_Diabetes",binding.healthProblemDiabetes.isChecked()?"1":"0");
                map.put("asthma",binding.asthma.isChecked()?1+"":0+"");
                map.put("anemia",binding.anemia.isChecked()?1+"":0+"");
                map.put("heart_disease",binding.heartDisease.isChecked()?1+"":0+"");
                map.put("angina",binding.angina.isChecked()?1+"":0+"");
                map.put("heart_attack",binding.heartAttack.isChecked()?1+"":0+"");
                map.put("stroke",binding.stroke.isChecked()?1+"":0+"");
                map.put("emphysema",binding.emphysema.isChecked()?1+"":0+"");
                map.put("bronchitis",binding.bronchitis.isChecked()?1+"":0+"");
                map.put("liver_condition",binding.liverCondition.isChecked()?1+"":0+"");
                map.put("thyroid_problem",binding.thyroid.isChecked()?1+"":0+"");
                map.put("hay_fever",binding.hayFever.isChecked()?1+"":0+"");
                map.put("health_problem_Bone_or_Joint",binding.BoneJointProblem.isChecked()?1+"":0+"");
                map.put("health_problem_Back_or_Neck",binding.BackNeckProblem.isChecked()?1+"":0+"");
                map.put("health_problem_Arthritis",binding.arthritis.isChecked()?1+"":0+"");
                map.put("health_problem_Cancer",binding.cancer.isChecked()?1+"":0+"");
                map.put("cancer",binding.cancer.isChecked()?1+"":0+"");
                map.put("health_problem_Stroke",binding.stroke.isChecked()?1+"":0+"");
                map.put("heart_failure",binding.heartFailure.isChecked()?1+"":0+"");
                map.put("blood_transfusion",binding.bloodTransfusion.isChecked()?1+"":0+"");
                map.put("health_problem_Blood_Pressure",binding.healthProblemBloodPressure.isChecked()?1+"":0+"");
                map.put("health_problem_Mental_Retardation",binding.healthProblemMentalRetardation.isChecked()?1+"":0+"");
                map.put("health_problem_Hearing",binding.healthProblemHearing.isChecked()?1+"":0+"");
                map.put("health_problem_Heart",binding.heartDisease.isChecked()?1+"":0+"");
                map.put("health_problem_Vision",binding.healthProblemVision.isChecked()?1+"":0+"");
                map.put("health_problem_Birth_Defect",binding.healthProblemBirthDefect.isChecked()?1+"":0+"");
                map.put("health_problem_Senility",binding.healthProblemSenility.isChecked()?1+"":0+"");
                map.put("health_problem_Other_Developmental",binding.healthProblemOtherDevelopmental.isChecked()?1+"":0+"");
                map.put("health_problem_Other_Injury",binding.healthProblemOtherInjury.isChecked()?1+"":0+"");
                map.put("health_problem_Breathing",binding.healthProblemBreathing.isChecked()?1+"":0+"");
                map.put("health_problem_Other_Impairment",binding.healthProblemOtherImpairment.isChecked()?1+"":0+"");
                map.put("health_problem_Weight",binding.healthProblemWeight.isChecked()?1+"":0+"");
                map.put("bronchitis_currently",binding.bronchitisCurrently.isChecked()?1+"":0+"");
                map.put("liver_condition_currently",binding.liverConditionCurrently.isChecked()?1+"":0+"");

                db.child("Students").child(auth.getCurrentUser().getUid()).child("Details").child("fixed").setValue(map);

                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            }
        });

        return  view;

    }
}