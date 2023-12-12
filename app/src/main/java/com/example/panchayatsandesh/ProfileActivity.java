package com.example.panchayatsandesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



    public class ProfileActivity extends AppCompatActivity {

        TextView fullname, email, phone;
        Button logout;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference reference;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            fullname = findViewById(R.id.name);
            email = findViewById(R.id.email);
            phone = findViewById(R.id.phone);
            logout = findViewById(R.id.logout);

            mAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();
            reference = firebaseDatabase.getReference("datauser");

            // Retrieve user data from Firebase
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                String userEmail = currentUser.getEmail();
                queryUserData(userEmail);
            } else {
                // Handle the case where the user is not logged in
                Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
                // You may want to redirect the user to the login screen or handle this situation appropriately.
            }

            // Logout button click listener
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        private void queryUserData(String userEmail) {
            // Query the database to get user data based on email
            reference.orderByChild("email").equalTo(userEmail)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // User with the provided email exists
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                    String fullName = userSnapshot.child("Fullname").getValue(String.class);
                                    String userPhone = userSnapshot.child("phone").getValue(String.class);

                                    // Set the retrieved data to TextViews
                                    fullname.setText(fullName);
                                    email.setText(userEmail);
                                    phone.setText(userPhone);
                                }
                            } else {
                                // Handle the case where the user data is not found
                                Toast.makeText(ProfileActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle the database error
                            Toast.makeText(ProfileActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        public void backToHome(View view) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }