package com.project4;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

class Slice {
    
    MyPoint centerPoint;   
    int radius;
    double initialAngle;
    double rotationAngle;
    double rStartAngle, rAngle, rEndAngle;
    MyColor color;
    String info;

    // constructor
    Slice(MyPoint point, int r, double initAngle, double Angle, MyColor color, String info){

        this.centerPoint = point;
        this.radius = r;
        this.initialAngle = initAngle;
        this.rotationAngle = Angle;
        this.rAngle = Math.toRadians(Angle);
        this.color = color;
        this.info = info;
    }

    Slice (Slice slice){
        
        // in case the object passed is the same as our current object
        if (this != slice){
            this.centerPoint = slice.getCenterPoint();
            this.radius = slice.radius;
            this.initialAngle = slice.initialAngle;
            this.rotationAngle = slice.getRotationAngle();
            this.rAngle = Math.toRadians(slice.rotationAngle);
        }    
    }

    //getters
    public MyPoint getCenterPoint(){return centerPoint;}
    public int getRadius(){return radius;}
    public double getInitialAngle(){return initialAngle;}
    public double getRotationAngle(){return rotationAngle;}
    public double getArcLength(){return (double)radius * rAngle;}
    public MyColor getColor(){return color;}
    public String getInfo(){return info;}

    public double area(){return 0.5 * rAngle * radius * radius;}
    public double perimeter(){return getArcLength();}

    @Override
    public String toString(){
        return "Center point: " + getCenterPoint() +
               ", Radius: " + radius +
               ", (Initial angle, rotation angle): (" +initialAngle + 
               ", " + rotationAngle + ")" +
               ", info: " + getInfo() +
               ", color: " + getColor();
    }

    public void draw(GraphicsContext GC){     
        GC.setFill(color.getJavaFXColor());
        GC.fillArc(centerPoint.getXCoordinate() - radius, centerPoint.getYCoordinate() - radius,
        2 * radius, 2 * radius, initialAngle, rotationAngle, ArcType.ROUND);
    }
}
