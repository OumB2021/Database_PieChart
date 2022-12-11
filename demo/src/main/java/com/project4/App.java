package com.project4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import java.io.IOException;
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
           
            //Objects
            MyPoint p = new MyPoint(650, 500, null);
            String filename = "War_and_Peace.txt";
            HistogramAlphaBet histogram = new HistogramAlphaBet(filename);
            HistogramAlphaBet.MyPieChart pie = histogram.new MyPieChart(userInput, p, 300, 0);
            pie.draw(graphicContext);
            PS.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
    }  
}
