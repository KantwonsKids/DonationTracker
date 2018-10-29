package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;

import kantwonskids.donationtrackerg14b.model.DonationCategory;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.R;

public class NewItemActivity extends AppCompatActivity{

    private EditText itemName;
    private EditText itemDescription;
    private EditText itemPrice;
    private EditText itemComments;
    private TextView errorMessage;
    private Spinner categorySpinner;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        String[] categories = new String[DonationCategory.values().length];
        for (int i = 0; i < DonationCategory.values().length; i++) {
            categories[i] = DonationCategory.values()[i].toString();
        }
        categorySpinner = (Spinner) findViewById(R.id.categories);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);
        categorySpinner.setAdapter(categoryAdapter);

        itemName = (EditText) findViewById(R.id.itemName);
        itemDescription = (EditText) findViewById(R.id.itemDescription);
        itemPrice = (EditText) findViewById(R.id.itemPrice);
        itemComments = (EditText) findViewById(R.id.itemComments);
        errorMessage = (TextView) findViewById(R.id.errorMessage);

        Button createDonation = (Button) findViewById(R.id.createDonation);
        createDonation.setOnClickListener((view) -> {
            attemptCreateDonation();
        });

    }

    private void attemptCreateDonation() {
        String name = itemName.getText().toString();
        String description = itemDescription.getText().toString();
        float price = Float.parseFloat(itemPrice.getText().toString());
        if (name.length() < 1) {
            String error = "Please enter a valid item name.";
            errorMessage.setText(error);
        } else if (description.length() < 1) {
            String error = "Please enter a valid item description.";
            errorMessage.setText(error);
        } else if (price <= 0) {
            String error = "Please enter a valid item price.";
            errorMessage.setText(error);
        } else {
            createDonation();
        }
    }

    private void createDonation() {
        final Model model = Model.getInstance();
        Location loc = model.getCurrentLocation();

        LocalDateTime time = LocalDateTime.now();
        String item = itemName.getText().toString();
        String description = itemDescription.getText().toString();
        String strPrice = itemPrice.getText().toString();
        float price = Float.parseFloat(strPrice);
        String strCategory = categorySpinner.getSelectedItem().toString();
        DonationCategory category = DonationCategory.valueOf(strCategory);
        String comments = itemComments.getText().toString();

        loc.addDonation(new Donation(time, item, description, price, category, comments));
        model.setCurrentLocation(loc);

        // save model
        Model.saveToPhone();

        //go back to inventory
        Intent intent_backToInventory = new Intent(this, InventoryActivity.class);
        startActivity(intent_backToInventory);
    }
}
