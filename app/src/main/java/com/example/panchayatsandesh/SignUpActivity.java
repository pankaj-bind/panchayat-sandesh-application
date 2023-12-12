package com.example.panchayatsandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    ImageView userimage;
    EditText fullname, email, phone, password, aadhar;
    Button submit;
    TextView signin;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userimage = findViewById(R.id.userimage);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        aadhar = findViewById(R.id.aadhar);
        submit = findViewById(R.id.submit);
        signin = findViewById(R.id.signin);

        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("datauser");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void submit(View view) {
        String Fullname = fullname.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        String Password = password.getText().toString();
        String Aadhar = aadhar.getText().toString();

        if (TextUtils.isEmpty(Fullname) || TextUtils.isEmpty(Email) ||
                TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Password) ||
                TextUtils.isEmpty(Aadhar)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(Email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
        } else if (!isValidPhoneNumber(Phone)) {
            Toast.makeText(this, "Please enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
        } else if (!isValidAadharNumber(Aadhar)) {
            Toast.makeText(this, "Please enter a valid 12-digit Aadhar number", Toast.LENGTH_SHORT).show();
        } else if (!isValidPassword(Password)) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
        } else {
            // Use the email as the key in Firebase
            String key = Email.replace(".", "_");

            storingdata storingdata = new storingdata(Fullname, Email, Phone, Password, Aadhar);

            reference.child(key).setValue(storingdata);

            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        return Patterns.PHONE.matcher(phone).matches() && phone.length() == 10;
    }

    private boolean isValidAadharNumber(String aadhar) {
        return aadhar.length() == 12;
    }

    private boolean isValidPassword(String password) {
        // Password must contain at least one uppercase letter, one lowercase letter, one digit, and one symbol
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}