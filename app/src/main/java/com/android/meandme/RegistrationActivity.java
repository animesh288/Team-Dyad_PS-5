package com.android.meandme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.android.meandme.databinding.ActivityRegistrationBinding;
import com.android.meandme.fragments.ChronicRegistrationFragment;
import com.android.meandme.fragments.GeneralRegistrationFragment;
import com.android.meandme.fragments.StudentLogin;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    public static HashMap<String ,String > map=new HashMap<>();

    private GeneralRegistrationFragment generalRegistrationFragment;
    private ChronicRegistrationFragment chronicRegistrationFragment;
    private FragmentTransaction generalTransaction,chronicTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        generalRegistrationFragment=new GeneralRegistrationFragment();
        generalTransaction=getSupportFragmentManager().beginTransaction();
        generalTransaction.replace(R.id.ll,generalRegistrationFragment);
        generalTransaction.commit();

    }
}