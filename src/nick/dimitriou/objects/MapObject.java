package nick.dimitriou.objects;

import nick.dimitriou.GUI.MyCanvas;

import java.io.Serializable;

/**
 * Abstact class for all the objects to inherit
 * @author Nick Dimitriou
 */
public abstract class MapObject implements Serializable {
    protected double x,y,rad; // the position and the size of the object
    protected char col; //the color of the object
    protected int ID; //a unique identifier for each object

    /**
     * Constructor for an object and setting the position size and color
     * @param x the width
     * @param y the height
     * @param rad the size
     * @param col the color
     */
    public MapObject(double x, double y, double rad, char col) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.col = col;
    }

    /**
     * return the position x of the object
     * @return the width position of the object
     */
    public double getX() {
        return x;
    }

    /**
     * return the position y of the object
     * @return the height position of the object
     */
    public double getY() {
        return y;
    }

    /**
     * return the rad of the object
     * @return the size of the object
     */
    public double getRad() {
        return rad;
    }

    /**
     * return the ID of the object
     * @return the unique identifier of the object
     */
    public int getObjectID() {
        return ID;
    }

    /**
     * set the ball at the position x, y
     * @param x the width position to set the object
     * @param y the height position to set the object
     */
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * draw the object into the canvas
     * @param myCanvas the canvas to draw the object
     */
    public void drawObject(MyCanvas myCanvas){
        myCanvas.showCircle(x,y,rad,col);
    }

    /**
     * return the string description of the object
     * @return
     */
    public String toString(){
        return  "At " + Math.round(x)+ ", "+Math.round(y);
    }

    /**
     * implement the type of the object
     * @return the type of the object
     */
    public abstract String getType();
}
