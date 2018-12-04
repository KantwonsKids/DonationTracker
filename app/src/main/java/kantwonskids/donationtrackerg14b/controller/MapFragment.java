package kantwonskids.donationtrackerg14b.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import java.util.List;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.OurLocation;
import kantwonskids.donationtrackerg14b.model.Model;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


/**
 * Fragment for a map view of all locations.
 * @author Amanda Schmitt
 * @version 1.0
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int LOCATION_REQUEST_CODE = 101;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);

        // Inflate the root view into the fragment.
        View rootView = inflater.inflate(R.layout.activity_map, container, false);

        // Set up maps.
        MapView mMapView = rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        Activity activity = this.getActivity();

        MapsInitializer.initialize(activity.getApplicationContext());

        mMapView.getMapAsync(this);

        Context temp = activity.getApplicationContext();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(temp);

        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, LOCATION_REQUEST_CODE);
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener((AppCompatActivity) activity, location -> {
                    if (location != null) {
                        LatLng ltlng = new LatLng(location.getLatitude(),
                                location.getLongitude());
                        MarkerOptions marker = new MarkerOptions().position(ltlng)
                                .title("You are here")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        mMap.addMarker(marker);
                    }
                });
        return rootView;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    Log.d("it worked?","message to say it worked");
                } else
                    Toast.makeText(this.getContext(), "Location Permission Denied", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        Model model = Model.getInstance();
        List<OurLocation> locs = model.getLocationList();

        // Zoom camera.
        OurLocation first = locs.get(0);
        LatLng cameraPos = first.getLatLng();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPos));
        // 15 is "street view" zoom level.
        // 10 is "city view" zoom level.
        // 10 is best to display all pins at once.
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Put a marker at every location in the location list.
        for (OurLocation loc : locs) {
            LatLng ltlng = loc.getLatLng();
            MarkerOptions marker = new MarkerOptions().position(ltlng)
                    .title(loc.getName()).snippet(loc.getPhoneNumber());
            mMap.addMarker(marker);
        }



    }

}
