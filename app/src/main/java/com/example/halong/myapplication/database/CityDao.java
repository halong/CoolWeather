package com.example.halong.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.halong.myapplication.bean.City;

import java.util.List;

@Dao
public interface CityDao {
    @Query("select * from cities")
    List<City> getAll();

    @Query("select * from cities where id = :id")
    City get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //对于id重复的item，后者replace前者
    void addItem(City... items);

    @Delete
    void removeItem(City... items);

    @Update
    void updateItem(City... items);
}
