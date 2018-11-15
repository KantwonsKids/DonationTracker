package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.Location;


/**
 * @author Daniel Profilli
 * @version 3.0
 *
 * Activity representing the initial page presented to the user
 * where they can either login or make an account
 */
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
        Button registerButton = findViewById(R.id.welcome_register_button);
        registerButton.setOnClickListener((view) -> {
            Intent intent_register = new Intent(this, RegistrationActivity.class);
            startActivity(intent_register);
        });


        // Load the serialized model
        Model.loadSavedData();
        //Model instance = Model.getInstance();
        if (Model.locationList.isEmpty())
        {
            readSampleData();
        }



    }

    /**
     * Reads in sample data from raw, formats into Location objects, and stores them in a list
     * within the model instance
     */
    private void readSampleData() {
        //Model model = Model.getInstance();

        try {
            // open a stream on the file
            Resources resources = getResources();
            InputStream is = resources.openRawResource(R.raw.sampledata);
            // wrap it in a buffered reader so that we can see lines
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            // initialize the locationList in the model
            //model.locationList = new ArrayList<>();

            String line;
            line = br.readLine(); // drop the header line
            while (line != null) {
                line = br.readLine();
                String[] tokens = line.split(",");
                // separate everything out
                int key = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double latitude = Double.parseDouble(tokens[2]);
                double longitude = Double.parseDouble(tokens[3]);
                String address = tokens[4];
                String city = tokens[5];
                String state = tokens[6];
                int zipcode = Integer.parseInt(tokens[7]);
                String type = tokens[8];
                String phoneNumber = tokens[9];
                String website = tokens[10];

                // create a donationData from these parameters and add it to model
                Location d = new Location(
                        key,
                        name,
                        latitude,
                        longitude,
                        address,
                        city,
                        state,
                        zipcode,
                        type,
                        phoneNumber,
                        website
                );
                Model.locationList.add(d);
            }
            // print the success
            Log.d("DATA", "Successfully populated the locationList");
            br.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

}
