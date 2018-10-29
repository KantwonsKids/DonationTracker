package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.List;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.*;

public class RegistrationActivity extends AppCompatActivity {


    // username and password field values
    private EditText usernameField;
    private EditText passwordField;
    private EditText confirmField;

    // Dynamically created edit field for location employees to enter their location
    private EditText locationTextField;

    private Spinner accTypeSpinner;
    private Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Create reference to dynamic location text field
        locationTextField = new EditText(this);

        // get references
        usernameField = findViewById(R.id.registration_usernameField);
        passwordField = findViewById(R.id.registration_passwordField);
        confirmField = findViewById(R.id.registration_confirmField);
        LinearLayout registrationLayout = findViewById(R.id.registration_linearLayout);

        // Create a spinner and populate with valid values
        accTypeSpinner = findViewById(R.id.registration_accountType_spinner);
        locationSpinner = findViewById(R.id.registration_location_spinner);

        // Get location names
        List<Location> locs = Model.donationDataList;
        String[] locNames = new String[locs.size()];
        for (int i = 0; i < locs.size(); i++) {
            locNames[i] = locs.get(i).getName();
        }

        // populate the invisible spinner for locationdata
        ArrayAdapter<String> adapter_loc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locNames);
        adapter_loc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter_loc);

        // Get values for each of the roles
        String[] types = new String[UserRole.values().length];
        for (int i = 0; i < UserRole.values().length; i++) {
            types[i] = UserRole.values()[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accTypeSpinner.setAdapter(adapter);
        // Callback for item clicked, which shows a new text box for location employees to pick
        // their location.
        accTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // If the user selects location employee, open a new text field to enter the location
                if (adapter.getItem(i).equals("Location Employee")) {
                    locationTextField.setHint("Name of location");
                    locationSpinner.setVisibility(View.VISIBLE);

//                    // Put the location text box below the spinner
//                    int newIndex = registrationLayout.indexOfChild(accTypeSpinner);
//                    registrationLayout.addView(locationTextField, newIndex + 1);
                } else {
//                    if (locationTextField.getParent() != null) {
//                        // if location text field has been added, delete it
//                        registrationLayout.removeView(locationTextField);
//                    }
                    locationSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

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

     /**
     * Attempts to register.  Either succeeds or presents a relevant error message
     */
    private void attemptRegistration() {
        usernameField.setError(null);
        passwordField.setError(null);
        confirmField.setError(null);


        String u = usernameField.getText().toString();
        String p = passwordField.getText().toString();
        String c = confirmField.getText().toString();
        String accType = (String) accTypeSpinner.getSelectedItem();
        String locationStr = (String) locationSpinner.getSelectedItem();
        Location loc = null;
        // find the real location
        if (accType.equals("Location Employee")) {
            for (int i = 0; i < Model.donationDataList.size(); i++) {
                if (Model.donationDataList.get(i).getName().equals(locationStr)) {
                    loc = Model.donationDataList.get(i);
                }
            }
        }


        // get reference to the model
        Model model = Model.getInstance();
        // check to see if the username exists in the list
        if (model._userList.isUsernameTaken(u)) {
            usernameField.setError("This username is taken!");
        } else if (!validateUsername(u).equals("VALID")) {
            usernameField.setError(validateUsername(u));
        } else if (!p.equals(c)) {
            passwordField.setError("Passwords do not match!");
        } else if (!validatePassword(p).equals("VALID")) {
            passwordField.setError(validatePassword(p));
        } else if (accType.equals("Location Employee") && !isValidLocation(loc)) {
            locationTextField.setError("Invalid location!");
        }
        else
        {
            // Determine the selected account type and create object accordingly
            User newUser = null;
            switch (accType) {
                case "Administrator":
                    newUser = new User(u, p, UserRole.ADMINISTRATOR);
                    break;
                case "Manager":
                    newUser = new User(u, p, UserRole.MANAGER);
                    break;
                case "Location Employee":
                    newUser = new User(u, p, UserRole.LOCATION_EMPLOYEE, loc);
                    break;
                case "User":
                    newUser = new User(u, p, UserRole.USER);
                    break;
            }
            model._userList.addUser(newUser);

            // save to file
            Model.saveToPhone();

            Intent welcomeIntent = new Intent(this, MainActivity.class);
            // Pass the user just created to the main activity to set as the logged in user
            welcomeIntent.putExtra("CURRENT_USER", (Parcelable)newUser);
            startActivity(welcomeIntent);
        }


    }

    /**
     * Checks a username's validity and returns a message indicating
     *
     * @param u username
     * @return message
     */
    private String validateUsername(String u) {
        String msg = "VALID";

        if (u.length() < 4) {
            msg = "Invalid Username.  Must be 4 or more characters.";
        } else {
            if (!Character.isLetter(u.charAt(0)))
            {
                msg = "Invalid Username.  Must begin with a letter.";
            } else {
                for (int i = 0; i < u.length(); i++) {
                    if (!Character.isLetterOrDigit(u.charAt(i))) {
                        msg = "Invalid Username.  Must consist of letters and numbers only.";
                    }
                }
            }
        }
        return msg;
    }

    /**
     * Checks a passwords's validity and returns a message indicating
     *
     * @param p password
     * @return message
     */
    private String validatePassword(String p) {
        String msg = "VALID";

        if (p.length() < 8) {
            msg = "Invalid Password.  Must be 8 or more characters.";
        } else {

            for (int i = 0; i < p.length(); i++) {
                if (!Character.isLetterOrDigit(p.charAt(i))) {
                    msg = "Invalid Password.  Must consist of letters and numbers only.";
                }
            }
        }
        return msg;
    }

    /**
     * Checks to see if the location string is non-empty, non-null, and exists in the locations list.
     * @param l the location to check
     * @return true if the location is valid, false otherwise
     */
    private boolean isValidLocation(Location l) {

        return Model.donationDataList.contains(l);
    }
}
