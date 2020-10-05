package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.practica.room.PracticaDB;
import com.example.practica.room.PruebaEntity;
import com.example.practica.ui.PracticaAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //initializate variable
    EditText editText;
    Button btnAdd,btnDeleteAll;
    RecyclerView recyclerView;

    List<PruebaEntity> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    PracticaDB database;
    PracticaAdapter practicaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assing varieble
        editText = findViewById(R.id.edit_text);
        btnAdd = findViewById(R.id.btn_add);
        btnDeleteAll = findViewById(R.id.btn_delete_all);
        recyclerView = findViewById(R.id.recycler_view);

        //initialize database
        database = PracticaDB.getInstance(this);
        //store database value in data list
        dataList = database.practicaDao().getAll();

        //initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
       practicaAdapter = new PracticaAdapter(MainActivity.this,dataList);
       //set adapter
        recyclerView.setAdapter(practicaAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get string from edit text
                String sText = editText.getText().toString().trim();
                //check condition
                if(!sText.equals("")){
                    //when text is not empty initialize main data
                    PruebaEntity data = new PruebaEntity();
                    //set text on main data
                    data.setText(sText);
                    //insert text in database
                    database.practicaDao().insert(data);
                    //clean edit text
                    editText.setText("");
                    //notify when data is insert
                    dataList.clear();
                    dataList.addAll(database.practicaDao().getAll());
                    practicaAdapter.notifyDataSetChanged();
                }
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete all data from database
                database.practicaDao().deleteAll(dataList);
                //notify when all data deleted
                dataList.clear();
                dataList.addAll(database.practicaDao().getAll());
                practicaAdapter.notifyDataSetChanged();
            }
        });
    }
}