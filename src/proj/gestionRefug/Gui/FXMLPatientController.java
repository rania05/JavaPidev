/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class FXMLPatientController implements Initializable {
    @FXML
    private Label labRefugie;
    @FXML
    private Label LabSujet;
    @FXML
    private Label LabContenu;
    @FXML
    private Label labDuree;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea contenu;
    @FXML
    private TableView<?> tabRef;
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
    private JFXTimePicker duree;
    private TextField refugie;
    
         ConsultationService Conslt =new ConsultationService();
    
      private ConsultationService ConsltServ ; 
      
        RefugieService reff=new RefugieService();
    
      private RefugieService refServ ; 
      
    @FXML
    private Label labNom;
    @FXML
    private Label LabPrenom;
    @FXML
    private Label LabAge;
    @FXML
    private Label lborigine;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
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
    @FXML
    private TextField nom;
    @FXML
    private TextField origine;
    private TextField camp;
    @FXML
    private Label lbcamp;
    @FXML
    private Button btAjout1;
    @FXML
    private TableView<?> tabRef1;
    @FXML
    private ComboBox<String> listeRefugie;
    @FXML
    private ComboBox<String> listeCamp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConsltServ = new ConsultationService ();
         List<RefConsult> listR=new ArrayList<>();
        listR= Conslt.affConsult();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        ref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sujetConslt.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenuConslt.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateConslt.setCellValueFactory(new PropertyValueFactory<>("date"));
        dureeConslt.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tabRef.setItems(observableList);
        List<String> listeC =Conslt.listeRefugie();
                ObservableList reffs = FXCollections.observableArrayList(listeC);
                 listeRefugie.setValue("liste refugiés");
                 listeRefugie.setItems(reffs);
        
        
          refServ = new RefugieService ();
         List<Refugie> listRe=new ArrayList<>();
        listRe= reff.afficherRefugie();
        ObservableList observableList1 = FXCollections.observableArrayList(listRe);
        nomref.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageref.setCellValueFactory(new PropertyValueFactory<>("age"));
        origineref.setCellValueFactory(new PropertyValueFactory<>("origine"));
        campref.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
        tabRef1.setItems(observableList1);
         List<String> listeC1 =reff.listeCamp();
                ObservableList campss = FXCollections.observableArrayList(listeC1);
                listeCamp.setValue("liste camps");
                listeCamp.setItems(campss);
        
    }    

    

    @FXML
    private void ConsultAfficher(ActionEvent event) {
         ConsultationService Conslt=new ConsultationService();
         List<RefConsult> listR=new ArrayList<>();
        listR= Conslt.affConsult();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        ref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sujetConslt.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenuConslt.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateConslt.setCellValueFactory(new PropertyValueFactory<>("date"));
        dureeConslt.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tabRef.setItems(observableList);
    
    }
     public void clearConslt()
     {
         
       
         sujet.clear();
         contenu.clear();
         
     }
     
    @FXML
    private void ConsultSupprimer(ActionEvent event) {
                 ConsultationService rs = new ConsultationService();
         RefConsult cc = (RefConsult)tabRef.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir Consult");
        }else{
            rs.supprimerConsult(cc.getSujet());
       
           JOptionPane.showMessageDialog(null, "Consult supprimer");
         sujet.clear();
         contenu.clear();
        
       
       
        cc=null;
    
}
         ConsultAfficher(event);
   clearConslt();
    }

    @FXML
    private void RefAjout(ActionEvent event) {
        
            
    RefugieService r= new RefugieService();
    if( nom.getText().isEmpty() || prenom.getText().isEmpty() || age.getText().isEmpty() 
            || origine.getText().isEmpty()  ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }
    else if(!nom.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         } 
    else if(!prenom.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre prenom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ prenom accepte que les lettres ");
            alert1.showAndWait();
         }
    else if(!age.getText().matches("^([1-9][0-9]{0,4}|31)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider l'age");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier l'age ");
            alert1.showAndWait();
         }
     else if(!origine.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre origine");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ origine accepte que les lettres ");
            alert1.showAndWait();
         }
    else{ Refugie refugie=new Refugie(
            nom.getText(),
            prenom.getText(),
            Integer.parseInt(age.getText()),
            origine.getText(),
            r.getIdCamps(listeCamp.getValue())
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
        campref.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
        tabRef1.setItems(observableList);
        
    }

    @FXML
    private void RefSupp(ActionEvent event) {
         RefugieService rs = new RefugieService();
         Refugie cc = (Refugie)tabRef1.getSelectionModel().getSelectedItem();
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
       
       
        cc=null;
    
    }
          refAffiche(event);
   clearRef();
    
    }
    
    
     public void clearRef()
     {
         
         nom.clear();
         prenom.clear();
         age.clear();
         origine.clear();
       
     }

    @FXML
    private void modifAff(MouseEvent event) {
    Refugie b=(Refugie) tabRef1.getSelectionModel().getSelectedItem();
       RefugieService b1= new RefugieService();
    nom.setText(b.getNom());
    prenom.setText(b.getPrenom());
    age.setText(String.valueOf(b.getAge()));
    origine.setText(b.getOrigine());
    //camp.setText(String.valueOf(b.getIdcamp()));  
    listeCamp.setValue(b1.getNomCamp(b.getIdcamp()));
    
    
    }

    @FXML
    private void modifiRef(ActionEvent event) {
        
         RefugieService b1= new RefugieService();
         if( nom.getText().isEmpty() || prenom.getText().isEmpty() || age.getText().isEmpty() 
            || origine.getText().isEmpty()  ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }
    else if(!nom.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         } 
    else if(!prenom.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre prenom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ prenom accepte que les lettres ");
            alert1.showAndWait();
         }
    else if(!age.getText().matches("^([1-9][0-9]{0,4}|31)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider l'age");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier l'age ");
            alert1.showAndWait();
         }
     else if(!origine.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre origine");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ origine accepte que les lettres ");
            alert1.showAndWait();
         }
    else{
      Refugie b=(Refugie) tabRef1.getSelectionModel().getSelectedItem();
      b1.modifierRefugie(
              nom.getText(), 
              prenom.getText(),
              Integer.parseInt(age.getText()),
              origine.getText(),
//              Integer.parseInt(camp.getText()));
            b1.getIdCamps(listeCamp.getValue()));
}
    refAffiche(event);
   clearRef();
        
    } 

    @FXML
    private void modtabref(MouseEvent event) {
         ConsultationService b1= new ConsultationService();
         RefConsult b=(RefConsult) tabRef.getSelectionModel().getSelectedItem();
         //refugie.setText(String.valueOf(b.getIdref()));
         listeRefugie.setValue(b1.getPrenomRefugie(b.getIdref()));
         sujet.setText(b.getSujet());
         contenu.setText(b.getContenu());
         
    }

    @FXML
    private void modifierConst(ActionEvent event) {
        
         ConsultationService b1= new ConsultationService();
          try {
             Time valueOf = java.sql.Time.valueOf(duree.getValue());
            } catch (Exception e) {
                 Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider duree");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ durée est vide ");
            alert1.showAndWait();

            }
    if(sujet.getText().isEmpty() || contenu.getText().isEmpty() 
            ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
   
         } else if(!sujet.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         }
    else{ 
      RefConsult b=(RefConsult) tabRef.getSelectionModel().getSelectedItem();
      b1.modifierConsultation(
      b1.getIdRefugie(listeRefugie.getValue()),
              sujet.getText(),
              contenu.getText());
         }
       ConsultAfficher(event);
         clearConslt();
        
    }
    
    
}