package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.concurrent.ThreadLocalRandom;


public class FightActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;
    private LutemonListAdapter adapter;

    int star = R.drawable.star;
    int defeat = R.drawable.defeat;
    int attack = R.drawable.fight;
    int shield = R.drawable.shield;
    int imageX = R.drawable.x;
    int checkeredFlag = R.drawable.checkeredflag;


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
        storage.clearDescriptionForFight(); // Clear fight description list
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


                Description description = new Description("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() +
                        "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth(), lutemonA.getImage(), imageX, imageX, imageX);
                storage.setFightDescription(description);

                description = new Description("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack()
                        + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth(), lutemonB.getImage(), imageX, imageX, imageX);
                storage.setFightDescription(description);


                // LutemonB attacks
                description = new Description(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!",
                        lutemonB.getImage(), attack, shield, lutemonA.getImage());
                storage.setFightDescription(description);

                if (ThreadLocalRandom.current().nextInt(0, 10 + 1) >= 9) {
                    // Critical hit, 2x dmg
                    description = new Description("Kriittinen isku!", star, star, star, star);
                    storage.setFightDescription(description);

                    lutemonA.defend(lutemonB.getAttack() * 2);
                } else {
                    // Normal hit
                    lutemonA.defend(lutemonB.getAttack());
                }

                // Cheack lutemonA's health, lutemonB wins
                if (lutemonA.getHealth() <= 0) {
                    description = new Description(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!", lutemonA.getImage(), defeat, defeat, defeat);
                    storage.setFightDescription(description);

                    lutemonB.setWins(lutemonB.getWins() + 1);
                    lutemonA.setLoses(lutemonA.getLoses() + 1);
                    break;
                }
                // Fight continues
                description = new Description(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää iskusta!", lutemonA.getImage(), star, star, star);
                storage.setFightDescription(description);


                // Stats print
                description = new Description("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack()
                        + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth(), lutemonB.getImage(), imageX, imageX, imageX);
                storage.setFightDescription(description);
                description = new Description("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() +
                        "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth(), lutemonA.getImage(), imageX, imageX, imageX);
                storage.setFightDescription(description);


                // LutemonA attacks
                description = new Description(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!",
                        lutemonA.getImage(), attack, shield, lutemonB.getImage());
                storage.setFightDescription(description);

                if (ThreadLocalRandom.current().nextInt(0, 10 + 1) >= 9) {
                    // Critical hit, 2x dmg
                    description = new Description("Kriittinen isku!", star, star, star, star);
                    storage.setFightDescription(description);

                    lutemonB.defend(lutemonA.getAttack() * 2);
                } else {
                    // Normal hit
                    lutemonB.defend(lutemonA.getAttack());
                }

                // Check lutemonB's health, lutemonA wins
                if (lutemonB.getHealth() <= 0) {
                    description = new Description(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!", lutemonB.getImage(), defeat, defeat, defeat);
                    storage.setFightDescription(description);


                    lutemonA.setWins(lutemonA.getWins() + 1);
                    lutemonB.setLoses(lutemonB.getLoses() + 1);
                    break;
                }
                // Fight continues
                description = new Description(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää iskusta!", lutemonB.getImage(), star, star, star);
                storage.setFightDescription(description);


            }

            // Fight ended
            adapter.notifyDataSetChanged();
            Description description = new Description("Taistelu loppui.", checkeredFlag, checkeredFlag, checkeredFlag, checkeredFlag);
            storage.setFightDescription(description);

            switchToFightDescriptionActivity();


            // Both lutemons' fights + 1
            lutemonA.setFights(lutemonA.getFights() + 1);
            lutemonB.setFights(lutemonB.getFights() + 1);
        }

    }

    public void switchToFightDescriptionActivity()    {
        Intent intent = new Intent(this, FightDescriptionActivity.class);
        startActivity(intent);
    }


}