package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

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

        // 📅 Show DatePicker when clicking Date field
        edtDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    OfflineAppointmentActivity.this,
                    (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                        // Note: month is 0-based, so +1
                        edtDate.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        // ⏰ Show TimePicker when clicking Time field
        edtTime.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    OfflineAppointmentActivity.this,
                    (TimePicker view, int selectedHour, int selectedMinute) -> {
                        // Format with leading zeros if needed
                        String formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                        edtTime.setText(formattedTime);
                    },
                    hour, minute, true  // true = 24-hour format, false = AM/PM format
            );
            timePickerDialog.show();
        });

        // ✅ Confirm Appointment
        btnConfirm.setOnClickListener(v -> {
            String date = edtDate.getText().toString().trim();
            String time = edtTime.getText().toString().trim();

            if (date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please select both date and time!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this,
                    "Offline Appointment booked at hospital on " + date + " at " + time,
                    Toast.LENGTH_LONG).show();
        });

        // 🔙 Back to dashboard
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(OfflineAppointmentActivity.this, PatientDashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
