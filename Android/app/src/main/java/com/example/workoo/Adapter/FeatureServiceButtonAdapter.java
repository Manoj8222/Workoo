package com.example.workoo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoo.R;

import java.util.ArrayList;

public class FeatureServiceButtonAdapter extends RecyclerView.Adapter<FeatureServiceButtonAdapter.ViewHolder> {
    ArrayList<String> list;
    Context context;

    public FeatureServiceButtonAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feature_service_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("ONERROR", "onBindViewHolder: "+list.get(position));
        holder.featureServiceCard.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView featureServiceCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            featureServiceCard = itemView.findViewById(R.id.featureServiceCard);
        }
    }
}
