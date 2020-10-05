package com.example.practica.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//add database entities
@Database(entities = {PruebaEntity.class}, version = 1)
public abstract class PracticaDB extends RoomDatabase {

    //create database intance
    private static PracticaDB database;

    //define database name
    private static String DATABASE_NAME="practica";

    public synchronized static PracticaDB getInstance(Context context){

        //Check condition
        if(database==null){
            //when database is null initialize database
            database = Room.databaseBuilder(context.getApplicationContext(),PracticaDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public abstract PracticaDao practicaDao();
}
