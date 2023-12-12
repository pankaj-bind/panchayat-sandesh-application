package com.example.panchayatsandesh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay for 3 seconds and then open LoginActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to start LoginActivity
                Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(loginIntent);

                // Close the SplashActivity
                finish();
            }
        }, 3000); // 3000 milliseconds (3 seconds)
    }
}
