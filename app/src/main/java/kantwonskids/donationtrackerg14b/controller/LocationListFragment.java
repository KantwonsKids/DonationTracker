package kantwonskids.donationtrackerg14b.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * Fragment for the location list.
 * Contains a recycler view to show all locations currently loaded.
 * Used as the fragment for one of the tabs on the main screen.
 */
public class LocationListFragment extends Fragment {
    RecyclerView recyclerView;

    /**
     * Sets up the recycler view.
     * @param recyclerView the recycler view to set up
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(
                Model.getInstance().locationList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * View adapter for the recycler view. Populates the recycler view with the elements of the global location list.
     */
    private static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        /**
         * The items to be shown in the list
         */
        private final List<Location> mLocations;

        /**
         * Sets the items to be used by the adapter.
         * @param locations the list of locations
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the root view into the fragment
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        // Get the recycler view
        recyclerView = (RecyclerView) rootView.findViewById(R.id.location_list);

        // set up the recycler view
        setupRecyclerView(recyclerView);
        return rootView;
    }
}
