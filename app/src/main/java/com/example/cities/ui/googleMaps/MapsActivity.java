package com.example.cities.ui.googleMaps;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.cities.R;
import com.example.cities.db.database.DatabaseClient;
import com.example.cities.db.entity.City;
import com.example.cities.db.entity.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpGoogleMap();
        displayCitiesMarkersOnMap();
    }

    private void setUpGoogleMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setOnMapClick();
    }

    void addMarkOnMap(double lat, double lng, String markTitle) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(markTitle));
    }

    void moveCameraOnMap(double lat, double lng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
    }

    void setOnMapClick() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Geocoder geoCoder = new Geocoder (getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    String address = "";
                    if (addresses.size() > 0)
                        for (int i = 0; i <= addresses.get(0).getMaxAddressLineIndex(); i++)
                            address += addresses.get(0).getAddressLine(i) + "\n";
                    else
                        Log.e("addresses.size","< 0");
                    address += "[" + latLng.latitude + ", " + latLng.longitude + "]";
                    Toast.makeText(getBaseContext(),address,Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void displayCitiesMarkersOnMap() {
        class ViewCities extends AsyncTask<Void, Void, List<City>> {
            List<Location> locations;

            @Override
            protected List<City> doInBackground(Void... voids) {
                locations = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .locationDao()
                        .findAllLocations();
                return DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .cityDao()
                        .findAllCities();
            }

            @Override
            protected void onPostExecute(List<City> cities) {
                super.onPostExecute(cities);
                markCitiesOnMap(cities, locations);
                moveCameraOnMap(Double.parseDouble(locations.get(0).getLatitude()), Double.parseDouble(locations.get(0).getLongitude()));
            }

            private void markCitiesOnMap(List<City> cityList, List<Location> locationList) {
                for (int i = 0; i < cityList.size(); i++)
                    addMarkOnMap(Double.parseDouble(locationList.get(i).getLatitude()),
                            Double.parseDouble(locationList.get(i).getLongitude()),
                            cityList.get(i).getName());
            }
        }
        new ViewCities().execute();
    }
}