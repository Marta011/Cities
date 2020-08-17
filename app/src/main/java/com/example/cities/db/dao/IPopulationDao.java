package com.example.cities.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cities.db.entity.Population;

import java.util.List;

@Dao
public interface IPopulationDao {

    @Insert
    void insertAllPopulations(List<Population> populations);

    @Insert
    void insertPopulation(Population population);

    @Query("SELECT * FROM Populations WHERE Id=:populationId")
    Population findPopulationById(int populationId);

    @Query("SELECT * FROM Populations WHERE Density=:densityStr")
    List<Population> findAllPopulationsByDensity(String densityStr);
}
