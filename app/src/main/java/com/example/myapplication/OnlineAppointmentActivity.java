package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OnlineAppointmentActivity extends AppCompatActivity {

    EditText edtDate, edtTime;
    Button btnConfirm, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_appointment);

        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBack = findViewById(R.id.btnBack);

        btnConfirm.setOnClickListener(v -> {
            String date = edtDate.getText().toString().trim();
            String time = edtTime.getText().toString().trim();

            Toast.makeText(this,
                    "Online Video Call Scheduled on " + date + " at " + time,
                    Toast.LENGTH_LONG).show();
        });

        // Go back to Patient Dashboard
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(OnlineAppointmentActivity.this, PatientDashboardActivity.class);
            // Optional: clear activity stack
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
