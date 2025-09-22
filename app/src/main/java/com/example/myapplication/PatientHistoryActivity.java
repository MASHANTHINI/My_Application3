package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientHistoryActivity extends AppCompatActivity {

    EditText etPatientName, etAge, etMedicalHistory, etSymptoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        // Initialize EditTexts
        etPatientName = findViewById(R.id.etPatientName);
        etAge = findViewById(R.id.etAge);
        etMedicalHistory = findViewById(R.id.etMedicalHistory);
        etSymptoms = findViewById(R.id.etSymptoms);

        // 🔹 Static data for patient@example.com
        String loggedInEmail = "patient@example.com";

        if (loggedInEmail.equals("patient@example.com")) {
            etPatientName.setText("John Doe");
            etAge.setText("30");
            etMedicalHistory.setText("Diabetes, High BP");
            etSymptoms.setText("Fever, Cough, Weakness");
        } else {
            Toast.makeText(this, "No history found for this user", Toast.LENGTH_SHORT).show();
        }

        // Disable editing if you want read-only history
        etPatientName.setEnabled(false);
        etAge.setEnabled(false);
        etMedicalHistory.setEnabled(false);
        etSymptoms.setEnabled(false);
    }
}
