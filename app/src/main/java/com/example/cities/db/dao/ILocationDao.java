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

    @Query("SELECT * FROM Locations WHERE Id=:locationId")
    Location findLocationById(int locationId);
}
