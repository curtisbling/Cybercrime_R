package com.example.loginactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText inputEmail, inputPass;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference databaseReference;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.report) {
                finish();
                return true;
            } else if (itemId == R.id.home) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

                return true;
            }
            else if(itemId==R.id.settings){
                startActivity(new Intent(LoginActivity.this, settingsActivity.class));
                finish();



            }

            return false;
        });

//variable declaration
        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);
        btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginactivity-4b42a-default-rtdb.firebaseio.com/");

        TextView button = findViewById(R.id.txtSignUp);
        TextView btnn = findViewById(R.id.ForgotPassword);
        TextView login = findViewById(R.id.btnLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String Email = inputEmail.getText().toString();
        String Password = inputPass.getText().toString();

        if (!Email.matches(emailPattern)) {
            inputEmail.setError("Enter Correct Email");
        } else if (Password.isEmpty() || Password.length() < 4) {
            inputPass.setError("Invalid Password");
        } else {
            progressDialog.setMessage("Logging in...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            // Hash the entered password using the same method as during registration
            String hashedPassword = hashPassword(Password);

            if (hashedPassword == null) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Error occurred while hashing the password", Toast.LENGTH_SHORT).show();
            } else {
                // Fetch the hashed password from the database
                databaseReference.child("users").orderByChild("Email").equalTo(Email)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                progressDialog.dismiss();
                                if (snapshot.exists()) {
                                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                        String storedHashedPassword = userSnapshot.child("Password").getValue(String.class);

                                        // Compare the hashed passwords
                                        if (storedHashedPassword.equals(hashedPassword)) {
                                            // Successful login
                                            sendUserToNextActivity();
                                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Password mismatch
                                            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    // User not found
                                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                progressDialog.dismiss();
                                // Handle onCancelled if needed
                            }
                        });
            }
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] hashedBytes = digest.digest(passwordBytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                stringBuilder.append(String.format("%02x", hashedByte));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this, reportcrime.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
