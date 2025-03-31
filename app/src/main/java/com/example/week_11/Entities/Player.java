package com.example.week_11.Entities;

import com.example.week_11.Interfaces.SoccerEntity;

public class Player implements SoccerEntity {
    private String name;
    private int age;
    private String nationality;
    private String position;
    private String team;
    private int jerseyNumber;

    public Player(String name, int age, String nationality, String position, String team, int jerseyNumber) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.position = position;
        this.team = team;
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType(){
        return "Player";
    }

    @Override
    public String getDescription1() {
        return "Age: "+ String.valueOf(age);
    }

    @Override
    public String getDescription2() {
        return "Nationality: " + nationality;
    }

    @Override
    public String getDescription3() {
        return "Team: "+ team + "    Position: " + position;
    }

    @Override
    public int getBirthDate() {
        return 2025-age;
    }
}
