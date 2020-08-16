package com.example.cities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cities.helpers.DbOperations;
import com.example.cities.ui.city.CityFragment;
import com.example.cities.ui.googleMaps.MapsActivity;
import com.example.cities.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private NavController navController;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attributeWidgets();
        setUpToolbar();
        setUpAppBarConfiguration();
        setUpNavigationUI();
        setUpMenuOptionActions();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.nav_map:
                        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_city:
                        // Create new fragment and transaction
                        Fragment newFragment = new CityFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack if needed
                        transaction.replace(R.id.nav_host_fragment, newFragment);
                        transaction.addToBackStack(null);
                        // Commit the transaction
                        transaction.commit();
                        break;
                    default:
                        Fragment homeFragment = new HomeFragment();
                        FragmentTransaction transactionHome = getSupportFragmentManager().beginTransaction();
                        transactionHome.replace(R.id.nav_host_fragment, homeFragment);
                        transactionHome.addToBackStack(null);
                        transactionHome.commit();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    void setUpMenuOptionActions() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_populateDb_Location)
                    DbOperations.populateDb_locationsTable(getApplicationContext());
                else if (item.getItemId() == R.id.menu_populateDb_Population)
                    DbOperations.populateDb_populationsTable(getApplicationContext());
                else
                    DbOperations.populateDb_citiesTable(getApplicationContext());
                return false;
            }
        });
    }

    void setUpAppBarConfiguration() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_city)
                .setDrawerLayout(drawer)
                .build();
    }

    void setUpNavigationUI() {
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    void setUpToolbar() {
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);
    }

    void attributeWidgets() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }
}