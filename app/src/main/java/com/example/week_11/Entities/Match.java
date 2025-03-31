package com.example.week_11.Entities;

import com.example.week_11.Interfaces.SoccerEntity;

public class Match implements SoccerEntity {
    private String homeTeam;
    private String awayTeam;
    private String score;
    private String league;
    private String date;
    private String stadium;

    public Match(String homeTeam, String awayTeam, String score, String league, String date, String stadium) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.league = league;
        this.date = date;
        this.stadium = stadium;
    }

    @Override
    public String getName() {
        return homeTeam + " vs " + awayTeam;
    }

    @Override
    public String getType(){
        return "Match";
    }

    @Override
    public String getDescription1() {
        return "Date: " + date;
    }

    @Override
    public String getDescription2() {
        return "Score: " + score;
    }

    @Override
    public String getDescription3() {
        return "HomeTeam: " + homeTeam + "    AwayTeam: " + awayTeam +"    Stadium: " + stadium;
    }

    @Override
    public int getBirthDate() {
        return Integer.parseInt(date.substring(0, 4));
    }
}
