from flask import Flask, request, jsonify
import numpy as np
import pickle

m1_depression01 = pickle.load(open('m1_depression01.pkl', 'rb'))
m2_Health_score = pickle.load(open('m2_Health_score.pkl', 'rb'))
m3_addiction_score = pickle.load(open('m3_addiction_score.pkl', 'rb'))
m4_sleep_work_score = pickle.load(open('m4_sleep_work_score.pkl', 'rb'))
m5_depression_per = pickle.load(open('m5_depression_per.pkl', 'rb'))

app = Flask(__name__)

@app.route('/')
def index():
    return "Me&Me started1"


@app.route('/predict', methods=['POST'])
def predict():
    gender = request.form.get('gender')
    age = request.form.get('age')
    education_level = request.form.get('education_level')
    household_size = request.form.get('household_size')
    household_income = request.form.get('household_income')
    asthma = request.form.get('asthma')
    anemia = request.form.get('anemia')
    ever_overweight = request.form.get('ever_overweight')
    blood_transfusion = request.form.get('blood_transfusion')
    heart_failure = request.form.get('heart_failure')
    heart_disease = request.form.get('heart_disease')
    angina = request.form.get('angina')
    heart_attack = request.form.get('heart_attack')
    stroke = request.form.get('stroke')
    emphysema = request.form.get('emphysema')
    bronchitis = request.form.get('bronchitis')
    liver_condition = request.form.get('liver_condition')
    thyroid_problem = request.form.get('thyroid_problem')
    bronchitis_currently = request.form.get('bronchitis_currently')
    liver_condition_currently = request.form.get('liver_condition_currently')
    thyroid_problem_currently = request.form.get('thyroid_problem_currently')
    cancer = request.form.get('cancer')
    first_cancer_type = request.form.get('first_cancer_type')
    second_cancer_type = request.form.get('second_cancer_type')
    third_cancer_type = request.form.get('third_cancer_type')
    hay_fever = request.form.get('hay_fever')
    arthritis_type = request.form.get('arthritis_type')
    BMI = request.form.get('BMI')
    pulse = request.form.get('pulse')
    trouble_sleeping_history = request.form.get('trouble_sleeping_history')
    sleep_hours = request.form.get('sleep_hours')
    vigorous_recreation = request.form.get('vigorous_recreation')
    moderate_recreation = request.form.get('moderate_recreation')
    sedentary_time = request.form.get('sedentary_time')
    vigorous_work = request.form.get('vigorous_work')
    moderate_work = request.form.get('moderate_work')
    drinks_per_occasion = request.form.get('drinks_per_occasion')
    lifetime_alcohol_consumption = request.form.get('lifetime_alcohol_consumption')
    drinks_past_year = request.form.get('drinks_past_year')
    cant_work = request.form.get('cant_work')
    limited_work = request.form.get('limited_work')
    memory_problems = request.form.get('memory_problems')
    health_problem_Other_Impairment = request.form.get('health_problem_Other_Impairment')
    health_problem_Bone_or_Joint = request.form.get('health_problem_Bone_or_Joint')
    health_problem_Weight = request.form.get('health_problem_Weight')
    health_problem_Back_or_Neck = request.form.get('health_problem_Back_or_Neck')
    health_problem_Arthritis = request.form.get('health_problem_Arthritis')
    health_problem_Cancer = request.form.get('health_problem_Cancer')
    health_problem_Other_Injury = request.form.get('health_problem_Other_Injury')
    health_problem_Breathing = request.form.get('health_problem_Breathing')
    health_problem_Stroke = request.form.get('health_problem_Stroke')
    health_problem_Blood_Pressure = request.form.get('health_problem_Blood_Pressure')
    health_problem_Mental_Retardation = request.form.get('health_problem_Mental_Retardation')
    health_problem_Hearing = request.form.get('health_problem_Hearing')
    health_problem_Heart = request.form.get('health_problem_Heart')
    health_problem_Vision = request.form.get('health_problem_Vision')
    health_problem_Diabetes = request.form.get('health_problem_Diabetes')
    health_problem_Birth_Defect = request.form.get('health_problem_Birth_Defect')
    health_problem_Senility = request.form.get('health_problem_Senility')
    health_problem_Other_Developmental = request.form.get('health_problem_Other_Developmental')
    marijuana_use = request.form.get('marijuana_use')
    marijuana_per_month = request.form.get('marijuana_per_month')
    cocaine_use = request.form.get('cocaine_use')
    cocaine_number_uses = request.form.get('cocaine_number_uses')
    cocaine_per_month = request.form.get('cocaine_per_month')
    heroine_use = request.form.get('heroine_use')
    heronine_per_month = request.form.get('heronine_per_month')
    meth_use = request.form.get('meth_use')
    meth_number_uses = request.form.get('meth_number_uses')
    meth_per_month = request.form.get('meth_per_month')
    inject_drugs = request.form.get('inject_drugs')
    rehab_program = request.form.get('rehab_program')
    start_smoking_age = request.form.get('start_smoking_age')
    current_smoker = request.form.get('current_smoker')
    previous_cigarettes_per_day = request.form.get('previous_cigarettes_per_day')
    current_cigarettes_per_day = request.form.get('current_cigarettes_per_day')
    days_quit_smoking = request.form.get('days_quit_smoking')
    household_smokers = request.form.get('household_smokers')
    prescriptions_count = request.form.get('prescriptions_count')

    input_query1 = np.array([[gender, age, education_level, household_size, household_income, asthma, anemia, ever_overweight, blood_transfusion, heart_failure, heart_disease, angina, heart_attack, stroke, emphysema, bronchitis, liver_condition, thyroid_problem, bronchitis_currently, liver_condition_currently, thyroid_problem_currently, cancer, first_cancer_type, second_cancer_type, third_cancer_type, hay_fever, arthritis_type, BMI, pulse, trouble_sleeping_history, sleep_hours, vigorous_recreation, moderate_recreation, sedentary_time, vigorous_work, moderate_work, drinks_per_occasion, lifetime_alcohol_consumption, drinks_past_year, cant_work, limited_work, memory_problems, health_problem_Other_Impairment, health_problem_Bone_or_Joint, health_problem_Weight, health_problem_Back_or_Neck, health_problem_Arthritis, health_problem_Cancer, health_problem_Other_Injury, health_problem_Breathing, health_problem_Stroke, health_problem_Blood_Pressure, health_problem_Mental_Retardation, health_problem_Hearing, health_problem_Heart, health_problem_Vision, health_problem_Diabetes, health_problem_Birth_Defect, health_problem_Senility, health_problem_Other_Developmental, marijuana_use, marijuana_per_month, cocaine_use, cocaine_number_uses, cocaine_per_month, heroine_use, heronine_per_month, meth_use, meth_number_uses, meth_per_month, inject_drugs, rehab_program, start_smoking_age, current_smoker, previous_cigarettes_per_day, current_cigarettes_per_day, days_quit_smoking, household_smokers, prescriptions_count]])

    depression = m1_depression01.predict(input_query1)[0]
    hs = m2_Health_score.predict(input_query1)[0]
    ads = m3_addiction_score.predict(input_query1)[0]
    ss = m4_sleep_work_score.predict(input_query1)[0]
    ds = m5_depression_per.predict(input_query1)[0]


    return jsonify({'depression01': str(depression),
                    'health_score': str(hs),
                    'addiction_score': str(ads),
                    'sleep_score': str(ss),
                    'depression_score': str(ds)})


if __name__ == '__main__':
    app.run(debug=True)
