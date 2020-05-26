/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.GUI;

import proj.gestionUser.Services.UserCRUD;
import proj.gestionUser.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nomUser;
    @FXML
    private TextField prenomUser;
    @FXML
    private TextField emailUser;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField mdpUser;
    @FXML
    private PasswordField confirmationMdpUser;
    @FXML
    private Button btnAddUser;
    @FXML
    private Text txtPhotoUser;
    @FXML
    private ComboBox<String> comboRole;
   

     Stage dialogStage = new Stage();
     Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboRole.getItems().addAll("Role Client", "Role Agent", "Role Docteur","Role Responsable Camp");
        // TODO
    }    

    @FXML
    private void addUser(ActionEvent event) throws IOException {    
        Utilisateur u = new Utilisateur();
        UserCRUD us = new UserCRUD();
        
            u.setNom(nomUser.getText());
            u.setUsername(UserName.getText());
            u.setPrenom(prenomUser.getText());
            u.setEmail(emailUser.getText());
            u.setPassword(mdpUser.getText());
            u.setEnable(1);
            if(comboRole.getSelectionModel().getSelectedItem().equals("Role Client")){
            u.setRoles("a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
            }else if(comboRole.getSelectionModel().getSelectedItem().equals("Role Agent")){
                u.setRoles("a:1:{i:0;s:10:\"ROLE_AGENT\";}");
            }
            else if(comboRole.getSelectionModel().getSelectedItem().equals("Role Docteur")){
                u.setRoles("a:1:{i:0;s:10:\"ROLE_DOCTEUR\";}");
            }else if(comboRole.getSelectionModel().getSelectedItem().equals("Role Responsable Camp")){
                u.setRoles("a:1:{i:0;s:10:\"ROLE_RESPCAMP\";}");
            }
            us.ajoutUser(u, u.getPassword());
            
            	
                if (u.getRoles().equals( "a:1:{i:0;s:11:\"ROLE_CLIENT\";}")) {
         Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                  scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Cassociale.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Hello User");
                    dialogStage.show(); 
        }
                else    if (u.getRoles().equals( "a:1:{i:0;s:10:\"ROLE_DOCTEUR\";}"))
                {
                     Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionRefug/Gui/FXMLConsultation.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Hello User");
                    dialogStage.show(); 
                
                }
                   else    if (u.getRoles().equals( "a:1:{i:0;s:10:\"ROLE_RESPCAMP\";}"))
                {
                     Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionRefug/Gui/FXMLRefugie.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Hello User");
                    dialogStage.show(); 
                
                }
                   else   
                {
                     Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                      scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionRefug/Gui/FXMLPatient.fxml")));
                    
                    dialogStage.setScene(scene);
                    System.out.println("Hello User");
                    dialogStage.show(); 
                
                }
                
    }

    @FXML
    private void goToLogin(ActionEvent event) throws SQLException, IOException, Exception {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Login.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();   
    }
    
}
