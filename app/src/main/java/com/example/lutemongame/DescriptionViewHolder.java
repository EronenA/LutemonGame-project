package com.example.lutemongame;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DescriptionViewHolder extends RecyclerView.ViewHolder{
    ImageView lutemon1;
    ImageView lutemon2;
    ImageView visual1;
    ImageView visual2;
    TextView description;

    public DescriptionViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemon1 = itemView.findViewById(R.id.imgLutemon1);
        lutemon2 = itemView.findViewById(R.id.imgLutemon2);
        visual1 = itemView.findViewById(R.id.imgVisual1);
        visual2 = itemView.findViewById(R.id.imgVisual2);
        description = itemView.findViewById(R.id.txtDescription);

    }

}
