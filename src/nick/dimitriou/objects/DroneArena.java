package nick.dimitriou.objects;

import nick.dimitriou.GUI.MyCanvas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class for all the objects in the map, drons and obstacles
 * @author Nick Dimitriou
 */

public class DroneArena implements Serializable {
    double x, y; // the width and height of the arena
    private List<MapObject> objects; //list for all the objects in the map

    public DroneArena(double x, double y) {
        this.x = x;
        this.y = y;
        objects = new ArrayList<>(); // array list of all the objects, initially empty
        objects.add(new RegularDrone(x/2,y/2,10,2));// adding a regular drone for when the simulations begin
    }

    /**
     * returns the arena size in x direction
     * @return the width of the arena
     */
    public double getX() {
        return x;
    }

    /**
     * returns the arena size in y direction
     * @return the height of the arena
     */
    public double getY() {
        return y;
    }

    /**
     *  returns the list of all the objects
     * @return
     */
    public List<MapObject> getObjects() {
        return objects;
    }

    /**
     * draws all the objects into the canvas
     * @param myCanvas the canvas we want to draw in
     */
    public void drawArena(MyCanvas myCanvas){
        for (MapObject object : objects){ //loops through the list
            object.drawObject(myCanvas); // to add each object separately;
        }
    }

    /**
     * checks all drones to see if it needs to change the angle of the drone
     */
    public void checkDrones(){
        for (MapObject drone : objects){
            if(drone instanceof Drone) { // only the drones need to change angles
                ((Drone) drone).checkDrone(this);
            }
        }
    }

    /**
     * adjust all the moving objects
     */
    public void adjustDrones(){
        for (MapObject drone : objects){
            //only the drones and the birds move
            if(drone instanceof Drone) {
                ((Drone) drone).adjustDrone();
            } else if(drone instanceof Bird){
                ((Bird) drone).adjustBird();
            }
        }
    }

    /**
     * Check the angle of the drone and if hitting and wall bounce in a viable random direction
     * and if it hits another drone change the angle, only the drones need this method
     * @param x the x position of the drone
     * @param y the y position of the drone
     * @param rad the size of the drone
     * @param direction the direction that the drone is heading now
     * @param notID the identifier of the drone not to be checked
     * @return returns the new direction that the drone will be headed
     */
    public Direction CheckDroneAngle(double x, double y, double rad, Direction direction, int notID){
        Direction answer = direction; // to store the answer that will return
        // first check if the drone hit a wall
        if (x < rad){// check to see if the drone hit the left wall
            answer = direction.goEast(); // send the drone to one of the east directions
        }

        if(x > this.x - rad){// check to see if the drone hit the right wall
            answer = direction.goWest(); // send the drone to one of the west directions
        }

        if (y < rad ){// check to see if the drone hit the top wall
            answer = direction.goSouth();// send the drone to one of the south directions
        }

        if(y > this.y - rad){// check to see if the drone hit the bottom wall
            answer = direction.goNorth();// send the drone to one of the north directions
        }

        // check if the drone hit another drone
        for (MapObject object : objects) {
            if(object instanceof Drone) {// specify to check only the drones
                // check all the drones except the one with the given ID
                if (object.getObjectID() != notID && ((Drone) object).hitting(x, y, rad)) {
                    // if it hits another drone send the drones to the opposite direction
                    answer = direction.getOpposite();
                }
            }
        }

        return answer;// return the new direction of the drone
    }

    /**
     * adds a regular drone
     * @return true if the drone can be added in the position and false otherwise
     */
    public boolean addRegular(){
        if(checkIfDroneExistThere(x / 2, y / 2)){
            return false;
        }
        objects.add(new RegularDrone(x/2,y/2,10,2));
        return true;
    }

    /**
     * adds a stupid drone
     * @return true if the drone can be added in the position and false otherwise
     */
    public boolean addStupid(){
        if(checkIfDroneExistThere(x / 2, y / 2)){
            return false;
        }
        objects.add(new StupidDrone(x/2,y/2,10,2));
        return true;
    }

    /**
     * adds a shielded drone
     * @return true if the drone can be added in the position and false otherwise
     */
    public boolean addShielded(){
        if(checkIfDroneExistThere(x / 2, y / 2)){
            return false;
        }
        objects.add(new ShieldedDrone(x/2,y/2,15,2));
        return true;
    }

    /**
     * add a obstacle to the list
     */
    public void addObstacle(){
        //get a random value to place the obstacle
        Random random = new Random();
        // subtracting 100 and adding 50 to be in the range 50 to x-50 to be inside the map
        int x = random.nextInt((int)this.x-100)+50;
        int y =random.nextInt((int) this.y-100)+50;
        objects.add(new RegularObstacle(x,y,10));
    }

    /**
     * counts how many regular obstacles are in the arena
     * @return the number of regular obstacles only
     */
    public int getNumOfRegularObstacles(){
        int counter = 0;
        for (MapObject object : objects){
            // the objects needs to be Regular Obstacle but not a Bird object in order to count only the regular obstacles
            if (object instanceof RegularObstacle && !(object instanceof Bird)){
                counter++;
            }
        }
        return counter;
    }

    /**
     * clears all the objects in the arena
     */
    public void clearArena(){
        objects.clear();// empty the objects array list
        // resets all the counters
        Drone.resetDroneCounter();
        RegularObstacle.resetObstacleCounter();
        StupidDrone.resetStupidCounter();
        ShieldedDrone.resetShieldedCounter();
    }

