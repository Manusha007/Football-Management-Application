package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PremierLeagueManager implements LeagueManager {

    //creating arraylist
    public ArrayList<FootballClub> Club_1 = new ArrayList<>();
    public ArrayList<Match> Match_1 = new ArrayList<>();

    public PremierLeagueManager(){}

    //reading file before running the program
    public PremierLeagueManager(String saveFile){
        try {
            BufferedReader csv_Reader_1 = new BufferedReader(new FileReader("save.csv"));
            String row_1;

            while((row_1 = csv_Reader_1.readLine()) != null){
                String[] data = row_1.split(",");
                FootballClub club = new FootballClub(data[0],data[1]);
                club.setNumOfWins(Integer.parseInt(data[2]));
                club.setNumOfDefeats(Integer.parseInt(data[3]));
                club.setNumOfDraws(Integer.parseInt(data[4]));
                club.setNumOfMatches(Integer.parseInt(data[5]));
                club.setNumOfScoredGoals(Integer.parseInt(data[6]));
                club.setNumOfReceivedGoals(Integer.parseInt(data[7]));
                club.setNumOfPoints(Integer.parseInt(data[8]));
                this.Club_1.add(club);
            }

            csv_Reader_1.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader csv_Reader_2 = new BufferedReader(new FileReader("matches.csv"));
            String row_2;
            while((row_2 = csv_Reader_2.readLine()) != null){
                String[] data = row_2.split(",");
                Match match = new Match(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],data[4],
                        Integer.parseInt(data[5]),Integer.parseInt(data[6]));
                this.Match_1.add(match);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //creating club
    public void createClub(String n, String l) {
        FootballClub new_Team = new FootballClub(n,l);
        this.Club_1.add(new_Team);
    }

    @Override
    //deleting club
    public Boolean deleteClub(String n, String l) {
        for(FootballClub club : Club_1){
            if(club.getClub_Name().equals(n) && club.getClub_Location().equals(l)){
                Club_1.remove(club);
                return true;
            }
        }
        return false;
    }

    @Override
    //displaying statics
    public void displayStatics(FootballClub club) {
        System.out.println();
        System.out.println("    Club Name: "+club.getClub_Name());
        System.out.println("    Club Location : "+club.getClub_Location());
        System.out.format("+----------------*--------------------*-----------------*-------------------*------------------------*--------------------------*------------------+%n");
        System.out.format("| Number of Wins |  Number of Defeats | Number of Draws | Number of Matches | Number of Scored Goals | Number of Received Goals | Number of Points |%n");
        System.out.format("+----------------*--------------------*-----------------*-------------------*------------------------*--------------------------*------------------+%n");
        String leftAlignFormat = "| %-14s | %-18s | %-15s | %-17s | %-22s | %-24s | %-16s |%n";
        System.out.format(leftAlignFormat, club.getNumOfWins(),club.getNumOfDefeats(),club.getNumOfDraws(),club.getNumOfMatches()
                ,club.getNumOfScoredGoals(),club.getNumOfReceivedGoals(),club.getNumOfPoints());
        System.out.println();
    }

    @Override
    //display league table
    public void displayLeagueTable() {
        System.out.format("+------------*-----------------*----------------*--------------------*-----------------*-------------------*------------------------*--------------------------*------------------+%n");
        System.out.format("|  Club Name |  Club Location  | Number of Wins |  Number of Defeats | Number of Draws | Number of Matches | Number of Scored Goals | Number of Received Goals | Number of Points |%n");
        System.out.format("+------------*-----------------*----------------*--------------------*-----------------*-------------------*------------------------*--------------------------*------------------+%n");

        for(FootballClub club : Club_1){
            String leftAlignFormat = "| %-10s | %-15s | %-14s | %-18s | %-15s | %-17s | %-22s | %-24s | %-16s |%n";
            System.out.printf(leftAlignFormat,club.getClub_Name(),club.getClub_Location(), club.getNumOfWins(),club.getNumOfDefeats(),club.getNumOfDraws(),club.getNumOfMatches()
                    ,club.getNumOfScoredGoals(),club.getNumOfReceivedGoals(),club.getNumOfPoints());
            System.out.println();
        }
    }

    @Override
    //adding match
    public void addMatch() {
        int day,month,year;
        String Club_Name_1,Club_Name_2;
        int Score_1, Score_2;
        Scanner sc_1 = new Scanner(System.in);
        Scanner sc_2 = new Scanner(System.in);
        Scanner sc_3 = new Scanner(System.in);
        System.out.println("\nSet match date");
        System.out.println("\nPlease enter following data correctly! ");
        System.out.println();
        System.out.print("Day : ");
        day = sc_3.nextInt();
        System.out.print("Month : ");
        month = sc_3.nextInt();
        System.out.print("Year : ");
        year = sc_3.nextInt();

        System.out.print("First Club Name : ");
        Club_Name_1 = sc_1.nextLine();
        System.out.print("First Club Score : ");
        Score_1 = sc_1.nextInt();
        System.out.print("Second Club Name  : ");
        Club_Name_2 = sc_2.nextLine();
        System.out.print("Second Club Score : ");
        Score_2 = sc_2.nextInt();

        Match match = new Match(day, month, year, Club_Name_1, Club_Name_2, Score_1, Score_2);
        this.Match_1.add(match);
        this.saveMatchData();
        FootballClub club1 = this.findClub(Club_Name_1);
        updateTableData(club1, Score_1, Score_2);
        FootballClub club2 = this.findClub(Club_Name_2);
        updateTableData(club2, Score_2, Score_1);

    }

    //generating random numbers
    public Match generateRandomMatch(){
        int day,month,year = 2020;
        String Club_Name_1,Club_Name_2;
        int Score_1, Score_2;

        Random random_1 = new Random();
        month = random_1.nextInt(12)+1;
        if(month == 2)
            day = random_1.nextInt(29)+1;
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            day = random_1.nextInt(30)+1;
        }
        else{
            day = random_1.nextInt(31)+1;
        }
        Club_Name_1 = this.Club_1.get(random_1.nextInt(this.Club_1.size())).getClub_Name();
        Club_Name_2 = this.Club_1.get(random_1.nextInt(this.Club_1.size())).getClub_Name();
        while(Club_Name_1.equals(Club_Name_2)){
            Club_Name_2 = this.Club_1.get(random_1.nextInt(this.Club_1.size())).getClub_Name();
        }
        Score_1 = random_1.nextInt(10);
        Score_2 = random_1.nextInt(10);

        Match match = new Match(day, month, year, Club_Name_1, Club_Name_2, Score_1, Score_2);
        this.Match_1.add(match);
        this.saveMatchData();
        FootballClub club1 = this.findClub(Club_Name_1);
        updateTableData(club1, Score_1, Score_2);
        FootballClub club2 = this.findClub(Club_Name_2);
        updateTableData(club2, Score_2, Score_1);
        return match;
    }

    //updating table
    public void updateTableData(FootballClub club, int s1, int s2){
        club.setNumOfScoredGoals(s1);
        club.setNumOfReceivedGoals(s2);
        club.setNumOfMatches(1);

        if(s1>s2){
            club.setNumOfWins(1);
            club.setNumOfPoints(3);
        }
        else if(s1==s2){
            club.setNumOfDraws(1);
            club.setNumOfPoints(1);
        }
        else{
            club.setNumOfDefeats(1);
        }
    }

    public Boolean searchClub(String n, String l){

        for(FootballClub club : this.Club_1){
            if(club.getClub_Name().equals(n) && club.getClub_Location().equals(l)){
                return true;
            }
        }

        return false;
    }

    public Boolean searchClub(String n){

        for(FootballClub club : this.Club_1){
            if(club.getClub_Name().equals(n)){
                return true;
            }
        }
        return false;
    }

    public FootballClub findClub(String n){

        for(FootballClub club : this.Club_1){
            if(club.getClub_Name().equals(n)){
                return club;
            }
        }
        return null;
    }

    @Override
    //saving club data
    public void saveData() {
        try {
            FileWriter csv_Writer_1 = new FileWriter("save.csv");
            for(FootballClub club : this.Club_1){
                csv_Writer_1.append(club.getClub_Name()+",");
                csv_Writer_1.append(club.getClub_Location()+",");
                csv_Writer_1.append(Integer.toString(club.getNumOfWins())+",");
                csv_Writer_1.append(Integer.toString(club.getNumOfDefeats())+",");
                csv_Writer_1.append(Integer.toString(club.getNumOfDraws())+",");
                csv_Writer_1.append(Integer.toString(club.getNumOfMatches())+",");
                csv_Writer_1.append(Integer.toString(club.getNumOfScoredGoals())+",");
                csv_Writer_1.append(Integer.toString(club.getNumOfReceivedGoals())+",");
                csv_Writer_1.append(Integer.toString(club.getNumOfPoints())+"\n");
            }
            csv_Writer_1.flush();
            csv_Writer_1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //saving match data
    public void saveMatchData() {
        try {
            FileWriter csv_Writer_2 = new FileWriter("matches.csv");
            for(Match match : Match_1){
                csv_Writer_2.append(Integer.toString(match.day)+",");
                csv_Writer_2.append(Integer.toString(match.month)+",");
                csv_Writer_2.append(Integer.toString(match.year)+",");
                csv_Writer_2.append(match.Club_1 +",");
                csv_Writer_2.append(match.Club_2 +",");
                csv_Writer_2.append(Integer.toString(match.Score_1)+",");
                csv_Writer_2.append(Integer.toString(match.Score_2)+"\n");
            }
            csv_Writer_2.flush();
            csv_Writer_2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void menu(){
        System.out.println("\n************************  Menu  **************************");
        System.out.println(" Please Enter 1 to Add a Club to Premier League ");
        System.out.println(" Please Enter 2 to Delete a Club from Premier League ");
        System.out.println(" Please Enter 3 to Display  Statistics of a Club ");
        System.out.println(" Please Enter 4 to Display the Premier League Table ");
        System.out.println(" Please Enter 5 to Add a Match ");
        System.out.println(" Please Enter 6 to save data ");
        System.out.println(" Please Enter 7 to launch GUI  ");
        System.out.println(" Please Enter 8 to quit ");
        System.out.println("***********************************************************");
        System.out.print(" Please Enter your Choice: ");

        Scanner sc = new Scanner(System.in);
        int user_Input = sc.nextInt();
        String n,l;
        Scanner sc1 = new Scanner(System.in);
        switch (user_Input){

            case 1:
                System.out.println("*********************************");
                System.out.println("Please Enter the name of the club:");
                n = sc1.nextLine();
                System.out.println("Please Enter the Location of the club:");
                l = sc1.nextLine();
                if(searchClub(n,l)){
                    System.out.println("Oops! Club already exists!");
                }
                else{
                    this.createClub(n,l);
                    System.out.println("New club added successfully!");
                }
                this.menu();
                break;

            case 2:
                System.out.println("**************************************");
                System.out.println("Please Enter the name of the club:");
                n = sc1.nextLine();
                System.out.println("Please Enter the Location of the club : ");
                l = sc1.nextLine();
                if(this.searchClub(n,l)){
                    this.deleteClub(n,l);
                    System.out.println("Club deleted successfully!");
                }
                else {
                    System.out.println("Error! Can't find the Club");
                }

                this.menu();
                break;

            case 3:
                System.out.println("**************************************");
                System.out.println("Please Enter the name of the club:");
                n = sc1.nextLine();
                System.out.println("Please Enter the Location of the club : ");
                l = sc1.nextLine();
                if(this.searchClub(n,l)) {
                    for (FootballClub club : this.Club_1) {
                        if (club.getClub_Name().equals(n) && club.getClub_Location().equals(l)) {
                            this.displayStatics(club);
                        }
                    }
                }
                else{
                    System.out.println("Error! Can't find the Club");
                }
                this.menu();
                break;

            case 4:
                if(this.Club_1.size()>0){
                    Collections.sort(this.Club_1,FootballClub.Points_Comparator.reversed());
                    this.displayLeagueTable();
                }
                else{
                    System.out.println("Please add Clubs first!");
                }
                this.menu();
                break;

            case 5:
                this.addMatch();
                this.menu();
                break;

            case 6:
                System.out.println("Successfully saved");
                this.saveData();
                this.menu();
                break;

            case 7:
                new Thread(){
                    @Override
                    public void run(){
                        javafx.application.Application.launch(Main.class);
                    }
                }.start();
                break;

            case 8:
                System.out.println("\n\n*****        +--------------------+          *****");
                System.out.println(" Thank you for using the Football Management System");
                System.out.println("\t\t\tExiting Program...");
                System.out.println("                  ***-------*** ");
                System.exit(0);
                break;

            case 9:
                this.generateRandomMatch();
                this.menu();
        }
    }


    public static void main(String[] args) {
        PremierLeagueManager league_1 = new PremierLeagueManager("save.csv");

        league_1.menu();
    }
}
