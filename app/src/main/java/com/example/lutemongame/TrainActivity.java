package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;
    private LutemonListAdapter adapter;

    //for measure time between clicks
    private static int clickCount;
    public static long startTime;


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

    public void trainLutemons(int points) {
        ArrayList<Lutemon> trainingLutemons = Storage.getInstance().getLutemonsAtTrain();
        // All lutemons at training get +1 xp and +1 attack
        for (Lutemon lutemon : trainingLutemons) {
            lutemon.setExperience(lutemon.getExperience() + points);
            lutemon.setAttack(lutemon.getAttack() + points);
            lutemon.setTrainingSessions(lutemon.getTrainingSessions() + 1);

        }
        Toast.makeText(context, "Sait " + points + "pistettä", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();

    }

    public void measureDifferenceBetweenClicks(View view) {
        long differenceBetweenClicks;
        int goal = 20;
        long differenceToGoal;
        int points;
        clickCount++;
        if (clickCount == 1)    {
            startTime = System.currentTimeMillis();
        }else   {
            differenceBetweenClicks = (System.currentTimeMillis() - startTime) / 100;
            differenceToGoal = Math.abs(differenceBetweenClicks - goal);
            clickCount = 0;
            System.out.println(differenceToGoal);
            if (differenceToGoal==0) {
                points = 10;
            } else if(differenceToGoal < 5) {
                points = 2;
            } else if(differenceToGoal < 10) {
                points = 1;
            }else {points = -3;}

        trainLutemons(points);
        }


        return;
    }

}