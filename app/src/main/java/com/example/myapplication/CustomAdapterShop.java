package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterShop extends RecyclerView.Adapter<CustomAdapterShop.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList item_id, item_title, item_quantity;
    CustomAdapterShop( Context context, ArrayList item_id, ArrayList item_title, ArrayList item_quantity){
        this.context = context;
        this.item_id = item_id;
        this.item_title = item_title;
        this.item_quantity = item_quantity;
    }
    @NonNull
    @Override
    public CustomAdapterShop.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_shop_row,parent,false);
        return new CustomAdapterShop.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapterShop.MyViewHolder holder, int position) {
        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_title_txt.setText(String.valueOf(item_title.get(position)));
        holder.item_quantity_txt.setText(String.valueOf(item_quantity.get(position)));

    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView item_id_txt,item_title_txt,item_quantity_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id_txt=itemView.findViewById(R.id.item_id_txt);
            item_title_txt=itemView.findViewById(R.id.item_title_txt);
            item_quantity_txt=itemView.findViewById(R.id.item_quantity_txt);
        }
    }
}
