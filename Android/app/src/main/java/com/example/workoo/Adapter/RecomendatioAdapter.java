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
import com.example.workoo.model.RecomendationCard;

import java.util.ArrayList;

public class RecomendatioAdapter extends RecyclerView.Adapter<RecomendatioAdapter.ViewHolder> {


    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<RecomendationCard> model;
    Context context;

    public RecomendatioAdapter( ArrayList<RecomendationCard> model, Context context,RecyclerViewInterface recyclerViewInterface) {

        this.model = model;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recomended_service_card,parent,false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String rating = String.valueOf(model.get(position).getRating());
        holder.imageView.setImageResource(R.drawable.electrician);
        holder.name.setText(model.get(position).getTaskerName());
        holder.skill.setText(model.get(position).getSkills());
        holder.fair.setText(model.get(position).getFair());
        holder.rating.setText(rating);
        holder.totalRating.setText(model.get(position).getTotalRating());
        if(model.get(position).getSkills().equals("Coding")){
            holder.imageView.setImageResource(R.drawable.coding);
        } else if (model.get(position).getSkills().equals("Plumbing")) {
            holder.imageView.setImageResource(R.drawable.plumbing);
        } else if (model.get(position).getSkills().equals("Electrician")) {
            holder.imageView.setImageResource(R.drawable.electrician);
        } else if (model.get(position).getSkills().equals("House Cleaning")) {
            holder.imageView.setImageResource(R.drawable.house_cleaning);
        } else if (model.get(position).getSkills().equals("Gardening")) {
            holder.imageView.setImageResource(R.drawable.gardening);
        } else if (model.get(position).getSkills().equals("Carpentry")) {
            holder.imageView.setImageResource(R.drawable.carpentry);
        } else if (model.get(position).getSkills().equals("Appliance Repair")) {
            holder.imageView.setImageResource(R.drawable.appliance_repair);
        } else if (model.get(position).getSkills().equals("Painting")) {
            holder.imageView.setImageResource(R.drawable.painting);
        } else if (model.get(position).getSkills().equals("Pest Control")) {
            holder.imageView.setImageResource(R.drawable.pest_control);
        } else if (model.get(position).getSkills().equals("Security Services")) {
            holder.imageView.setImageResource(R.drawable.security_services);
        } else if (model.get(position).getSkills().equals("Babysitting")) {
            holder.imageView.setImageResource(R.drawable.babysitting);
        } else if (model.get(position).getSkills().equals("Elderly Care")) {
            holder.imageView.setImageResource(R.drawable.elderly_care);
        } else if (model.get(position).getSkills().equals("Car Wash")) {
            holder.imageView.setImageResource(R.drawable.car_wash);
        } else if (model.get(position).getSkills().equals("Laundry Services")) {
            holder.imageView.setImageResource(R.drawable.laundry_services);
        } else if (model.get(position).getSkills().equals("Interior Design")) {
            holder.imageView.setImageResource(R.drawable.interior_design);
        } else if (model.get(position).getSkills().equals("Mobile Repair")) {
            holder.imageView.setImageResource(R.drawable.mabile_repair);
        } else if (model.get(position).getSkills().equals("IT Support")) {
            holder.imageView.setImageResource(R.drawable.it_support);
        } else if (model.get(position).getSkills().equals("Fitness Trainer")) {
            holder.imageView.setImageResource(R.drawable.fitness_trainer);
        } else if (model.get(position).getSkills().equals("Photography")) {
            holder.imageView.setImageResource(R.drawable.photography);
        } else if (model.get(position).getSkills().equals("Pet Grooming")) {
            holder.imageView.setImageResource(R.drawable.petgrooming);
        } else if (model.get(position).getSkills().equals("Tutoring")) {
            holder.imageView.setImageResource(R.drawable.tutoring);
        } else if (model.get(position).getSkills().equals("Vehicle Repair")) {
            holder.imageView.setImageResource(R.drawable.vehiclerepair);
        } else if (model.get(position).getSkills().equals("Cooking Services")) {
            holder.imageView.setImageResource(R.drawable.cookingservices);
        } else if (model.get(position).getSkills().equals("Massage Therapy")) {
            holder.imageView.setImageResource(R.drawable.massagetherapy);
        } else if (model.get(position).getSkills().equals("Yoga Instructor")) {
            holder.imageView.setImageResource(R.drawable.yogainstructor);
        } else if (model.get(position).getSkills().equals("Home Automation Setup")) {
            holder.imageView.setImageResource(R.drawable.homeautomation);
        } else if (model.get(position).getSkills().equals("Courier Services")) {
            holder.imageView.setImageResource(R.drawable.courierservicesjpg);
        } else if (model.get(position).getSkills().equals("Home Sanitization")) {
            holder.imageView.setImageResource(R.drawable.homesanitization);
        } else if (model.get(position).getSkills().equals("Party Decoration")) {
            holder.imageView.setImageResource(R.drawable.partydecoration);
        } else if (model.get(position).getSkills().equals("Hairdressing")) {
            holder.imageView.setImageResource(R.drawable.hairdressing);
        } else if (model.get(position).getSkills().equals("Makeup Artist")) {
            holder.imageView.setImageResource(R.drawable.makeupartist);
        } else if (model.get(position).getSkills().equals("Shoe Repair")) {
            holder.imageView.setImageResource(R.drawable.shoerepair);
        } else if (model.get(position).getSkills().equals("Landscaping")) {
            holder.imageView.setImageResource(R.drawable.landscaping);
        } else if (model.get(position).getSkills().equals("Computer Repair")) {
            holder.imageView.setImageResource(R.drawable.computer_repair);
        } else if (model.get(position).getSkills().equals("Networking Setup")) {
            holder.imageView.setImageResource(R.drawable.networkingsetup);
        } else if (model.get(position).getSkills().equals("Solar Panel Installation")) {
            holder.imageView.setImageResource(R.drawable.solar);
        } else if (model.get(position).getSkills().equals("Roof Repair")) {
            holder.imageView.setImageResource(R.drawable.roofrepair);
        } else if (model.get(position).getSkills().equals("Furniture Assembly")) {
            holder.imageView.setImageResource(R.drawable.furnitureassembly);
        } else if (model.get(position).getSkills().equals("Tile Installation")) {
            holder.imageView.setImageResource(R.drawable.tileinstallation);
        } else if (model.get(position).getSkills().equals("Window Cleaning")) {
            holder.imageView.setImageResource(R.drawable.windowcleaning);
        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //initialization
        ImageView imageView;
        TextView name,skill,fair,rating,totalRating;
        public ViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView10);
            name = itemView.findViewById(R.id.taskerName);
            skill = itemView.findViewById(R.id.skill);
            fair = itemView.findViewById(R.id.fair);
            rating = itemView.findViewById(R.id.rating);
            totalRating = itemView.findViewById(R.id.totalRating);

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
