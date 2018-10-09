package com.example.halong.myapplication.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "counties")
public class County  {
    /**
     * id : 1837
     * name : 昆明
     * weather_id : CN101290101
     */

    @PrimaryKey
    private int id;
    private String name;
    private String weather_id;

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

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }

    @Override
    public String toString() {
        return name;
    }
}