    /**
     * checks to see if a drone hit an obstacle and if it hit
     * based on the type of the drone has the appropriate action
     */
    public void obstacleCollision(){
        // split the drones and the obstacles from the objects
        List<Drone> drones = new ArrayList<>();// create lists to separate the objects
        List<RegularObstacle> obstacles = new ArrayList<>();
        for (MapObject object : objects){
            // loop through the objects so to split them
            if (object instanceof Drone){
                drones.add((Drone) object);
            } else {
                obstacles.add((RegularObstacle) object);
            }
        }
        // create variables to store the drone and the obstacles that collide
        Drone droneHit = null;
        RegularObstacle obstacleHit = null;
        boolean hit = false;// boolean to check if it there was a collision
        for (Drone drone : drones){
            for (RegularObstacle obstacle: obstacles){
                // check to see if the drone hit an obstacle
                if(drone.hitting(obstacle.x,obstacle.y,obstacle.rad)){
                    hit = true;// make the variable hit true
                    droneHit = drone;// pass to the variables the drone and the obstacle that collide
                    obstacleHit = obstacle;
                }
            }
        }
        if (hit){// if there was a collision
            if (droneHit instanceof RegularDrone) {// if the drone that hit with the obstacle was a regular one
                regularDroneHit((RegularDrone) droneHit, obstacleHit);
            } else if (droneHit instanceof StupidDrone){// if the drone that hit with the obstacle was a stupid one
                stupidDroneHit((StupidDrone) droneHit, obstacleHit);
            } else if(droneHit instanceof ShieldedDrone){// if the drone that hit with the obstacle was a shielded one
               shieldedDroneHit((ShieldedDrone) droneHit, obstacleHit);
            }
        }
    }

    /**
     * for the regular drone to avoid the obstacles
     * @param regularDrone the drone that was in the collision
     * @param regularObstacle the obstacle that was in the collision
     */
    private void regularDroneHit(RegularDrone regularDrone, RegularObstacle regularObstacle){
        if (!regularDrone.isHitObstacle()) {
            regularDrone.avoider(regularObstacle);
        }
    }

    /**
     * for the stupid drone to hit the obstacles and both the obstacle and the drone to be destroyed
     * @param stupidDrone the drone that was in the collision
     * @param regularObstacle the obstacle that was in the collision
     */
    private void stupidDroneHit(StupidDrone stupidDrone, RegularObstacle regularObstacle){
        // destroy bot the drone and obstacle
        objects.remove(stupidDrone);
        objects.remove(regularObstacle);
    }

    /**
     * for the shielded drone to hit the obstacle and the obstacle to be destroyed
     * and it shield to be destroyed
     * @param shieldedDrone the drone that was in the collision
     * @param regularObstacle the obstacle that was in the collision
     */
    private void shieldedDroneHit(ShieldedDrone shieldedDrone, RegularObstacle regularObstacle){
        objects.remove(regularObstacle); //  destroy the obstacle
        int index = objects.indexOf(shieldedDrone); // get the index of the shielded drone
        objects.remove(index);// remove that drone from the object list
        // add a new drone to the same position with the same direction and the same id only regular and not shielded
        objects.add(index, new RegularDrone(shieldedDrone.getX(),shieldedDrone.getY(),
                shieldedDrone.getRad()-5, shieldedDrone.getDroneSpeed(), shieldedDrone.getObjectID(), shieldedDrone.getDirection()));
        ((RegularDrone) objects.get(index)).setWasShielded(true);// set the wasShielded of the regular drone to true
    }

    /**
     * method to control the birds that appear in the map
     */
    public void bird(){
        // use a random to generate a bird in the map
        Random randomAppearance = new Random();
        int num = randomAppearance.nextInt(250);
        Random randomEntry = new Random();
        if (num == 0){// if the random number is 0 generate a bird
            int yCor = randomEntry.nextInt((int) this.getY());// get a random number for the bird that the height will appear
            objects.add(new Bird(10,yCor,10));
        }
        checkIfBirdReachedEnd();
    }

    /**
     * checks to see if the bird reached the end of the map
     */
    private void checkIfBirdReachedEnd(){
        MapObject bird = null;// variable to store the bird if it reached the end
        boolean found = false;// set true if reached end
        for (MapObject object : objects){// loops through all the objects
            if (object instanceof Bird){// if it is bird
                if(object.getX() >= this.getX()){// check if the x is greater or equally
                    found = true;// set the found to true because a bird reached the end
                    bird = object;// store that bird
                }
            }
        }
        //delete that bird outside of the loop to avoid exceptions
        if (found){
            objects.remove(bird);
        }
    }

    /**
     * checks if a drone exist in the specified place
     * @param x the width position that will be ckecked
     * @param y the height position that will be checked
     * @return true if there is a drone in the position and false otherwise
     */
    private boolean checkIfDroneExistThere(double x, double y){
        for (MapObject object: objects){// loops through all the objects
            //adds 80 more to accomodate the moving speed and the rad of the drone
            if ((x >= object.getX()-40 && x<=object.getX()+40)  && (y >=object.getY()-40 && y <= object.getY()+40)){
                return true;
            }
        }
        return false;
    }
}