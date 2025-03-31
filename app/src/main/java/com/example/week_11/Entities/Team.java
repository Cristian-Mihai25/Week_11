package com.example.week_11.Entities;

import com.example.week_11.Interfaces.SoccerEntity;

public class Team implements SoccerEntity {
    private String name;
    private String country;
    private String league;
    private String stadium;
    private int foundedYear;

    public Team(String name, String country, String league, String stadium, int foundedYear) {
        this.name = name;
        this.country = country;
        this.league = league;
        this.stadium = stadium;
        this.foundedYear = foundedYear;
    }
    public String getLeague(){
        return league;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType(){
        return "Team";
    }

    @Override
    public String getDescription1() {
        return "Founded in: " + foundedYear;
    }

    @Override
    public String getDescription2() {
        return "Country: " + country;
    }

    @Override
    public String getDescription3() {
        return "League: " + league + "    Stadium: " + stadium;
    }
}
