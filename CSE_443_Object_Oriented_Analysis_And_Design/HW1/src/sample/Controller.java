package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * @author Erdem Duman <br>
 * This class is a bridge between UI and Solver methods. <br>
 */

public class Controller {

    @FXML private VBox vbox_id;
    @FXML private TextField variables_id;
    @FXML private ComboBox<String> comboBox;

    private Integer variables;
    private ArrayList<ArrayList<Double>> matrix;
    private Error error;
    private String method = "Gaussian";

    /**
     * This method is the first method that is called when program starts.<br>
     * Listeners for comboBox and textfield, that is used to get variable <br>
     * number, is created in this method.<br>
     */
    @FXML
    private void initialize(){
        error = new Error();
        comboBox.getItems().add("Gaussian");
        comboBox.getItems().add("Matrix Inversion");
        comboBox.setValue("Gaussian");
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                method = t1;
            }
        });

        matrix = new ArrayList<>();
        variables_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    vbox_id.getChildren().clear();
                    variables = Integer.parseInt(newValue);
                    for(int i = 0; i < variables; i++){
                        vbox_id.getChildren().add(new TextField());
                    }
                } catch(Exception e){

                }
            }
        });
    }

    /**
     * This method generates a string for "how to use" button.<br>
     * @return "how to use" string. <br>
     */
    private String howToUseStr(){
        String str = "\n\t- You have to type the number of variables." +
                "\n\t- Fill the matrix." +
                "\n\t  For instance, if your equation is 2x+3y=10;" +
                "\n\t  You must type 2 3 10 into the text field below." +
                "\n\t  Do the same thing for other equations." +
                "\n\t  Enjoy!";
        return str;
    }

    /**
     * This method generates a popup window whether "how to use" <br>
     * button is clicked.<br>
     * @param e ActionEvent object.<br>
     */
    public void howToUseButton(ActionEvent e){

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(howToUseStr()));
        Scene dialogScene = new Scene(dialogVbox, 370, 140);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    /**
     * This method generates a popup window whether "Solve" button <br>
     * is clicked. <br>
     * @param str the string that is showed on popup window. <br>
     */
    private void resultPopUp(String str){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(str));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * This method involves the operations that will be run <br>
     * after the "Solve" button click.<br>
     * @param e ActionEvent object.<br>
     */
    public void buttonClick(ActionEvent e){
        matrix.clear();
        int errorFlag = 0;
        if(this.variables == null){
            resultPopUp(error.noVariable());
            errorFlag = 1;
        }

        if(errorFlag != 1){
            for(int i = 0; i < vbox_id.getChildren().size(); i++){
                String s = ((TextField)vbox_id.getChildren().get(i)).getText();
                String[] tokens = s.split(" ");
                ArrayList<Double> temp = new ArrayList<>();
                if(tokens.length != this.variables + 1){
                    resultPopUp(error.variableError());
                    errorFlag = 1;
                    break;
                }
                for (String each : tokens) {
                    Double tmpDouble = Double.parseDouble(each);
                    temp.add(tmpDouble);
                }
                matrix.add(temp);
            }
        }

        if(errorFlag != 1)
            solve();

    }

    /**
     * This method generates a string whether there is a solution<br>
     * for given method.<br>
     *
     * @param result ArrayList that involves the results.<br>
     * @return the output text <br>
     */
    private String resultText(ArrayList<Double> result){

        String str = "\n\tMethod: " + method + "\n";
        for(int i = 0; i < result.size(); i++){
            str += "\n\tv" + (i+1) + " = " + result.get(i);
        }
        return str;
    }

    /**
     * This method evaluates the method type and solves <br>
     * the equations according to given method type.<br>
     */
    private void solve(){
        ArrayList<SolvingMethods> methodList = new ArrayList<>();

        methodList.add(new Gaussian(matrix,variables));
        methodList.add(new InverseMatrix(matrix,variables));

        for(SolvingMethods solver: methodList){
            if(solver.methodName().equals(method)){
                ArrayList<Double> result = solver.solveEquation();
                if(!isNan(result))
                    resultPopUp(resultText(result));
                else
                    resultPopUp(error.nanError());

                break;
            }
        }
    }

    /**
     * This method checks the results whether there is no <br>
     * solutions.<br>
     * @param result ArrayList of results.<br>
     * @return true whether there is no solutions. False otherwise.<br>
     */
    private boolean isNan(ArrayList<Double> result){
        for(int i = 0; i < result.size(); i++){
            if(Double.isNaN(result.get(i))){
                return true;
            }
        }

        return false;
    }

}
