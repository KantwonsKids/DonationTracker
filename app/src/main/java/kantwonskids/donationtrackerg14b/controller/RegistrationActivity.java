package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.*;

public class RegistrationActivity extends AppCompatActivity {


    // username and password field values
    private EditText usernameField;
    private EditText passwordField;
    private EditText confirmField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // get references
        usernameField = findViewById(R.id.registration_usernameField);
        passwordField = findViewById(R.id.registration_passwordField);
        confirmField = findViewById(R.id.registration_confirmField);



        // Attempt to create an account if the account doesn't already exist
        Button createAccountButton = findViewById(R.id.registration_createAccount_button);
        createAccountButton.setOnClickListener((view) -> {
            attemptRegistration();
        });


        // Return to welcome screen if cancel button is pressed
        Button cancelButton = findViewById(R.id.registration_cancel_button);
        cancelButton.setOnClickListener((view) -> {
            Intent intent_cancel = new Intent(this, WelcomeActivity.class);
            startActivity(intent_cancel);
        });
    }

    private void attemptRegistration() {
        usernameField.setError(null);
        passwordField.setError(null);
        confirmField.setError(null);

        String u = usernameField.getText().toString();
        String p = passwordField.getText().toString();
        String c = confirmField.getText().toString();

        // get reference to the model
        Model model = Model.getInstance();
        // check to see if the username exists in the list
        if (model._userList.usernameTaken(u)) {
            usernameField.setError("Username is taken");
        }
        else if (!p.equals(c)) {
            passwordField.setError("Passwords do not match!");
        }
        else
        {
            model._userList.addUser(new User(u, p));
            //Model.push(model);
            Intent intent_welcome = new Intent(this, MainActivity.class);
            startActivity(intent_welcome);
        }


    }
}
