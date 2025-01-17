package com.example.workoo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoo.Interface.FavoriteActionListener;
import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.model.SearchTaskerList;
import com.example.workoo.model.TaskerSearchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTaskerListAdapter extends RecyclerView.Adapter<SearchTaskerListAdapter.ViewHolder> {
    private ArrayList<TaskerSearchResponse> list;
    private Context context;
    private RecyclerViewInterface recyclerViewOnClick;
    private FavoriteActionListener favoriteListener;

    ServiceApi serviceApi = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);

    public SearchTaskerListAdapter(ArrayList<TaskerSearchResponse> list, Context context, RecyclerViewInterface recyclerViewOnClick) {
        this.list = list;
        this.context = context;
        this.recyclerViewOnClick = recyclerViewOnClick;
    }

    public void setFavoriteActionListener(FavoriteActionListener listener) {
        this.favoriteListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_tasker_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskerSearchResponse tasker = list.get(position);

        // Set basic tasker information
        holder.ratingBar.setRating(tasker.getRating() != null ?
                tasker.getRating().floatValue() : 0f);
        holder.Name.setText(tasker.getTaskerName());
        holder.taskerDescription.setText(tasker.getDescription());
        holder.taskerLocation.setText(tasker.getLocation());
        holder.fair.setText(String.valueOf(tasker.getFair()));
        holder.number_of_ratings.setText(String.valueOf(tasker.getTotalProject()));
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
        // Set favorite status
//        boolean isFavorite = tasker.isFavorite();
//        holder.heartFilled.setVisibility(isFavorite ? View.VISIBLE : View.INVISIBLE);
//        holder.heartBorder.setVisibility(isFavorite ? View.INVISIBLE : View.VISIBLE);

        // Set image if available
//        if (tasker.getImg() != null) {
//            // Use Glide or other image loading library
//            Glide.with(context)
//                    .load(tasker.getImg())
//                    .circleCrop()
//                    .into(holder.taskerImage);
//        }
        Long userId = MainActivity.userId;
        serviceApi.checkFavoriteStatus(tasker.getId(), userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean isFavorite = response.body();
                    if (holder.getAdapterPosition() == position) {
                        holder.heartFilled.setVisibility(isFavorite ? View.VISIBLE : View.INVISIBLE);
                        holder.heartBorder.setVisibility(isFavorite ? View.INVISIBLE : View.VISIBLE);
//                        tasker.setFavorite(isFavorite);

                    }
                } else {
                    if (holder.getAdapterPosition() == position) {
                        holder.heartFilled.setVisibility(View.INVISIBLE);
                        holder.heartBorder.setVisibility(View.VISIBLE);
                        Toast.makeText(context, "Failed to check favorite status", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (holder.getAdapterPosition() == position) {
                    holder.heartFilled.setVisibility(View.INVISIBLE);
                    holder.heartBorder.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView taskerImage, heartFilled, heartBorder;
        TextView Name, taskerLocation, taskerDescription, fair, number_of_ratings;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
            setupClickListeners();
        }

        private void initializeViews(View itemView) {
            ratingBar = itemView.findViewById(R.id.rating);
            taskerImage = itemView.findViewById(R.id.taskerImage);
            Name = itemView.findViewById(R.id.Name);
            taskerLocation = itemView.findViewById(R.id.taskerLocation);
            taskerDescription = itemView.findViewById(R.id.taskerDescription);
            fair = itemView.findViewById(R.id.fair);
            number_of_ratings = itemView.findViewById(R.id.number_of_ratings);
            heartBorder = itemView.findViewById(R.id.heart_border);
            heartFilled = itemView.findViewById(R.id.heart_filled);
        }

        private void setupClickListeners() {
            heartBorder.setOnClickListener(v -> handleAddFavorite());
            heartFilled.setOnClickListener(v -> handleRemoveFavorite());

            itemView.setOnClickListener(v -> {
                if (recyclerViewOnClick != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    recyclerViewOnClick.onItemClick(getAdapterPosition());
                }
            });
        }

        private void handleAddFavorite() {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && favoriteListener != null) {
                Long taskerId = list.get(position).getId();
                favoriteListener.onAddToFavorite(taskerId, position);
            }
        }

        private void handleRemoveFavorite() {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && favoriteListener != null) {
                Long taskerId = list.get(position).getId();
                favoriteListener.onRemoveFromFavorite(taskerId, position);
            }
        }
    }

    public void updateFavoriteStatus(int position, boolean isFavorite) {
        if (position >= 0 && position < list.size()) {
            TaskerSearchResponse tasker = list.get(position);
//            tasker.setFavorite(isFavorite);
            notifyItemChanged(position);
        }
    }
}
