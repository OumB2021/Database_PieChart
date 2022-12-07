package com.project4;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape {
    MyPoint endPoint1, endPoint2;   //end points        
    MyPoint center;                 //center point of the arc
    double degreeAngle;             //The angular extension of the arc in degrees
    double initDegreeAngle;         //initial angle in degree
    double initRadianAngle;         //initial angle in radian
    double rotationAngle;           //rotation angle 
    MyColor color;                  //Arc's color
    MyOval Oval;                    //The oval bound of the arc
    double a, b;                    //half width and half height of the arc's oval
    double r;                       //radius

    MyArc(MyOval O, MyPoint p, double IDA, double DA, double r, MyColor color) {
        super(p, color);          
        this.Oval = O;               
        this.a = O.getWidth();    
        this.b = O.getHeight();   
        this.initDegreeAngle = IDA; //start angle
        this.degreeAngle = DA;      
        this.initRadianAngle = Math.toRadians(IDA);  //converts the start angle from degrees to radians
        this.rotationAngle = Math.toRadians(DA + IDA); 
        this.center = O.getCenterPoint();  //sets the center of the arc to the center of the oval
        double x = center.getXCoordinate(); //sets the x coordinate of the arc to that of the center of the oval
        double y = center.getYCoordinate(); //sets the y coordinate of the arc to that of the center of the oval
        this.color = color; //sets the color
        double x1 = (x + (a * b) / Math.sqrt(Math.pow(b,2) + Math.pow(a * Math.tan(initRadianAngle), 2)));

        double y1 = (y + (a * b * Math.tan(initRadianAngle)) / 
        Math.sqrt(Math.pow(b,2) + Math.pow(a * Math.tan(initRadianAngle), 2)));

        double x2 = (x + (a * b) / Math.sqrt(Math.pow(b,2) + Math.pow(a * Math.tan(rotationAngle), 2)));

        double y2 = (y + (a * b * Math.tan(rotationAngle)) /
        Math.sqrt(Math.pow(b,2) + Math.pow(a * Math.tan(rotationAngle), 2)));

        endPoint1 = new MyPoint(x1, y1, null); 
        endPoint2 = new MyPoint(x2, y2, null);
        this.r = r;
    }

    //copy constructor
    MyArc (MyArc A, MyPoint p, MyColor color){
        super(p, color);         
        Oval = A.getOval();
        a = Oval.getWidth();
        b = Oval.getHeight();
        center = Oval.getCenterPoint();
        initDegreeAngle = A.initialAngle();
        degreeAngle = A.getAngle();
        initRadianAngle = Math.toRadians(initDegreeAngle);  //converts the start angle from degrees to radians
        rotationAngle = Math.toRadians(degreeAngle + initDegreeAngle); 
    }

    //Getters 
    public MyOval getOval(){return Oval;} 
    public MyPoint getCenter(){return center;}
    public double initialAngle(){return initDegreeAngle;}
    public double getAngle (){return degreeAngle;}
    public MyPoint getStartingPoint(){return endPoint1;}
    public MyPoint getEndPoint(){return endPoint2;}
    public double arcLength(){return (2 * Math.PI * r) * (degreeAngle/360);}


    @Override
    double area(){
        double HpW = b + a;
        double HmW = b - a;
        double HxW = b * b;
        return (0.5 * HxW * initRadianAngle - (HmW * Math.atan(Math.sin(2 * rotationAngle)) /
        (HpW + HxW * Math.cos(2 * rotationAngle))) - Math.atan(HmW * Math.cos(2 * initRadianAngle)) /
        (HpW + HxW * Math.cos(2 * initRadianAngle))) ;
    }

    @Override
    double perimeter(){
        return initRadianAngle * Math.sqrt(2.0 * (a * a + b * b));
    }

    @Override
    public MyRectangle getMyBoundingRectangle(){
        return Oval.getMyBoundingRectangle();
    }

    @Override
    public boolean pointInMyShape(MyPoint p) { //returns true if a MyPoint object is located inside a MyArc object
      double pointAngle = center.getAngleX(p);
      double dx = center.getXCoordinate() - p.getXCoordinate();
      double dy = center.getYCoordinate() - p.getYCoordinate();
      return Math.pow(b * dx, 2) + Math.pow(a * dy, 2) <= Math.pow(a * b, 2) && pointAngle >= initDegreeAngle 
      && pointAngle <= initDegreeAngle + degreeAngle;
    }

    @Override
    public boolean similarObjects(MyShape s)          //compares shapes to see if they are similar in terms of attributes
    {
      if (!s.getClass().toString().equals("class MyArc"))
        return false;
      MyArc A = (MyArc) s;
      return (this.Oval == A.getOval() && this.degreeAngle == A.getAngle());
    }

    @Override
    public void Stroke(GraphicsContext GC){
        GC.setStroke(color.getJavaFXColor());
        GC.strokeArc(center.getXCoordinate() - a, center.getYCoordinate() - a, 2 * a, 2 * b,
        initDegreeAngle, degreeAngle, ArcType.ROUND);
    }

    @Override
    public void draw(GraphicsContext GC) {
        GC.setFill(color.getJavaFXColor());
        GC.fillArc(center.getXCoordinate() - a, center.getYCoordinate() - b, 2 * a, 2 * b, 
        initDegreeAngle, degreeAngle, ArcType.ROUND);
    }

    @Override
    public String toString() {
        return "Arc Center : "+ center +
               "\nOval's Width = " + 2 * a + 
               "\nOval's Height = " + 2 * b +
               "\nStart Angle : "+ degreeAngle +
               "\nPermimeter =  " + perimeter() +
               "\nArea = " + area() +
               "\nColor = " + color.getJavaFXColor(); 
    }
} //end of class MyArc