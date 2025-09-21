package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientDashboardActivity extends AppCompatActivity {

    Button btnProgress, btnAppointment, btnNearby, btnVideoCall, btnDemoVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        btnProgress = findViewById(R.id.btnProgress);
        btnAppointment = findViewById(R.id.btnAppointment);
        btnNearby = findViewById(R.id.btnNearby);
        btnVideoCall = findViewById(R.id.btnVideoCall);
        btnDemoVideos = findViewById(R.id.btnDemoVideos);

        btnProgress.setOnClickListener(v ->
                Toast.makeText(this, "Progress section clicked", Toast.LENGTH_SHORT).show());

        btnAppointment.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookAppointmentActivity.class);
            startActivity(intent);
        });

        btnNearby.setOnClickListener(v ->
                Toast.makeText(this, "Nearby Centres clicked", Toast.LENGTH_SHORT).show());

        btnVideoCall.setOnClickListener(v -> {
            // Open Scheduled Calls page
            Intent intent = new Intent(this, ScheduledCallsActivity.class);
            startActivity(intent);
        });

        btnDemoVideos.setOnClickListener(v ->
                Toast.makeText(this, "Demo Videos clicked", Toast.LENGTH_SHORT).show());
    }
}
