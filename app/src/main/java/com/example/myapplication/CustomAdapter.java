package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList event_id, event_title, event_date, event_time;
    CustomAdapter( Context context, ArrayList event_id, ArrayList event_title, ArrayList event_date,
                  ArrayList event_time){
        this.context = context;
        this.event_id = event_id;
        this.event_title = event_title;
        this.event_date = event_date;
        this.event_time = event_time;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.event_id_txt.setText(String.valueOf(event_id.get(position)));
        holder.event_title_txt.setText(String.valueOf(event_title.get(position)));
        holder.event_date_txt.setText(String.valueOf(event_date.get(position)));
        holder.event_time_txt.setText(String.valueOf(event_time.get(position)));

    }

    @Override
    public int getItemCount() {
        return event_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView event_id_txt,event_title_txt,event_date_txt,event_time_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            event_id_txt=itemView.findViewById(R.id.event_id_txt);
            event_title_txt=itemView.findViewById(R.id.event_title_txt);
            event_date_txt=itemView.findViewById(R.id.event_date_txt);
            event_time_txt=itemView.findViewById(R.id.event_time_txt);
        }
    }
}
