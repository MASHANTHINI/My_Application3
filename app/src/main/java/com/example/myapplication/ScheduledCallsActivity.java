package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScheduledCallsActivity extends AppCompatActivity {

    LinearLayout videoCallListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_calls);

        videoCallListLayout = findViewById(R.id.videoCallListLayout);

        loadScheduledVideoCalls();
    }

    private void loadScheduledVideoCalls() {
        videoCallListLayout.removeAllViews();

        SharedPreferences prefs = getSharedPreferences("Appointments", MODE_PRIVATE);
        String appointmentsJson = prefs.getString("appointments_list", null);

        if (appointmentsJson == null) {
            Toast.makeText(this, "No scheduled video calls found", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONArray appointments = new JSONArray(appointmentsJson);

            if (appointments.length() == 0) {
                Toast.makeText(this, "No scheduled video calls found", Toast.LENGTH_SHORT).show();
                return;
            }

            for (int i = 0; i < appointments.length(); i++) {
                JSONObject appointment = appointments.getJSONObject(i);
                String doctor = appointment.getString("doctor");
                String date = appointment.getString("date");
                String time = appointment.getString("time");

                final int index = i; // For lambda

                // TextView for appointment details
                TextView tv = new TextView(this);
                tv.setText("Doctor: " + doctor + "\nDate: " + date + "\nTime: " + time);
                tv.setTextSize(16f);
                tv.setPadding(20, 20, 20, 10);

                // Join Call button
                Button btnJoin = new Button(this);
                btnJoin.setText("Join Call");
                btnJoin.setOnClickListener(v -> {
                    // Open dummy video call screen
                    Intent intent = new Intent(ScheduledCallsActivity.this, VideoCallSetupActivity.class);
                    intent.putExtra("doctor", doctor);
                    intent.putExtra("date", date);
                    intent.putExtra("time", time);
                    startActivity(intent);

                    // Remove this appointment from the list
                    removeAppointment(index);
                });

                // Add to layout
                videoCallListLayout.addView(tv);
                videoCallListLayout.addView(btnJoin);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading appointments", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeAppointment(int index) {
        try {
            SharedPreferences prefs = getSharedPreferences("Appointments", MODE_PRIVATE);
            String appointmentsJson = prefs.getString("appointments_list", null);
            if (appointmentsJson == null) return;

            JSONArray appointments = new JSONArray(appointmentsJson);
            JSONArray updatedAppointments = new JSONArray();

            for (int i = 0; i < appointments.length(); i++) {
                if (i != index) {
                    updatedAppointments.put(appointments.getJSONObject(i));
                }
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("appointments_list", updatedAppointments.toString());
            editor.apply();

            // Refresh list
            loadScheduledVideoCalls();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
