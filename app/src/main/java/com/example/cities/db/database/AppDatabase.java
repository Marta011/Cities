package com.example.cities.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cities.db.dao.ICityDao;
import com.example.cities.db.dao.ILocationDao;
import com.example.cities.db.dao.IPopulationDao;
import com.example.cities.db.entity.City;
import com.example.cities.db.entity.Location;
import com.example.cities.db.entity.Population;

@Database(entities = {Location.class, Population.class, City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ILocationDao locationDao();

    public abstract IPopulationDao populationDao();

    public abstract ICityDao cityDao();

}
