package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;

import android.text.InputType;
import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.*;


import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.User;
import kantwonskids.donationtrackerg14b.model.UserList;
import kantwonskids.donationtrackerg14b.model.UserRole;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    //private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private UserList userList = Model.getInstance().getUserList();
    // --Commented out by Inspection (11/15/18, 12:36 PM):private View mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.login_username);

        mPasswordView = findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if ((id == EditorInfo.IME_ACTION_DONE) || (id == EditorInfo.IME_NULL)) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button mEmailSignInButton = findViewById(R.id.login_login_button);
        mEmailSignInButton.setOnClickListener(view -> attemptLogin());

        // Send to registration screen when button is pressed
        View regView = findViewById(R.id.login_noAccount_textview);
        regView.setOnClickListener((view) -> {
            Intent intent_register = new Intent(this, RegistrationActivity.class);
            startActivity(intent_register);
        });

        // Log in as a guest
        View guestView = findViewById(R.id.login_asGuest_textview);
        guestView.setOnClickListener((view) -> GuestLogin());

        View forgotPassView = findViewById(R.id.forgot_pass_textView);
        forgotPassView.setOnClickListener((view) -> requestEmail());

        //View mLoginFormView = findViewById(R.id.login_form);
        //View mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        Editable emailText = mEmailView.getText();
        String email = emailText.toString();
        //String password = mPasswordView.getText().toString();

        View focusView = mPasswordView;

        // Get instance of model to compare the username / password with the list of valid users
        //Model model = Model.getInstance();
        User potentialUser = userList.getUser(email);

        if (userList.isValidUser(potentialUser)) {
            login(potentialUser);
        } else {
            focusView.requestFocus();
            mPasswordView.setError(getString(R.string.error_incorrect_password));
        }
    }

    /**
     * Logs in to the app as a guest, by passing in a dummy user
     * This is the only way to have the Guest UserType
     */
    private void GuestLogin()
    {
        login(new User(null, null, UserRole.GUEST, null));
    }

    /**
     * Logs in to the app, once the username and password have passed verification.
     * Shows the main screen with a "success" page.
     * @param potentialUser The user to log in as.
     */
    private void login(User potentialUser) {
//        Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("CURRENT_USER", (Parcelable)potentialUser);
        Model.getInstance().setCurrentUser(potentialUser);
        startActivity(intent);
    }

    private void requestEmail() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Send Password Recovery");
        builder.setMessage("Enter email.");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        String email = input.getText().toString();
        builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.cancel());
        builder.setPositiveButton("SEND", (dialog, which) -> sendEmail(email));
        builder.show();
    }

    private void sendEmail(String email) {
        Editable usernameText = mEmailView.getText();
        String username = usernameText.toString();
        User user = userList.getUser(username);
        String password;
        if (user != null) {
            password = user.getPassword();
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
            i.putExtra(Intent.EXTRA_SUBJECT, "Donation Tracker Password");
            i.putExtra(Intent.EXTRA_TEXT   , "The password for account " + username +
                    "is " + password + ".");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(LoginActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        } else {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(LoginActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Password Recovery Failed");
            builder.setMessage("We could not find that username.");
            builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.cancel());
            builder.show();
        }
    }

}

