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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView sign_up = findViewById(R.id.sign_up);
        EditText email = findViewById(R.id.email_up);
        EditText password = findViewById(R.id.pass);
        TextView up = findViewById(R.id.sign_in);
        Button register = findViewById(R.id.Register);
        auth = FirebaseAuth.getInstance();

        up.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                startActivity(new Intent(sign_up.this, MainActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(txt_email.isEmpty()){
                    email.setError("fill field");
                    email.requestFocus();
                } else if(txt_password.isEmpty()){
                    password.setError("fill Field");
                    password.requestFocus();
                }
                else{
                    registerUser(txt_email,txt_password);
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(sign_up.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(sign_up.this,MainActivity.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(sign_up.this,"Registration Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}