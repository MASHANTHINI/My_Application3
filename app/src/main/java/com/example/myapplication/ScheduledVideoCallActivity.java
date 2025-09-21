package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class ScheduledVideoCallActivity extends AppCompatActivity {

    TextView txtDetails;
    Button btnJoinCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_video_call);

        txtDetails = findViewById(R.id.txtDetails);
        btnJoinCall = findViewById(R.id.btnJoinCall);

        String doctor = "Unknown";
        String date = "N/A";
        String time = "N/A";

        SharedPreferences prefs = getSharedPreferences("Appointments", MODE_PRIVATE);
        String appointmentsJson = prefs.getString("appointments_list", null);

        if (appointmentsJson != null) {
            try {
                JSONArray appointments = new JSONArray(appointmentsJson);
                JSONObject firstAppointment = appointments.getJSONObject(0); // show first
                doctor = firstAppointment.getString("doctor");
                date = firstAppointment.getString("date");
                time = firstAppointment.getString("time");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading appointments", Toast.LENGTH_SHORT).show();
            }
        }

        txtDetails.setText("Doctor: " + doctor + "\nDate: " + date + "\nTime: " + time);

        final String finalDoctor = doctor;
        final String finalDate = date;
        final String finalTime = time;

        btnJoinCall.setOnClickListener(v -> {
            // Open dummy video call page
            Intent intent = new Intent(ScheduledVideoCallActivity.this, VideoCallSetupActivity.class);
            intent.putExtra("doctor", finalDoctor);
            intent.putExtra("date", finalDate);
            intent.putExtra("time", finalTime);
            startActivity(intent);
        });
    }
}
