package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;

/**
 * @author Erdem Duman <br>
 * This class connects user interface and source code of program. <br>
 */
public class Controller {

    @FXML ComboBox<String> combobox;
    @FXML private Text purpose;
    @FXML private Text skeleton;
    @FXML private Text engine;
    @FXML private Text seating;

    /**
     * This method initializes combo box with plane names.<br>
     */
    @FXML
    private void initialize() {

        combobox.getItems().add("TPX 100");
        combobox.getItems().add("TPX 200");
        combobox.getItems().add("TPX 300");
        combobox.setValue("TPX 100");

    }

    /**
     * This method takes place when the button is clicked.<br>
     * It shows production steps of the chosen plane.<br>
     * @param e ActionEvent object.<br>
     */
    public void showButton(ActionEvent e){
        PlaneFactory planeFactory = new PlaneFactory();
        Plane plane = planeFactory.getPlane(combobox.getValue());

        purpose.setText("Purpose: " + plane.purpose());
        skeleton.setText("Skeleton: " + plane.constructSkeleton());
        engine.setText("Engine: " + plane.placeEngines());
        seating.setText("Seating: " + plane.placeSeats());
    }
}
