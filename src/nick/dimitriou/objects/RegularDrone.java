package nick.dimitriou.objects;

/**
 * Class for the regular drone that avoids the obstacles
 * @author Nick Dimitriou
 */
public class RegularDrone extends Drone {
    private boolean hitObstacle = false; // boolean to see if the drone is hitting an obstacle
    private boolean wasShielded = false;// boolean to see if the drone was a shielded before it became a regular

    /**
     * Create a regular drone at the position x,y of the size rad with a speed and a color
     * @param x the width position
     * @param y the height position
     * @param rad the size
     * @param droneSpeed the speed
     */
    public RegularDrone(double x, double y, double rad, double droneSpeed) {
        super(x, y, rad,droneSpeed,'r');// calling the super constructor with the red character to create a red drone
    }

    /**
     * Constructor to use when creating a regular drone as a shielded
     * @param x the width position 
     * @param y the height position 
     * @param rad the size 
     * @param droneSpeed the speed
     * @param ID the id it was as a shielded drone
     * @param direction the direction that was going
     */
    public RegularDrone(double x, double y, double rad, double droneSpeed,int ID, Direction direction) {
        super(x, y, rad,droneSpeed,'r');
        this.ID = ID;
        this.direction = direction;
    }

    /**
     * return if the obstacle is hitting a obstacle
     * @return
     */
    public boolean isHitObstacle() {
        return hitObstacle;
    }

    /**
     * Set the parameter stating if the drone is hitting a obstacle
     * @param hitObstacle boolean if the drone is hitting a obstacle
     */
    public void setHitObstacle(boolean hitObstacle) {
        this.hitObstacle = hitObstacle;
    }

    /**
     * the type of the drone 
     * @return a string with the type
     */
    @Override
    public String getType() {
        return "Regular Drone";
    }

    /**
     * sates boolean that states if the drone was shielded before
     * @param wasShielded the variable that holds if it was shielded 
     */
    public void setWasShielded(boolean wasShielded) {
        this.wasShielded = wasShielded;
    }

    /**
     * return the string with details of the drone
     * @return
     */
    @Override
    public String toString() {
        if(!wasShielded) {// add that the drone was shielded based on the variable
            return super.toString();
        }
        return super.toString() + "\nshield was destroyed";
    }

    /**
     * method to make the drone avoid any obstacle
     * @param obstacle the obstacle that the drone is going to hit
     */
    public void avoider(RegularObstacle obstacle){
        if (direction.ordinal() % 2 !=0){// if the drone is going side ways turn it straight
            direction = direction.add(1);
            return;// return so the method will run again and now it will be straight
        }
        int change = 0;// method to store the change in teh direction needed
        if(!hitObstacle) {
            if (direction.ordinal() == 2 || direction.ordinal() == 6) {// the drone will hit from top or bottom
                // based on the way that the drone will hit the obstacle find the optimal solution
                if ((x < obstacle.getX() && y > obstacle.getY()) || (x > obstacle.getX() && y < obstacle.getY())) {
                    change = -2;
                } else change = 2;
            } else {// the drone will hit from left or right
                // based on the way that the drone will hit the obstacle find the optimal solution
                if ((x < obstacle.getX() && y < obstacle.getY()) || (x > obstacle.getX() && y > obstacle.getY())) {
                    change = -2;
                } else change = 2;

            }
        }

        int finalChange = change;
        new Thread(() -> {// create a new thread that will handle the movement of the drone
            setHitObstacle(true);// the drone is currently in the thread so dont call again the methhod
            direction = direction.add(finalChange);// add the change to the direction
            while (hitting(obstacle.getX(),obstacle.getY(), obstacle.getRad())){
                adjustDrone();// adjust the drone will the drone still hits the obstacle
            }
            try {//sleep so the movement seems more natural
                Thread.sleep(50);
            } catch (InterruptedException e) {//catch the if teh thread cant sleep
                e.printStackTrace();
            }
            direction = direction.add(-finalChange);//make the direction of the drone the starting one
            setHitObstacle(false);// set that the thread had finish
        }).start();
    }
}
