package com.example.halong.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.halong.myapplication.bean.County;

import java.util.List;

@Dao
public interface CountyDao extends BaseDao<County> {
    @Query("select * from counties")
    List<County> getAll();

    @Query("select * from counties where id = :id")
    County getCountyById(int id);
}
