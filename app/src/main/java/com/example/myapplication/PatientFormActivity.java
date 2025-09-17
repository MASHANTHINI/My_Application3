package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientFormActivity extends AppCompatActivity {
    EditText etName, etAge, etTherapy;
    Button btnSave;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        // Initialize views
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etTherapy = findViewById(R.id.etTherapy);
        btnSave = findViewById(R.id.btnSave);

        // Initialize DB
        db = new DatabaseHelper(this);

        // Save Button click
        btnSave.setOnClickListener(v -> {
            String pname = etName.getText().toString().trim();
            String page = etAge.getText().toString().trim();
            String ptherapy = etTherapy.getText().toString().trim();

            if (pname.isEmpty() || page.isEmpty() || ptherapy.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.addPatient(pname, page, ptherapy)) {
                Toast.makeText(this, "Patient saved!", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etAge.setText("");
                etTherapy.setText("");
            } else {
                Toast.makeText(this, "Error saving patient!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
