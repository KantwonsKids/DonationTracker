package kantwonskids.donationtrackerg14b.controller;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Location;
import kantwonskids.donationtrackerg14b.model.Model;

/**
 * Fragment for a map view of all locations.
 * @author Amanda
 * @version 1.0
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) throws NullPointerException {
        // Inflate the root view into the fragment.
        View rootView = inflater.inflate(R.layout.activity_map, container, false);

        // Set up maps.
        MapView mMapView = rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        Activity activity = getActivity();
        if (activity != null) {
            MapsInitializer.initialize(activity.getApplicationContext());
            mMapView.getMapAsync(this);
        }

        return rootView;

    }

    @Override
    public void onMapReady(GoogleMap map) {
        Model model = Model.getInstance();
        List<Location> locations = model.getLocationList();

        // Zoom camera.
        Location first = locations.get(0);
        LatLng cameraPos = first.getLatLng();
        map.moveCamera(CameraUpdateFactory.newLatLng(cameraPos));
        // 15 is "street view" zoom level.
        // 10 is "city view" zoom level.
        // 10 is best to display all pins at once.
        map.moveCamera(CameraUpdateFactory.zoomTo(10));


        // Put a marker at every location in the location list.
        for (Location loc : locations) {
            LatLng latLong = loc.getLatLng();
            MarkerOptions marker = new MarkerOptions();
            marker.position(latLong);
            marker.title(loc.getName());
            marker.snippet(loc.getPhoneNumber());
            map.addMarker(marker);
        }
    }

}
