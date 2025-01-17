package com.example.workoo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.R;
import com.example.workoo.model.AllService;

import java.util.ArrayList;

public class AllServicesAdapter extends RecyclerView.Adapter<AllServicesAdapter.ViewHolder> {
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<AllService> list = new ArrayList<>();

    public AllServicesAdapter(Context context, RecyclerViewInterface recyclerViewInterface, ArrayList<AllService> list) {
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_services_card,parent,false);
        return new AllServicesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.serviceImg.setImageResource(list.get(position).getImage());
        holder.serviceName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImg;
        TextView serviceName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImg = itemView.findViewById(R.id.serviceImg);
            serviceName = itemView.findViewById(R.id.serviceName);

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
