package com.example.halong.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.halong.myapplication.bean.Province;

import java.util.List;

@Dao
public interface ProvinceDao extends BaseDao<Province> {
    @Query("select * from provinces")
    List<Province> getAll();

    @Query("select * from provinces where id = :id")
    Province get(int id);
}
