package com.example.panchayatsandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IssueActivity extends AppCompatActivity {
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);


    }

    private void dialNumber(String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(dialIntent);
    }

    private void sendEmail(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + emailAddress));
        startActivity(emailIntent);
    }

    private void openWhatsApp(String phoneNumber) {
        String url = "https://api.whatsapp.com/send?phone=" + phoneNumber;
        Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
        whatsappIntent.setData(Uri.parse(url));
        startActivity(whatsappIntent);
    }

    public void dialButton(View view) {
        dialNumber("9506608157");
    }

    public void emailButton(View view) {
        sendEmail("panchayatsandesh@gmail.com");
    }

    public void whatsappButton(View view) {
        openWhatsApp("9506608157");
    }
}