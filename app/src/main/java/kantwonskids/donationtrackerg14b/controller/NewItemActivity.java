package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import kantwonskids.donationtrackerg14b.model.DonationCategory;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.R;

/**
 * @author Amanda Schmidt
 * @version 1.0
 *
 * Activity for adding new donations to a location inventory
 */
public class NewItemActivity extends AppCompatActivity{

    private EditText itemName;
    private EditText itemDescription;
    private EditText itemPrice;
    private EditText itemComments;
    private TextView errorMessage;
    private Spinner categorySpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        DonationCategory[] donationCategories = DonationCategory.values();
        String[] categories = new String[donationCategories.length];
        for (int i = 0; i < donationCategories.length; i++) {
            categories[i] = donationCategories[i].toString();
        }
        categorySpinner = findViewById(R.id.categories);
        SpinnerAdapter categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);
        categorySpinner.setAdapter(categoryAdapter);

        itemName = findViewById(R.id.itemName);
        itemDescription = findViewById(R.id.itemDescription);
        itemPrice = findViewById(R.id.itemPrice);
        itemComments = findViewById(R.id.itemComments);
        errorMessage = findViewById(R.id.errorMessage);

        Button createDonation = findViewById(R.id.createDonation);
        createDonation.setOnClickListener((view) -> attemptCreateDonation());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        ActionBar nonNull = Objects.requireNonNull(actionBar);
        nonNull.setTitle("Add new item");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void attemptCreateDonation() {
        Editable itemText = itemName.getText();
        String name = itemText.toString();
        Editable descriptionText = itemDescription.getText();
        String description = descriptionText.toString();
        Editable priceText = itemPrice.getText();
        float price = Float.parseFloat(priceText.toString());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
        String formattedTime = time.format(formatter);

        Editable itemText = itemName.getText();
        String itemString = itemText.toString();
        String item = itemString.toUpperCase();

        Editable descriptionText = itemDescription.getText();

        Editable priceText = itemPrice.getText();
        String strPrice = priceText.toString();
        float price = Float.parseFloat(strPrice);

        Object selectedItem = categorySpinner.getSelectedItem();
        String strCategory = selectedItem.toString();

        DonationCategory category = DonationCategory.valueOf(strCategory.toUpperCase());
        Editable commentsText = itemComments.getText();
        String comments = commentsText.toString();

        model.getCurrentLocation().addDonation(new Donation(formattedTime, item,
                descriptionText.toString(), price, category,
                comments, loc));
//        Model.setCurrentLocation(loc);

        // save model
        Model.saveToPhone();

        //go back to inventory
        Intent intent_backToInventory = new Intent(this, InventoryActivity.class);
        startActivity(intent_backToInventory);
    }
}
