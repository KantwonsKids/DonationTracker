package kantwonskids.donationtrackerg14b.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;

import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.User;
import kantwonskids.donationtrackerg14b.model.UserList;


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
    private int loginAttempts = 0;
    private static final int LOCKOUT_NUMBER = 2;
    private static final int LOCKOUT_MINUTES = 5;
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

        //View mLoginFormView = findViewById(R.id.login_form);
        //View mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (loginAttempts >= LOCKOUT_NUMBER) {
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle("Lock out");
            String message = String.format("After attempting registration with incorrect" +
                    "credentials %d times, you are locked out. You will be able to attempt login" +
                    " again after %d minutes", LOCKOUT_NUMBER, LOCKOUT_MINUTES);
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);
            alertDialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alertDialog.dismiss();
                    mEmailView.setText("");
                    mPasswordView.setText("");
                }
            }, LOCKOUT_MINUTES * 60 * 1000);
        }

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
        UserList userList = Model.getInstance().getUserList();
        User potentialUser = userList.getUser(email);

        if (userList.isValidUser(potentialUser)) {
            login(potentialUser);
        } else {
            focusView.requestFocus();
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            loginAttempts++;
        }
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

}

