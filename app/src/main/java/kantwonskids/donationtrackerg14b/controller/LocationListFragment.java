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
import kantwonskids.donationtrackerg14b.model.OurLocation;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * Fragment for the location list.
 * Contains a recycler view to show all locations currently loaded.
 * Used as the fragment for one of the tabs on the main screen.
 */
public class LocationListFragment extends Fragment {

    /**
     * Sets up the recycler view.
     * @param recyclerView the recycler view to set up
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * View adapter for the recycler view. Populates the recycler view with the elements
     * of the global location list.
     */
    private static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        /**
         * The items to be shown in the list
         */
        private final List<OurLocation> mLocations;

        /**
         * Sets the items to be used by the adapter.
         */
        SimpleItemRecyclerViewAdapter() {
            mLocations = Model.getInstance().getLocationList();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater
                    .inflate(R.layout.location_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            //final Model model = Model.getInstance();
            holder.mLocation = mLocations.get(position);
            OurLocation location = mLocations.get(position);
            holder.mContentView.setText(location.toString());

            holder.mView.setOnClickListener(v -> {
                Context context = v.getContext();
                Intent intent = new Intent(context,
                        LocationDetailActivity.class);
                //intent.putExtra(LocationDetailFragment.ARG_NAME,
                //      holder.mLocation.getName());
                Model.getInstance().setCurrentLocation(holder.mLocation);
                context.startActivity(intent);
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
            final View mView;
            final TextView mContentView;
            OurLocation mLocation;


            ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = view.findViewById(R.id.content);
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the root view into the fragment
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        // Get the recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.location_list);

        // set up the recycler view
        setupRecyclerView(recyclerView);
        return rootView;
    }
}
