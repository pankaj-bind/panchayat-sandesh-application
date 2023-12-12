package com.example.panchayatsandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void certificate(View view) {
        Intent intent = new Intent(getApplicationContext(), CertificateActivity.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }

    public void report(View view) {
        Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
        startActivity(intent);
    }

    public void issue(View view) {
        Intent intent = new Intent(getApplicationContext(), IssueActivity.class);
        startActivity(intent);
    }

    public void notification(View view) {
        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        startActivity(intent);
    }
}