package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erdem Duman <br>
 * This class connects user interface and source code.<br>
 */
public class Controller {

    @FXML private ComboBox<String> base_suit;
    @FXML private ComboBox<String> flame_thrower;
    @FXML private ComboBox<String> auto_rifle;
    @FXML private ComboBox<String> rocket_launcher;
    @FXML private ComboBox<String> laser;
    @FXML private Text cost;
    @FXML private Text weight;

    private List<ComboBox<String>> additions;
    private List<Suit> suitObjs;
    private Suit chosenSuit;
    private Integer flame_thrower_count;
    private Integer auto_rifle_count;
    private Integer rocket_launcher_count;
    private Integer laser_count;

    /**
     * This method is the one, which runs when <br>
     * the program is started.<br>
     */
    @FXML
    private void initialize(){

        initLists();
        initComboBoxes();
    }

    /**
     * This method initializes all the list of the class.<br>
     */
    private void initLists(){
        additions = new ArrayList<>();

        additions.add(flame_thrower);
        additions.add(auto_rifle);
        additions.add(rocket_launcher);
        additions.add(laser);

        suitObjs = new ArrayList<>();

        suitObjs.add(new Dec());
        suitObjs.add(new Ora());
        suitObjs.add(new Tor());
    }

    /**
     * This method initializes all the combo boxes of the class.<br>
     */
    private void initComboBoxes(){
        base_suit.getItems().add("Dec");
        base_suit.getItems().add("Ora");
        base_suit.getItems().add("Tor");
        base_suit.setValue("Dec");

        for(ComboBox<String> addition : additions){
            for(Integer i = 0; i < 10; i++){
                addition.getItems().add(i.toString());
            }
            addition.setValue("0");
        }
    }

    /**
     * This method takes action when the button in user interface <br>
     * is clicked.<br>
     * @param e ActionEvent object. <br>
     */
    public void showButton(ActionEvent e){

        String suitname = base_suit.getValue();

        for(Suit obj: suitObjs){
            if(suitname.equals(obj.getName())){
                chosenSuit = obj;
                break;
            }
        }

        flame_thrower_count = Integer.parseInt(flame_thrower.getValue());
        auto_rifle_count = Integer.parseInt(auto_rifle.getValue());
        rocket_launcher_count = Integer.parseInt(rocket_launcher.getValue());
        laser_count = Integer.parseInt(laser.getValue());

        for(int i = 0; i < flame_thrower_count; i++){
            chosenSuit = new Flamethrower(chosenSuit);
        }

        for(int i = 0; i < auto_rifle_count; i++){
            chosenSuit = new AutoRifle(chosenSuit);
        }

        for(int i = 0; i < rocket_launcher_count; i++){
            chosenSuit = new RocketLauncher(chosenSuit);
        }

        for(int i = 0; i < laser_count; i++){
            chosenSuit = new Laser(chosenSuit);
        }

        cost.setText(chosenSuit.getCost().toString());
        weight.setText(chosenSuit.getWeight().toString());
    }

}
