package com.android.meandme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.meandme.databinding.ActivityCallBinding;
import com.android.meandme.databinding.ActivityGraphBinding;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CallActivity extends AppCompatActivity {


    ActivityCallBinding binding;
    String url="https://meme-prediction-api.herokuapp.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HashMap<String ,String > m=new HashMap<>();
        m.put("gender","1");
        m.put("age","25");
        m.put("education_level","4");
        m.put("household_size","2");
        m.put("household_income","6");
        m.put("asthma","0");
        m.put("anemia","0");
        m.put("ever_overweight","0");
        m.put("blood_transfusion","0");
        m.put("heart_failure","0");
        m.put("heart_disease","0");
        m.put("angina","0");
        m.put("heart_attack","0");
        m.put("stroke","0");
        m.put("emphysema","0");
        m.put("bronchitis","0");
        m.put("liver_condition","0");
        m.put("thyroid_problem","0");
        m.put("bronchitis_currently","0");
        m.put("liver_condition_currently","0");
        m.put("thyroid_problem_currently","0");
        m.put("cancer","0");
        m.put("first_cancer_type","0");
        m.put("second_cancer_type","0");
        m.put("third_cancer_type","0");
        m.put("hay_fever","0");
        m.put("arthritis_type","0");
        m.put("BMI","19.64");
        m.put("pulse","92");
        m.put("trouble_sleeping_history","0");
        m.put("sleep_hours","8");
        m.put("vigorous_recreation","-1");
        m.put("moderate_recreation","-1");
        m.put("sedentary_time","240");
        m.put("vigorous_work","0");
        m.put("moderate_work","0");
        m.put("drinks_per_occasion","0");
        m.put("lifetime_alcohol_consumption","-1");
        m.put("drinks_past_year","0");
        m.put("cant_work","0");
        m.put("limited_work","0");
        m.put("memory_problems","0");
        m.put("health_problem_Other_Impairment","0");
        m.put("health_problem_Bone_or_Joint","0");
        m.put("health_problem_Weight","0");
        m.put("health_problem_Back_or_Neck","0");
        m.put("health_problem_Arthritis","0");
        m.put("health_problem_Cancer","0");
        m.put("health_problem_Other_Injury","0");
        m.put("health_problem_Breathing","0");
        m.put("health_problem_Stroke","0");
        m.put("health_problem_Blood_Pressure","0");
        m.put("health_problem_Mental_Retardation","0");
        m.put("health_problem_Hearing","0");
        m.put("health_problem_Heart","0");
        m.put("health_problem_Vision","0");
        m.put("health_problem_Diabetes","0");
        m.put("health_problem_Birth_Defect","0");
        m.put("health_problem_Senility","0");
        m.put("health_problem_Other_Developmental","0");
        m.put("marijuana_use","0");
        m.put("marijuana_per_month","0");
        m.put("cocaine_use","0");
        m.put("cocaine_number_uses","0");
        m.put("cocaine_per_month","0");
        m.put("heroine_use","0");
        m.put("heronine_per_month","0");
        m.put("meth_use","0");
        m.put("meth_number_uses","0");
        m.put("meth_per_month","0");
        m.put("inject_drugs","0");
        m.put("rehab_program","0");
        m.put("start_smoking_age","0");
        m.put("current_smoker","0");
        m.put("previous_cigarettes_per_day","0");
        m.put("current_cigarettes_per_day","0");
        m.put("days_quit_smoking","0");
        m.put("household_smokers","4");
        m.put("prescriptions_count","0");


        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String data1=jsonObject.getString("addiction_score");
                            String data2=jsonObject.getString("depression01");
                            String data3=jsonObject.getString("depression_score");
                            String data4=jsonObject.getString("health_score");
                            String data5=jsonObject.getString("sleep_score");
                            binding.txt.setText(data1+"\n"+data2+"\n"+data3+"\n"+data4+"\n"+data5);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CallActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        }){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return m;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(CallActivity
                .this);
                queue.add(stringRequest);
            }
        });
    }
}