package com.example.cities.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cities.db.entity.City;

import java.util.List;

@Dao
public interface ICityDao {
    @Insert
    void insertAllCities(List<City> cities);

    @Query("SELECT * FROM Cities")
    List<City> findAllCities();

    @Query("SELECT * FROM Cities WHERE Id=:cityId LIMIT 1")
    City findCityById(int cityId);
}
