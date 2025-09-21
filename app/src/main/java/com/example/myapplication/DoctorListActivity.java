package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity {

    TextView txtTitle;
    ListView listDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        txtTitle = findViewById(R.id.txtTitle);
        listDoctors = findViewById(R.id.listDoctors);

        String disease = getIntent().getStringExtra("disease");

        txtTitle.setText("Doctors for: " + disease);

        List<String> doctors = new ArrayList<>();

        switch (disease.toLowerCase()) {
            case "heart":
                doctors.add("Dr. Ramesh - Cardiologist");
                doctors.add("Dr. Priya - Cardiologist");
                break;
            case "diabetes":
                doctors.add("Dr. Meena - Endocrinologist");
                doctors.add("Dr. Ravi - Diabetes Specialist");
                break;
            default:
                doctors.add("Dr. Lakshmi - General Physician");
                doctors.add("Dr. Kumar - Family Doctor");
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                doctors
        );
        listDoctors.setAdapter(adapter);

        // ✅ When patient selects a doctor
        listDoctors.setOnItemClickListener((parent, view, position, id) -> {
            String selectedDoctor = doctors.get(position);

            Intent intent = new Intent(DoctorListActivity.this, AppointmentTypeActivity.class);
            intent.putExtra("doctor", selectedDoctor);
            startActivity(intent);
        });
    }
}
