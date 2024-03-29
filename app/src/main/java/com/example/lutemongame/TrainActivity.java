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

    //variables for measure time between clicks
    private static int clickCount;
    public static long startTime;
    int points;


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
        // All lutemons at training get +1 xp and + points based on clicks to attack
        for (Lutemon lutemon : trainingLutemons) {
            lutemon.setExperience(lutemon.getExperience() + 1);
            if (points > 0) {
                points = points * lutemon.getExperience();
                lutemon.setAttack(lutemon.getAttack() + points);
                lutemon.setDefence(lutemon.getDefence() + points);
            }else {
                lutemon.setAttack(lutemon.getAttack() + points);
                lutemon.setDefence(lutemon.getDefence() + points);
            }
            lutemon.setTrainingSessions(lutemon.getTrainingSessions() + 1);

        }
        Toast.makeText(context, "Sait " + points + " pistettä", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();

    }
// Target 2 seconds for 2 cliks at trainin, score gets better the closer you get
    public void measureDifferenceBetweenClicks(View view) {
        long differenceBetweenClicks;
        int goal = 20;
        long differenceToGoal;

        clickCount++;
        if (Storage.getInstance().getLutemonsAtTrain().size()==0) {
            Toast.makeText(context, "Lisää ainakin yksi lutemon Harjoitus-areenalle", Toast.LENGTH_LONG).show();
        }else {

            //measure difference between cliks
            if (clickCount == 1)    {
                startTime = System.currentTimeMillis();
            }else   {
                differenceBetweenClicks = (System.currentTimeMillis() - startTime) / 100;
                differenceToGoal = Math.abs(differenceBetweenClicks - goal);

                // points at training based difference
                clickCount = 0;
                System.out.println(differenceToGoal);
                if (differenceToGoal==0) {
                    points = 10;
                } else if(differenceToGoal < 5) {
                    points = 2;
                } else if(differenceToGoal < 10) {
                    points = 1;
                }else {points = -3;}
                trainLutemons(points); // points to train lutemons
                //Toast.makeText(context, "Ero " + differenceToGoal, Toast.LENGTH_LONG).show();
            }

        }

    }

}