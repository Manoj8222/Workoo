package com.example.workoo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.R;
import com.example.workoo.model.Scheduled;

import java.util.ArrayList;

public class ScheduledCardAdapter extends RecyclerView.Adapter<ScheduledCardAdapter.ViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Scheduled> list;

    public ScheduledCardAdapter(Context context, ArrayList<Scheduled> list,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheduled_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String totalJobs = String.valueOf(list.get(position).getTotalProjects());
        String numberOfRatings = String.valueOf(list.get(position).getTotalProjects());
        holder.taskerName.setText(list.get(position).getTaskerName());
        holder.numberOfRating.setText(numberOfRatings);
        holder.totalJobs.setText(totalJobs);
        holder.rating.setRating(list.get(position).getRating());
        holder.taskerImage.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskerName,numberOfRating,totalJobs;
        RatingBar rating;
        ImageView taskerImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskerName = itemView.findViewById(R.id.tasker_name);
            numberOfRating = itemView.findViewById(R.id.number_of_ratings);
            totalJobs = itemView.findViewById(R.id.totalJobs);
            rating = itemView.findViewById(R.id.rating);
            taskerImage = itemView.findViewById(R.id.tasker_profile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int position = getAbsoluteAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
