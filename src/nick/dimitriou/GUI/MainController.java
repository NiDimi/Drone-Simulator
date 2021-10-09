package nick.dimitriou.GUI;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nick.dimitriou.objects.DroneArena;
import nick.dimitriou.objects.MapObject;
import nick.dimitriou.objects.ShieldedDrone;
import nick.dimitriou.objects.StupidDrone;
import nick.dimitriou.saveload.SaveLoad;

import java.util.List;
import java.util.Optional;

/**
 * Class to control the GUI
 * @author Nick Dimitriou
 */
public class MainController {
    private StatusTimer timer;// the timer that the animation is going to be based on
    private DroneArena arena;// the arena that the drones will be in
    private MyCanvas myCanvas;// the canvas that all the animation will be drawn

    @FXML
    private Canvas canvas;
    @FXML
    private HBox hBox;// for all the buttons in the bottom of the GUI
    @FXML
    private BorderPane borderPane;// the boarderPane that all the application is on
    @FXML
    private MenuItem addDrone;// the button to add a regular drone
    @FXML
    private MenuItem addObstacle;// the button to add a regular obstacle
    @FXML
    private MenuItem addStupid;// the button to add a stupid drone
    @FXML
    private MenuButton menuButton;// the button to add objects
    @FXML
    private Menu add;// the button to add a drone
    @FXML
    private MenuItem addShielded;// the button to add a shielded drone
    @FXML
    private VBox centerVBox;// for the title and the buttons to load and begin in the start
    @FXML
    private MenuBar topMenuBar;// the top menu bat
    @FXML
    private Button startButton;// the resume button
    @FXML
    private Button pauseButton;// the pause button
    @FXML
    private Button clickButton;// the button to use the click function
    @FXML
    private Button dragButton;// the button to use the drag function

    /**
     * The constructor that is called when the application is loading
     */
    public MainController() {
        arena = new DroneArena(950,650);// creating the arena for the drones

        timer = new StatusTimer() {// creating a new timer for the application

            @Override
            public void handle(long l) {// adding what commands need to run every time the timer is running
                arena.checkDrones();
                arena.adjustDrones();
                drawWorld();
                arena.obstacleCollision();
                arena.bird();
            }

        };
    }

    /**
     * the button to exit the application
     */
    @FXML
    public void exitButton(){
        timer.stop();// stop the animation
        System.exit(0);// and exit the application with exit code 0 as no error occur
    }

