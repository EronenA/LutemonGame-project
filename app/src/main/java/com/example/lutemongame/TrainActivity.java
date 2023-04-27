package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrainActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonAtTrainList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonListAdapter(getApplicationContext(), storage.getLutemonsAtTrain()));
        context = TrainActivity.this;

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

}