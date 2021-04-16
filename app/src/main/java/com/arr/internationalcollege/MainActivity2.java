package com.arr.internationalcollege;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView mWelcome,mTotalFee,mTotalHours;
    RadioButton mGraduation,mUnderGraduation;
    Spinner mCourseSpinner;
    CheckBox mAccommodation,mMedicalInsurance;
    Button mAddCourse;

    ArrayList<Course> mCourses = new ArrayList<>();
    ArrayList<String> mCourseNames = new ArrayList<>();

    double extraFee,totalFee;
    int maxHours,totalHours;
    Course courseSel;
    ArrayList<Course> mAddedCourses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mWelcome = findViewById(R.id.txtStudentName);
        mTotalFee = findViewById(R.id.txtTotalFee);
        mTotalHours = findViewById(R.id.txtTotalHours);
        mGraduation = findViewById(R.id.radioButton1);
        mUnderGraduation = findViewById(R.id.radioButton2);
        mCourseSpinner = findViewById(R.id.spinnerCourse);
        mAccommodation = findViewById(R.id.checkBox1);
        mMedicalInsurance = findViewById(R.id.checkBox2);
        mAddCourse = findViewById(R.id.btnAddCourse);

        mWelcome.setText("Welcome "+MainActivity.studentLogged+"...");

        fillData();
        for(Course course:mCourses)
            mCourseNames.add(course.getName());

        mGraduation.setOnClickListener(new RadioButtonEvents());
        mUnderGraduation.setOnClickListener(new RadioButtonEvents());

        ArrayAdapter courseAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,mCourseNames);
        mCourseSpinner.setAdapter(courseAdapter);
        mCourseSpinner.setOnItemSelectedListener(this);

        mAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAddedCourses.size() >= 1){
                    int repeatCourse = 0;
                    for(Course course:mAddedCourses){
                        if(course.getName().equalsIgnoreCase(courseSel.getName())){
                            Toast.makeText(getApplicationContext(),"Course Already Added in List",Toast.LENGTH_SHORT).show();
                            repeatCourse = 1;
                            break;
                        }
                    }
                    if(repeatCourse == 0){
                        double tot = totalHours + courseSel.getHours();
                        if(tot <= maxHours){
                            Toast.makeText(getApplicationContext(),"You Can add this Course",Toast.LENGTH_SHORT).show();
                            mAddedCourses.add(courseSel);
                            for(Course course:mAddedCourses){
                                totalFee += course.getFee();
                                totalHours += course.getHours();
                            }
                            mTotalFee.setText(String.format("%.2f",totalFee));
                            mTotalHours.setText(String.valueOf(totalHours));
                        }else{
                            Toast.makeText(getApplicationContext(),"Maximum Hours Exceeded",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    mAddedCourses.add(courseSel);

                    totalFee = courseSel.getFee();
                    totalHours = courseSel.getHours();

                    mTotalFee.setText(String.format("%.2f",totalFee));
                    mTotalHours.setText(String.valueOf(totalHours));
                }
            }
        });

        mAccommodation.setOnCheckedChangeListener(new  CheckBoxEvents());
        mMedicalInsurance.setOnCheckedChangeListener(new CheckBoxEvents());

    }

    public void fillData(){
        mCourses.add(new Course("Java",1300.0,6));
        mCourses.add(new Course("Swift",1500.0,5));
        mCourses.add(new Course("iOS",1350.0,5));
        mCourses.add(new Course("Android",1400.0,7));
        mCourses.add(new Course("Database",1000.0,4));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String courseSelected = mCourseNames.get(i);
        for(Course course:mCourses){
            if(course.getName().equalsIgnoreCase(courseSelected)){
                courseSel = course;
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        courseSel = mCourses.get(0);
    }

    private class RadioButtonEvents implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int hours =0;
            int id = view.getId();
            if (id == R.id.radioButton1) {
                hours += 21;
                mAddedCourses.clear();
                totalFee = 0.0;
                totalHours = 0;
                mTotalFee.setText(String.format("%.2f",totalFee));
                mTotalHours.setText(String.valueOf(totalHours));
                Toast.makeText(getApplicationContext(), String.valueOf(hours), Toast.LENGTH_SHORT).show();
            } else if (id == R.id.radioButton2) {
                hours += 19;
                mAddedCourses.clear();
                totalFee = 0.0;
                totalHours = 0;
                mTotalFee.setText(String.format("%.2f",totalFee));
                mTotalHours.setText(String.valueOf(totalHours));
                Toast.makeText(getApplicationContext(), String.valueOf(hours), Toast.LENGTH_SHORT).show();
            } else {
                throw new IllegalStateException("Unexpected value: " + view.getId());
            }
            maxHours = hours;
        }
    }

    private class CheckBoxEvents implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int id = compoundButton.getId();
            if (id == R.id.checkBox1) {
                if(compoundButton.isChecked()){
                    totalFee += 1000;
                }else {
                    totalFee -= 1000;
                }
            } else if (id == R.id.checkBox2) {
                if(compoundButton.isChecked()){
                    totalFee += 700;
                }else {
                    totalFee -= 700;
                }
            }
            mTotalFee.setText(String.format("%.2f",totalFee));
        }
    }
}