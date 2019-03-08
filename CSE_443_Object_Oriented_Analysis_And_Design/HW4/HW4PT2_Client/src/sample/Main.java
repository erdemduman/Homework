package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Erdem Duman<br>
 * Main class<br>
 */

public class Main extends Application {

    /**
     * In this method, program is started.<br>
     * @param primaryStage stage object<br>
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Graphian Client");
        primaryStage.setScene(new Scene(root, 520, 470));
        primaryStage.show();
    }


    /**
     * Main method.<br>
     * @param args main arguments<br>
     */
    public static void main(String[] args) {
        launch(args);
    }
}
