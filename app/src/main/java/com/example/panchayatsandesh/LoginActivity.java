package com.example.panchayatsandesh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    TextView forgotpassword, signup;
    Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgotpassword = findViewById(R.id.forgotpassword);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Check if the user is already logged in
        if (mAuth.getCurrentUser() != null) {
            // User is already logged in, redirect to MainActivity
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    public void login(View view) {
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            final String Email_data = email.getText().toString();
            final String Password_data = password.getText().toString();

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

            Query check_email = databaseReference.orderByChild("email").equalTo(Email_data);
            check_email.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() > 0) {
                        // User with the provided email exists

                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            String password_check = userSnapshot.child("password").getValue(String.class);

                            if (password_check.equals(Password_data)) {
                                // Correct password, login successful

                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent2);
                                finish();
                            } else {
                                // Incorrect password
                                Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        // User does not exist
                        Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LoginActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
