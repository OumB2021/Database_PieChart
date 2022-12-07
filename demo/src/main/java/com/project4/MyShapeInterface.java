
package com.project4;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

interface MyShapeInterface {

  MyRectangle getMyBoundingRectangle(); //returns the bounding rectangle of the object MyShape 
  boolean pointInMyShape(MyPoint p); //returns true if the MyPoint object is found in a MyShape object
  boolean similarObjects(MyShape s);  //returns true if the MyShape object passed in is similar to a subclass object of MyShape 

  static MyRectangle intersectMyRectangles(MyRectangle R1, MyRectangle R2){
    double x1 = R1.getTlc().x;  
    double y1 = R1.getTlc().y;  
    double w1 = R1.getWidth();  
    double h1 = R1.getHeight(); 

    double x2 = R2.getTlc().x;    
    double y2 = R2.getTlc().y;   
    double w2 = R2.getWidth(); 
    double h2 = R2.getHeight(); 

    if (y1 + h1 < y2 || y1 > y2 + h2) 
      return null;                    
    if (x1 + w1 < x2 || x1 > x2 + w2) 
      return null;                   

    double xMax = Math.max(x1, x2); //assigns the maximum of the x coordinates of R1 and R2 to xMax
    double yMax = Math.max(y1, y2); //assigns the maximum of the y coordinates of R1 and R2 to yMax
    double xMin = Math.min(x1 + w1, x2 + w2); 
    double yMin = Math.min(y1 + h1, y2 + h2); 

    MyPoint p = new MyPoint(xMax, yMax, null); //creates a new Point with the maximum coordinates of R1 and R2
    return new MyRectangle(Math.abs(xMin - xMax), Math.abs(yMin - yMax), p, null); 
    //returns the new Myrectangle with the absolute values of the difference between the maximum and minumum coordinates
  }

  //returns the overlapping rectangle of the bounding rectangles of the MyShape objects
  static MyRectangle overlapMyShape(MyShape S1, MyShape S2){
    MyRectangle R1 = S1.getMyBoundingRectangle(); //assigns the bounding rectangle of the MyShape S1 to R1
    MyRectangle R2 = S2.getMyBoundingRectangle(); //assigns the bounding rectangle of the MyShape S2 to R2
    return intersectMyRectangles(R1, R2); //retuns the intersection of R1 and R2
  }

  static List<MyPoint> intersectMyShapes(MyShape S1, MyShape S2) //retuns the set of intersections points of the MyShape objects
  {
    MyRectangle R = overlapMyShape(S1, S2);
    if (R == null)
      return null;
    
    //Get dimensions and points of the rectangle
    double x = R.getTlc().x;
    double y = R.getTlc().y;
    double w = R.getWidth();  
    double h = R.getHeight(); 

    //creates a new list for the intersection points
    List<MyPoint> intersect = new ArrayList<MyPoint>(); 
    
    for (int i = 0; i < w; i++) //loops through the points and collect the intersections points and stores them in the list
    {
      double xi = x + i;
      for (int j = 0; j < h; j++) {
        MyPoint p = new MyPoint(xi, (y + j), null);
        if (S1.pointInMyShape(p) && S2.pointInMyShape(p)) {
          intersect.add(p);
        }
      }
    }
    return intersect;
  }

  static void drawIntersectMyShapes(MyShape S1, MyShape S2, GraphicsContext GC) //draws the shape resulting from the intersection of the points
  { 
    List<MyPoint> intersect = intersectMyShapes(S1, S2); //creates a new list for the intersection points

    MyPoint p;
    for (int i = 0; i < intersect.size(); i++){
        p = intersect.get(i);
        p.draw(GC);
    }
  }
}
