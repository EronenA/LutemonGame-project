package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FightActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonAtFightList); // list lutemons at fight

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonListAdapter(getApplicationContext(), storage.getLutemonsAtFight()));
        context = FightActivity.this;

        storage.setActivityOn("fight"); // set current activity
        System.out.println(storage.getActivityOn()); // test line, remove final version

        //Lutemon testLutemon = new Black("matti");                 // test lutemon
        //Storage.getInstance().addLutemonToFight(testLutemon);

    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchToTrainActivity(View view) {
        Intent intent = new Intent(this, TrainActivity.class);
        startActivity(intent);
    }

}