/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class FXMLEventFrontController implements Initializable {

    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> colotitre;
    @FXML
    private TableColumn<?, ?> cololieu;
    @FXML
    private TableColumn<?, ?> colodate;
    @FXML
    private TableColumn<?, ?> coloprix;
    @FXML
    private TableColumn<?, ?> colodescription;
    @FXML
    private ImageView image;
    @FXML
    private JFXButton refId;
    @FXML
    private JFXButton consid;
    @FXML
    private JFXButton cmpid;
    @FXML
    private JFXButton csid;
    @FXML
    private JFXButton Event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    

   

   
    
      
     @FXML
    private void InterfaceCamp(ActionEvent event)  {
       Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
    }

 


    @FXML
    private void refugiego(ActionEvent event) {
           Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
          
    }

    @FXML
    private void consultationgo(ActionEvent event)  {
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
          
    }

 

    @FXML
    private void CasSociale(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Cassociale.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

    @FXML
    private void EventAction(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("proj/gestionEvent/FXML/FXMLEventFront.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

     @FXML
    private void logoutAction(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Login.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }
   
    
}
