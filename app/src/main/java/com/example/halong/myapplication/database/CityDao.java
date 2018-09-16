package com.example.halong.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.halong.myapplication.bean.City;

import java.util.List;

@Dao
public interface CityDao extends BaseDao<City> {
    @Query("select * from cities")
    List<City> getAll();

    @Query("select * from cities where id = :id")
    City getCityById(int id);
}
