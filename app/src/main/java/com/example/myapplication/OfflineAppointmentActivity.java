package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OfflineAppointmentActivity extends AppCompatActivity {

    TextView txtLocation;
    EditText edtDate, edtTime;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_appointment);

        txtLocation = findViewById(R.id.txtLocation);
        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);
        btnConfirm = findViewById(R.id.btnConfirm);
        View btnBack = findViewById(R.id.btnBack);

        txtLocation.setText("Hospital Location: ABC Hospital, Main Street, City");

        btnConfirm.setOnClickListener(v -> {
            String date = edtDate.getText().toString().trim();
            String time = edtTime.getText().toString().trim();

            Toast.makeText(this,
                    "Offline Appointment booked at hospital on " + date + " at " + time,
                    Toast.LENGTH_LONG).show();
        });
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(OfflineAppointmentActivity.this, PatientDashboardActivity.class);
            // Optional: clear activity stack
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
