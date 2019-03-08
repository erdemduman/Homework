package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

/**
 * @author Erdem Duman <br>
 * This class connects user interface and source code of program. <br>
 */
public class Controller {

    @FXML public ComboBox<String> plane_combo;
    @FXML public ComboBox<String> market_combo;
    @FXML public Text purpose;
    @FXML public Text skeleton;
    @FXML public Text engine;
    @FXML public Text engine_injection;
    @FXML public Text seating;
    @FXML public Text seating_cover;

    /**
     * This method initializes combo boxes with plane and market names.<br>
     */
    @FXML
    private void initialize() {

        plane_combo.getItems().add("TPX 100");
        plane_combo.getItems().add("TPX 200");
        plane_combo.getItems().add("TPX 300");
        plane_combo.setValue("TPX 100");

        market_combo.getItems().add("Domestic");
        market_combo.getItems().add("Eurasia");
        market_combo.getItems().add("Other");
        market_combo.setValue("Domestic");

    }

    /**
     * This method takes place when the button is clicked.<br>
     * It shows production steps of the chosen plane of chosen market.<br>
     * @param e ActionEvent object.<br>
     */
    public void showButton(ActionEvent e){
        AbstractFactory planeFactory = FactoryGenerator.getFactory("Plane");
        AbstractFactory marketFactory = FactoryGenerator.getFactory("Market");

        Plane plane = planeFactory.getPlane(plane_combo.getValue());
        Market market = marketFactory.getMarket(market_combo.getValue());

        purpose.setText("Purpose: " + plane.purpose());
        skeleton.setText("Skeleton: " + plane.constructSkeleton());
        engine.setText("Engine: " + plane.placeEngines());
        engine_injection.setText("Engine Injection: " + market.engineInjectionType());
        seating.setText("Seating: " + plane.placeSeats());
        seating_cover.setText("Seating Cover: " + market.seatingCover());
    }

}
