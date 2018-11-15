package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.DonationCategory;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * @author Daniel Profili
 * @version 1.0
 *
 * Allows for searching of donations based on name
 */
public class AdvancedSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        // Populate the page with checkboxes for each category
        LinearLayout layout = findViewById(R.id.adv_search_linear_layout);
        ArrayList<String> selectedCategories = new ArrayList<>();
        Button goButton = findViewById(R.id.adv_search_go_button);
        for (DonationCategory dc : DonationCategory.values()) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(dc.toString());
            int buttonLoc = layout.indexOfChild(goButton);
            checkBox.setOnCheckedChangeListener((group, isChecked) -> {
                if (isChecked) {
                    selectedCategories.add(dc.toString());
                }
            });
            layout.addView(checkBox, buttonLoc - 1);
        }

        // Set up the button click listener
        TextInputEditText queryView = findViewById(R.id.adv_search_query_text);
//        String searchQuery = queryView.getText().toString();
        goButton.setOnClickListener((view -> {
            Intent intent = new Intent(this, DonationSearchResultsActivity.class);
//            intent.setAction(Intent.ACTION_SEARCH);
            // get search results
            Editable queryText = queryView.getText();
            String query = queryText.toString();
            List<Donation> toSearch;
            Intent newIntent = getIntent();
            String scope =  newIntent.getStringExtra("SCOPE");
            if ((scope != null) && "ALL".equals(scope)) {
                toSearch = Model.getInstance().getAllDonations();
            } else {
                //Location location = Model.getCurrentLocation();
                toSearch = Model.getInstance().currentLocation.getDonations();
            }

            List<Donation> searchResults = Model.search(toSearch, query);
//            intent.putExtra(SearchManager.QUERY, query);
            intent.putStringArrayListExtra("SELECTED_CATEGORIES", selectedCategories);
            intent.putParcelableArrayListExtra("SEARCH_RESULTS",
                    (ArrayList<Donation>)searchResults);
            startActivity(intent);

        }));
    }
}
