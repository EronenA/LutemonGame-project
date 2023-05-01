package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FightDescriptionActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private DescriptionListAdapter adapter;
    private static int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_description);


        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvDescription);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//.setReverseLayout(true);
        ArrayList<String> clear = new ArrayList<>();
        storage.clearDescriptionSetToScreen();
        adapter = new DescriptionListAdapter(getApplicationContext(), storage.getDescriptionSetToScreen());
        recyclerView.setAdapter(adapter);

        step = 0;

    }

    public void setDescriptionsOnScreen(View view)   {

        int steps = storage.getDescriptionForFight().size();
        if (steps > step) {
            Description description = storage.getDescriptionString(step);
            storage.setDescriptionToScreen(description);
            System.out.println(description);
            adapter.notifyItemInserted(step);
            recyclerView.scrollToPosition(step);

            step++;
        }
    }
}