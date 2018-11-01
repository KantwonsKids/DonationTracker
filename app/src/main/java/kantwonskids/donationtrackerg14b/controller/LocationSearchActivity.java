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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;

public class LocationSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        Intent intent = getIntent();
        ArrayList<Location> searchResults = intent.getParcelableArrayListExtra("SEARCH_RESULTS");
        // convert list of strings into list of locations
        RecyclerView recyclerView = findViewById(R.id.search_results);
        setupRecyclerView(recyclerView, searchResults);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.search_results);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Location> list) {
        final Model model = Model.getInstance();
        recyclerView.setAdapter(new LocationSearchActivity.SimpleItemRecyclerViewAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder> {

        /**
         * The items to be shown in the list
         */
        private final List<Location> mInventory;

        /**
         * Sets the items to be used by the adapter.
         *
         * @param inventory
         */
        SimpleItemRecyclerViewAdapter(List<Location> inventory) {
            mInventory = inventory;
        }

        @Override
        public LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_list_content, parent, false);
            return new LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final LocationSearchActivity.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            final Model model = Model.getInstance();
            holder.mLocation = mInventory.get(position);
            holder.mContentView.setText(mInventory.get(position).toString());

            holder.mView.setOnClickListener( (View v) -> {
                Context context = v.getContext();
                Intent item_detail_intent = new Intent(context, LocationDetailActivity.class);
                model.setCurrentLocation(holder.mLocation);
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
            public Location mLocation;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }

        }
    }
}
