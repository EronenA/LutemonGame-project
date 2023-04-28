package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class TrainActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;
    private LutemonListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonAtTrainList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(getApplicationContext(), storage.getLutemonsAtTrain());
        recyclerView.setAdapter(adapter);

        context = TrainActivity.this;
        storage.setActivityOn("train");
        System.out.println(storage.getActivityOn()); // test line, remove final version


        //Lutemon testLutemon = new Black("matti");                 // test lutemon
        //Storage.getInstance().addLutemonToTrain(testLutemon);

    }


    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchToFightActivity(View view) {
        Intent intent = new Intent(this, FightActivity.class);
        startActivity(intent);
    }

    public void trainLutemons(View view) {
        ArrayList<Lutemon> trainingLutemons = Storage.getInstance().getLutemonsAtTrain();
        // All lutemons at training get +1 xp and +1 attack
        for (Lutemon lutemon : trainingLutemons) {
            lutemon.setExperience(lutemon.getExperience() + 1);
            lutemon.setAttack(lutemon.getAttack() + 1);

        }

        adapter.notifyDataSetChanged();

    }

}