package kantwonskids.donationtrackerg14b.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.time.LocalDateTime;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.DonationCategory;

/**
 * an Activity to represent the inventory (list of donation items)
 * at a given location
 */
public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Button addItemButton = (Button) findViewById(R.id.addItem);
        addItemButton.setOnClickListener((view) -> {
            Intent intent_addToInventory = new Intent(this, NewItemActivity.class);
            startActivity(intent_addToInventory);
        });

        View recyclerView = findViewById(R.id.inventory);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        final Model model = Model.getInstance();
        recyclerView.setAdapter(new InventoryActivity.SimpleItemRecyclerViewAdapter(
                model.getCurrentLocation().getDonations()));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<InventoryActivity.SimpleItemRecyclerViewAdapter.ViewHolder> {

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
        public InventoryActivity.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.inventory_content, parent, false);
            return new InventoryActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final InventoryActivity.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            final Model model = Model.getInstance();
            holder.mDonation = mInventory.get(position);
            holder.mContentView.setText(mInventory.get(position).toString());

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
            public Donation mDonation;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
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
