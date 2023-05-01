package com.example.lutemongame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DescriptionListAdapter extends RecyclerView.Adapter<DescriptionViewHolder>{

    private Context context;
    private ArrayList<String> descriptions;

    public DescriptionListAdapter(Context context, ArrayList<String>descriptions) {
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

        holder.description.setText(descriptions.get(position));

    }

    @Override
    public int getItemCount() {
        return descriptions.size();
    }
}
