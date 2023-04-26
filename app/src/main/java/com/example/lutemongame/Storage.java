package com.example.lutemongame;

import java.util.ArrayList;
//import java.util.HashMap;

public class Storage {

    private static Storage storage = null;
    private ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>(); // Array list for lutemons at home
    private ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>(); // Array list for lutemons at training
    private ArrayList<Lutemon> lutemonsAtFight = new ArrayList<>(); // // Array list for lutemons at fighting
    //private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    public static Storage getInstance() {
        if (storage == null)    {
            storage = new Storage();
        }
        return storage;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemonsAtHome.add(lutemon);
    }
}
