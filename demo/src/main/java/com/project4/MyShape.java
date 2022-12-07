package com.project4;
import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape implements MyShapeInterface{

    //Variables
    MyPoint p;                //Point of the javas display coordinate system
    MyColor color;            //Color of the shape

    //Constructors
    MyShape(MyPoint p, MyColor shapeColor){
        setPoint(p);
        setColor(shapeColor);
    }

    MyShape(int x, int y, MyColor shapeColor){
        this.p = new MyPoint(x, y, color);
        setColor(shapeColor);
    }

    //Setters
    public void setPoint(MyPoint p) { this.p = p; }            //sets the point p
    public void setPoint(int x, int y) { p.setPoint(x, y); }   //assigns x and y as the point's coordinates
    
    public void setColor(MyColor shapeColor){
        //default color of the shape is Black unless choser otherwise by the user
        this.color = Optional.ofNullable(shapeColor).orElse(MyColor.BLACK);
    }

    //Getters 
    public MyPoint getPoint() {return p;}                        //returns the point
    public MyColor getShapeColor() {return color;}               //returns the color of the shape
    public double getXCoordinate(){return p.getXCoordinate();}   //returns the x coordinate of the point
    public double getYCoordinate(){return p.getYCoordinate();}   //returns the y coordinate of the point

    // abstract methods 
    abstract double area();                   
    abstract double perimeter();             
    abstract void draw (GraphicsContext GC);
    abstract void Stroke(GraphicsContext GC);

    @Override
    public String toString() {return "This is a MyShape object";}  //Object's description
    
} // end of the class MyShape



