/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import proj.gestionRefug.entities.RefConsult;
import proj.gestionRefug.entities.Refugie;
import proj.gestionRefug.services.ConsultationService;
import proj.gestionRefug.services.RefugieService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLPatientConsltController implements Initializable {
    
     ConsultationService Conslt =new ConsultationService();
    
      private ConsultationService ConsltServ ; 
      
    
    @FXML
    private Label label;
    @FXML
    private Button btAjout;
    @FXML
    private Label labRefugie;
    @FXML
    private Label LabSujet;
    @FXML
    private Label LabContenu;
    @FXML
    private TableView<?> tabRef;
    @FXML
    private Label labDuree;
    @FXML
    private TextField refugie;
    @FXML
    private TextField sujet;
    @FXML
    private TextField contenu;
    @FXML
    private JFXTimePicker duree;
    @FXML
    private TableColumn<?, ?> ref;
    @FXML
    private TableColumn<?, ?> sujetConslt;
    @FXML
    private TableColumn<?, ?> contenuConslt;
    @FXML
    private TableColumn<?, ?> dateConslt;
    @FXML
    private TableColumn<?, ?> dureeConslt;
    @FXML
    private Button actualiserConsult;
    @FXML
    private Button modConsult;
    @FXML
    private Button suppConsult;
    @FXML
    private JFXButton btnaccueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ConsltServ = new ConsultationService ();
         List<RefConsult> listR=new ArrayList<>();
        listR= Conslt.affConsult();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        ref.setCellValueFactory(new PropertyValueFactory<>("idref"));
        sujetConslt.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenuConslt.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateConslt.setCellValueFactory(new PropertyValueFactory<>("date"));
        dureeConslt.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tabRef.setItems(observableList);
    }    
    
    private void ConsultAfficher(ActionEvent event) {
         
         ConsultationService Conslt=new ConsultationService();
         List<RefConsult> listR=new ArrayList<>();
        listR= Conslt.affConsult();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        ref.setCellValueFactory(new PropertyValueFactory<>("idref"));
        sujetConslt.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenuConslt.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateConslt.setCellValueFactory(new PropertyValueFactory<>("date"));
        dureeConslt.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tabRef.setItems(observableList);
    }
    
    private void ConsultSupprimer (ActionEvent event){
        
        ConsultationService rs = new ConsultationService();
         RefConsult cc = (RefConsult)tabRef.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir Consult");
        }else{
            rs.supprimerConsult(cc.getSujet());
       
           JOptionPane.showMessageDialog(null, "Consult supprimer");
         refugie.clear();
         sujet.clear();
         contenu.clear();
        
       
       
        cc=null;
    
    }
         ConsultAfficher(event);
   clearConslt();
        
        
    }
     public void clearConslt()
     {
         
         refugie.clear();
         sujet.clear();
         contenu.clear();
         
     }
     
     
     
    private void ConsultAjouter (ActionEvent event) {
        
        
    RefugieService r= new RefugieService();
    if( ref.getText().isEmpty() || sujet.getText().isEmpty() || contenu.getText().isEmpty() 
            ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }
    else if(!ref.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         } else if(!sujet.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         }
    else{ RefConsult consultation =new RefConsult(
            Integer.parseInt( ref.getText()),
            sujet.getText(),
            contenu.getText()
           // duree.
    );   
      ConsultationService b= new ConsultationService();
      b.ajouterConsultation(consultation);
    Notifications notif=Notifications.create()
            .title("consultation ajouté")
            .text("Une nouvelle consultation vient d'être ajoutée !")
            .darkStyle().graphic(null).hideAfter(Duration.seconds(5))
            .position(Pos.TOP_LEFT)
            .onAction(new EventHandler<ActionEvent>() {
                   
                public void handle(ActionEvent event) {
                        System.out.println("Clicked ont notif");
                    }
                });
    notif.showConfirm();
    }
   ConsultAfficher(event);
   clearConslt();
    

        
     
    }

    @FXML
    private void accueilgo(MouseEvent event) {
    }

    
    
}
