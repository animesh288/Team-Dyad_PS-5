
  

# ME & ME ( Depression Score, Health Score, Addiction Score, Depression )

  

## GOAL

Statistically finding out Depression Percentage, Means Converting a classification problem to a sort of Regression problem by using some statistical measures and corelation between feature


## DATASET
  
https://wwwn.cdc.gov/nchs/nhanes/default.aspx 

## DESCRIPTION
  
Me&ME is Android App which provides different 5 Scores maily focused on Mentality Level of Student to it's parents and teachers, Our App help Parent's to know about their child, They get analysis of it. On the basis of the Depression Score, We also provide some necessary Task to student's on behalf of it's predicted score which help him to tackle the situation Mentally.

We also have added a Gamified approach (Parent's approve the tasks donw by student's and after that he get's point) and Streak Count, Which mainly motivates student's to keep uding the app and build their mental strength

## WORK DONE

* Analyzed the data, searched for missing values, seperated categorical and continous data.
* Removed unnecessary columns
* Converting some column data to Meaning full numerical data
* Grouping and Dealing with lots of Object Type of Data
* Build Relation Between PolicyType with other Two Column
* Recreated BasePolicy Column and compared it with PolicyType and which bought some insightful results
* Provided Desired Values to Data which was available in Range.
* Performed Exploratory Data Analysis
* Split the dataset into Train and Test data.
* Trained model with the following algorithms:
    * Random Forest Classifier
    * Linear Regression
    * Lasso Regression
    * Ridge Regression
    * Logistic Regression
* Also evaluated the performance of the model with highest accuracy.


## LIBRARIES Requirment

click==8.0.4
colorama==0.4.4
Flask==2.0.3
imbalanced-learn==0.9.0
itsdangerous==2.1.0
Jinja2==3.0.3
joblib==1.1.0
MarkupSafe==2.1.0
numpy==1.22.2
pandas==1.4.1
python-dateutil==2.8.2
pytz==2021.3
scikit-learn==1.0.1
scipy==1.8.0
six==1.16.0
sklearn==0.0
threadpoolctl==3.1.0
Werkzeug==2.0.3
gunicorn


  

## ACCURACIES

| **Model**| Type | Score | 
| --- | --- | --- |
|1. Depression Status| RFC Classification | 0.837 |
|2. Health Score | Linear Regression | 0.939 % |
|3. Addiction Score| Ridge Regression | 0.836 % |
|4. Sleep & Work Score | Ridge Regression | 0.96 % |
|5. Depression Score | Ridge Regression | 0.810 % |



## CONTRIBUTED BY

*Team Dyad*
Codeutsava 5.0 
2nd Year NIT Raipur
