package com.example.halong.myapplication.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

@Dao
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)  //对于id重复的item，后者replace前者
    void addItem(T... items);

    @Delete
    void removeItem(T... items);

    @Update
    void updateItem(T... items);
}
