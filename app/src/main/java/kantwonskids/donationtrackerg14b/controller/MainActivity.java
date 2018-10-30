package kantwonskids.donationtrackerg14b.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.User;

/**
 * The main dashboard activity.
 * Can only be accessed by logging in, and will throw an exception if no CURRENT_USER is specified.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User currentUser = getIntent().getParcelableExtra("CURRENT_USER");
        if (currentUser == null) {
            throw new IllegalArgumentException("Cannot log in without specifying a user!");
        }
        Model.getInstance().setCurrentUser(currentUser);

        setContentView(R.layout.activity_location_list);

        View recyclerView = findViewById(R.id.location_list);
        assert recyclerView != null;
//        setupRecyclerView((RecyclerView) recyclerView);

        // set up the app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Locations");
//        ab.setDisplayHomeAsUpEnabled(true);

//        ImageButton  sidebarButton = (ImageButton) findViewById(R.id.sidebarButton);
//        sidebarButton.setOnClickListener((view) -> {
//            logout();
//        });

        // set up tabs
//        TabLayout tabLayout = findViewById(R.id.location_list_tab_layout);
    }

}
