package nick.dimitriou;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/MainGUI.fxml"));// load the GUI from the FXML
        primaryStage.setTitle("Drone simulator 27001654");// add the title
        primaryStage.setScene(new Scene(root, 1000, 750));// spesify the sizes of the drone
        primaryStage.setResizable(false);// dont allow the user to resize so it doesnt mess up how the application looks
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
