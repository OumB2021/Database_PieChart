package com.project4;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class HistogramAlphaBet {
    
    //Holds the frequency of each alphabet
    Map<Character, Integer> frequency = new HashMap<>();
    //Holds the probability of each alphabet 
    Map<Character, Double> probability = new HashMap<>();
    //Name of the file
    String fileName;

    // default constructor
    HistogramAlphaBet(){}

    // overloaded constructor with file name as argument
    HistogramAlphaBet(String file){
        setFileName(file);
        //frequency = new HashMap<Character, Integer>(); // create new Hashmap object
        Scanner scanner; 
        // Exception handler to read data from text
        try {
            String str = "";
            scanner = new Scanner(Paths.get(this.fileName));
            while(scanner.hasNextLine()){
                str = scanner.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase();
                
                // If an empty line has not been read
                if (str.length() != 0){
                    for (int i = 0; i<str.length(); i++)
                        incrementFrequency(frequency, str.charAt(i));                  
                }
            }

            // close the file
            scanner.close();
        } 

        catch (Exception e) {
            System.out.println("Error, failed to open the file.");
        }
    }

    // copy constructor
    HistogramAlphaBet(Map<Character, Integer> map){
        frequency.putAll(map);      
    }
    //set the filename
    public void setFileName(String fileName){this.fileName = fileName;}

    //Getters
    public Map<Character,Integer> getFrequency(){return frequency;}

    public Integer getCumulativeFrequency(){return frequency.values().stream().reduce(0, Integer::sum);}

    public Map<Character, Integer> sortUpFrequency(){
        return frequency
               .entrySet()
               .stream()
               .sorted(Map.Entry.comparingByValue())
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public Map<Character, Integer> sortDownFrequency(){
        return frequency
               .entrySet()
               .stream()
               .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public Map<Character, Double> getProbability(){
        double inverseCumulativeFrequency = 1.0 / getCumulativeFrequency();

        for (Character key : frequency.keySet())
            probability.put(key, (double) frequency.get(key) * inverseCumulativeFrequency);

        return probability.entrySet()
                          .stream()
                          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public Double getSumOfProbability(){return probability.values().stream().reduce(0.0, Double::sum);}

    public String getFile(){return fileName;}

    //Increment the frequency of each alphabet in the map
    private static<K> void incrementFrequency(Map<K, Integer>map, K key){
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }

    @Override
    public String toString(){

        String output = "Frequency of Characters:\n";
        for (Character alphabet : frequency.keySet())
            output += alphabet + ": " + frequency.get(alphabet) + "\n";

        return output;
    }
//------------------------------------------------------------------------------------------------------------------
    public class MyPieChart{

        Map <Character, Slice> slices = new HashMap<>();

        int number;
        MyPoint center;
        int radius;
        double rotateAngle;

        // constructor
        MyPieChart(int number, MyPoint center, int radius, double rotateAngle){
            
            this.number = number;
            this.center = center;
            this.radius = radius;
            this.rotateAngle = rotateAngle;

            probability = getProbability(); // return the probability 
            slices = getMyPieChart();
        }

        public void setNumberofSlices(int num){this.number = num;}

        public Map<Character, Slice> getMyPieChart(){
            
            MyColor[] colors = MyColor.getColors();
            Random rand = new Random();
            double startAngle = rotateAngle;
            
            for (Character key : probability.keySet()){
                double angle = 360.0 * probability.get(key);
                slices.put(key, new Slice(center, radius, startAngle, angle, colors[rand.nextInt(colors.length)],
                String.format("%.2f", probability.get(key))));
                startAngle += angle;
            }

            return slices;
        }
        
        public void draw(GraphicsContext GC){

            // Slices requirements
            int index = 0;
            double slicesInitialAngle = 0;
            double slicesRotationAngle = 0;
            double remainingCharAngle = 0;
            double sumOfProb = 0;
            MyPoint point = slices.get('A').getCenterPoint();
            int radius = slices.get('A').getRadius();
            MyColor color = MyColor.BLACK; //will be used for the text

            // Get the color code for the legend
            MyRectangle legendRec;
            double recSize = 20;
            double fontSize = 20;
            MyPoint rectPoint = new MyPoint(70, 820, null);
            double yCoordinateOfText = rectPoint.getYCoordinate();
            double xDisplacementOfShape;

            //set title of legend canvas
            GC.setFont(new Font("Roboto", 27));
            GC.setTextAlign(TextAlignment.CENTER);
            GC.strokeText("M Y    P I E    C H A R T    L E G E N D ", 500, 780);
            GC.fillText("M Y    P I E    C H A R T    L E G E N D ", 500, 780);

            for (Character key : probability.keySet()){ 

                // In case user doesn't want to display any slice
                if (index == 0)
                    new Slice(point, radius, remainingCharAngle, 360 - remainingCharAngle, MyColor.GRAY, Double.toString(1 - sumOfProb)).draw(GC);
                

                // draw the slices
                if (index < number){
                    slices.get(key).draw(GC);
                    slicesInitialAngle = slices.get(key).initialAngle;
                    slicesRotationAngle = slices.get(key).getRotationAngle();
                    sumOfProb += Double.parseDouble(slices.get(key).getInfo());
                    radius = slices.get(key).getRadius();

                    legendRec = new MyRectangle(recSize, recSize, rectPoint, slices.get(key).color);
                    legendRec.draw(GC);
                    xDisplacementOfShape = rectPoint.getXCoordinate() + 170;
                    yCoordinateOfText = rectPoint.getYCoordinate() + 15;
                    GC.setFont(new Font(null, fontSize));
                    GC.setStroke(color.getJavaFXColor());
                    GC.setFill(color.getJavaFXColor());
                    GC.strokeText(("Grade " + key.toString() + ": " + slices.get(key).getInfo()), rectPoint.getXCoordinate() + 83, yCoordinateOfText + 3);
                    rectPoint = new MyPoint(xDisplacementOfShape + 5, 820, null);
                    index++;
                    
                }

                //Draw the rest of the pie chart
                if ((index == number - 1 && index != 5) || index == 1){
                    remainingCharAngle = slicesInitialAngle + slicesRotationAngle;
                    new Slice(point, radius, remainingCharAngle, 360 - remainingCharAngle, MyColor.GRAY, Double.toString(1 - sumOfProb)).draw(GC);
                }
                
            }

            // Get the probability for the rest of the grades
            if (index < 5 || index == 0){
                legendRec = new MyRectangle(recSize, recSize, rectPoint, MyColor.GRAY);
                    legendRec.draw(GC);
                    xDisplacementOfShape = rectPoint.getYCoordinate() + 30;
                    yCoordinateOfText = rectPoint.getYCoordinate() + 15;
                    GC.setFont(new Font(null, fontSize));
                    GC.setStroke(color.getJavaFXColor());
                    GC.strokeText("Other grades: " + String.format("%.2f", 1 - sumOfProb), rectPoint.getXCoordinate() + 120, yCoordinateOfText + 3);
            }

        }
    }
}
