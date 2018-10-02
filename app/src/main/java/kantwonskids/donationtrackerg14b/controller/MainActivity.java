package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.User;

/**
 * The main dashboard activity.
 * Can only be accessed by logging in, and will throw an exception if no CURRENT_USER is specified.
 */
public class MainActivity extends AppCompatActivity {
    private Button logoutButton;
    private TextView userInfoTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener((view) -> {
            logout();
        });
        User currentUser = getIntent().getParcelableExtra("CURRENT_USER");
        if (currentUser == null) {
            throw new IllegalArgumentException("Cannot log in without specifying a user!");
        }
        Model.getInstance().setCurrentUser(currentUser);
        userInfoTextView = findViewById(R.id.main_userInfoTextView);
        displayUserInformation(Model.getInstance().getCurrentUser(), userInfoTextView);
    }

    /**
     * Logs the user out when the logout button is pressed and goes back to the login screen.
     */
    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        // Log out the current user
        Model.getInstance().setCurrentUser(null);
        startActivity(intent);
    }

    /**
     * Displays the user's information (including username and account type)
     * @param user the user to display
     */
    private void displayUserInformation(User user, TextView textView) {
        textView.setText(user.toString());
    }

}
