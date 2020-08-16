package com.example.cities.helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cities.db.database.DatabaseClient;
import com.example.cities.db.entity.City;
import com.example.cities.db.entity.Location;
import com.example.cities.db.entity.Population;

import java.util.ArrayList;
import java.util.List;

public class DbOperations {
    private static DbOperations singleton;

    private DbOperations() {
    }

    public static DbOperations getInstance() {
        if (singleton == null)
            singleton = new DbOperations();
        return singleton;
    }

    public static void populateDb_locationsTable(final Context context) {

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

        class InsertLocations extends AsyncTask<Location, Void, Void> {
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

    public static void populateDb_populationsTable(final Context context) {

        final List<Population> populations = new ArrayList<>();

        Population populationGdynia = new Population();
        populationGdynia.setInhabitantsNumber(123433);
        populationGdynia.setDensity("1830os./km2");

        Population populationGdansk = new Population();
        populationGdansk.setInhabitantsNumber(466631);
        populationGdansk.setDensity("1772os./km2");

        Population populationSopot = new Population();
        populationSopot.setInhabitantsNumber(33500);
        populationSopot.setDensity("2114,2os./km2");

        Population populationBialystok = new Population();
        populationBialystok.setInhabitantsNumber(297459);
        populationBialystok.setDensity("2912os./km2");

        Population populationWarszawa = new Population();
        populationWarszawa.setInhabitantsNumber(1777972);
        populationWarszawa.setDensity("3437os./km2");

        Population populationKrakow = new Population();
        populationKrakow.setInhabitantsNumber(774839);
        populationKrakow.setDensity("2371os./km2");

        Population populationKatowice = new Population();
        populationKatowice.setInhabitantsNumber(294510);
        populationKatowice.setDensity("1799os./km2");

        populations.add(populationGdynia);
        populations.add(populationGdansk);
        populations.add(populationSopot);
        populations.add(populationBialystok);
        populations.add(populationWarszawa);
        populations.add(populationKrakow);
        populations.add(populationKatowice);

        class InsertPopulations extends AsyncTask<Population, Void, Void> {
            @Override
            protected Void doInBackground(Population... population) {
                DatabaseClient.
                        getInstance(context).
                        getAppDatabase().
                        populationDao().
                        insertAllPopulations(populations);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, "Dodano populacje pomyślnie", Toast.LENGTH_SHORT).show();
            }
        }
        new InsertPopulations().execute();
    }

    public static void populateDb_citiesTable(final Context context) {

        final List<City> cities = new ArrayList<>();

        City cityGdynia = new City();
        cityGdynia.setName("Gdynia");
        cityGdynia.setCountry("Polska");
        cityGdynia.setProvince("pomorskie");
        cityGdynia.setLicensePlate("GA");
        cityGdynia.setCityLaw("10.02.1926");
        cityGdynia.setCreationDate("przed 1253r.");
        cityGdynia.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/POL_Gdynia_COA.svg/800px-POL_Gdynia_COA.svg.png");
        cityGdynia.setLocationId(1);
        cityGdynia.setPopulationId(1);

        City cityGdansk = new City();
        cityGdansk.setName("Gdańsk");
        cityGdansk.setCountry("Polska");
        cityGdansk.setProvince("pomorskie");
        cityGdansk.setLicensePlate("GD");
        cityGdansk.setCityLaw("1457");
        cityGdansk.setCreationDate("997r.");
        cityGdansk.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/POL_Gda%C5%84sk_COA.svg/1280px-POL_Gda%C5%84sk_COA.svg.png");
        cityGdansk.setLocationId(2);
        cityGdansk.setPopulationId(2);

        City citySopot = new City();
        citySopot.setName("Sopot");
        citySopot.setCountry("Polska");
        citySopot.setProvince("pomorskie");
        citySopot.setLicensePlate("GSP");
        citySopot.setCityLaw("08.10.1901r.");
        citySopot.setCreationDate("VIII w.");
        citySopot.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/POL_Sopot_COA.svg/800px-POL_Sopot_COA.svg.png");
        citySopot.setLocationId(3);
        citySopot.setPopulationId(3);

        City cityBialystok = new City();
        cityBialystok.setName("Białystok");
        cityBialystok.setCountry("Polska");
        cityBialystok.setProvince("podlaskie");
        cityBialystok.setLicensePlate("BI");
        cityBialystok.setCityLaw("27.07.1691");
        cityBialystok.setCreationDate("1440-1444");
        cityBialystok.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/POL_Bia%C5%82ystok_formal_COA.svg/491px-POL_Bia%C5%82ystok_formal_COA.svg.png");
        cityBialystok.setLocationId(4);
        cityBialystok.setPopulationId(4);

        City cityWarszawa = new City();
        cityWarszawa.setName("Warszawa");
        cityWarszawa.setCountry("Polska");
        cityWarszawa.setProvince("mazowieckie");
        cityWarszawa.setLicensePlate("WA");
        cityWarszawa.setCityLaw("brak danych");
        cityWarszawa.setCreationDate("XIII w.");
        cityWarszawa.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/POL_Warszawa_COA.svg/357px-POL_Warszawa_COA.svg.png");
        cityWarszawa.setLocationId(5);
        cityWarszawa.setPopulationId(5);

        City cityKrakow = new City();
        cityKrakow.setName("Kraków");
        cityKrakow.setCountry("Polska");
        cityKrakow.setProvince("małopolskie");
        cityKrakow.setLicensePlate("KR");
        cityKrakow.setCityLaw("1257r.");
        cityKrakow.setCreationDate("brak danych");
        cityKrakow.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/POL_Krak%C3%B3w_COA.svg/389px-POL_Krak%C3%B3w_COA.svg.png");
        cityKrakow.setLocationId(6);
        cityKrakow.setPopulationId(6);

        City cityKatowice = new City();
        cityKatowice.setName("Katowice");
        cityKatowice.setCountry("Polska");
        cityKatowice.setProvince("śląskie");
        cityKatowice.setLicensePlate("SK");
        cityKatowice.setCityLaw("11.09.1865r.");
        cityKatowice.setCreationDate("XVI w.");
        cityKatowice.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Katowice_Herb.svg/487px-Katowice_Herb.svg.png");
        cityKatowice.setLocationId(7);
        cityKatowice.setPopulationId(7);

        cities.add(cityGdynia);
        cities.add(cityGdansk);
        cities.add(citySopot);
        cities.add(cityBialystok);
        cities.add(cityWarszawa);
        cities.add(cityKrakow);
        cities.add(cityKatowice);

        class InsertCities extends AsyncTask<City, Void, Void> {
            @Override
            protected Void doInBackground(City... city) {
                DatabaseClient.
                        getInstance(context).
                        getAppDatabase().
                        cityDao().
                        insertAllCities(cities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, "Dodano miasta pomyślnie", Toast.LENGTH_SHORT).show();
            }
        }
        new InsertCities().execute();
    }
}
