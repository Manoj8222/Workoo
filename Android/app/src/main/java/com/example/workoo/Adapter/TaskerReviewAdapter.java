package com.example.workoo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoo.R;
import com.example.workoo.model.ReviewResponse;
import com.example.workoo.model.TaskerReviewModel;

import java.util.ArrayList;

public class TaskerReviewAdapter extends RecyclerView.Adapter<TaskerReviewAdapter.ViewHolder> {
    ArrayList<ReviewResponse> reviewsList = new ArrayList<>();
    Context context;

    public TaskerReviewAdapter(ArrayList<ReviewResponse> list, Context context) {
        this.reviewsList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tasker_review,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.name.setText(list.get(position).getReviewerName());
//        holder.review.setText(list.get(position).getReview());
//        holder.reviewerProfile.setImageResource(list.get(position).getImage());
//        holder.rating.setRating(list.get(position).getRating());
        ReviewResponse review = reviewsList.get(position);
        holder.name.setText(review.getUserName());
//        holder.d.setText(review.getDate());
        holder.review.setText(review.getReviewDescription());
        holder.rating.setRating((float) review.getRating());


        // Handle profile photo
        if (review.getProfilePhoto() != null && !review.getProfilePhoto().isEmpty()) {
            // Assuming profilePhoto is a Base64 encoded string
            byte[] imageBytes = Base64.decode(review.getProfilePhoto(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.reviewerProfile.setImageBitmap(bitmap);
        } else {
            holder.reviewerProfile.setImageResource(R.drawable.profile_filled); // Default profile image
        }
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView reviewerProfile;
        TextView review,name;
        RatingBar rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewerProfile = itemView.findViewById(R.id.reviewerProfile);
            review = itemView.findViewById(R.id.review);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
        }
    }

}
