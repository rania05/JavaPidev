/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class UtilisateurController implements Initializable {

    @FXML
    private AnchorPane bloquer;
    @FXML
    private Button retour;
    @FXML
    private Button vider;
    @FXML
    private TextField recherche;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> clNom;
    @FXML
    private TableColumn<?, ?> clPrenom;
    @FXML
    private TableColumn<?, ?> clEmail;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private Text nomm;
    @FXML
    private Text prenom;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtroles;
    @FXML
    private Button btn;
    @FXML
    private Button supprimer;
    @FXML
    private ImageView imgv;
    @FXML
    private Button bloc;
    @FXML
    private JFXButton btnrefgo;
    @FXML
    private JFXButton btnConsultation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void directAccueil(ActionEvent event) {
    }

    @FXML
    private void vider(ActionEvent event) {
    }

    @FXML
    private void recher(ActionEvent event) {
    }

    @FXML
    private void validerModif(ActionEvent event) {
    }

    @FXML
    private void validersupp(ActionEvent event) {
    }

    @FXML
    private void validerBloc(ActionEvent event) {
    }

    @FXML
    private void refugiego(ActionEvent event) {
    }

    @FXML
    private void consultationgo(ActionEvent event) {
    }

    @FXML
    private void InterfaceCamp(ActionEvent event) {
    }


    @FXML
    private void CasSociale(ActionEvent event) {
    }
    
}
