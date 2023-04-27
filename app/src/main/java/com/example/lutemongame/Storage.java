package com.example.lutemongame;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import java.util.HashMap;

public class Storage {

    private static Storage storage = null;
    private ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>(); // Array list for lutemons at home
    private ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>(); // Array list for lutemons at training
    private ArrayList<Lutemon> lutemonsAtFight = new ArrayList<>(); // // Array list for lutemons at fighting

    //private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
        }

    public void addLutemonToHome (Lutemon lutemon){
            lutemonsAtHome.add(lutemon);
        }

    public ArrayList<Lutemon> getLutemonsAtHome() {
        return lutemonsAtHome;
    }

    public ArrayList<Lutemon> getLutemonsAtTrain() {
        return lutemonsAtTrain;
    }

    public ArrayList<Lutemon> getLutemonsAtFight() {
        return lutemonsAtFight;
    }

    public void saveUsers(Context context) { // Save lutemons from the arraylists to own files
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemonsAtHome.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemonsAtHome);
            lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemonsAtTrain.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemonsAtTrain);
            lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemonsAtFight.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemonsAtFight);
            lutemonWriter.close();
        } catch (IOException e) {
            System.out.println("Tiedoston kirjoittaminen ei onnistunut");
            e.printStackTrace();
        }
    }
    public void loadUsers(Context context) { // Load lutemons from the files to correct ArraysLists
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemonsAtHome.data"));
            lutemonsAtHome = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader = new ObjectInputStream(context.openFileInput("lutemonsAtTrain.data"));
            lutemonsAtTrain = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader = new ObjectInputStream(context.openFileInput("lutemonsAtFight.data"));
            lutemonsAtFight = (ArrayList<Lutemon>) lutemonReader.readObject();

            lutemonReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tiedoston lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Tiedoston lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Tiedoston lukeminen ei onnistunut");
            e.printStackTrace();
        }
    }
    public Lutemon getLutemonFromHomeById(int id) {
        return lutemonsAtHome.remove(id);
    }
    public void addLutemonToTrain(Lutemon lutemon) {
        lutemonsAtTrain.add(lutemon);
    }
    public void addLutemonToFight(Lutemon lutemon) {
        lutemonsAtFight.add(lutemon);
    }


}

