package kantwonskids.donationtrackerg14b.controller;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Location;

public class LocationSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        Intent intent = getIntent();
        ArrayList<Location> searchResults = intent.getParcelableArrayListExtra("SEARCH_RESULTS");
    }


}
