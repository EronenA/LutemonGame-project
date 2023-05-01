package com.example.lutemongame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DescriptionListAdapter extends RecyclerView.Adapter<DescriptionViewHolder>{

    private Context context;
    private ArrayList<Description> descriptions;

    public DescriptionListAdapter(Context context, ArrayList<Description>descriptions) {
        this.context = context;
        this.descriptions = descriptions;
    }

    @NonNull
    @Override
    public DescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DescriptionViewHolder(LayoutInflater.from(context).inflate(R.layout.description_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DescriptionViewHolder holder, int position) {

        //holder.description.setText(descriptions.get(position));
        holder.lutemon1.setImageResource(descriptions.get(position).getImageLutemon1());
        holder.lutemon2.setImageResource(descriptions.get(position).getImageLutemon2());
        holder.visual1.setImageResource(descriptions.get(position).getImageVisualDescription1());
        holder.visual2.setImageResource(descriptions.get(position).getImageVisualDescription2());
        holder.description.setText(descriptions.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return descriptions.size();
    }
}
