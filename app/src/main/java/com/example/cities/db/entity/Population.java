package com.example.cities.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Populations")
public class Population {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @ColumnInfo(name = "InhabitantsNumber")
    private int inhabitantsNumber;

    @ColumnInfo(name = "Density")
    private String density;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInhabitantsNumber() {
        return inhabitantsNumber;
    }

    public void setInhabitantsNumber(int inhabitantsNumber) {
        this.inhabitantsNumber = inhabitantsNumber;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }
}
