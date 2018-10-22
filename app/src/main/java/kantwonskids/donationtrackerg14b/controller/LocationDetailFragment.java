package kantwonskids.donationtrackerg14b.controller;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Donation;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * @author Juliana Petrillo
 * @version 2.0
 *
 * A fragment representing a single Location detail screen.
 */
public class LocationDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_NAME = "name";
    public static final String ARG_ADDRESS = "address";
    public static final String ARG_CITY = "city";
    public static final String ARG_STATE = "state";

    /**
     * The content this fragment is presenting.
     */
    private Location mLocation;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_NAME)) {
            Model model = Model.getInstance();
            mLocation = model.getCurrentLocation();
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mLocation.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);

        if (mLocation != null) {
            ((TextView) rootView.findViewById(R.id.location_detail)).setText(
                    String.format("Location: "+ "\n" +
                            mLocation.getAddress() + "\n" +
                            mLocation.getCity() + ", " +
                            mLocation.getState() + " " +
                            mLocation.getZipcode() + "\n\n" + "Contact: " + "\n" +
                            mLocation.getPhoneNumber() + "\n" +
                            mLocation.getWebsite()));

            // store each donation's info
            Collection<Donation> donations = mLocation.getDonations();
            Iterator<Donation> iter = donations.iterator();
            // TODO: REMOVE THAT + 1 CAUSE ITS A PLACEHOLDER FOR NO REAL DONATIONS IN THE LIST
            String [] donationStrings = new String[donations.size() + 1];
            int i = 0;
            while (iter.hasNext()) {
                Donation d = iter.next();
                donationStrings[i] =
                                Integer.toString(i) + ".\t\t\t" + d.getCategory().toString() +
                                 ":\t\t\t\t\t\t" + d.getName() +
                                "\n\t\t\t\t\t\t" + d.getDescription() + "\n";
                i++;
            }
            // TODO: REMOVE THIS WHEN THERE ARE REAL DONATIONS
            donationStrings[i] = "1.\t\t\tFOOD:\t\t\t\t\t\t Spaghetti\n\t\t\t\t\t\tWhat a tasty treat!\n";
            // create an adapter for the donations
            ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, donationStrings);

            ((ListView) rootView.findViewById(R.id.donations_at_location)).setAdapter(adap);
        }

        return rootView;
    }
}
