package com.example.practica.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//define table name
@Entity(tableName = "prueba_list")
public class PruebaEntity implements Serializable {
    //create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;
    //create text colum
    @ColumnInfo(name = "text")
    private String text;

    //Generate getter and setter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
