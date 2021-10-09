package nick.dimitriou.GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 * Class to handle the canvas, used by the GUI
 * @author Nick Dimitriou
 */

public class MyCanvas {
    private GraphicsContext graphicsContext;
    private double x; // the width
    private double y; // the height
    private Image cloud;

    /**
     * The contractor sets up relevant graphics context and the size of the canvas
     * @param graphicsContext relevant graphics context
     * @param x the width
     * @param y the height
     */
    public MyCanvas(GraphicsContext graphicsContext, double x, double y) {
        this.graphicsContext = graphicsContext;
        this.x = x;
        this.y = y;
        this.cloud = new Image("nick/dimitriou/GUI/photos/cloud.png");
    }

    /**
     * clears the canvas
     */
    public void clearCanvas(){
        graphicsContext.clearRect(0,0,x,y); // clears all the canvas
    }

    /**
     * draw clouds in the canvas
     */
    public void cloudDrawing(){
        graphicsContext.drawImage(cloud,100, 200,200,100);
        graphicsContext.drawImage(cloud,500, 300,200,100);
        graphicsContext.drawImage(cloud,350, 100,200,100);
        graphicsContext.drawImage(cloud,100, 500,200,100);
        graphicsContext.drawImage(cloud,800, 100,200,100);
        graphicsContext.drawImage(cloud,700, 550,200,100);
    }

    /**
     * Passes the first letter of an color and returns the color
     * @param color the first letter of the color
     * @return the color that the first character represents
     */
    private Color colorFromChar (char color){
        Color answer = Color.BLACK; // setting the default value black, so if an invalid argument is passed we will have a color
        //using a switch statement to check the color that represents the color
        switch (color){
            case 'y':
                answer = Color.YELLOW;
                break;
            case 'w':
                answer = Color.WHITE;
                break;
            case 'r':
                answer = Color.RED;
                break;
            case 'g':
                answer = Color.GREEN;
                break;
            case 'b':
                answer = Color.BLUE;
                break;
            case 'o':
                answer = Color.ORANGE;
                break;
        }
        return answer;
    }

    /**
     * setting the color in the graphic context
     * @param color the color you want to set
     */
    public void setFillColour (Color color){
        graphicsContext.setFill(color);
    }

    /**
     * Draws a circle into the graphic context
     * @param x the x position that the center of the circle will be
     * @param y the y position that the center of the circle will be
     * @param rad the size of the circle
     * @param col the color that you want the circle to be
     */
    public void showCircle(double x, double y, double rad, char col){
        setFillColour(colorFromChar(col));
        graphicsContext.fillArc(x-rad,y-rad, rad*2,rad*2,0,360, ArcType.ROUND);
    }
}
