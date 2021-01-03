package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Collections;

public class Main extends Application {
    public void start(Stage stage) {

        Label label_1 = new Label("Premier League Points Table");
        Font font_1 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,25);
        label_1.setFont(font_1);
        label_1.setTextAlignment(TextAlignment.CENTER);
        //Creating a table view
        TableView<FootballClub> table = new TableView<FootballClub>();

        VBox vbox_1 = new VBox();
        Scene scene = new Scene(vbox_1, 1000, 700);
        //Creating columns
        TableColumn Club = new TableColumn("Club");
        Club.setCellValueFactory(new PropertyValueFactory<>("Club_Name"));
        TableColumn Wins = new TableColumn("Wins");
        Wins.setCellValueFactory(new PropertyValueFactory<>("NumOfWins"));
        TableColumn Defeats = new TableColumn("Defeats");
        Defeats.setCellValueFactory(new PropertyValueFactory<>("NumOfDefeats"));
        TableColumn Draws =  new TableColumn("Draws");
        Draws.setCellValueFactory(new PropertyValueFactory<>("NumOfDraws"));
        TableColumn Played =  new TableColumn("Played");
        Played.setCellValueFactory(new PropertyValueFactory<>("NumOfMatches"));
        TableColumn Goals = new TableColumn("Scored Goals");
        Goals.setCellValueFactory(new PropertyValueFactory<>("NumOfScoredGoals"));
        TableColumn Received_Goal = new TableColumn("Received Goals");
        Received_Goal.setCellValueFactory(new PropertyValueFactory<>("NumOfReceivedGoals"));
        TableColumn Points =  new TableColumn("Points");
        Points.setCellValueFactory(new PropertyValueFactory<>("NumOfPoints"));

        PremierLeagueManager league_1 = new PremierLeagueManager("save.csv");
        Collections.sort(league_1.Club_1,FootballClub.Points_Comparator.reversed());
        for(FootballClub club : league_1.Club_1){
            table.getItems().add(club);
        }

        TableView<Match> match_table = new TableView<Match>();
        TableColumn Date = new TableColumn("Date");
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn Clubs = new TableColumn("Clubs");
        Clubs.setCellValueFactory(new PropertyValueFactory<>("clubs"));
        TableColumn Scores = new TableColumn("Scores");
        Scores.setCellValueFactory(new PropertyValueFactory<>("scores"));



        Label l = new Label("");

        //Creating button to sort according to the points
        Button B_Sort_Points = new Button("Most Points");
        EventHandler<ActionEvent> sortPointsEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                l.setText("Sorting Successful!");
                table.getItems().clear();
                Collections.sort(league_1.Club_1,FootballClub.Points_Comparator.reversed());
                for(FootballClub club : league_1.Club_1){
                    table.getItems().add(club);
                }
            }
        };
        B_Sort_Points.setOnAction(sortPointsEvent);

        //Creating button for sort according to the Goals
        Button B_Sort_Goals = new Button("Sort - Most Goals");
        EventHandler<ActionEvent> sortGoalsEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                l.setText("Sorting Successful!");
                table.getItems().clear();
                Collections.sort(league_1.Club_1,FootballClub.Goals_Comparator.reversed());
                for(FootballClub club : league_1.Club_1){
                    table.getItems().add(club);
                }
            }
        };
        B_Sort_Goals.setOnAction(sortGoalsEvent);

        //Creating button to sort according to wins
        Button B_Sort_Wins = new Button("Sort - Most Wins");
        EventHandler<ActionEvent> sortWinsEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                l.setText("Sorting Successful!");
                table.getItems().clear();
                Collections.sort(league_1.Club_1,FootballClub.Wins_Comparator.reversed());
                for(FootballClub club : league_1.Club_1){
                    table.getItems().add(club);
                }
            }
        };
        B_Sort_Wins.setOnAction(sortWinsEvent);

        //Creating button to generate random match
        Button B_Generate_Match = new Button("Generate Random Match");
        EventHandler<ActionEvent> randomMatchEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {

                table.getItems().clear();
                Match new_Match=league_1.generateRandomMatch();
                l.setText("Random match added!  |  "+new_Match.getDate()+"  |  "+new_Match.getClubs()+"  |  "+new_Match.getScores());
                Collections.sort(league_1.Club_1,FootballClub.Points_Comparator.reversed());
                for(FootballClub club : league_1.Club_1){
                    table.getItems().add(club);
                }
            }
        };
        B_Generate_Match.setOnAction(randomMatchEvent);

        //Creating Back Button
        Button B_Back = new Button("Back");
        EventHandler<ActionEvent> backEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                stage.setScene(scene);
                stage.show();
            }
        };
        B_Back.setOnAction(backEvent);



        //creating Button to display matches
        Button B_Display_Matches = new Button("Display All Matches");
        EventHandler<ActionEvent> displayMatchesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Collections.sort(league_1.Match_1, Match.Date_Comparator);
                match_table.getItems().clear();
                for(Match match : league_1.Match_1){
                    match_table.getItems().add(match);
                }
                Label label = new Label("Please enter the Date");
                Label label1 = new Label("Date (DD/MM/YYYY)");
                TextField textField_1 = new TextField();
                Button B_Search = new Button("Search");
                EventHandler<ActionEvent> searchMatchEvent = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e){
                        match_table.getItems().clear();
                        for(Match match : league_1.Match_1){
                            if(match.getDate().equals(textField_1.getText())){
                                match_table.getItems().add(match);
                            }
                        }
                    }
                };
                B_Search.setOnAction(searchMatchEvent);

                VBox vBox_1 = new VBox();
                vBox_1.setSpacing(5);
                vBox_1.setPadding(new Insets(10, 50, 50, 60));
                vBox_1.getChildren().addAll(label_1, match_table,B_Back,label,label1,textField_1,B_Search);
                Scene scene1 = new Scene(vBox_1, 800, 500);
                stage.setScene(scene1);
                stage.show();
            }
        };
        B_Display_Matches.setOnAction(displayMatchesEvent);

        table.getColumns().addAll(Club,Wins,Defeats,Draws,Played,Goals,Received_Goal,Points);
        match_table.getColumns().addAll(Date,Clubs,Scores);

        //Setting the size of the table
        table.setMaxSize(900, 1100);
        match_table.setMaxSize(900, 1100);

        vbox_1.setSpacing(5);
        vbox_1.setPadding(new Insets(10, 50, 50, 60));
        vbox_1.getChildren().addAll(label_1, table,B_Sort_Points,B_Sort_Goals,B_Sort_Wins,B_Generate_Match,B_Display_Matches,l);

        //Setting the scene
        stage.setTitle("Premier League Points Table");
        stage.setScene(scene);
        stage.show();
    };



    public static void main(String args[]){
        launch(args);
    }
}
