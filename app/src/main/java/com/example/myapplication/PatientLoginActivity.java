package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientLoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            if(email.equals("patient@example.com") && password.equals("1234")) {
                Toast.makeText(this, "Patient Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PatientLoginActivity.this, PatientDashboardActivity.class));
                finish(); // optional: closes login screen
            } else {
                Toast.makeText(this, "Invalid Patient Credentials", Toast.LENGTH_SHORT).show();
            }

        });
    }
}