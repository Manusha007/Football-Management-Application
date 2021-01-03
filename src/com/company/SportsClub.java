package com.company;

public abstract class SportsClub {

    private String Club_Name;
    private String Club_Location;

    public SportsClub(){}

    public SportsClub(String n, String l){
        this.Club_Name = n;
        this.Club_Location = l;
    }

    public String getClub_Name(){
        return this.Club_Name;
    }

    public void setClub_Name(String clubName){
        this.Club_Name = clubName;
    }

    public String getClub_Location(){
        return this.Club_Location;
    }

    public void setClub_Location(String clubLocation){
        this.Club_Location = clubLocation;
    }

}

