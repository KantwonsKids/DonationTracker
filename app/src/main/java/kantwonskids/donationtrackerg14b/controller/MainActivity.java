package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import kantwonskids.donationtrackerg14b.R;

public class MainActivity extends AppCompatActivity {
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener((view) -> {
            logout();
        });
    }

    /**
     * Logs the user out when the logout button is pressed and goes back to the login screen.
     */
    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
