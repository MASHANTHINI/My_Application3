package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VideoCallSetupActivity extends AppCompatActivity {

    TextView tvDoctorName, tvTimer;
    Button btnEndCall;
    private int seconds = 0;
    private Handler handler = new Handler();
    private Runnable timerRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_fullscreen);

        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvTimer = findViewById(R.id.tvTimer);
        btnEndCall = findViewById(R.id.btnEndCall);

        String doctor = getIntent().getStringExtra("doctor");
        tvDoctorName.setText("Doctor: " + doctor);

        // Timer simulation
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                int mins = seconds / 60;
                int secs = seconds % 60;
                tvTimer.setText(String.format("%02d:%02d", mins, secs));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(timerRunnable);

        btnEndCall.setOnClickListener(v -> {
            handler.removeCallbacks(timerRunnable);
            Toast.makeText(this, "Video Call Ended", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
