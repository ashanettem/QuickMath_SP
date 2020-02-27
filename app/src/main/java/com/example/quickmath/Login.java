package com.example.quickmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button login, signup;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.eTUN);
        password = findViewById(R.id.eTPWD);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.btnReg);

        sp = getSharedPreferences(getString(R.string.SP_REG),0);

        login.setOnClickListener(view -> {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference usersDB = db.collection("Users");

            String user = email.getText().toString();
            String pass = password.getText().toString();




            usersDB.whereEqualTo("email", user).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                        User currentUser = documentSnapshot.toObject(User.class);

                        /*String email = currentUser.getEmail();
                        //String password = currentUser.getPassword();

                        if (email.equals(user) && password.equals(pass)){
                            if (currentUser.getRole().equals("Student")){
                                Intent intent = new Intent(view.getContext(),choices.class);
                                intent.putExtra("User" , email);
                                startActivity(intent);
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                            else if (currentUser.getRole().equals("Teacher")){
                                Intent intent = new Intent(view.getContext(),adminDash.class);
                                intent.putExtra("User" , email);
                                startActivity(intent);
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(Login.this, "Please Try Again", Toast.LENGTH_LONG).show();
                        }*/


                        Intent i = new Intent(view.getContext(),MainActivity.class);
                        startActivity(i);
                    }
                }
            });

        });

        signup.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), Registration.class);
            startActivity(i);
        });

    }


}
