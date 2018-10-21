package com.example.halong.myapplication.database;

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
    @Query("select * from counties")
    List<County> getAll();

    @Query("select * from counties where id = :id")
    County get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //对于id重复的item，后者replace前者
    void addItem(County... items);

    @Delete
    void removeItem(County... items);

    @Update
    void updateItem(County... items);
}
