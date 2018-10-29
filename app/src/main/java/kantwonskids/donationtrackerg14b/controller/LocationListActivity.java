package kantwonskids.donationtrackerg14b.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.*;

import java.util.List;

/**
 * @author Juliana Petrillo
 * @version 2.0
 *
 * An activity representing a list of Locations. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of locations, which when touched,
 * lead to a {@link LocationDetailActivity} representing
 * location details. On tablets, the activity presents the list of locations and
 * location details side-by-side using two vertical panes.
 */
public class LocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        View recyclerView = findViewById(R.id.location_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        ImageButton  sidebarButton = (ImageButton) findViewById(R.id.sidebarButton);
        sidebarButton.setOnClickListener((view) -> {
            logout();
        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(
                Model.donationDataList));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        /**
         * The items to be shown in the list
         */
        private final List<Location> mLocations;

        /**
         * Sets the items to be used by the adapter.
         * @param locations
         */
        SimpleItemRecyclerViewAdapter(List<Location> locations) {
            mLocations = locations;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Model model = Model.getInstance();
            holder.mLocation = mLocations.get(position);
            holder.mContentView.setText(mLocations.get(position).toString());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context,
                            LocationDetailActivity.class);
                    //intent.putExtra(LocationDetailFragment.ARG_NAME,
                      //      holder.mLocation.getName());
                    model.setCurrentLocation(holder.mLocation);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            if (mLocations != null) {
                return mLocations.size();
            } else {
                return 0;
            }
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

    /**
     * Logs the user out when the logout button is pressed and goes back to the login screen.
     */
    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        // Log out the current user
        Model.getInstance().setCurrentUser(null);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder =
                new AlertDialog.Builder(LocationListActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Logout?");
        builder.setMessage("Press OK to be logged out of the app. Press CANCEL"
                + " or tap outside of this dialogue to return to the app.");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        builder.show();
    }
}
