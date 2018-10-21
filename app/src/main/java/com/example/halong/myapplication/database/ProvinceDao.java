package com.example.halong.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.bean.Province;

import java.util.List;

@Dao
public interface ProvinceDao {
    @Query("select * from provinces")
    List<Province> getAll();

    @Query("select * from provinces where id = :id")
    Province get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //对于id重复的item，后者replace前者
    void addItem(Province... items);

    @Delete
    void removeItem(Province... items);

    @Update
    void updateItem(Province... items);
}
