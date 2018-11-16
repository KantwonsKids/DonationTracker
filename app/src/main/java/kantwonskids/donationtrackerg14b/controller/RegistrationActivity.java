package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.Objects;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.UserRole;
import kantwonskids.donationtrackerg14b.model.User;

/**
 * @author Juliana Petrillo
 * @version 1.0
 *
 * An activity representing the registration page for a user
 */
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
        //LinearLayout registrationLayout = findViewById(R.id.registration_linearLayout);

        // Create a spinner and populate with valid values
        accTypeSpinner = findViewById(R.id.registration_accountType_spinner);
        locationSpinner = findViewById(R.id.registration_location_spinner);

        // Get location names
        List<Location> locations = Model.getInstance().getLocationList();
        String[] locNames = new String[locations.size()];
        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            locNames[i] = location.getName();
        }

        // populate the invisible spinner for location data
        ArrayAdapter<String> adapter_loc = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locNames);
        adapter_loc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter_loc);

        // Get values for each of the roles
        UserRole[] userValues = UserRole.values();
        String[] types = new String[userValues.length];
        for (int i = 0; i < userValues.length; i++) {
            types[i] = userValues[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accTypeSpinner.setAdapter(adapter);
        // Callback for item clicked, which shows a new text box for location employees to pick
        // their location.
        accTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // If the user selects location employee,
                // open a new text field to enter the location
                String currentItem = Objects.requireNonNull(adapter.getItem(i));
                if ("Location Employee".equals(currentItem)) {
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
        createAccountButton.setOnClickListener((view) -> attemptRegistration());


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


        Editable uText = usernameField.getText();
        String u = uText.toString();
        Editable pText = passwordField.getText();
        String p = pText.toString();
        Editable cText = confirmField.getText();
        String c = cText.toString();

        String accType = (String) accTypeSpinner.getSelectedItem();
        String locationStr = (String) locationSpinner.getSelectedItem();
        Location loc = null;
        Model model = Model.getInstance();
        // find the real location
        if ("Location Employee".equals(accType)) {
            for (int i = 0; i < model.getLocationList().size(); i++) {
                Location location = model.getLocationList().get(i);
                String name = location.getName();
                if (name.equals(locationStr)) {
                    loc = model.getLocationList().get(i);
                }
            }
        }

        User newUser = validate(u, p, c, accType, loc);
        if (newUser != null) {
            model.getUserList().addUser(newUser);
            model.setCurrentUser(newUser);

            // save to file
            Model.saveToPhone();

            Intent welcomeIntent = new Intent(this, MainActivity.class);
            // Pass the user just created to the main activity to set as the logged in user
            welcomeIntent.putExtra("CURRENT_USER", (Parcelable)newUser);
            startActivity(welcomeIntent);
        }
    }

    private User createNewUser(String accType, String u, String p, Location loc) {
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

        return newUser;
    }

    private User validate(String u, String p, String c, String accType,
                            Location loc) {
        User newUser = null;
        String validUsername = validateUsername(u);
        String validPassword = validatePassword(p);
        // check to see if the username exists in the list
        if (Model.getInstance().getUserList().isUsernameTaken(u)) {
            usernameField.setError("This username is taken!");

        } else if (!"VALID".equals(validUsername)) {
            usernameField.setError(validateUsername(u));
        } else if (!p.equals(c)) {
            passwordField.setError("Passwords do not match!");
        } else if (!"VALID".equals(validPassword)) {
            passwordField.setError(validatePassword(p));
        } else if ("Location Employee".equals(accType) && !isValidLocation(loc)) {
            locationTextField.setError("Invalid location!");
        } else {
            // Determine the selected account type and create object accordingly
            newUser = createNewUser(accType, u, p, loc);

       }

        return newUser;
    }

    /**
     * Checks a username's validity and returns a message indicating
     *
     * @param u username
     * @return message
     */
    private String validateUsername(CharSequence u) {
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
    private String validatePassword(CharSequence p) {
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
     * Checks to see if the location string is non-empty, non-null,
     * and exists in the locations list.
     * @param l the location to check
     * @return true if the location is valid, false otherwise
     */
    private boolean isValidLocation(Location l) {

        return Model.getInstance().getLocationList().contains(l);
    }
}
