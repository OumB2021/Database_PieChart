package com.project4;
import javafx.scene.canvas.GraphicsContext;

public class MyRectangle extends MyShape{
   
    //Variables 
    double w;    //The width of the rectangle
    double h;    //The height of the rectangle
    MyPoint Tlc; //Top left corner point

    //Constructors
    MyRectangle(double h, double w, MyPoint Tlc, MyColor color){
        super(Tlc, color);
        this.Tlc = Tlc; 
        this.h= h;
        this.w= w; 
    }

    //Getters 
    public MyPoint getTlc(){return Tlc;}    //returns the top left corner point
    public double getWidth(){return w;}     //returns the the width of the rectangle
    public double getHeight(){return h;}    //returns the height of the rectangle

    //Overriden methods
    @Override
    public double perimeter(){return (2 * w * h);}     //returns the perimeter of the rectangle

    @Override
    public double area(){return (w * h);}              //returns the area of the rectangle

    @Override
    public String toString(){
        return "Rectangle top left corner X coordinate: " + Tlc. getXCoordinate()+
               "\nRectangle top left corner Y coordinate: " + Tlc.getYCoordinate() +
               "\nRectangle's width: " + getWidth() +
               "\nRectangle's height: " + getHeight() +
               "\nRectangle's perimeter: " + perimeter() +
               "\nRectangle's Area: " + area();

    }

    @Override
    public MyRectangle getMyBoundingRectangle(){return this;}

    @Override
    public boolean pointInMyShape(MyPoint point){                    
       double x = getWidth(); 
       double y = getHeight();
       double xr = point.getXCoordinate();
       double yr = point.getYCoordinate();
       return ((xr <= x && x <= xr + w) && (yr <= y && y <= yr + h));
    }

    @Override                                       
    public boolean similarObjects(MyShape s)  //returns true if the MyShape object passed in is similar to the MyRectangle object
    {
        if (!s.getClass().toString().equals("class MyRectyangle"))
          return false;
        MyRectangle r = (MyRectangle) s;
        return (this.w == r.getWidth() && this.h == r.getHeight());
    }

    @Override
    public void Stroke(GraphicsContext GC){
        GC.setStroke(color.getJavaFXColor());
        GC.strokeRect(this.p.getXCoordinate(), this.p.getYCoordinate(), w,  h);
    }

    @Override
    public void draw(GraphicsContext GC){
        GC.setFill(color.getJavaFXColor());
        GC.fillRect(this.p.getXCoordinate(), this.p.getYCoordinate(), w,h);
    }

} // end of the class MyRectangle
