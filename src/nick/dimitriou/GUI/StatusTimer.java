package nick.dimitriou.GUI;

import javafx.animation.AnimationTimer;

/**
 * Overitting the animation timer to add a function to get what the timer is
 * @author Nick Dimitriou
 */
public abstract class StatusTimer extends AnimationTimer {
    private boolean running;// variable to hold if the timer is running or not

    /**
     * start the animation and set the running to true
     */
    @Override
    public void start() {
        super.start();// use the super to start
        running = true;// and add the varriable to running
    }

    /**
     * stop the animation adn set the running to false
     */
    @Override
    public void stop() {
        super.stop();// use the super to stop
        running = false;// and add teh variable to not running
    }

    /**
     * return the state of the animation
     * @return
     */
    public boolean isRunning(){
        return running;
    }
}
