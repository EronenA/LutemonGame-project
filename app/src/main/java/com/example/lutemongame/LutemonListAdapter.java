package com.example.lutemongame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

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

        //RadioGroup rgLutemon = holder.rgSelectedLutemon.findViewById(position);
        RadioButton rb;
        rb = new RadioButton(context);
        //rgLutemon.addView(rb);
        holder.rgSelectedLutemon.addView(rb);

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
