package com.example.cities;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.cities.db.dao.ILocationDao;
import com.example.cities.db.dao.IPopulationDao;
import com.example.cities.db.database.AppDatabase;
import com.example.cities.db.entity.Location;
import com.example.cities.db.entity.Population;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DbInstrumentedTest {
    private AppDatabase db;
    private ILocationDao locationDao;
    private IPopulationDao populationDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        locationDao = db.locationDao();
        populationDao = db.populationDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeLocationAndReadInList() throws Exception {
        Location location = new Location();
        location.setLongitude("23.67534234234");
        location.setLatitude("3.8711253029");
        locationDao.insertLocation(location);
        List<Location> locations = locationDao.findAllLocationsByLat("3.8711253029");
        assertEquals(locations.isEmpty(), false);
    }

    @Test
    public void writePopulationAndReadInList() throws Exception {
        Population population = new Population();
        population.setDensity("123os/km2");
        population.setInhabitantsNumber(23345477);
        populationDao.insertPopulation(population);
        List<Population> populations = populationDao.findAllPopulationsByDensity("123os/km2");
        assertEquals(populations.isEmpty(), false);
    }
}
