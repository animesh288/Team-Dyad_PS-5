package com.android.meandme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.meandme.databinding.ActivityGraphBinding;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ramijemli.percentagechartview.callback.AdaptiveColorProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GraphActivity extends AppCompatActivity {

    ActivityGraphBinding binding;
    String url="https://meme-prediction-api.herokuapp.com/predict";

    HashMap<String,String> map;

    FirebaseAuth auth;
    DatabaseReference db;
    HashMap<String,String> m;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGraphBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference();
        map=new HashMap<>();
        m=new HashMap<>();


        init();
        fillMap();



        binding.getScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plotGraph();
            }
        });



    }

    private void plotGraph()
        {

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

                        int addiction=(int)(Double.parseDouble(data1));
                        if(addiction>=100) addiction=99;
                        if(addiction<=0) addiction=1;

                        int depression01=(int)(Double.parseDouble(data2));
                        int depression=(int)(Double.parseDouble(data3));
                        if(depression>=100) depression=99;
                        if(depression<=0) depression=1;


                        int health=(int)(Double.parseDouble(data4));
                        if(health>=100) health=99;
                        if(health<=0) health=1;


                        int sleep=(int)(Double.parseDouble(data5));
                        if(sleep>=100) sleep=99;
                        if(sleep<=0) sleep=1;



                        AdaptiveColorProvider colorProvider = new AdaptiveColorProvider() {
                            @Override
                            public int provideProgressColor(float progress) {
                                if (progress <= 33)
                                    return Color.GREEN;
                                else if (progress <= 67)
                                    return Color.rgb(255,165,0);
                                else return Color.RED;
                            }

                            @Override
                            public int provideBackgroundColor(float progress) {
                                return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .8f);
                            }

                            @Override
                            public int provideTextColor(float progress) {
                                return provideProgressColor(progress);
                            }

                            @Override
                            public int provideBackgroundBarColor(float progress) {
                                return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .5f);
                            }
                        };
                        AdaptiveColorProvider colorProvider2 = new AdaptiveColorProvider() {
                            @Override
                            public int provideProgressColor(float progress) {
                                if (progress <= 33)
                                    return Color.RED;
                                else if (progress <= 67)
                                    return Color.rgb(255,165,0);
                                else return Color.GREEN;
                            }

                            @Override
                            public int provideBackgroundColor(float progress) {
                                return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .8f);
                            }

                            @Override
                            public int provideTextColor(float progress) {
                                return provideProgressColor(progress);
                            }

                            @Override
                            public int provideBackgroundBarColor(float progress) {
                                return ColorUtils.blendARGB(provideProgressColor(progress), Color.BLACK, .5f);
                            }
                        };


                        binding.depressionScore.setAdaptiveColorProvider(colorProvider);
                        binding.addictionGraph.setAdaptiveColorProvider(colorProvider);
                        binding.healthGraph.setAdaptiveColorProvider(colorProvider2);
                        binding.sleepGraph.setAdaptiveColorProvider(colorProvider2);

                        binding.addictionGraph.setProgress(addiction,true);
                        binding.depressionScore.setProgress(depression,true);
                        binding.healthGraph.setProgress(health,true);
                        binding.sleepGraph.setProgress(sleep,true);


                        Log.i("animeshanimesh",data1+"\n"+data2+"\n"+data3+"\n"+data4+"\n"+data5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(GraphActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }){

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return map;
                }
            };
            RequestQueue queue= Volley.newRequestQueue(GraphActivity
                    .this);
            queue.add(stringRequest);
        }

    private void fillMap() {
        db.child("Students").child(auth.getCurrentUser().getUid()).child("Details").child("varied").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot x:snapshot.getChildren()){
                    map.put(x.getKey().toString(),x.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db.child("Students").child(auth.getCurrentUser().getUid()).child("Details").child("fixed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot x:snapshot.getChildren()){
                    map.put(x.getKey().toString(),x.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for (Map.Entry<String,String> entry : m.entrySet()){
            if(!map.containsKey(entry.getKey())) map.put(entry.getKey(),entry.getValue());
        }

        Log.i("animesh",map.toString());


    }
    private void init(){


        m.put("gender","0");
        m.put("age","51.5");
        m.put("education_level","2.5");
        m.put("household_size","4");
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
        m.put("BMI","28.7");
        m.put("pulse","71.28");
        m.put("trouble_sleeping_history","0");
        m.put("sleep_hours","7");
        m.put("vigorous_recreation","0");
        m.put("moderate_recreation","0");
        m.put("sedentary_time","660");
        m.put("vigorous_work","0");
        m.put("moderate_work","0");
        m.put("drinks_per_occasion","1");
        m.put("lifetime_alcohol_consumption","0");
        m.put("drinks_past_year","5");
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
        m.put("household_smokers","3");
        m.put("prescriptions_count","2.79");
    }

}