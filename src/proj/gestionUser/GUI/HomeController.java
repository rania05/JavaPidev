/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HomeController implements Initializable {
    
    
     Stage dialogStage = new Stage();
    Scene scene;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GotoComm(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Cassociale.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Going to shop");
                    dialogStage.show(); 
    }

    @FXML
    private void GotoUser(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/utilisateur.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Going to User");
                    dialogStage.show(); 
    }
    
}
