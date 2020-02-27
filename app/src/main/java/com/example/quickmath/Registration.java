package com.example.quickmath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {

    EditText regEmail;
    EditText regPass;
    EditText regFName;
    EditText regLName;
    EditText regAge;
    Button regBtn;

    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        regEmail = findViewById(R.id.eTEmail);
        regPass = findViewById(R.id.eTPWD);
        regFName = findViewById(R.id.eTFN);
        regLName = findViewById(R.id.eTLN);
        regAge = findViewById(R.id.eTDate);
        regBtn = findViewById(R.id.rButton);


        regBtn.setOnClickListener(this::register);

    }

    private void register(View view){

        String firstName = regFName.getText().toString().trim();
        String lastName = regLName.getText().toString().trim();
        String password = regPass.getText().toString().trim();
        String email = regEmail.getText().toString().trim();
        int age = Integer.parseInt(regAge.getText().toString().trim());


        if(firstName.isEmpty()|| firstName.length() > 12 ){

            Toast.makeText(Registration.this, "Invalid Input, name too short or too long", Toast.LENGTH_SHORT).show();

        }

        if(lastName.isEmpty() || lastName.length() > 12 ){

            Toast.makeText(Registration.this, "Invalid Input, name too short or too long", Toast.LENGTH_SHORT).show();

        }


        if(email.isEmpty()){
            Toast.makeText(Registration.this, "Invalid Input, Email Syntax Incorrect", Toast.LENGTH_SHORT).show();


        }

        if(password.isEmpty() || password.length() > 16){
            Toast.makeText(Registration.this, "Invalid Input, Password must be between 8 - 16 characters", Toast.LENGTH_SHORT).show();


        }

        if(age == 0){
            Toast.makeText(Registration.this, "Invalid Input, Date of Birth must be between 8 characters in numerical format", Toast.LENGTH_SHORT).show();


        }

        else {

            User newStudent  = new User(firstName, lastName, email ,password, "Student");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Users").add(newStudent);

            Intent intent;
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);


        }

    }

}
