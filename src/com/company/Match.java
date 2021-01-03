package com.company;

import java.util.Comparator;

public class Match {

    public int day,month,year;
    public String Club_1;
    public String Club_2;
    public int Score_1, Score_2;

    public Match(int d, int m, int y, String c_1,String c_2,int s_1,int s_2){
        this.day = d;
        this.month = m;
        this.year = y;
        this.Club_1 = c_1;
        this.Club_2 = c_2;
        this.Score_1 = s_1;
        this.Score_2 = s_2;

    }

    public String getDate(){
        return Integer.toString(this.day)+"/"+Integer.toString(this.month)+"/"+Integer.toString(this.year);
    }
    public String getClubs(){
        return this.Club_1 +" Vs "+this.Club_2;
    }
    public String getScores(){
        return Integer.toString(this.Score_1)+" - "+Integer.toString(this.Score_2);
    }

    public static Comparator<Match> Date_Comparator = new Comparator<Match>() {
        @Override
        public int compare(Match o1, Match o2) {

            Integer Year_1 = o1.year;
            Integer Year_2 = o2.year;

            if(!Year_1.equals(Year_2)){
                return Year_1.compareTo(Year_2);
            }
            else {
                Integer Month_1 = o1.month;
                Integer Month_2 = o2.month;

                if(!Month_1.equals(Month_2)){
                    return Month_1.compareTo(Month_2);
                }
                else{
                    Integer Day_1 = o1.day;
                    Integer Day_2 = o2.day;
                    return Day_1.compareTo(Day_2);
                }
            }
        }
    };
}

