package kantwonskids.donationtrackerg14b.controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.content.res.Resources;

import kantwonskids.donationtrackerg14b.R;
import kantwonskids.donationtrackerg14b.model.Model;
import kantwonskids.donationtrackerg14b.model.Donation;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Juliana Petrillo
 * @version 2.0
 *
 * An activity representing a list of Locations. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of locations, which when touched,
 * lead to a {@link LocationDetailActivity} representing
 * location details. On tablets, the activity presents the list of locations and
 * location details side-by-side using two vertical panes.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ActionBar nonNull = Objects.requireNonNull(ab);
        nonNull.setTitle(R.string.home_page_title);
//        ab.setDisplayHomeAsUpEnabled(true);

        // Search
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
//            SearchableList locationList = Model.getInstance().locationList;
            //Model model = Model.getInstance();
            List<Donation> donationList = Model.getInstance().getAllDonations();
            List<Donation> searchResults = Model.search(donationList, query);

            Intent resultsIntent = new Intent(this,
                    DonationSearchResultsActivity.class);
            resultsIntent.putParcelableArrayListExtra("SEARCH_RESULTS",
                    (ArrayList<Donation>) searchResults);
            startActivity(resultsIntent);
        }

        // set up tabs
        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.location_list_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.addTab(tabLayout.newTab().setText("text"));

    }

    /**
     * Sets up the view pager, which manages what tabs are displayed.
     * Here is where new tabs would be added via addFragment().
     * @param vp the view pager to set up
     */
    private void setupViewPager(ViewPager vp) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        LocationListFragment listFragment = new LocationListFragment();
        Resources resources = getResources();
        adapter.addFragment(listFragment, resources.getString(R.string.location_tab_title));

        MapFragment mapFragment = new MapFragment();
        adapter.addFragment(mapFragment, resources.getString(R.string.map_tab_title));
        vp.setAdapter(adapter);
    }

    private final class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList;
        private final List<String> fragmentTitleList;

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            fragmentList = new ArrayList<>();
            fragmentTitleList = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        // Set up the search bar
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        SearchManager nonNull = Objects.requireNonNull(searchManager);
        searchView.setSearchableInfo(nonNull.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.advanced_search:
                // Show the advanced search activity
                Intent intent = new Intent(this, AdvancedSearchActivity.class);
                intent.putExtra("SCOPE", "ALL");
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Logs the user out when the logout button is pressed and goes back to the login screen.
     */
    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        // Log out the current user
        //Model model = Model.getInstance();
        Model.getInstance().setCurrentUser(null);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Logout?");
        builder.setMessage("Press OK to be logged out of the app. Press CANCEL"
                + " or tap outside of this dialogue to return to the app.");
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.setPositiveButton("OK", (dialog, which) -> logout());
        builder.show();
    }
}
