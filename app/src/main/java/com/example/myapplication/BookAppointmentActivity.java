package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText edtDisease, edtSymptoms;
    Button btnFindDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        edtDisease = findViewById(R.id.edtDisease);
        edtSymptoms = findViewById(R.id.edtSymptoms);
        btnFindDoctors = findViewById(R.id.btnFindDoctors);

        btnFindDoctors.setOnClickListener(v -> {
            String disease = edtDisease.getText().toString().trim();
            String symptoms = edtSymptoms.getText().toString().trim();

            if (disease.isEmpty()) {
                Toast.makeText(this, "Please enter disease/problem", Toast.LENGTH_SHORT).show();
                return;
            }

            // For prototype: create a new appointment (dummy doctor/time)
            try {
                SharedPreferences prefs = getSharedPreferences("Appointments", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                // Get existing appointments
                String existing = prefs.getString("appointments_list", null);
                JSONArray appointmentsArray;
                if (existing != null) {
                    appointmentsArray = new JSONArray(existing);
                } else {
                    appointmentsArray = new JSONArray();
                }

                // Create a new dummy appointment based on input
                JSONObject newApp = new JSONObject();
                newApp.put("doctor", "Dr. Assigned");  // In real app, assign doctor from list
                newApp.put("date", "2025-09-25");      // For demo, fixed date
                newApp.put("time", "11:00 AM");        // For demo, fixed time
                newApp.put("disease", disease);
                newApp.put("symptoms", symptoms);

                // Add to array
                appointmentsArray.put(newApp);

                // Save back to SharedPreferences
                editor.putString("appointments_list", appointmentsArray.toString());
                editor.apply();

                Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error booking appointment", Toast.LENGTH_SHORT).show();
            }

            // Optionally, open DoctorListActivity
            Intent intent = new Intent(BookAppointmentActivity.this, DoctorListActivity.class);
            intent.putExtra("disease", disease);
            intent.putExtra("symptoms", symptoms);
            startActivity(intent);
        });
    }
}
