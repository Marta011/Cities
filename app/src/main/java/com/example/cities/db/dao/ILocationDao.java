package com.example.cities.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cities.db.entity.Location;

import java.util.List;

@Dao
public interface ILocationDao {
    @Insert
    void insertAllLocations(List<Location> locations);

    @Insert
    void insertLocation(Location location);

    @Query("SELECT * FROM Locations WHERE Id=:locationId")
    Location findLocationById(int locationId);

    @Query("SELECT * FROM Locations")
    List<Location> findAllLocations();

    @Query("SELECT * FROM Locations WHERE Latitude=:lat")
    List<Location> findAllLocationsByLat(String lat);
}
