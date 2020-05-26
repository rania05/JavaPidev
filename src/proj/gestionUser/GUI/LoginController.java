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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane Username;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField txt_user_name;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button inscrire;
    
    Stage dialogStage = new Stage();
    Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Image image=new Image("/Image/apple-touch-icon.png");
       // imageview.setImage(image);
    }    

    @FXML
    private void connexionUtilisateur(ActionEvent event) throws   IOException, SQLException {
        UserCRUD us = new UserCRUD();
        Utilisateur u = new Utilisateur();
        u.setUsername(txt_user_name.getText());
        u.setPassword(txt_password.getText());
       String role;
        
        if (us.verifpassword(u.getUsername(), u.getPassword()) ) {
        
            	Utilisateur  u1 = us.recuperer_type_compte(txt_user_name.getText(), txt_password.getText());
                System.out.println(u1.getRoles());
                if (u1.getRoles().equals( "a:1:{i:0;s:11:\"ROLE_CLIENT\";}")) {
         Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                  scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Cassociale.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Hello User");
                    dialogStage.show(); 
        }
                else    if (u1.getRoles().equals( "a:1:{i:0;s:10:\"ROLE_DOCTEUR\";}"))
                {
                     Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionRefug/Gui/FXMLConsultation.fxml")));
                    dialogStage.setScene(scene);
                    System.out.println("Hello User");
                    dialogStage.show(); 
                
                }
                   else    if (u1.getRoles().equals( "a:1:{i:0;s:10:\"ROLE_RESPCAMP\";}"))
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
                
        }else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("user inccorrect");
            alert.setContentText("please check your informations!");
            alert.showAndWait();}
         
    }

    @FXML
    private void goToInscription(ActionEvent event)  throws   SQLException, IOException, Exception {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/inscription.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show(); 
    }
    
}
