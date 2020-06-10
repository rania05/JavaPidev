/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


   

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class EventFXMLController implements Initializable {
     @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> colotitre;

    @FXML
    private TableColumn<?, ?> coloaffiche;

    @FXML
    private TableColumn<?, ?> cololieu;

    @FXML
    private TableColumn<?, ?> coloprix;

    @FXML
    private TableColumn<?, ?> colodate;

    @FXML
    private TableColumn<?, ?> colodescription;

    @FXML
    private Button ajouter;

    @FXML
    private Button supprimer;

    @FXML
    private Button modifier;

    @FXML
    private Button afficher;

    @FXML
    private Label titre1;

    @FXML
    private Label affiche;

    @FXML
    private Label lieu;

    @FXML
    private Label prix;

    @FXML
    private Label date;

    @FXML
    private Label descrption;

    @FXML
    private TextField labeltitre;

    @FXML
    private TextField labelaffiche;

    @FXML
    private TextField labellieu;

    @FXML
    private TextField labelprix;

    @FXML
    private TextField labeldate;

    @FXML
    private TextField labeldescription;

    @FXML
    void Modifier(ActionEvent event) {

    }

    @FXML
    void Supprimer(ActionEvent event) {

    }

    @FXML
    void ajouter(ActionEvent event) {

    }

    @FXML
    void show1(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
