package com.company;

import java.util.Comparator;

public class FootballClub extends SportsClub {

    private int NumOfWins = 0;
    private int NumOfDraws = 0;
    private int NumOfDefeats = 0;
    private int NumOfMatches = 0;
    private int NumOfScoredGoals = 0;
    private int NumOfPoints = 0;
    private int NumOfReceivedGoals = 0;

    public FootballClub(){
        super();
    }

    public int getNumOfWins() {
        return this.NumOfWins;
    }

    public void setNumOfWins(int _numOfWins) {
        this.NumOfWins += _numOfWins;
    }

    public int getNumOfDraws() {
        return this.NumOfDraws;
    }

    public void setNumOfDraws(int _numOfDraws) {
        this.NumOfDraws += _numOfDraws;
    }

    public int getNumOfDefeats() {
        return this.NumOfDefeats;
    }

    public void setNumOfDefeats(int _numOfDefeats) {
        this.NumOfDefeats += _numOfDefeats;
    }

    public int getNumOfScoredGoals() {
        return this.NumOfScoredGoals;
    }

    public void setNumOfScoredGoals(int _numOfScoredGoals) {
        this.NumOfScoredGoals += _numOfScoredGoals;
    }

    public int getNumOfReceivedGoals() {
        return this.NumOfReceivedGoals;
    }

    public void setNumOfReceivedGoals(int _numOfReceivedGoals) {
        this.NumOfReceivedGoals += _numOfReceivedGoals;
    }

    public int getNumOfPoints() {
        return this.NumOfPoints;
    }

    public void setNumOfPoints(int _points) {
        this.NumOfPoints += _points;
    }

    public int getNumOfMatches() { return this.NumOfMatches; }

    public void setNumOfMatches(int _numOfMatches) {
        this.NumOfMatches += _numOfMatches;
    }

    public FootballClub(String n, String l){
        super(n,l);
    }

    public static Comparator<FootballClub> Points_Comparator = new Comparator<FootballClub>() {
        @Override
        public int compare(FootballClub o1, FootballClub o2) {
            Integer Point_1 = o1.getNumOfPoints();
            Integer Point_2 = o2.getNumOfPoints();

            if(Point_1 != Point_2){
                return Point_1.compareTo(Point_2);
            }
            else {
                Integer goal_Difference_1 = o1.getNumOfScoredGoals() - o1.getNumOfReceivedGoals();
                Integer goal_Difference_2 = o2.getNumOfScoredGoals() - o2.getNumOfReceivedGoals();
                return goal_Difference_1.compareTo(goal_Difference_2);
            }
        }
    };

    public static Comparator<FootballClub> Goals_Comparator = new Comparator<FootballClub>() {
        @Override
        public int compare(FootballClub o1, FootballClub o2) {
            Integer Goal_1 = o1.getNumOfScoredGoals();
            Integer Goal_2 = o2.getNumOfScoredGoals();

            if(Goal_1 != Goal_2){
                return Goal_1.compareTo(Goal_2);
            }
            else {
                Integer goal_Difference_1 = o1.getNumOfScoredGoals() - o1.getNumOfReceivedGoals();
                Integer goal_Difference_2 = o2.getNumOfScoredGoals() - o2.getNumOfReceivedGoals();
                return goal_Difference_1.compareTo(goal_Difference_2);
            }
        }
    };

    public static Comparator<FootballClub> Wins_Comparator = new Comparator<FootballClub>() {
        @Override
        public int compare(FootballClub o1, FootballClub o2) {
            Integer Win_1 = o1.getNumOfWins();
            Integer Win_2 = o2.getNumOfWins();

            if(Win_1 != Win_2){
                return Win_1.compareTo(Win_2);
            }
            else {
                Integer goal_Difference_1 = o1.getNumOfScoredGoals() - o1.getNumOfReceivedGoals();
                Integer goal_Difference_2 = o2.getNumOfScoredGoals() - o2.getNumOfReceivedGoals();
                return goal_Difference_1.compareTo(goal_Difference_2);
            }
        }
    };

}
