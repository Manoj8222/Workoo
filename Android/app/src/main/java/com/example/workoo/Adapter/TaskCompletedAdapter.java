package com.example.workoo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.R;
import com.example.workoo.model.TaskCompleteModel;

import java.util.ArrayList;

public class TaskCompletedAdapter extends RecyclerView.Adapter<TaskCompletedAdapter.ViewHolder> {

    private ArrayList<TaskCompleteModel> mData;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public TaskCompletedAdapter(ArrayList<TaskCompleteModel> mData, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.mData = mData;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.completed_card_rv,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskerProfile.setImageResource(mData.get(position).getImage());
        holder.bookingDate.setText(mData.get(position).getBookingDate());
        holder.bookingTime.setText(mData.get(position).getBookingTime());
        holder.taskerName.setText(mData.get(position).getTaskerName());
        if(position == 10){
            holder.taskerProfile.setImageResource(R.drawable.profile_two);
        } else if (position == 11) {
            holder.taskerProfile.setImageResource(R.drawable.profile_three);
        } else if (position == 13) {
            holder.taskerProfile.setImageResource(R.drawable.profile_four);
        } else if (position == 14) {
            holder.taskerProfile.setImageResource(R.drawable.profile_five);
        } else if (position == 15) {
            holder.taskerProfile.setImageResource(R.drawable.profile_six);
        } else if (position == 8) {
            holder.taskerProfile.setImageResource(R.drawable.profile_seven);
        } else if (position == 7) {
            holder.taskerProfile.setImageResource(R.drawable.profile_eight);
        } else if (position == 6) {
            holder.taskerProfile.setImageResource(R.drawable.profile_nine);
        } else if (position == 5) {
            holder.taskerProfile.setImageResource(R.drawable.profile_ten);
        } else if (position == 4) {
            holder.taskerProfile.setImageResource(R.drawable.profile_eleven);
        } else if (position == 3) {
            holder.taskerProfile.setImageResource(R.drawable.profile_twelve);
        } else if (position == 2) {
            holder.taskerProfile.setImageResource(R.drawable.profile_therteen);
        } else if (position == 1) {
            holder.taskerProfile.setImageResource(R.drawable.profile_fourteenj);
        } else if (position == 0) {
            holder.taskerProfile.setImageResource(R.drawable.profile_fifteen);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookingDate, bookingTime,taskerName,chatButton,bookAgainButton;
        ImageView taskerProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingDate = itemView.findViewById(R.id.booking_date);
            bookingTime = itemView.findViewById(R.id.booking_time);
            taskerName = itemView.findViewById(R.id.tasker_name);
            chatButton = itemView.findViewById(R.id.chat_button);
            bookAgainButton = itemView.findViewById(R.id.book_again_button);
            taskerProfile = itemView.findViewById(R.id.tasker_profile);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
