package kantwonskids.donationtrackerg14b.controller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kantwonskids.donationtrackerg14b.R;
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
    private static final String ARG_NAME = "name";
    //public static final String ARG_ADDRESS = "address";
    //public static final String ARG_CITY = "city";
    //public static final String ARG_STATE = "state";

    /**
     * The content this fragment is presenting.
     */
    private Location mLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        Bundle bundle = getArguments();
        if (bundle.containsKey(ARG_NAME)) {
            Model model = Model.getInstance();
            mLocation = model.getCurrentLocation();
//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mLocation.getName());
//            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);

        if (mLocation != null) {
            TextView locationTextView = rootView.findViewById(R.id.location_detail_container);
            locationTextView.setText(mLocation.detailString());

            // store each donation's info
//            Collection<Donation> donations = mLocation.getDonations();
//            Iterator<Donation> iteration = donations.iterator();
//            // remove this
//            String [] donationStrings = new String[donations.size() + 1];
//            int i = 0;
//            while (iteration.hasNext()) {
//                Donation d = iteration.next();
//                donationStrings[i] =
//                                Integer.toString(i) + ".\t\t\t" + d.getCategory().toString() +
//                                 ":\t\t\t\t\t\t" + d.getName() +
//                                "\n\t\t\t\t\t\t" + d.getDescription() + "\n";
//                i++;
//            }
//            // remove this
//            donationStrings[i] = "1.\t\t\tFOOD:\t\t\t\t\t\t Spaghetti\n\t\t\t\t\t\tWhat
// a tasty treat!\n";
//            // create an adapter for the donations
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
// android.R.layout.simple_list_item_1, donationStrings);
//
//            ((ListView) rootView.findViewById(R.id.donations_at_location)).setAdapter(adapter);
        }

        return rootView;
    }
}
