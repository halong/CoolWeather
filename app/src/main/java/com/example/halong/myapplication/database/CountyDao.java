package com.example.halong.myapplication.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.bean.County;

import java.util.List;

@Dao
public interface CountyDao {
    @Query("select * from counties where cityId= :cityId")
    LiveData<List<County>> getCountiesByCityId(int cityId);

    @Query("select * from counties where id = :id")
    LiveData<County> get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //对于id重复的item，后者replace前者
    void addItem(County... items);

    @Delete
    void removeItem(County... items);

    @Update
    void updateItem(County... items);
}