    /**
     * shows the information of the application
     */
    @FXML
    public void aboutButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);// creating a new alert to show the information
        alert.setTitle("Instructions");// adding the title
        alert.setHeaderText(null);// seting to null as we dont want it to show
        alert.setContentText("Drone simulator\n\n" +// a short description
                "There are 3 types of drones\n" +
                "The regular one - red\n" +
                "The shielded one - red with black\n" +
                "The stupid one - yellow\n\n" +
                "There are 2 types of obstacles\n" +
                "The regular obstacle - green\n" +
                "The bird - black");
        boolean state = timer.isRunning();// getting the state of the timer
        timer.stop();// stopping the timer
        alert.showAndWait();
        if(state) {// setting the timer back to it original form
            startButton();
        }
    }

    /**
     * resumes the application if paused
     */
    @FXML
    public void startButton(){
        startButton.setDisable(true);// disable the start button so the user will know is pressed
        pauseButton.setDisable(false);// enable the pause button so the user can press it
        timer.start();
    }

    /**
     * pauses the application
     */
    @FXML
    public void pauseButton(){
        pauseButton.setDisable(true);// disable the pause button so the user will know is pressed
        startButton.setDisable(false);// enable the start button so the user can press it
        timer.stop();
    }

    /**
     * draws the animation
     */
    private void drawWorld(){
        myCanvas.clearCanvas();// clear the canvas from its previous state
        myCanvas.cloudDrawing();// draw the clouds
        arena.drawArena(myCanvas);// draw the arena with the drones
    }

    /**
     * it starts the application after the loading menu
     */
    @FXML
    public void begin(){
        //set the size of the canvas based on the arena
        canvas.setHeight(arena.getY());
        canvas.setWidth(arena.getX());
        myCanvas = new MyCanvas(canvas.getGraphicsContext2D(),arena.getX(),arena.getY());// create a new canvas
        centerVBox.setVisible(false);// dont show anymore the start menu
        hBox.setDisable(false);// enable the bottom buttons
        topMenuBar.setVisible(true);// show the top menu
        startButton();// set the original state of the program at running
        drag();// and the original mode to drag
    }

    /**
     * clears the canvas from all the objects
     */
    @FXML
    public void clearButton(){
        boolean state = timer.isRunning();//get the state of the timer
        timer.stop();
        // display a dialog to make sure that the user wants to delete everything
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Warning");// set the title
        dialog.setHeaderText("Are you sure you want to delete all the objects ?");//show a message
        //add yes or no buttons
        dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES){// if the user answers and the answer is yes
           arena.clearArena();// clear the arena
            //and enable all buttons so the user can add again objects
           addObstacle.setDisable(false);
           addDrone.setDisable(false);
           addStupid.setDisable(false);
           addShielded.setDisable(false);
           menuButton.setDisable(false);
        }
        drawWorld();// to clear if it was on pause
        if(state) {//set the original state of the timer
            timer.start();
        }
    }

    /**
     * adds a regular drone
     */
    @FXML
    public void addDrone(){
        if(!arena.addRegular()){// check if it can add a drone and adds if it can
            alertForDroneInThatPosition();// if it cannot display a error
        }
        if (arena.getObjects().size() >=20){// make sure that only 20 objects will be in the map at atime
            menuButton.setDisable(true);// disbale the button so the user cant press it
        }
        makeAddDroneDisabled();// disable the add drone button for a second so the user cant add another drone in top of the new one
    }

    /**
     * disables the add drone button for a second
     */
    public void makeAddDroneDisabled(){
        new Thread() {// create new thread so the application does not stop
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        add.setDisable(true);// set the button to disable
                    }
                });
                try {
                    Thread.sleep(1000); //wait for one second
                }
                catch(InterruptedException ignored) {
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        add.setDisable(false);// enable again the button
                    }
                });
            }
        }.start();// start the thread
    }

    /**
     * add a regular obstacle
     */
    @FXML
    public void addObstacle(){
        arena.addObstacle();// add the obstacle to the arena
        if(arena.getNumOfRegularObstacles() == 7){// only 7 regularo obstacles can be in the arena
            addObstacle.setDisable(true);// disable the button so the user cant press it
        }
    }

    /**
     * add a stupid drone
     */
    @FXML
    public void addStupid(){
        if(!arena.addStupid()){// try to add a stupid drone in the arena
            alertForDroneInThatPosition();// if it does not succed display an alert
        }
        if (StupidDrone.getCounter() == 3){// only 3 stupid drones can be in the arena
            addStupid.setDisable(true);// disbale the button so the user cant create more
        }
        makeAddDroneDisabled();// disable the add drone button for a second so the user cant add another drone in top of the new one
    }

    /**
     * add a shielded drone
     */
    @FXML
    public void addShielded(){
        if(!arena.addShielded()){// try to add a shiedled drone in the arena
            alertForDroneInThatPosition();// if it does not succeed display an alert
        }
        if (ShieldedDrone.getCounter() == 4){// only 4 shielded drones can be in the arena
            addShielded.setDisable(true);//disbale the button so the user cant create more
        }
        makeAddDroneDisabled();// disable the add drone button for a second so the user cant add another drone in top of the new one
    }

    /**
     * the drag mode, the user can drag the drones around the arena
     */
    @FXML
    public void drag(){
        canvas.setOnMouseClicked(null);// remove the click mode if it was in ppalce
        dragButton.setDisable(true);// disable the drag button so the user will know is being used
        clickButton.setDisable(false);// enable the click button so the user can switch moded
        this.canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {// add a new mouse eventhandler
            @Override
            public void handle(MouseEvent e) {
                List<MapObject> objects = arena.getObjects();// get all the objects
                for (MapObject object : objects) {// loop through all the objects
                    if (object.getX() - 15 <= e.getX() && object.getX() + 15 >= e.getX()// giving a space of 30 check if the mouse was in a drone
                            && object.getY() - 15 <= e.getY() && object.getY() + 15 >= e.getY()) {
                        object.setXY(e.getX(), e.getY());// set the drone to the mouse
                    }
                }
                drawWorld();// draw the word so the animation of dragging is displayed
            }
        });
    }

    /**
     * the click mode, the user can click the drone to get information for it
     */
    @FXML
    public void clicked(){
        canvas.setOnMouseDragged(null);// remove the drag mode if it was in place
        dragButton.setDisable(false);// enable the drag button so the user can click it
        clickButton.setDisable(true);// disable the click button so the user know is using it
        this.canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {// add a mouse event handler
            @Override
            public void handle(MouseEvent e) {
                List<MapObject> objects = arena.getObjects();
                for (MapObject object : objects) {// loop through each object
                    if (object.getX() - 15 <= e.getX() && object.getX() + 15 >= e.getX()// giving a space of 30 see if the user click one a object
                            && object.getY() - 15 <= e.getY() && object.getY() + 15 >= e.getY()) {
                        showInformation(object);// display inforamtion about hte object
                    }
                }
            }
        });
    }

    /**
     * show information about hte object
     * @param object the object of whom information will be shown
     */
    private void showInformation(MapObject object){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);// create a new alert of type information
        alert.setTitle("Information about " + object.getType()+" " + object.getObjectID());// set the title on the object type and the id
        alert.setHeaderText(object.getType() + " " + object.getObjectID());// display infromation about the object
        alert.setContentText(object.toString());
        boolean state = timer.isRunning();// get the state of the timer
        timer.stop();
        // put a nice style in the alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        alert.showAndWait();
        if (state) {// set the timer to its original state
            startButton();
        }
    }

    /**
     * save the arena
     */
    public void save(){
        SaveLoad.save(this.arena);// save the arena
    }

    /**
     * loads arena
     */
    public void load(){
        DroneArena newArena = SaveLoad.load(this.arena);// get the new arena from the load class
        if (!(newArena == null)) {// if a file was return so it wasnt a null set the arena
            arena = newArena;
            begin();
        }
    }

    /**
     * display an alert in the case the user tries to add a drone in top of another
     */
    private void alertForDroneInThatPosition(){
        Alert alert = new Alert(Alert.AlertType.ERROR);// create a new alert
        alert.setTitle("Error: adding the drone");// set the title
        alert.setHeaderText(null);// no need for headed so null
        alert.setContentText("There was an object passing by and the drone could not be added");
        alert.showAndWait();
    }

}
