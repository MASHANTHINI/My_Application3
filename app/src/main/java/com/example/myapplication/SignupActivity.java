package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    Button btnDoctorSignup, btnPatientSignup;
    TextView tvGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnDoctorSignup = findViewById(R.id.btnDoctorSignup);
        btnPatientSignup = findViewById(R.id.btnPatientSignup);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnDoctorSignup.setOnClickListener(v ->
                startActivity(new Intent(SignupActivity.this, DoctorSignupActivity.class)));

        btnPatientSignup.setOnClickListener(v ->
                startActivity(new Intent(SignupActivity.this, PatientSignupActivity.class)));

        // Go to Login page if already user
        tvGoToLogin.setOnClickListener(v ->
                startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }
}
