package com.example.panchayatsandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CertificateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);
    }

    public void backToHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void caste(View view) {
        Intent intent = new Intent(getApplicationContext(), CasteCertificate.class);
        startActivity(intent);
        finish();
    }

    public void dominical(View view) {
        Intent intent = new Intent(getApplicationContext(), DominicalCertificate.class);
        startActivity(intent);
        finish();
    }

    public void income(View view) {
        Intent intent = new Intent(getApplicationContext(), IncomeCertificate.class);
        startActivity(intent);
        finish();
    }
}