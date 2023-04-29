package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FightActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;
    private LutemonListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonAtFightList); // list lutemons at fight
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(getApplicationContext(), storage.getLutemonsAtFight());
        recyclerView.setAdapter(adapter);

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


    public void lutemonsFight(View view) {
        if (Storage.getInstance().getLutemonsAtFight().size() != 2) { // Not enough lutemons at fighting arena
            Toast.makeText(context, "Ei tarpeeksi Lutemoneja Taistelu-areenalla!", Toast.LENGTH_LONG).show();

        } else {
            Lutemon lutemonA = Storage.getInstance().getLutemonFromFightByIdWithoutRemove(0);
            Lutemon lutemonB = Storage.getInstance().getLutemonFromFightByIdWithoutRemove(1);

            if (lutemonA.getHealth() <= 0 | lutemonB.getHealth() <= 0) { // One of the Lutemons has 0 or less health
                Toast.makeText(context, "Siirrä hävinnyt Lutemon kotiin lepäämään...", Toast.LENGTH_LONG).show();
                return;
            }

            while (lutemonA.getHealth() > 0 | lutemonB.getHealth() > 0) {
                // Stats print
                System.out.println("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth());
                System.out.println("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth());
                // LutemonB attacks
                System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!");
                lutemonA.defend(lutemonB.getAttack());

                if (lutemonA.getHealth() <= 0) { // LutemonB wins
                    System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!");
                    lutemonB.setWins(lutemonB.getWins() + 1);
                    lutemonA.setLoses(lutemonA.getLoses() + 1);
                    break;
                }
                System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää elossa!");

                // Stats print
                System.out.println("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth());
                System.out.println("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth());
                // LutemonA attacks
                System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!");
                lutemonB.defend(lutemonA.getAttack());

                if (lutemonB.getHealth() <= 0) { // LutemonA wins
                    System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!");
                    lutemonA.setWins(lutemonB.getWins() + 1);
                    lutemonB.setLoses(lutemonA.getLoses() + 1);
                    break;
                }
                System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää elossa!");
            }

            System.out.println("Taistelu loppui.");
            adapter.notifyDataSetChanged();

            // Both lutemons' fights + 1
            lutemonA.setFights(lutemonA.getFights() + 1);
            lutemonB.setFights(lutemonB.getFights() + 1);
        }

    }


}