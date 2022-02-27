package com.android.meandme.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.meandme.DailyCheckInActivity;
import com.android.meandme.R;
import com.android.meandme.databinding.FragmentGeneralRegistrationBinding;
import com.android.meandme.databinding.FragmentQuestionBinding;

import java.util.HashMap;

public class QuestionFragment extends Fragment {

    FragmentQuestionBinding binding;
    HashMap<String,Boolean> map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentQuestionBinding.inflate(getLayoutInflater(),container,false);

        View view=binding.getRoot();

        map=new HashMap<>();
        map.put("excited",false);
        map.put("tired",false);
        map.put("content",false);
        map.put("stressed",false);
        map.put("sad",false);
        map.put("angry",false);

        binding.angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("angry", map.get("angry")^true);
                if(map.get("angry")) binding.angry.setBackgroundColor(Color.WHITE);
                else binding.angry.setBackground(null);
            }
        });
        binding.excited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("excited", map.get("excited")^true);
                if(map.get("excited")) binding.excited.setBackgroundColor(Color.WHITE);
                else binding.excited.setBackground(null);
            }
        });
        binding.tired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("tired", map.get("tired")^true);
                if(map.get("tired")) binding.tired.setBackgroundColor(Color.WHITE);
                else binding.tired.setBackground(null);
            }
        });
        binding.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("content", map.get("content")^true);
                if(map.get("content")) binding.content.setBackgroundColor(Color.WHITE);
                else binding.content.setBackground(null);
            }
        });
        binding.stressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("stressed", map.get("stressed")^true);
                if(map.get("stressed")) binding.stressed.setBackgroundColor(Color.WHITE);
                else binding.stressed.setBackground(null);
            }
        });
        binding.sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("sad", map.get("sad")^true);
                if(map.get("sad")) binding.sad.setBackgroundColor(Color.WHITE);
                else binding.sad.setBackground(null);
            }
        });




        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DailyCheckInActivity.class));
            }
        });

        return view;

    }
}