package com.example.bookingplus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar.setVisibility(ProgressBar.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateProgress();
            }
        }, 500);
    }

    private void updateProgress() {
        final int maxProgress = 100;

        Handler progressHandler = new Handler();
        Runnable updateProgressRunnable = new Runnable() {
            @Override
            public void run() {
                if (progress < maxProgress) {
                    progress++;
                    progressBar.setProgress(progress);
                    progressHandler.postDelayed(this, 10);
                } else {
                    progressBar.setVisibility(ProgressBar.GONE);
                    startNextActivity();
                }
            }
        };
        progressHandler.post(updateProgressRunnable);
    }
    private void startNextActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}