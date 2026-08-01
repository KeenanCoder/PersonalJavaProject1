package com.PersonalJavaProject.megatictactoe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leaderboard_entries")
public class LeaderboardEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    private String difficulty;
    private int wins;

    public LeaderboardEntry(){}

    public LeaderboardEntry(String playerName, String difficulty, int wins){
        this.playerName = playerName;
        this.difficulty = difficulty;
        this.wins = wins;
    }

    public Long getId(){ return id; }
    public String getPlayerName(){ return playerName; }
    public void setPlayerName(String playerName){ this.playerName = playerName; }
    public String getDifficulty(){ return difficulty; }
    public void setDifficulty(String difficulty){ this.difficulty = difficulty; }
    public int getWins(){ return wins; }
    public void setWins(int wins){ this.wins = wins; }
}