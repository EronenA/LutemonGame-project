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
    ImageView lutemonImage, imgFight, imgTrain, imgHome;
    TextView lutemonNameAndColor, lutemonAttack, lutemonDefence, lutemonHealt, lutemonExperience, lutemonFights, lutemonWins, lutemonLosses, lutemonTrainings;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonImage = itemView.findViewById(R.id.ivLutemonImage);
        lutemonNameAndColor = itemView.findViewById(R.id.txtLutemonName);
        lutemonAttack = itemView.findViewById(R.id.txtLutemonAttack);
        lutemonDefence = itemView.findViewById(R.id.txtLutemonDefence);
        lutemonHealt = itemView.findViewById(R.id.txtLutemonHealt);
        lutemonExperience = itemView.findViewById(R.id.txtLutemonExperience);
        imgFight = itemView.findViewById(R.id.imgFight);
        imgTrain = itemView.findViewById(R.id.imgTrain);
        imgHome = itemView.findViewById(R.id.imgHome);
        lutemonFights = itemView.findViewById(R.id.txtLutemonFights);
        lutemonWins = itemView.findViewById(R.id.txtLutemonWins);
        lutemonLosses = itemView.findViewById(R.id.txtLutemonLosses);
        lutemonTrainings = itemView.findViewById(R.id.txtLutemonTrainings);

    }
}
