package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;
    private LutemonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonAtHomeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(getApplicationContext(), storage.getLutemonsAtHome());
        recyclerView.setAdapter(adapter);

        context = MainActivity.this;
        storage.setActivityOn("home");
        System.out.println(storage.getActivityOn()); // test line, remove final version


    }

    @Override
    public void onResume() {
        super.onResume();

        // Lutemons rest at home and heal to max health
        for (Lutemon lutemon : storage.getLutemonsAtHome()) {
            lutemon.setHealth(lutemon.getMaxHealth());
        }

        adapter.notifyDataSetChanged();
    }

    public void switchToAddLutemonActivity(View view)    {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToTrainActivity(View view)    {
        Intent intent = new Intent(this, TrainActivity.class);
        startActivity(intent);
    }

    public void switchToFightActivity(View view)    {
        Intent intent = new Intent(this, FightActivity.class);
        startActivity(intent);
    }

    public void hashMapForLutemonsFighting(View view)  {                                    // test for convert Arraylist to hashmap
        HashMap<Integer, Lutemon> LutMap = Storage.getInstance().convertArrayListToHashmap("train");


        System.out.println(LutMap.get(2).getName());

    }

    public void saveLutemons(View view)  {
        Storage.getInstance().saveLutemons(context);
    }
    public void loadLutemons(View view)  {
        Storage.getInstance().loadLutemons(context);
        recyclerView.setAdapter(new LutemonListAdapter(getApplicationContext(), storage.getLutemonsAtHome()));
    }


}