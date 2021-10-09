package nick.dimitriou.objects;

import java.io.Serializable;

/**
 * Abstact class for all the drones in the map
 * @author Nick Dimitriou
 */
public abstract class Drone extends MapObject implements Serializable {
    static int droneCounter = 0; //used to know how many drones we have and to have an identifier
    protected Direction direction;// the direction that the drone is heading
    protected double droneSpeed;// the speed that the drone is moving

    /**
     * Create a drone at the position x,y of the size rad with a speed and a color
     * @param x the width position
     * @param y the height position
     * @param rad the size
     * @param droneSpeed the speed
     * @param col the color
     */
    public Drone(double x, double y, double rad, double droneSpeed, char col) {
        super(x, y, rad, col);
        ID = droneCounter++;// setting the identifier of the drone based on the counter
        direction = Direction.randomDirection();// get a random direction for the drone to move
        this.droneSpeed = droneSpeed;
    }

    /**
     * sets the droneCounter to zero
     */
    protected static void resetDroneCounter() {
        Drone.droneCounter = 0;
    }

    /**
     * return the string description of the object
     * @return
     */
    @Override
    public String toString() {
        return super.toString() +" going " + direction;
    }

    /**
     * is drone at x,y hitting something
     * @param x the width position of the object that see if it is going to hit
     * @param y the height position of the object that see if it is going to hit
     * @param rad the size of the object that see if jt is going to hit
     * @return true if is hitting or false if it is not
     */
    public boolean hitting (double x, double y, double rad){
        //add 200 so it will not need to touch the other object
        return (x-this.x) * (x-this.x) + (y - this.y) * (y-this.y) < (rad + this.rad) * (rad + this.rad)+200;
    }

    /**
     * return the direction of the drone
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * returns the drone speed
     * @return
     */
    public double getDroneSpeed() {
        return droneSpeed;
    }

    /**
     * change the direction of the drone if the drone is hitting a wall or another object
     * @param droneArena the arena that the drones are
     */
    protected void checkDrone(DroneArena droneArena) {
        direction = droneArena.CheckDroneAngle(x,y,rad,direction,ID);
    }

    /**
     * move the drone based on the speed and the direction
     */
    protected void adjustDrone() {
        double radAngle = direction.getAngle() * Math.PI/180;// put the angle in radians
        x += droneSpeed * Math.cos(radAngle);// the new X position
        y += droneSpeed * Math.sin(radAngle);// the new Y position
    }
}
