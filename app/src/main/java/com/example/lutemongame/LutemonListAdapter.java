package com.example.lutemongame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    //private Storage storage;


    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());
        holder.lutemonNameAndColor.setText(lutemons.get(position).getName() + " (" + lutemons.get(position).getColor() + ")");
        holder.lutemonAttack.setText("Hyökkäys: " + String.valueOf(lutemons.get(position).getAttack()));
        holder.lutemonDefence.setText("Puolustus: " + String.valueOf(lutemons.get(position).getDefence()));
        holder.lutemonHealt.setText("Elämä: " + String.valueOf(lutemons.get(position).getHealth()));
        holder.lutemonExperience.setText("Kokemus: " + String.valueOf(lutemons.get(position).getExperience()));
        holder.lutemonFights.setText("Taistelut: " + String.valueOf(lutemons.get(position).getFights()));
        holder.lutemonWins.setText("Voitot: " + String.valueOf(lutemons.get(position).getWins()));
        holder.lutemonLosses.setText("Häviöt: " + String.valueOf(lutemons.get(position).getLoses()));
        holder.lutemonTrainings.setText("Treenit: " + String.valueOf(lutemons.get(position).getTrainingSessions()));


        // Home button visibility
        if (Storage.getInstance().getActivityOn().equals("home")) {
            holder.imgHome.setVisibility(View.GONE);
        } else {
            holder.imgHome.setVisibility(View.VISIBLE);
        }

        // Train button visibility
        if (Storage.getInstance().getActivityOn().equals("train")) {
            holder.imgTrain.setVisibility(View.GONE);
        } else {
            holder.imgTrain.setVisibility(View.VISIBLE);
        }

        // Fight button visibility
        if (Storage.getInstance().getActivityOn().equals("fight")) {
            holder.imgFight.setVisibility(View.GONE);
        } else {
            holder.imgFight.setVisibility(View.VISIBLE);
        }

        holder.imgTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                // to train
                if (Storage.getInstance().getActivityOn() == "home") { // from home to train
                    Lutemon lutemon = Storage.getInstance().getLutemonFromHomeById(pos);
                    Storage.getInstance().addLutemonToTrain(lutemon);
                    notifyItemRemoved(pos);
                }
                if (Storage.getInstance().getActivityOn() == "fight") { // from fight to train
                    Lutemon lutemon = Storage.getInstance().getLutemonFromFightById(pos);
                    Storage.getInstance().addLutemonToTrain(lutemon);
                    notifyItemRemoved(pos);
                }
            }
        });

        holder.imgFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                // to fight
                if (Storage.getInstance().getActivityOn() == "home") { // from home to fight
                    if (Storage.getInstance().getLutemonsAtFight().size() < 2) {
                        Lutemon lutemon = Storage.getInstance().getLutemonFromHomeById(pos);
                        Storage.getInstance().addLutemonToFight(lutemon);
                        notifyItemRemoved(pos);
                    }else Toast.makeText(context, "Taistelu-areena täynnä! Siirrä Lutemoneja pois areenalta siirtääksesi uuden", Toast.LENGTH_LONG).show(); //System.out.println("Taistelu-areena täynnä! Siirrä Lutemoneja pois areenalta siirtääksesi uuden");
                }
                if (Storage.getInstance().getActivityOn() == "train") { // from train to fight
                    if (Storage.getInstance().getLutemonsAtFight().size() < 2) {
                        Lutemon lutemon = Storage.getInstance().getLutemonFromTrainById(pos);
                        Storage.getInstance().addLutemonToFight(lutemon);
                        notifyItemRemoved(pos);
                    }else Toast.makeText(context, "Taistelu-areena täynnä! Siirrä Lutemoneja pois areenalta siirtääksesi uuden", Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                // to home
                if (Storage.getInstance().getActivityOn().equals("train")) { // from train to home
                    Lutemon lutemon = Storage.getInstance().getLutemonFromTrainById(pos);
                    Storage.getInstance().addLutemonToHome(lutemon);
                    notifyItemRemoved(pos);
                }
                if (Storage.getInstance().getActivityOn().equals("fight")) { // from fight to home
                    Lutemon lutemon = Storage.getInstance().getLutemonFromFightById(pos);
                    Storage.getInstance().addLutemonToHome(lutemon);
                    notifyItemRemoved(pos);
                }
            }
        });


        //RadioGroup rgLutemon = holder.rgSelectedLutemon.findViewById(position);
        //RadioButton rb;
        //rb = new RadioButton(context);
        //rgLutemon.addView(rb);
        //holder.rgSelectedLutemon.addView(rb);

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
