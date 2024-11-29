package com.example.bookingplus;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AuthManager {
    private final FirebaseAuth firebaseAuth;
    private final Context context;

    public AuthManager(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    Toast.makeText(context, task.isSuccessful() ? "Registration Successfully" : "Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                    if (task.isSuccessful()) context.startActivity(new Intent(context, LoginActivity.class));
                });
    }

    public void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    Toast.makeText(context, task.isSuccessful() ? "Login Successfully" : "Login Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                    if (task.isSuccessful()) context.startActivity(new Intent(context, MainActivity.class));
                });
    }

    public void signOut() {
        firebaseAuth.signOut();
        Toast.makeText(context, "Logout Successfully", Toast.LENGTH_SHORT).show();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
