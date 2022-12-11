package com.project4;
import javafx.scene.paint.Color;

public enum MyColor {
    MAROON	(128,0,0,255),
    DARKRED	(139,0,0,255),
 	BROWN (165,42,42,255),
 	FIREBRICK (178,34,34,255),
 	CRIMSON (220,20,60,255),
 	RED (255,0,0,255),
 	TOMATO (255,99,71,255),
 	CORAL (255,127,80,255),
 	INDIANRED (205,92,92,255),
 	LIGHTCORAL (240,128,128,255),
 	DARKSALMON (233,150,122,255),
 	SALMON (250,128,114,255),
 	LIGHTSALMON (255,160,122,255),
 	ORANGERED (255,69,0,255),
 	DARKORANGE (255,140,0,255),
 	ORANGE (255,165,0,255),
 	GOLD (255,215,0,255),
 	DARKGOLDENROD (184,134,11,255),
 	GOLDENROD (218,165,32,255),
 	PALEGOLDENROD (238,232,170,255),
 	DARKKHAKI (189,183,107,255),
 	KHAKI (240,230,140,255),
 	OLIVE (128,128,0,255),
 	YELLOW (255,255,0,255),
 	YELLOWGREEN (154,205,50,255),
 	DARKOLIVEGREEN (85,107,47,255),
    OLIVEDRAB (107,142,35,255),
 	LAWNGREEN (124,252,0,255),
 	CHARTREUSE (127,255,0,255),
 	GREENYELLOW (173,255,47,255),
 	DARKGREEN (0,100,0,255),
 	GREEN (0,128,0,255),
 	FORESTGREEN (34,139,34,255),
 	LIME (0,255,0,255),
 	LIMEGREEN (50,205,50,255),
 	LIGHTGREEN (144,238,144,255),
 	PALEGREEN (152,251,152,255),
 	DARKSEAGREEN (143,188,143,255),
 	MEDIUMSPRINGGREEN (0,250,154,255),
 	SPRINGGREEN (0,255,127,255),
 	SEAGREEN (46,139,87,255),
 	MEDIUMAQUAMARINE (102,205,170,255),
 	MEDIUMSEAGREEN (60,179,113,255),
 	LIGHTSEAGREEN (32,178,170,255),
 	DARKSLATEGRAY (47,79,79,255),
 	TEAL (0,128,128,255),
 	DARKCYAN (0,139,139,255),
 	DARKTURQUOISE (0,206,209,255),
 	CADETBLUE (95,158,160,255),
 	STEELBLUE (70,130,180,255),
 	CORNFLOWERBLUE (100,149,237,255),
 	DEEPSKYBLUE (0,191,255,255),
 	DODGERBLUE (30,144,255,255),
 	MIDNIGHTBLUE (25,25,112,255),
 	NAVY (0,0,128,255),
 	MEDIUMBLUE (0,0,205,255),
 	BLUE (0,0,255,255),
 	ROYALBLUE (65,105,225,255),
 	BLUEVIOLET (138,43,226,255),
 	INDIGO (75,0,130,255),
 	DARKSLATEBLUE (72,61,139,255),
 	SLATEBLUE (106,90,205,255),
 	MEDIUMSLATEBLUE (123,104,238,255),
 	MEDIUMPURPLE (147,112,219,255),
 	DARKMAGENTA (139,0,139,255),
 	DARKVIOLET (148,0,211,255),
 	DARKORCHID (153,50,204,255),
 	MEDIUMORCHID (186,85,211,255),
 	PURPLE (128,0,128,255),
 	MAGENTA (255,0,255,255),
 	ORCHID (218,112,214,255),
 	MEDIUMVIOLETRED (199,21,133,255),
 	PALEVIOLETRED (219,112,147,255),
 	DEEPPINK (255,20,147,255),
 	HOTPINK (255,105,180,255),
 	WHEAT (245,222,179,255),
 	CORNSILK (255,248,220,255),
 	TAN (210,180,140,255),
 	ROSYBROWN (188,143,143,255),
 	SLATEGRA (112,128,144,255),
 	LIGHTSLATEGRAY (119,136,153,255),
 	BLACK (0,0,0,255),
 	DIMGRAY (105,105,105,255),
 	GRAY (128,128,128,255);
    
    //variables
    private int r; //Red Colors range from 0 to 255
    private int b; //Blue Colors range from 0 to 255
    private int g; //Green Colors range from 0 to 255
	private int a; //Opacity range from 0 to 255
    private int argb; // Pack of Opacity - Red - Green - Blue in bits

    //constructor
    MyColor(int r, int g, int b, int a){
       setR(r);
	   setG(g);
       setB(b);
	   setA(a);
       setARGB(r, g, b, a);
    }

    //Setters 
    public void setR(int r){if(r>=0 && r<=255){this.r = r;}}
    public void setG(int g){if(g>=0 && g<=255){this.g = g;}}
    public void setB(int b){if(b>=0 && b<=255){this.b = b;}}
	public void setA(int a){if(a>=0 && a<=255){this.a = a;}}
    public void setARGB(int a, int r, int g, int b) {
        this.argb = (a << 24) & 0xFF000000 |
                    (r << 16) & 0x00FF0000|
                    (g << 8) & 0x0000FF00|
                     b;
	}

	//Getters
    public int getR(){return r;} //returns the red color
	public int getG(){return g;} //returns the green color
	public int getB(){return b;} //returns the blue color
	public int getA(){return a;} //returns the alpha
	public int getARGB(){return argb;} //returns the arbg

	//Get Hexadecimal representation Method
	public String getHexadecimal(){
		return "#" + Integer.toHexString(getARGB()).toUpperCase();
	}

	//Return the JavaFx Color objects of the constant colors in MyColor. 
	public Color getJavaFXColor(){return Color.rgb(r, g, b, ((double) a/255.0));}
	 
	//Stores All colors in an Array 
	public static MyColor[] getColors(){return MyColor.values();}
       
	//Print Colors and hexadecimal representation
	public String printColorsAndHex(){
		return "Red component : " + getR() +
			   "\nGreen component : " + getG() +
			   "\nBlue component : " + getB() +
			   "\nHexadecimal value : " + this.getHexadecimal();
	}	

} // end of class MyColor

