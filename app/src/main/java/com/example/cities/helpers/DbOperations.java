package com.example.cities.helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cities.db.database.AppDatabase;
import com.example.cities.db.database.DatabaseClient;
import com.example.cities.db.entity.Location;

import java.util.ArrayList;
import java.util.List;

public class DbOperations {
    private static DbOperations singleton;

    private DbOperations() { }

    public static DbOperations getInstance() {
        if (singleton == null)
            singleton = new DbOperations();
        return singleton;
    }

    public static void populateDb(final Context context) {

        final List<Location> locations = new ArrayList<>();

        Location locationGdynia = new Location();
        locationGdynia.setLatitude("54.51525353137278");
        locationGdynia.setLongitude("18.533814512193203");

        Location locationGdansk = new Location();
        locationGdansk.setLatitude("54.34645766065677");
        locationGdansk.setLongitude("18.655145913362503");

        Location locationSopot = new Location();
        locationSopot.setLatitude("54.446525147595594");
        locationSopot.setLongitude("18.573247008025646");

        Location locationBialystok = new Location();
        locationBialystok.setLatitude("53.13128459585991");
        locationBialystok.setLongitude("23.178987801074985");

        Location locationWarszawa = new Location();
        locationWarszawa.setLatitude("52.22746570023589");
        locationWarszawa.setLongitude("21.026040986180305");

        Location locationKrakow = new Location();
        locationKrakow.setLatitude("50.064577845986015");
        locationKrakow.setLongitude("19.947691038250923");

        Location locationKatowice = new Location();
        locationKatowice.setLatitude("50.2685906951073");
        locationKatowice.setLongitude("18.965222351253033");

        locations.add(locationGdynia);
        locations.add(locationGdansk);
        locations.add(locationSopot);
        locations.add(locationBialystok);
        locations.add(locationWarszawa);
        locations.add(locationKrakow);
        locations.add(locationKatowice);

        class InsertLocations extends AsyncTask<Location, Void, Void>
        {
            @Override
            protected Void doInBackground(Location... location) {
                DatabaseClient.
                        getInstance(context).
                        getAppDatabase().
                        locationDao().
                        insertAllLocations(locations);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, "Dodano lokalizacje pomyślnie", Toast.LENGTH_SHORT).show();
            }
        }
        new InsertLocations().execute();
    }
}
