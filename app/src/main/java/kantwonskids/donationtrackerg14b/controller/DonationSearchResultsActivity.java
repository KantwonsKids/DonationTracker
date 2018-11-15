package kantwonskids.donationtrackerg14b.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.DonationCategory;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * @author Daniel Profili
 * @version 1.0
 * 
 * An activity to return the results of a search of donations in a location
 */
public class DonationSearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        Intent intent = getIntent();
        List<Donation> searchResults =
                intent.getParcelableArrayListExtra("SEARCH_RESULTS");
        List<String> selectedCategories =
                intent.getStringArrayListExtra("SELECTED_CATEGORIES");
        // convert list of strings into list of locations
        RecyclerView recyclerView = findViewById(R.id.search_results);

        // filter search results based on selected categories
        Collection<Donation> toRemove = new ArrayList<>();
        if (selectedCategories != null) {
            for (Donation d : searchResults) {
                DonationCategory.setCurrentDonationCategory(d.getCategory());
                if (!selectedCategories.contains(DonationCategory._currentDonation.toString())) {
//                    searchResults.remove(d);
                    toRemove.add(d);
                }
            }

            searchResults.removeAll(toRemove);
        }

        setupRecyclerView(recyclerView, searchResults);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ActionBar nonNull = Objects.requireNonNull(ab);
        nonNull.setTitle(R.string.search_results);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Donation> list) {
        if (list.isEmpty()) {
            TextView message = findViewById(R.id.search_results_message);
            message.setVisibility(View.VISIBLE);
            message.setText("Your search returned no results.");
        }
        recyclerView.setAdapter(new DonationSearchResultsActivity.
                SimpleItemRecyclerViewAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<DonationSearchResultsActivity.
            SimpleItemRecyclerViewAdapter.ViewHolder> {

        /**
         * The items to be shown in the list
         */
        private final List<Donation> mInventory;

        /**
         * Sets the items to be used by the adapter.
         *
         * @param inventory inventory of location to be passed in
         */
        SimpleItemRecyclerViewAdapter(List<Donation> inventory) {
            mInventory = inventory;
        }

        @NonNull
        @Override
        public DonationSearchResultsActivity.SimpleItemRecyclerViewAdapter.ViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.inventory_content,
                    parent, false);
            return new DonationSearchResultsActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final DonationSearchResultsActivity.
                SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            //final Model model = Model.getInstance();
            holder.mDonation = mInventory.get(position);
            Donation donation = mInventory.get(position);
            holder.mContentView.setText(donation.toString());
            holder.locationNameView.setText(donation.getLocationString());
            holder.itemIcon.setImageResource(R.drawable.ic_tshirt);

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
            final TextView locationNameView;
            final ImageView itemIcon;
            Donation mDonation;


            ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = view.findViewById(R.id.item_name);
                locationNameView = view.findViewById(R.id.location_name);
                itemIcon = view.findViewById(R.id.item_icon);
            }

        }
    }
}
