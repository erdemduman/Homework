package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;


/**
 * @author Erdem Duman<br>
 * This class connects GUI and Client.<br>
 */
public class Controller {

    @FXML private TextField num_edges;
    @FXML private VBox vbox;
    @FXML private TextField name;
    private int edgeNumber;


    /**
     * First method, which is called after program started.<br>
     */
    @FXML
    private void initialize() {

        num_edges.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    vbox.getChildren().clear();
                    edgeNumber = Integer.parseInt(newValue);
                    for(int i = 0; i < edgeNumber; i++){
                        vbox.getChildren().add(new TextField());
                    }
                } catch(Exception e){

                }
            }
        });


    }

    /**
     * This method invokes the button that allows client to<br>
     * get incidence matrix.<br>
     * @param e ActionEvent object.<br>
     */
    public void incMatrix_invoke(ActionEvent e){

        Graph<Object> graph = new GraphImpl<>();
        String name_var = name.getText();

        for (Object obj: vbox.getChildren()){
            String str = ((TextField)obj).getText();
            String[] token = str.split(",");
            Edge<Object> edge = new EdgeImpl<Object>(token[0], token[1], Integer.parseInt(token[2]));
            graph.insert(edge);
        }

        TheBestClient client = new TheBestClient(graph, name_var);
        client.connect();
        Integer[][] incMat = client.getIncMatrix();

        String str = "";
        str += "Indices: \n";
        List<Object> souAndDest = graph.getSourceAndDest();

        for(int i = 0; i < souAndDest.size(); i++){
            str += i + ") " + souAndDest.get(i) + "\n";
        }

        str += "\n";

        for(int i = 0; i < incMat.length; i++){
            for(int j = 0; j < incMat[0].length; j++){
                str += incMat[i][j] + "  ";
            }
            str += "\n\n";
        }

        resultPopUp(str);
    }



    /**
     * This method generates a pop-up screen.<br>
     * @param str the text that will be shown on screen.<br>
     */
    private void resultPopUp(String str){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(str));
        Scene dialogScene = new Scene(dialogVbox, 400, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * This method invokes the button that allows client to<br>
     * get minimum spanning tree of graph.<br>
     * @param e ActionEvent object.<br>
     */
    public void mst_invoke(ActionEvent e){
        Graph<Object> graph = new GraphImpl<>();
        String name_var = name.getText();

        for (Object obj: vbox.getChildren()){
            String str = ((TextField)obj).getText();
            String[] token = str.split(",");
            Edge<Object> edge = new EdgeImpl<Object>(token[0], token[1], Integer.parseInt(token[2]));
            graph.insert(edge);
        }

        TheBestClient client = new TheBestClient(graph, name_var);
        client.connect();
        Integer mst = client.getMst();

        String s = "";
        s += "Minimum Spanning Tree is: " + mst.toString();

        resultPopUp(s);
    }
}
