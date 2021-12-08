package com.work.practice_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.login);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        TextView sign = findViewById(R.id.sign);
        TextView sign_up = findViewById(R.id.signUp);
        auth = FirebaseAuth.getInstance();

        sign_up.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, sign_up.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(txt_email.isEmpty()){
                    email.setError("Fill field");
                    email.requestFocus();
                }else if(txt_password.isEmpty()){
                    password.setError("fill field");
                    password.requestFocus();
                }else{
                    signIn(txt_email,txt_password);
                }
            }
        });



    }

    private void signIn(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener(){
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Home.class));
            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}