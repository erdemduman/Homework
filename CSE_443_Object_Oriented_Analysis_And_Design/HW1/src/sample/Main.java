package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Erdem Duman <br>
 * This class is the main class that starts the UI. <br>
 */

public class Main extends Application {

    /**
     * This method creates UI with given height and width values. <br>
     * @param primaryStage the stage object. <br>
     * @throws Exception whether UI couldn't been started. <br>
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("LinearSolverDeluxe");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    /**
     * This method calls UI. <br>
     * @param args main args. <br>
     */
    public static void main(String[] args) {
        launch(args);
    }
}
