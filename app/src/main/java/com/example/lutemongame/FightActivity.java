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
import java.util.concurrent.TimeUnit;

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

    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchToTrainActivity(View view) {
        Intent intent = new Intent(this, TrainActivity.class);
        startActivity(intent);
    }


    public void lutemonsFight(View view) throws InterruptedException {
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

                System.out.println("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth()); //Test
                System.out.println("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth()); //Test
                txtFight.append("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
                txtFight.append("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n");
                //storage.setFightDescription("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
                //storage.setFightDescription("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n");

                Description description = new Description("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() +
                        "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n", lutemonA.getImage(), R.drawable.x, R.drawable.x, R.drawable.x);
                storage.setFightDescription(description);

                description = new Description("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack()
                        + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n", lutemonB.getImage(), R.drawable.x, R.drawable.x, R.drawable.x);
                storage.setFightDescription(description);


                // LutemonB attacks
                System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!"); //Test
                txtFight.append(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!\n");
                //storage.setFightDescription(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!\n");
                description = new Description(lutemonB.getColor() + "(" + lutemonB.getName() + ") hyökkää " + lutemonA.getColor() + "(" + lutemonA.getName() + ") kimppuun!\n",
                        lutemonB.getImage(), R.drawable.fight, R.drawable.shield, lutemonA.getImage());
                storage.setFightDescription(description);

                if (ThreadLocalRandom.current().nextInt(0, 10 + 1) >= 9) {
                    // Critical hit, 2x dmg
                    System.out.println("Kriittinen isku!");
                    txtFight.append("Kriittinen isku!\n");
                    //storage.setFightDescription("Kriittinen isku!\n");
                    description = new Description("Kriittinen isku!\n", R.drawable.star, R.drawable.star, R.drawable.star, R.drawable.star);
                    storage.setFightDescription(description);

                    lutemonA.defend(lutemonB.getAttack() * 2);
                } else {
                    // Normal hit
                    lutemonA.defend(lutemonB.getAttack());
                }

                // Cheack lutemonA's health, lutemonB wins
                if (lutemonA.getHealth() <= 0) {
                    System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!"); //Test
                    txtFight.append(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!\n");
                    //storage.setFightDescription(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!\n");
                    description = new Description(lutemonA.getColor() + "(" + lutemonA.getName() + ") pyörtyy!\n", lutemonA.getImage(), R.drawable.defeat, R.drawable.defeat, R.drawable.defeat);
                    storage.setFightDescription(description);

                    lutemonB.setWins(lutemonB.getWins() + 1);
                    lutemonA.setLoses(lutemonA.getLoses() + 1);
                    break;
                }
                // Fight continues
                System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää iskusta!"); //Test
                txtFight.append(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää iskusta!\n");
                //storage.setFightDescription(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää iskusta!\n");
                description = new Description(lutemonA.getColor() + "(" + lutemonA.getName() + ") selviää iskusta!", lutemonA.getImage(), R.drawable.star, R.drawable.star, R.drawable.star);
                storage.setFightDescription(description);


                // Stats print
                System.out.println("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth()); //Test
                System.out.println("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth()); //Test
                txtFight.append("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n");
                txtFight.append("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
                //storage.setFightDescription("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n");
                //storage.setFightDescription("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");


                description = new Description("2. " + lutemonB.getColor() + "(" + lutemonB.getName() + ") att: " + lutemonB.getAttack()
                        + "; def: " + lutemonB.getDefence() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n", lutemonB.getImage(), R.drawable.x, R.drawable.x, R.drawable.x);
                storage.setFightDescription(description);
                description = new Description("1. " + lutemonA.getColor() + "(" + lutemonA.getName() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefence() +
                        "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n", lutemonA.getImage(), R.drawable.x, R.drawable.x, R.drawable.x);
                storage.setFightDescription(description);


                // LutemonA attacks
                System.out.println(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!"); //Test
                txtFight.append(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!\n");
                //storage.setFightDescription(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!\n");
                description = new Description(lutemonA.getColor() + "(" + lutemonA.getName() + ") hyökkää " + lutemonB.getColor() + "(" + lutemonB.getName() + ") kimppuun!\n",
                        lutemonA.getImage(), R.drawable.fight, R.drawable.shield, lutemonB.getImage());
                storage.setFightDescription(description);

                if (ThreadLocalRandom.current().nextInt(0, 10 + 1) >= 9) {
                    // Critical hit, 2x dmg
                    System.out.println("Kriittinen isku!");
                    txtFight.append("Kriittinen isku!\n");
                    //storage.setFightDescription("Kriittinen isku!\n");
                    description = new Description("Kriittinen isku!\n", R.drawable.star, R.drawable.star, R.drawable.star, R.drawable.star);
                    storage.setFightDescription(description);

                    lutemonB.defend(lutemonA.getAttack() * 2);
                } else {
                    // Normal hit
                    lutemonB.defend(lutemonA.getAttack());
                }

                // Check lutemonB's health, lutemonA wins
                if (lutemonB.getHealth() <= 0) {
                    System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!"); //Test
                    txtFight.append(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!\n");
                    //storage.setFightDescription(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!\n");
                    description = new Description(lutemonB.getColor() + "(" + lutemonB.getName() + ") pyörtyy!\n", lutemonB.getImage(), R.drawable.defeat, R.drawable.defeat, R.drawable.defeat);
                    storage.setFightDescription(description);


                    lutemonA.setWins(lutemonA.getWins() + 1);
                    lutemonB.setLoses(lutemonB.getLoses() + 1);
                    break;
                }
                // Fight continues
                System.out.println(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää iskusta!"); //Test
                txtFight.append(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää iskusta!\n");
                //storage.setFightDescription(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää iskusta!\n");
                description = new Description(lutemonB.getColor() + "(" + lutemonB.getName() + ") selviää iskusta!", lutemonB.getImage(), R.drawable.star, R.drawable.star, R.drawable.star);
                storage.setFightDescription(description);


            }

            // Fight ended
            System.out.println("Taistelu loppui."); //Test
            txtFight.append("Taistelu loppui.\n");
            adapter.notifyDataSetChanged();
            //storage.setFightDescription("Tähän kuvausta 1");
            //storage.setFightDescription("Tähän kuvausta 2");
            Description description = new Description("Taistelu loppui.", R.drawable.checkeredflag, R.drawable.checkeredflag, R.drawable.checkeredflag, R.drawable.checkeredflag);
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