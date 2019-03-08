package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Erdem Duman <br>
 * This is the main class of program.<br>
 *
 */
public class Main extends Application {

    /**
     * This method starts the program.<br>
     * @param primaryStage Stage object. <br>
     * @throws Exception exception.<br>
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("I Miss ZırhSan");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    /**
     * Main method of program.<br>
     * @param args main arguments.<br>
     */
    public static void main(String[] args) {
        launch(args);
    }
}
