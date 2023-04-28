package com.example.lutemongame;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashMap;

public class Storage {

    private static Storage storage = null;
    private ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>(); // Array list for lutemons at home
    private ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>(); // Array list for lutemons at training
    private ArrayList<Lutemon> lutemonsAtFight = new ArrayList<>(); // // Array list for lutemons at fighting
    private static String activityOn = "home";

    private HashMap<Integer, Lutemon> lutemonsHashMap = new HashMap<>();

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
        }

    public void addLutemonToHome (Lutemon lutemon){
            lutemonsAtHome.add(lutemon);
        }

    public void addLutemonToTrain(Lutemon lutemon) {
        lutemonsAtTrain.add(lutemon);
    }
    public void addLutemonToFight(Lutemon lutemon) {
        lutemonsAtFight.add(lutemon);
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

    public void saveLutemons(Context context) { // Save lutemons from the arraylists to own files
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemonsAtHome.data", Context.MODE_PRIVATE)); // s
            lutemonWriter.writeObject(lutemonsAtHome);
            lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemonsAtTrain.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemonsAtTrain);
            lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemonsAtFight.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemonsAtFight);
            lutemonWriter = new ObjectOutputStream(context.openFileOutput("idCounter.data", Context.MODE_PRIVATE));
            lutemonWriter.writeInt(Lutemon.getNumberOfCreatedLutemons());
            lutemonWriter.close();
        } catch (IOException e) {
            System.out.println("Tiedoston kirjoittaminen ei onnistunut");
            e.printStackTrace();
        }
    }
    public void loadLutemons(Context context) { // Load lutemons from the files to correct ArraysLists
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemonsAtHome.data"));
            lutemonsAtHome = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader = new ObjectInputStream(context.openFileInput("lutemonsAtTrain.data"));
            lutemonsAtTrain = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader = new ObjectInputStream(context.openFileInput("lutemonsAtFight.data"));
            lutemonsAtFight = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader = new ObjectInputStream(context.openFileInput("idCounter.data"));
            Lutemon.setIdCounter(lutemonReader.readInt());


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

    public Lutemon getLutemonFromTrainById(int id) {
        return lutemonsAtTrain.remove(id);
    }

    public Lutemon getLutemonFromFightById(int id) {
        return lutemonsAtFight.remove(id);
    }

    public void setActivityOn(String activity)  {
        this.activityOn = activity;
    }

    public String getActivityOn()   {
        return activityOn;
    }

    public HashMap<Integer, Lutemon> convertArrayListToHashmap(String list)  { // Convert Arraylist to hashmap, parameter which list
        lutemonsHashMap.clear();

        if (list == "train") { // convert Hashmap lutemons at training
            for (Lutemon lutemon : lutemonsAtTrain) {
                lutemonsHashMap.put(lutemon.getId(), lutemon);
            }
        }


        if (list == "fight") {
            for (Lutemon lutemon : lutemonsAtFight) { // convert Hashmap lutemons at training
                lutemonsHashMap.put(lutemon.getId(), lutemon);
            }
        }
        return lutemonsHashMap;
    }
}

