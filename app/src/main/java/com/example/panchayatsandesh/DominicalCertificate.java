package com.example.panchayatsandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class DominicalCertificate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_certificate);
    }

    public void fillform(View view) {
        // Open the Google Form link in a web browser
        String formUrl = "https://docs.google.com/forms/d/e/1FAIpQLSc62HDUkJ3-7bLeunaYCOcV02difI42mJPZFi5ntr1BhhTPwg/viewform?usp=sf_link";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(formUrl));
        startActivity(intent);
    }

    public void download(View view) {
        // Add download functionality if needed
    }

    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), CertificateActivity.class);
        startActivity(intent);
        finish();
    }
}
