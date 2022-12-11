package com.project4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;
 
 
public class App extends Application {
 
    public static void main(String[] args) {
        launch(args);
    }
   
    @Override
    public void start(Stage PS) throws IOException {
 
        try{
            Canvas canvas = new Canvas(1000, 1000);
            GraphicsContext graphicContext = canvas.getGraphicsContext2D();      
            Pane P = new Pane();
            P.getChildren().add(canvas);
            Scene newScene = new Scene(P, (MyColor.CORNSILK).getJavaFXColor());
            PS.setTitle("Project4");
            PS.setScene(newScene);
           
            String input = JOptionPane.showInputDialog("How many slices would you like to display?");
            int userInput = Integer.parseInt(input);
 
            //Input validation
            while (!(userInput >= 0 && userInput <=5)){
                input = JOptionPane.showInputDialog("Enter a number from 0 to 5");
                userInput = Integer.parseInt(input);
            }
            //----------------------------------------------------------------------------------------------------
            String url = "jdbc:mysql://localhost:3306/Sample?allowLoadLocalInfile=true";
            String username = "root";
            String password = "Jkjkjk94+";
            
            // Establish connection
            StudentDatabase DB = new StudentDatabase(url, username, password);

            // Creates variables
            String ddlCreateTable, tableName, filename, fromTable;

            // Creates and insert values into the schedule table
            filename = "C:/Users/baarr/OneDrive/Bureau/Java/Project 4/demo/ScheduleSpring2022.txt";
            tableName = "Schedule";
            ddlCreateTable = StudentDatabaseInterface.ddlCreateTableSchedule;
            StudentDatabase.Schedule schedule = DB.new Schedule(ddlCreateTable, filename, tableName);

            // Creates and insert values into the courses table
            fromTable = "Schedule";
            tableName = "Courses";
            ddlCreateTable = StudentDatabaseInterface.ddlCreateTableCourses;
            StudentDatabase.Courses courses = DB.new Courses(ddlCreateTable, tableName, fromTable);

            // Creates and insert values into the Students table
            tableName = "Students";
            ddlCreateTable = StudentDatabaseInterface.ddlCreateTableStudents;
            StudentDatabase.Students student = DB.new Students(ddlCreateTable, tableName);
            
            // Creates and insert values into the Classes table
            tableName = "Classes";
            ddlCreateTable = StudentDatabaseInterface.ddlCreateTableClasses;
            StudentDatabase.Classes classes = DB.new Classes(ddlCreateTable, tableName);

            // Creates and insert values into the AggregateGrades table
            fromTable = tableName;
            tableName = "AggregateGrades";
            ddlCreateTable = StudentDatabaseInterface.ddlCreateTableAggregateGrades;
            StudentDatabase.AggregateGrades grades = DB.new AggregateGrades(ddlCreateTable, tableName, fromTable);


            Map <Character, Integer> finalGrades = grades.getGrades(tableName);
            //----------------------------------------------------------------------------------------------------
            //Objects
            MyPoint p = new MyPoint(500, 370, null);
            HistogramAlphaBet histogram = new HistogramAlphaBet(finalGrades);
            HistogramAlphaBet.MyPieChart pie = histogram.new MyPieChart(userInput, p, 310, 0);
            pie.draw(graphicContext);
            PS.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
    }  
}
