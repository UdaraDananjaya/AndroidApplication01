package com.example.bookingplus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAddVehicle = findViewById(R.id.btnAddVehicle);
        Button btnSearchVehicle = findViewById(R.id.btnSearchVehicle);
        Button btnViewBookings = findViewById(R.id.btnViewBookings);

        btnAddVehicle.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddVehicleActivity.class);
            startActivity(intent);
        });

        btnSearchVehicle.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchVehicleActivity.class);
            startActivity(intent);
        });

        btnViewBookings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewBookingsActivity.class);
            startActivity(intent);
        });
    }
}