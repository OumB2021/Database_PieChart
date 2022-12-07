package com.project4;
import javafx.scene.canvas.*;

public class MyOval extends MyShape{
    
    //variables 
    double major, minor;    //major and minor axis of the oval
    double w, h;            //width and height of the oval
    MyPoint center;         //Top left corner point of the oval

    //constructors
    MyOval(double a, double b, MyPoint center, MyColor color){
        super(center, color);     //calls the super class constructor
        this.center = center;
        this.w = a; //half width of the oval
        this.h = b; //half height of the oval
        this.major = Math.max(a, b);
        this.minor = Math.min(a, b);                                         
    }

    //Getters
    public MyPoint getCenterPoint(){return center;}                 //returns the center point of the oval
    public double getX(){return center.getXCoordinate();}           //returns top left corner point x coordinate
    public double getY(){return center.getYCoordinate();}           //returns top left corner point y coordinate
    public double getA(){return major;}                                 //returns the absissa of the oval
    public double getB(){return minor;}                                 //returns the abscissa axis of the oval
    public double getWidth(){return w;}
    public double getHeight(){return h;}
     
     //returns the perimeter of the oval
     @Override
     public double perimeter() {return 2 * Math.PI * Math.sqrt(((major *major) + (minor * minor)) / 2);}   

     //returns the perimeter of the oval
     @Override 
     public double area() {return 2 * major * minor; }                           

     @Override
     public String toString(){
        return "Oval center X coordinate: " + center.getXCoordinate() +
               "\nOval center Y coordinate: " + center.getYCoordinate() +
               "\nOval major axis: " + major +
               "\nOval minor axis: " + minor +
               "\nOval perimeter: " + perimeter() +
               "\nOval Area: " + area();       
    }

    //returns the bounding rectangle of the MyOval object
    @Override
    public MyRectangle getMyBoundingRectangle(){
        // get X and Y coordinates of the oval
        double x = center.getXCoordinate();
        double y = center.getYCoordinate();
       
        // creates new point with the Oval coordinates
        MyPoint p = new MyPoint(x, y, null);

        return new MyRectangle(w, h, p,null);
    }

    @Override
    public boolean pointInMyShape(MyPoint p){
        if (w == h) return center.distance(p) <= w;
        else {
            double dx = center.getXCoordinate() - p.getXCoordinate();
            double dy = center.getYCoordinate() - p.getYCoordinate();
            return Math.pow(h * dx, 2) + Math.pow(w * dy, 2) <= Math.pow(w * h, 2);
        }
    }

    @Override                                 
    public boolean similarObjects(MyShape s)  //returns true if the MyShape object passed in is similar to the MyOval object
    { 
        if (!s.getClass().toString().equals("class MyOval"))
          return false;
        MyOval o = (MyOval) s;
        return (this.major == o.getA() && this.minor == o.getB());
      }

    @Override
    public void Stroke(GraphicsContext GC){
        GC.setStroke(color.getJavaFXColor());
        GC.strokeOval(center.getXCoordinate(), center.getYCoordinate(), major, minor);
    }

    @Override
    public void draw(GraphicsContext GC){
        GC.setFill(color.getJavaFXColor());
        GC.fillOval(center.getXCoordinate(), center.getYCoordinate(),major, minor);
    }

 } // end of the class MyOval
