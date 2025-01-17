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
import com.example.workoo.model.FavoriteModel;

import java.util.ArrayList;

public class MyTaskerFavoriteAdapter extends RecyclerView.Adapter<MyTaskerFavoriteAdapter.ViewHolder> {

    ArrayList<FavoriteModel> list;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public MyTaskerFavoriteAdapter(ArrayList<FavoriteModel> list, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faviorite_tasker_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String totalJobs = String.valueOf(list.get(position).getTotalJob());
        String numberOfRatings = String.valueOf(list.get(position).getTotalReview());
        holder.taskerName.setText(list.get(position).getTaskerName());
        holder.numberOfRating.setText(numberOfRatings);
        holder.totalJobs.setText(totalJobs);
        holder.rating.setRating(list.get(position).getRating());
        holder.taskerImage.setImageResource(list.get(position).getTaskerImg());
        if(position == 0){
            holder.taskerImage.setImageResource(R.drawable.profile_two);
        } else if (position == 1) {
            holder.taskerImage.setImageResource(R.drawable.profile_three);
        } else if (position == 3) {
            holder.taskerImage.setImageResource(R.drawable.profile_four);
        } else if (position == 4) {
            holder.taskerImage.setImageResource(R.drawable.profile_five);
        } else if (position == 5) {
            holder.taskerImage.setImageResource(R.drawable.profile_six);
        } else if (position == 6) {
            holder.taskerImage.setImageResource(R.drawable.profile_seven);
        } else if (position == 7) {
            holder.taskerImage.setImageResource(R.drawable.profile_eight);
        } else if (position == 8) {
            holder.taskerImage.setImageResource(R.drawable.profile_nine);
        } else if (position == 9) {
            holder.taskerImage.setImageResource(R.drawable.profile_ten);
        } else if (position == 10) {
            holder.taskerImage.setImageResource(R.drawable.profile_eleven);
        } else if (position == 11) {
            holder.taskerImage.setImageResource(R.drawable.profile_twelve);
        } else if (position == 12) {
            holder.taskerImage.setImageResource(R.drawable.profile_therteen);
        } else if (position == 13) {
            holder.taskerImage.setImageResource(R.drawable.profile_fourteenj);
        } else if (position == 14) {
            holder.taskerImage.setImageResource(R.drawable.profile_fifteen);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
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
