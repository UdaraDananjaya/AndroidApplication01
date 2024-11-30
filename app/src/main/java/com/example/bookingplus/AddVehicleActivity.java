package com.example.bookingplus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText etVehicleName, etVehicleType, etRent;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        etVehicleName = findViewById(R.id.etVehicleName);
        etVehicleType = findViewById(R.id.etVehicleType);
        etRent = findViewById(R.id.etRent);
        Button btnSaveVehicle = findViewById(R.id.btnSaveVehicle);

        db = FirebaseFirestore.getInstance();

        // Set the listener to save vehicle details
        btnSaveVehicle.setOnClickListener(v -> saveVehicle());
    }

    private void saveVehicle() {
        String name = etVehicleName.getText().toString();
        String type = etVehicleType.getText().toString();
        String rentStr = etRent.getText().toString();

        // Validate input fields
        if (name.isEmpty() || type.isEmpty() || rentStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert rent to double
        double rent = 0;
        try {
            rent = Double.parseDouble(rentStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid rent amount", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a vehicle object to save to Firestore
        Map<String, Object> vehicle = new HashMap<>();
        vehicle.put("name", name);
        vehicle.put("type", type);
        vehicle.put("rent", rent);

        // Save the vehicle data to Firestore
        db.collection("vehicles").add(vehicle)
                .addOnSuccessListener(documentReference ->
                        Toast.makeText(this, "Vehicle Added", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to Add Vehicle", Toast.LENGTH_SHORT).show());
    }
}
