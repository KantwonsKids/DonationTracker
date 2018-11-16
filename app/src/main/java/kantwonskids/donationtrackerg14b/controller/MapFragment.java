package kantwonskids.donationtrackerg14b.controller;


import android.graphics.Camera;
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

public class MapFragment extends Fragment implements OnMapReadyCallback {


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the root view into the fragment.
        View rootView = inflater.inflate(R.layout.activity_map, container, false);

        // Set up maps.
        MapView mMapView = rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        MapsInitializer.initialize(getActivity().getApplicationContext());
        mMapView.getMapAsync(this);

        return rootView;

    }

    @Override
    public void onMapReady(GoogleMap map) {
        Model model = Model.getInstance();
        List<Location> locs = model.getLocationList();

        // Zoom camera.
        LatLng cameraPos = new LatLng(locs.get(0).getLatitude(), locs.get(0).getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLng(cameraPos));
        // 15 is "street view" zoom level.
        // 10 is "city view" zoom level.
        // 10 is best to display all pins at once.
        map.moveCamera(CameraUpdateFactory.zoomTo(10));


        // Put a marker at every location in the location list.
        for (Location loc : locs) {
            LatLng ltlng = new LatLng(loc.getLatitude(), loc.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(ltlng).title(loc.getName()).snippet(loc.getPhoneNumber());
            map.addMarker(marker);
        }
    }

}
