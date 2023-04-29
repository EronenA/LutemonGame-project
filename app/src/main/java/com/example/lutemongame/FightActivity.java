package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class FightActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;
    private LutemonListAdapter adapter;
    private TextView txtFight;


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

        txtFight = findViewById(R.id.txtFight);

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
        // Not enough Lutemons at fighting arena
        if (Storage.getInstance().getLutemonsAtFight().size() != 2) {
            Toast.makeText(context, "Ei tarpeeksi Lutemoneja Taistelu-areenalla!", Toast.LENGTH_LONG).show();
        } else {
            Lutemon lutemonA = Storage.getInstance().getLutemonFromFightByIdWithoutRemove(0);
            Lutemon lutemonB = Storage.getInstance().getLutemonFromFightByIdWithoutRemove(1);

            // One of the Lutemons has 0 or less health
            if (lutemonA.getHealth() <= 0 | lutemonB.getHealth() <= 0) {
                Toast.makeText(context, "Siirrä hävinnyt Lutemon kotiin lepäämään...", Toast.LENGTH_LONG).show();
                return;
            }

            // Fight begins
            while (lutemonA.getHealth() > 0 | lutemonB.getHealth() > 0) {
                // Stats print
                System.out.println("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth()); //Test
                System.out.println("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth()); //Test
                txtFight.append("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
                txtFight.append("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n");
                // LutemonB attacks
                System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!"); //Test
                txtFight.append(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!\n");
                if (ThreadLocalRandom.current().nextInt(0, 10 + 1) >= 9) {
                    // Critical hit, 2x dmg
                    System.out.println("Kriittinen isku!");
                    txtFight.append("Kriittinen isku!\n");
                    lutemonA.defend(lutemonB.getAttack() * 2);
                } else {
                    // Normal hit
                    lutemonA.defend(lutemonB.getAttack());
                }

                // Cheack lutemonA's health, lutemonB wins
                if (lutemonA.getHealth() <= 0) {
                    System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!"); //Test
                    txtFight.append(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!\n");
                    lutemonB.setWins(lutemonB.getWins() + 1);
                    lutemonA.setLoses(lutemonA.getLoses() + 1);
                    break;
                }
                // Fight continues
                System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää iskusta!"); //Test
                txtFight.append(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää iskusta!\n");

                // Stats print
                System.out.println("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth()); //Test
                System.out.println("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth()); //Test
                txtFight.append("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n");
                txtFight.append("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
                // LutemonA attacks
                System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!"); //Test
                txtFight.append(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!\n");
                if (ThreadLocalRandom.current().nextInt(0, 10 + 1) >= 9) {
                    // Critical hit, 2x dmg
                    System.out.println("Kriittinen isku!");
                    txtFight.append("Kriittinen isku!\n");
                    lutemonB.defend(lutemonA.getAttack() * 2);
                } else {
                    // Normal hit
                    lutemonB.defend(lutemonA.getAttack());
                }

                // Check lutemonB's health, lutemonA wins
                if (lutemonB.getHealth() <= 0) {
                    System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!"); //Test
                    txtFight.append(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!\n");
                    lutemonA.setWins(lutemonA.getWins() + 1);
                    lutemonB.setLoses(lutemonB.getLoses() + 1);
                    break;
                }
                // Fight continues
                System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää iskusta!"); //Test
                txtFight.append(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää iskusta!\n");

            }

            // Fight ended
            System.out.println("Taistelu loppui."); //Test
            txtFight.append("Taistelu loppui.\n");
            adapter.notifyDataSetChanged();

            // Both lutemons' fights + 1
            lutemonA.setFights(lutemonA.getFights() + 1);
            lutemonB.setFights(lutemonB.getFights() + 1);
        }

    }


}