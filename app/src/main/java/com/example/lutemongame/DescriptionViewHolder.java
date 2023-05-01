package com.example.lutemongame;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DescriptionViewHolder extends RecyclerView.ViewHolder{
    TextView description;

    public DescriptionViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.txtDescription);

    }

}
