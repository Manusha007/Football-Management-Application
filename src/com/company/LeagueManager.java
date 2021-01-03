package com.company;

public interface LeagueManager {

    public void createClub(String n, String l);
    public Boolean deleteClub(String n, String l);
    public void displayStatics(FootballClub club);
    public void displayLeagueTable();
    public void addMatch();
    public void saveData();
}
