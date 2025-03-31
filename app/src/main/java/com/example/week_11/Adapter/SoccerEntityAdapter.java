package com.example.week_11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week_11.Interfaces.SoccerEntity;
import com.example.week_11.R;

import java.util.List;

public class SoccerEntityAdapter extends RecyclerView.Adapter<SoccerEntityAdapter.ViewHolder> {

    private final List<SoccerEntity> soccerEntities;
    private final Context context;


    public SoccerEntityAdapter(Context context, List<SoccerEntity> SoccerEntities) {
        this.context = context;
        this.soccerEntities = SoccerEntities;

    }


    //Implement RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.soccerentity_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SoccerEntity soccerEntity = soccerEntities.get(position);

        // Bind data to views
        holder.tvName.setText(soccerEntity.getName());
        holder.tvType.setText(soccerEntity.getType());
        holder.tvDesc1.setText(soccerEntity.getDescription1());
        holder.tvDesc2.setText(soccerEntity.getDescription2());
        holder.tvDesc3.setText(soccerEntity.getDescription3());


    }

    @Override
    public int getItemCount() {
        return soccerEntities.size();
    }

    //update adapter
    public void updateItems(List<SoccerEntity> newItems) {
        soccerEntities.clear();
        soccerEntities.addAll(newItems);
        notifyDataSetChanged();
    }

    //ViewHolder class
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvType, tvDesc1, tvDesc2, tvDesc3;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_Entity_name);
            tvType = itemView.findViewById(R.id.tv_Entity_category);
            tvDesc1 = itemView.findViewById(R.id.tv_Entity_description_1);
            tvDesc2 = itemView.findViewById(R.id.tv_Entity_description_2);
            tvDesc3 = itemView.findViewById(R.id.tv_Entity_description_3);
        }
    }
}
