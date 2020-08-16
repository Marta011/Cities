package com.example.cities.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Cities",
        foreignKeys = {
                @ForeignKey(entity = Location.class,
                        parentColumns = "Id",
                        childColumns = "LocationId",
                        onDelete = CASCADE),
                @ForeignKey(entity = Population.class,
                        parentColumns = "Id",
                        childColumns = "PopulationId",
                        onDelete = CASCADE)
        }
)
public class City {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Country")
    private String country;

    @ColumnInfo(name = "Province")
    private String province;

    @ColumnInfo(name = "LicensePlate")
    private String licensePlate;

    @ColumnInfo(name = "CityLaw")
    private String cityLaw;

    @ColumnInfo(name = "CreationDate")
    private String creationDate;

    @ColumnInfo(name = "ImageUrl")
    private String imageUrl;

    @ColumnInfo(name = "LocationId")
    private int locationId;

    @ColumnInfo(name = "PopulationId")
    private int populationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCityLaw() {
        return cityLaw;
    }

    public void setCityLaw(String cityLaw) {
        this.cityLaw = cityLaw;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getPopulationId() {
        return populationId;
    }

    public void setPopulationId(int populationId) {
        this.populationId = populationId;
    }
}
