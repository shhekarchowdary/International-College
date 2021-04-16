package com.arr.internationalcollege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText mUserName,mPassword,mStudentName;
    Button mLogin;

    public static String studentLogged;
    HashMap<String,String> mStudentsData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.etxtUserNAme);
        mPassword = findViewById(R.id.etxtPassword);
        mStudentName = findViewById(R.id.etxtStudentName);
        mLogin = findViewById(R.id.btnLogin);

        fillData();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mUserName.getText().toString().trim().isEmpty()){
                    if(!mPassword.getText().toString().trim().isEmpty()){
                        if(!mStudentName.getText().toString().trim().isEmpty()){
                            String userName = mUserName.getText().toString().trim();
                            String password = mPassword.getText().toString().trim();
                            String studentName = mStudentName.getText().toString().trim();
                            for(String usrName:mStudentsData.keySet()){
                                if(userName.equals(usrName)){
                                    if(password.equals(mStudentsData.get(usrName))){
                                        studentLogged = studentName;
                                        Intent i = new Intent(getBaseContext(),MainActivity2.class);
                                        startActivity(i);
                                        break;
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Enter Student Name",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Enter User Name",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void fillData(){
        mStudentsData.put("Student1","Student1@123");
        mStudentsData.put("Student2","Student2@123");
        mStudentsData.put("Student3","Student3@123");
        mStudentsData.put("Student4","Student4@123");
        mStudentsData.put("Student5","Student5@123");
        mStudentsData.put("test","test");
    }
}