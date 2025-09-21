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

        txtTitle.setText("Ayurvedic Doctors for: " + disease);

        List<String> doctors = new ArrayList<>();

        switch (disease.toLowerCase()) {
            case "arthritis":
            case "joint pain":
                doctors.add("Dr. Anil – Panchakarma Specialist (Arthritis & Vasti)");
                doctors.add("Dr. Kavya – Ayurveda Physician (Abhyanga & Joint Care)");
                break;

            case "skin":
            case "psoriasis":
            case "eczema":
                doctors.add("Dr. Lakshmi – Panchakarma & Skin Specialist (Virechana, Raktamokshana)");
                doctors.add("Dr. Manoj – Ayurveda Consultant (Herbal Skin Therapies)");
                break;

            case "digestion":
            case "acidity":
            case "ibs":
                doctors.add("Dr. Priya – Ayurveda Physician (Virechana & Gut Health)");
                doctors.add("Dr. Arun – Panchakarma Expert (Vamana, Pitta Balance)");
                break;

            case "stress":
            case "anxiety":
            case "insomnia":
                doctors.add("Dr. Rekha – Panchakarma Therapist (Shirodhara, Nasya)");
                doctors.add("Dr. Vivek – Ayurveda Psychosomatic Specialist");
                break;

            case "obesity":
            case "weight":
                doctors.add("Dr. Swathi – Panchakarma Doctor (Udvartana, Vamana)");
                doctors.add("Dr. Sanjay – Lifestyle & Diet Ayurveda Expert");
                break;

            case "neurological":
            case "sciatica":
            case "paralysis":
            case "migraine":
                doctors.add("Dr. Meera – Panchakarma & Neuro Specialist (Basti, Shirodhara)");
                doctors.add("Dr. Rajesh – Ayurveda Consultant (Neurological Disorders)");
                break;

            default:
                doctors.add("Dr. Suresh – General Ayurveda Physician");
                doctors.add("Dr. Divya – Panchakarma Consultant");
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
