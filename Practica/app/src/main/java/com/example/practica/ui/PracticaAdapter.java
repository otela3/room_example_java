package com.example.practica.ui;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica.R;
import com.example.practica.room.PracticaDB;
import com.example.practica.room.PruebaEntity;

import java.util.List;

public class PracticaAdapter extends RecyclerView.Adapter<PracticaAdapter.ViewHolder> {
    //initialize variable
    private List<PruebaEntity> dataList;
    private Activity context;
    private PracticaDB database;

    //create constructor

    public PracticaAdapter(Activity context,List<PruebaEntity> dataList) {
        this.dataList = dataList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //initialize Entity
        final PruebaEntity data = dataList.get(position);
        //initialize database
        database = PracticaDB.getInstance(context);
        //set text on text view
        holder.textView.setText(data.getText());
        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize Entity
                PruebaEntity d = dataList.get(holder.getAdapterPosition());
                //get id
                final int sID = d.getID();
                //get text
                String sText = d.getText();
                //create dialog
                final Dialog dialog = new Dialog(context);
                //set content view
                dialog.setContentView(R.layout.dialog_update);
                //initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //initialize heigth
                int heigth = WindowManager.LayoutParams.WRAP_CONTENT;
                //set layout
                dialog.getWindow().setLayout(width,heigth);
                //show dialog
                dialog.show();

                //initialize and assign variable
                final EditText editText = dialog.findViewById(R.id.edit_text);
                Button btnUpdate = dialog.findViewById(R.id.bt_update);

                //set text on edit text
                editText.setText(sText);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Dimiss dialog
                        dialog.dismiss();
                        //get update text from edit text
                        String uText = editText.getText().toString().trim();
                        //update text in database
                        database.practicaDao().update(sID,uText);
                        //notify when data is update
                        dataList.clear();
                        dataList.addAll(database.practicaDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize entity
                PruebaEntity d = dataList.get(holder.getAdapterPosition());
                //delete text from database
                database.practicaDao().delete(d);
                //notify when data is delete
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //initialize variable
        TextView textView;
        ImageView btEdit,btdelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            textView = itemView.findViewById(R.id.text_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btdelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
