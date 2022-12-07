package com.project4;
import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;

public class MyPoint {

    public double x;         //x coordinate of the point
    public double y;         //y coordinate of the point
    MyColor pointColor;      //color of the point

    //Constructor
    MyPoint() {setPoint(0,0); this.pointColor = MyColor.BLACK;}

    MyPoint(double x, double y, MyColor color){
        setPoint(x,y);
        this.pointColor = Optional.ofNullable(color).orElse(MyColor.BLACK);
    } 

    MyPoint(MyPoint p, MyColor color){
        setPoint(p);
        this.pointColor = Optional.ofNullable(color).orElse(MyColor.BLACK);
    } 

    //Setters
    public void setPoint(MyPoint p){
        this.x = p.getXCoordinate();
        this.y = p.getYCoordinate();
    }

    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(MyColor pointColor) {
        this.pointColor = Optional.ofNullable(pointColor).orElse(MyColor.BLACK);
        //The default color is blue unless chosen otherwise by the user
    } 

    //Getters 
    public MyColor getColor() {return pointColor;}     //returns the color of the point
    public double getXCoordinate(){return x;}          //returns the x coordinate of the point
    public double getYCoordinate(){return y;}          //returns the y coordinate of the point


    public void translate(double dx, double dy){
        //Changes the x and y coordinates by the specified distance
        this.x += dx;
        this.y += dy;
    }

    public double distanceFromCoordinatesToOrigin(){ 
    //Distance from points x and y to origin
        return Math.sqrt((x*x) + (y*y));
    }

    public double distance(MyPoint p){
        //Distance from points x and y to other defined points
        double dx = x - p.getXCoordinate();
        double dy = y - p.getYCoordinate();
        return Math.sqrt((dx*dx) + (dy*dy));
    }

    public double getAngleX(MyPoint p){
        //returns the angleX
        double dx = p.getXCoordinate() - x;
        double dy = p.getYCoordinate() - y;
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    public void draw(GraphicsContext GC){
        GC.setFill(pointColor.getJavaFXColor());
        GC.fillRect(x,y,1,1);
    }
    
    //Print the position x and y of the point
    @Override
    public String toString() {return "(x = " + x + ", y=" + y + ")" ;}

} //end of the class MyPoint
