
package com.project4;

public class MyCircle extends MyOval {
  //variables
    private double radius; //radius of the circle

    //constructor
    public MyCircle(MyPoint p, int r, MyColor color){
      super(r, r, p, color); //calls the constructor from the super class of MyOval and uses the radius as minor and major axis 
      this.radius = r;       //sets the radius of the circle to r
    }
  
    public double getRadius() { return radius; } //retuns the radius of the circle
  
    @Override
    public boolean similarObjects(MyShape s)   //returns true if the MyShape object passed in is similar to the MyCircle object
    {
      if (!s.getClass().toString().equals("class MyCircle"))
        return false;

      MyCircle circle = (MyCircle) s;
      return circle.getRadius() == this.radius;
    }
  
    @Override
    public String toString() //retuns the decsription of the circle object
    {
        return "Circle info : "+
               "\nCenterX = " + p.getXCoordinate() + radius + 
               "\nCenterY = " + p.getYCoordinate() + radius +
               "\nRadius = " + radius + 
               "\nPermimeter =  " + perimeter() +
               "\nArea = " + area();
    }
}

