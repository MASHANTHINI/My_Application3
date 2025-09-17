package com.example.panchakarma;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientFormActivity extends AppCompatActivity {
    EditText name, age, therapy;
    Button saveBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        name = findViewById(R.id.etName);
        age = findViewById(R.id.etAge);
        therapy = findViewById(R.id.etTherapy);
        saveBtn = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        saveBtn.setOnClickListener(v -> {
            String pname = name.getText().toString();
            String page = age.getText().toString();
            String ptherapy = therapy.getText().toString();

            if(db.addPatient(pname, page, ptherapy)) {
                Toast.makeText(this, "Patient saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error saving patient!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
