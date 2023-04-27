package com.example.lutemongame;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    ImageView lutemonImage;
    TextView lutemonNameAndColor, lutemonColor, lutemonAttack, lutemonDefence, lutemonHealt, lutemonExperience;
    RadioGroup rgSelectedLutemon;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonImage = itemView.findViewById(R.id.ivLutemonImage);
        lutemonNameAndColor = itemView.findViewById(R.id.txtLutemonName);
        //lutemonColor = itemView.findViewById(R.id.txtLutemonColor);
        lutemonAttack = itemView.findViewById(R.id.txtLutemonAttack);
        lutemonDefence = itemView.findViewById(R.id.txtLutemonDefence);
        lutemonHealt = itemView.findViewById(R.id.txtLutemonHealt);
        lutemonExperience = itemView.findViewById(R.id.txtLutemonExperience);
        rgSelectedLutemon = itemView.findViewById(R.id.rgSelectedLutemon);


    }
}
