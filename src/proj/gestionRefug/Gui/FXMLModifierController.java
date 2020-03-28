/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLModifierController implements Initializable {
    @FXML
    private Label labelnom;
    @FXML
    private Label labelpr;
    @FXML
    private Label labelage;
    @FXML
    private Label labelorg;
    @FXML
    private Label labelcamp;
    @FXML
    private TextField nomref;
    @FXML
    private TextField prenomref;
    @FXML
    private TextField ageref;
    @FXML
    private TextField origineref;
    @FXML
    private TextField campref;
    @FXML
    private Button btnmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modref(ActionEvent event) {
        
    }
    
}
