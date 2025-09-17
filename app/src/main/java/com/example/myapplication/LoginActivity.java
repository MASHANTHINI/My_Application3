package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnDoctorLogin, btnPatientLogin;
    TextView tvGoToSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDoctorLogin = findViewById(R.id.btnDoctorLogin);
        btnPatientLogin = findViewById(R.id.btnPatientLogin);
        tvGoToSignup = findViewById(R.id.tvGoToSignup);

        btnDoctorLogin.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, DoctorLoginActivity.class)));

        btnPatientLogin.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, PatientLoginActivity.class)));

        // Go to Signup page if new user
        tvGoToSignup.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
    }
}
