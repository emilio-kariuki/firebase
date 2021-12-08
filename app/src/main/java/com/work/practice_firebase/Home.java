package com.work.practice_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logOut = findViewById(R.id.logOut);
        auth = FirebaseAuth.getInstance();
        MediaPlayer mp = MediaPlayer.create(this,R.raw.click);

        logOut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mp.start();
                auth.signOut();
                startActivity(new Intent(Home.this,MainActivity.class));
                finish();
            }
        });
    }
}