/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import proj.gestionRefug.entities.Refugie;
import proj.gestionRefug.services.RefugieService;

/**
 *
 * @author ASUS
 */
/*public class FXMLDocumentController implements Initializable {
    RefugieService ref=new RefugieService();
    
    private RefugieService refServ ; 
    @FXML
    private Label label; 
    @FXML
    private Button btAjout;
    @FXML
    private Label labNom;
    @FXML
    private Label LabPrenom;
    @FXML
    private Label LabAge;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField origine;
    @FXML
    private Label lborigine;
    @FXML
    private Label lbcamp;
    @FXML
    private TextField camp;
    @FXML
    private TableView<?> tabRef;
    @FXML
    private TableColumn<?, ?> nomref;
    @FXML
    private TableColumn<?, ?> prenomref;
    @FXML
    private TableColumn<?, ?> ageref;
    @FXML
    private TableColumn<?, ?> origineref;
    @FXML
    private TableColumn<?, ?> campref;
    @FXML
    private Button actualiserRef;
    @FXML
    private Button modRef;
    @FXML
    private Button suppRef;
    /*
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refServ = new RefugieService ();
         List<Refugie> listR=new ArrayList<>();
        listR= ref.afficherRefugie();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        nomref.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageref.setCellValueFactory(new PropertyValueFactory<>("age"));
        origineref.setCellValueFactory(new PropertyValueFactory<>("origine"));
        campref.setCellValueFactory(new PropertyValueFactory<>("idcamp"));
        tabRef.setItems(observableList);
    }    

    @FXML
    private void RefAjout(ActionEvent event) {
      /*  Refugie refugie=new Refugie(
            nom.getText(),
            prenom.getText(),
            Integer.parseInt(age.getText()),
            origine.getText(),
            Integer.parseInt(camp.getText()));   
    RefugieService b= new RefugieService();
    b.ajouterRefugie(refugie);
    
    */
    /*
    
   
    RefugieService r= new RefugieService();
    if( nom.getText().isEmpty() || prenom.getText().isEmpty() || age.getText().isEmpty() 
            || origine.getText().isEmpty() || camp.getText().isEmpty() ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }else if(!age.getText().matches("^([1-9][0-9]{0,4}|31)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider l'age");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier l'age ");
            alert1.showAndWait();
         }
    else if(!nom.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         }
    else{ Refugie refugie=new Refugie(
            nom.getText(),
            prenom.getText(),
            Integer.parseInt(age.getText()),
            origine.getText(),
            Integer.parseInt(camp.getText())  
    );   
      RefugieService b= new RefugieService();
      b.ajouterRefugie(refugie);
    Notifications notif=Notifications.create()
            .title("Refugié ajouté")
            .text("Un nouveau refugié vient d'être ajoutée !")
            .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
            .position(Pos.TOP_LEFT)
            .onAction(new EventHandler<ActionEvent>() {
                   
                public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
    notif.showConfirm();
    }
    refAffiche(event);
   clearRef();
    

    }

    
    
    
    
    
    @FXML
    private void refAffiche(ActionEvent event) {
        
        RefugieService ref=new RefugieService();
        List<Refugie> listR=new ArrayList<>();
        listR= ref.afficherRefugie();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        nomref.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageref.setCellValueFactory(new PropertyValueFactory<>("age"));
        origineref.setCellValueFactory(new PropertyValueFactory<>("origine"));
        campref.setCellValueFactory(new PropertyValueFactory<>("idcamp"));
        tabRef.setItems(observableList);
        
    }

    @FXML
    private void RefSupp(ActionEvent event) throws SQLException  {
       
         RefugieService rs = new RefugieService();
         Refugie cc = (Refugie)tabRef.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir refugie");
        }else{
            rs.supprimerRefugie(cc.getNom());
       
           JOptionPane.showMessageDialog(null, "refugie supprimer");
         nom.clear();
         prenom.clear();
         age.clear();
         origine.clear();
         camp.clear();
       
        cc=null;
    
    }
        
        
        
        
    }
        /*
    
     if (tabRef.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer Refugie");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette annonce ?");

            Optional<ButtonType> optionDeleteRefugieAlert = alert.showAndWait();
            if (optionDeleteRefugieAlert.get() == ButtonType.OK) {
                refServ.supprimerRefugie(tabRef.getSelectionModel().getSelectedItems().getNom);
                Alert Alert1 = new Alert(Alert.AlertType.INFORMATION);
                Alert1.setTitle("Supprimer une annonce");
                Alert1.setHeaderText("Resultat:");
                Alert1.setContentText("Annonce supprimée avec succès!");

                Alert1.showAndWait();

            } else if (optionDeleteRefugieAlert.get() == ButtonType.CANCEL) {

            }

        } else {
            Alert Alert1 = new Alert(Alert.AlertType.WARNING);
            Alert1.setTitle("Select Annonce");
            Alert1.setHeaderText(null);
            Alert1.setContentText("Vous devez d'abord sélectionner une annonce!");
            Alert1.showAndWait();
        }

        tabRef.setItems().removeAll(tabRef.getSelectionModel().getSelectedItem().);
        
        
        
        
    }
    
    */

           /*   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("Confirmation");
              alert.setHeaderText("Suppression");
              alert.setContentText("Voulez vous supprimer ce Refugié ?");

          Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
         Refugie r = (Refugie) tabRef.getSelectionModel().getSelectedItem();
       String nom = nomref.getText();
        RefugieService rs = new RefugieService();
        rs.supprimerRefugie(nom);
       
        clearModif();
       
    } 
else{
    clearModif();
    
}       
        
    }
    
    public void clearModif()
     {
         
         nom.clear();
         prenom.clear();
         age.clear();
         origine.clear();
         camp.clear();
     }
 
    *//*
     public void clearRef()
     {
         
         nom.clear();
         prenom.clear();
         age.clear();
         origine.clear();
         camp.clear();
     }
    
    
    
    
    
    
    
    
    
    
}

    */