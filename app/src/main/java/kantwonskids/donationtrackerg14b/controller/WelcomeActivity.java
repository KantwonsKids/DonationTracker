package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import kantwonskids.donationtrackerg14b.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Start the login activity when login button is pressed
        Button loginButton = findViewById(R.id.welcome_login_button);
        loginButton.setOnClickListener((view) -> {
            Intent intent_login = new Intent(this, LoginActivity.class);
            startActivity(intent_login);
        });

        // Send to registration screen when button is pressed
        Button regiButton = findViewById(R.id.welcome_register_button);
        regiButton.setOnClickListener((view) -> {
            Intent intent_regi = new Intent(this, RegistrationActivity.class);
            startActivity(intent_regi);
        });
    }
}
