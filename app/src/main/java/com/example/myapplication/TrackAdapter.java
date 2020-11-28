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

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList track_item_id,  track_item_quantity,track_item_title;

    TrackAdapter(Context context, ArrayList track_item_id, ArrayList track_item_title,ArrayList track_item_quantity){
        this.context=context;
        this.track_item_id=track_item_id;
        this.track_item_title=track_item_title;
        this.track_item_quantity=track_item_quantity;

    }


    @NonNull
    @Override
    public TrackAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.track_row, parent ,false);
        return new TrackAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackAdapter.MyViewHolder holder, int position) {
        holder.track_id_txt.setText(String.valueOf(track_item_id.get(position)));
        holder.track_title_txt.setText(String.valueOf(track_item_title.get(position)));
        holder.track_quantity_txt.setText(String.valueOf(track_item_quantity.get(position)));

    }

    @Override
    public int getItemCount() {
        return track_item_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView track_id_txt,track_title_txt,track_quantity_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            track_id_txt=itemView.findViewById(R.id.track_id_txt);
            track_title_txt=itemView.findViewById(R.id.track_title_txt);
            track_quantity_txt=itemView.findViewById(R.id.track_quantity_txt);

        }
    }
}
