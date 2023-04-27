package com.example.lutemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonAtHomeList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonListAdapter(getApplicationContext(), storage.getLutemonsAtHome()));
        context = MainActivity.this;

    }
    public void switchToAddLutemonActivity(View view)    {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }

    public void moveLutemonsFromHome(View view)  {
        RadioGroup rgSelectedLutemon = findViewById(R.id.rgSelectedLutemon);
        //int testi = rb.getCheckedRadioButtonId();
        Lutemon testi = Storage.getInstance().getLutemonFromHomeById(rgSelectedLutemon.getCheckedRadioButtonId());

        System.out.println(testi.getName());
        //RadioGroup rgSelectedLutemon = findViewById(R.id.rgSelectedLutemon);
        //CheckBox selectedLutemon = findViewById(R.id.cbSelectLutemon);

        //Lutemon lutemonMove = Storage.getInstance().getLutemonFromHomeById(rgSelectedLutemon.getCheckedRadioButtonId());

        //System.out.println(lutemonMove.getName());
        //CheckBox selectedLutemons = findViewById(R.id.cbSelectLutemon);

        //System.out.println(selectedLutemons);
    }

    public void saveLutemons(View view)  {
        Storage.getInstance().saveUsers(context);
    }
    public void loadLutemons(View view)  {
        Storage.getInstance().loadUsers(context);
    }
}