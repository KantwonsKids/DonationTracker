package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * @author Juliana Petrillo
 * @version 2.0
 *
 * An activity representing a single Location detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MainActivity}.
 */
public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        // Show the Up button in the action bar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.location_detail_title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //Model model = Model.getInstance();
        //Location mLocation = Model.getCurrentLocation();
//        TextView locationTitle = findViewById(R.id.locationTitle);
//        locationTitle.setText(mLocation.getName());
        TextView locationDetail = findViewById(R.id.location_detail_text);
        locationDetail.setText(Model.getInstance().getCurrentLocation().detailString());

        Button inventoryButton = findViewById(R.id.viewInventory);
        inventoryButton.setOnClickListener((view) -> {
            Intent intent_inventory = new Intent(this, InventoryActivity.class);
            startActivity(intent_inventory);
        });


        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(LocationDetailFragment.ARG_NAME,
//                    getIntent().getStringExtra(LocationDetailFragment.ARG_NAME));
//            LocationDetailFragment fragment = new LocationDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.location_detail_container, fragment)
//                    .commit();
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LocationDetailActivity.this,
                MainActivity.class));
        finish();
    }
}
