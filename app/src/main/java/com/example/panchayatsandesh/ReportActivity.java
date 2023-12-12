package com.example.panchayatsandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    public void backToHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void solar(View view) {
        String formUrl = "https://megsird.gov.in/publications/Solar_Street_Light.pdf";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(formUrl));
        startActivity(intent);
    }
}