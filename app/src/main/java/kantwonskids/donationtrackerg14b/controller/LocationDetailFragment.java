package kantwonskids.donationtrackerg14b.controller;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.controller.dummy.DummyContent;
import kantwonskids.donationtrackerg14b.model.DonationData;
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
    private DonationData mLocation;

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
        }

        return rootView;
    }
}
