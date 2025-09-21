package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentTypeActivity extends AppCompatActivity {

    TextView txtDoctor;
    Button btnOnline, btnOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_type);

        txtDoctor = findViewById(R.id.txtDoctor);
        btnOnline = findViewById(R.id.btnOnline);
        btnOffline = findViewById(R.id.btnOffline);

        String doctor = getIntent().getStringExtra("doctor");
        txtDoctor.setText("Selected Doctor: " + doctor);

        btnOnline.setOnClickListener(v -> {
            Intent intent = new Intent(AppointmentTypeActivity.this, OnlineAppointmentActivity.class);
            intent.putExtra("doctor", doctor);
            startActivity(intent);
        });

        btnOffline.setOnClickListener(v -> {
            Intent intent = new Intent(AppointmentTypeActivity.this, OfflineAppointmentActivity.class);
            intent.putExtra("doctor", doctor);
            startActivity(intent);
        });
    }
}
