package kantwonskids.donationtrackerg14b.controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;

public class LocationSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        Intent intent = getIntent();
        ArrayList<Donation> searchResults = intent.getParcelableArrayListExtra("SEARCH_RESULTS");
        ArrayList<String> selectedCategories = intent.getStringArrayListExtra("SELECTED_CATEGORIES");
        // convert list of strings into list of locations
        RecyclerView recyclerView = findViewById(R.id.search_results);

        // filter search results based on selected categories
        Collection<Donation> toRemove = new ArrayList<>();
        if (selectedCategories != null) {
            for (Donation d : searchResults) {
                if (!selectedCategories.contains(d.getCategory().toString())) {
//                    searchResults.remove(d);
                    toRemove.add(d);
                }
            }

            searchResults.removeAll(toRemove);
        }

        setupRecyclerView(recyclerView, searchResults);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.search_results);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Donation> list) {
        recyclerView.setAdapter(new LocationSearchActivity.SimpleItemRecyclerViewAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder> {

        /**
         * The items to be shown in the list
         */
        private final List<Donation> mInventory;

        /**
         * Sets the items to be used by the adapter.
         *
         * @param inventory
         */
        SimpleItemRecyclerViewAdapter(List<Donation> inventory) {
            mInventory = inventory;
        }

        @Override
        public LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.inventory_content, parent, false);
            return new LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            final Model model = Model.getInstance();
            holder.mDonation = mInventory.get(position);
            holder.mContentView.setText(mInventory.get(position).toString());
            holder.locationNameView.setText(mInventory.get(position).getLocationString());
            holder.itemIcon.setImageResource(R.drawable.ic_tshirt);

            holder.mView.setOnClickListener( (View v) -> {
                Context context = v.getContext();
                Intent item_detail_intent = new Intent(context, ItemDetailActivity.class);
                model.setCurrentDonation(holder.mDonation);
                context.startActivity(item_detail_intent);
            });

        }


        @Override
        public int getItemCount() {
            return mInventory.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public final TextView locationNameView;
            public final ImageView itemIcon;
            public Donation mDonation;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.item_name);
                locationNameView = (TextView)view.findViewById(R.id.location_name);
                itemIcon = view.findViewById(R.id.item_icon);
            }

        }
    }
}
