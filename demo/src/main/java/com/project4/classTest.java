package com.project4;
//import java.util.Random;

import java.io.IOException;
import java.util.Map;

//import javax.swing.JOptionPane;

//import javax.swing.JOptionPane;

public class classTest {
    public static void main(String[] args) throws IOException {
      
        MyPoint p = new MyPoint(100, 100, MyColor.RED);
        String filename = "Project1/War_and_Peace.txt";
        //String filename = JOptionPane.showInputDialog("Enter the name of the file:");
        HistogramAlphaBet histogram = new HistogramAlphaBet(filename);
        Map<Character, Integer> example = histogram.sortDownFrequency();
        example.forEach((K, V) -> System.out.println(K + ": " + V));
        System.out.println("\nNumber of characters: " + example.values().stream().reduce(0, (x, y) -> x + y) + "\n");
        example = histogram.sortDownFrequency();

        HistogramAlphaBet.MyPieChart pie = histogram.new MyPieChart(6, p, 50, 30);
        System.out.println("Probability : " + histogram.getProbability());
        System.out.println("\nFrequencies: " + example);
        System.out.println("\nSum of prob: " + histogram.getSumOfProbability()+"\n");

        Map<Character, Slice> slices = pie.getMyPieChart();
        slices.forEach((K,V) -> System.out.println(K + ": " + slices.get(K)));

        double sumOfAngles = 0.0;
        for (Character key : slices.keySet()){sumOfAngles += slices.get(key).getRotationAngle();}
        System.out.println("Sum: " + sumOfAngles);

    }
} //end of classTest
