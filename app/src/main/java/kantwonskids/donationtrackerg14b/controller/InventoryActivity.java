package kantwonskids.donationtrackerg14b.controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * an Activity to represent the inventory (list of donation items)
 * at a given location
 */
public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        FloatingActionButton addItemButton = findViewById(R.id.addItem);
        addItemButton.setOnClickListener((view) -> {
            Intent intent_addToInventory = new Intent(this, NewItemActivity.class);
            startActivity(intent_addToInventory);
        });

        // set up the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ActionBar nonNull = Objects.requireNonNull(ab);
        nonNull.setDisplayHomeAsUpEnabled(true);
        //Model model = Model.getInstance();
        //Location location = Model.getCurrentLocation();
        ab.setTitle(Model._currentLocation.getName());

        View recyclerView = findViewById(R.id.inventory);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        // Search
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
//            SearchableList locationList = Model.getInstance().locationList;
            List<Donation> donationList = Model._currentLocation.getDonations();
            List<Donation> searchResults = Model.search(donationList, query);

            Intent resultsIntent = new Intent(this,
                    DonationSearchResultsActivity.class);
            resultsIntent.putParcelableArrayListExtra("SEARCH_RESULTS",
                    (ArrayList<Donation>) searchResults);
            startActivity(resultsIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        // Set up the search bar
        final SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        SearchManager nonNull = Objects.requireNonNull(searchManager);
        searchView.setSearchableInfo(nonNull.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.advanced_search:
                // Show the advanced search activity
                Intent intent = new Intent(this, AdvancedSearchActivity.class);
                intent.putExtra("SCOPE", "LOCATION");
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //final Model model = Model.getInstance();
        //Location location = Model.getCurrentLocation();
        recyclerView.setAdapter(new InventoryActivity.SimpleItemRecyclerViewAdapter(
                Model._currentLocation.getDonations()));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<InventoryActivity.
            SimpleItemRecyclerViewAdapter.ViewHolder> {

        /**
         * The items to be shown in the list
         */
        private final List<Donation> mInventory;

        /**
         * Sets the items to be used by the adapter.
         *
         * @param inventory donations per location
         */
        SimpleItemRecyclerViewAdapter(Collection<Donation> inventory) {
            mInventory = new ArrayList<>(inventory);
        }

        @NonNull
        @Override
        public InventoryActivity.SimpleItemRecyclerViewAdapter.ViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater
                    .inflate(R.layout.inventory_content, parent, false);
            return new InventoryActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final InventoryActivity.SimpleItemRecyclerViewAdapter.
                ViewHolder holder, int position) {
            //final Model model = Model.getInstance();
            holder.mDonation = mInventory.get(position);
            Donation donation = mInventory.get(position);
            holder.mContentView.setText(donation.toString());

            holder.mView.setOnClickListener( (View v) -> {
                Context context = v.getContext();
                Intent item_detail_intent = new Intent(context, ItemDetailActivity.class);
                Model.setCurrentDonation(holder.mDonation);
                context.startActivity(item_detail_intent);
            });

        }


        @Override
        public int getItemCount() {
            return mInventory.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mContentView;
            Donation mDonation;


            ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = view.findViewById(R.id.item_name);
//                locationNameView = view.findViewById(R.id.location_name);
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InventoryActivity.this,
                LocationDetailActivity.class));
        finish();
    }
}
