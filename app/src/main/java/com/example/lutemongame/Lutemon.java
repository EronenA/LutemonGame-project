package com.example.lutemongame;

import java.io.Serializable;

public abstract class Lutemon implements Serializable {
    protected String name;
    protected int image;
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

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getId() {
        return id;
    }

    public int getFights() {
        return fights;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getTrainingSessions() {
        return trainingSessions;
    }

    public static int getNumberOfCreatedLutemons() {
        return idCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFights(int fights) {
        this.fights = fights;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public void setTrainingSessions(int trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    public static void setIdCounter(int idCounter) {
        Lutemon.idCounter = idCounter;
    }

    public void defend(int attack) {
        int dmg = (attack - this.defence);
        this.health = (this.health - dmg);

    }

}
