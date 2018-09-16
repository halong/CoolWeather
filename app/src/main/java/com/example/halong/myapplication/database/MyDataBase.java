package com.example.halong.myapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.halong.myapplication.MyApplication;
import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.bean.County;
import com.example.halong.myapplication.bean.Province;

@Database(entities = {City.class, County.class,Province.class},version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    private static volatile MyDataBase Instance;

    public static MyDataBase getInstance() {
        if (Instance == null) {
            synchronized (MyDataBase.class){
                if (Instance == null) {
                    Instance= Room.databaseBuilder(MyApplication.getApplication(),MyDataBase.class,"mydatabase")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return Instance;
    }



    public abstract CityDao cityDao();
    public abstract CountyDao countyDao();
    public abstract ProvinceDao provinceDao();
}
