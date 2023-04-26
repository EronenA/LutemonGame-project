package com.example.lutemongame;

public abstract class Lutemon {
    protected String name;
    protected String color;
    protected int attack;
    protected int defence;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected int id;
    private static int idCounter;
    protected int fights;
    protected int wins;
    protected int loses;
    protected int trainingSessions;

    public Lutemon(String name, String color, int attack, int defence, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defence = defence;
        this.experience = 0;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.id = idCounter;
        this.fights = 0;
        this.wins = 0;
        this.loses = 0;
        this.trainingSessions = 0;

        idCounter++;
    }
}
