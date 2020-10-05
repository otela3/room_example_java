package com.example.practica.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PracticaDao {
    //inset Query
    @Insert(onConflict = REPLACE)
    void insert(PruebaEntity pruebaEntity);

    //delete Query
    @Delete
    void delete(PruebaEntity pruebaEntity);

    //delete all Query
    @Delete
    void deleteAll(List<PruebaEntity> pruebaEntity);

    //update Query
    @Query("UPDATE prueba_list set text = :sText WHERE ID = :sID")
    void update(int sID, String sText);

    //get all data Query
    @Query("SELECT * FROM prueba_list")
    List<PruebaEntity> getAll();
}
