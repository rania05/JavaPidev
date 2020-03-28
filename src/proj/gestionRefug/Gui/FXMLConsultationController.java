/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import proj.gestionRefug.entities.RefConsult;
import proj.gestionRefug.services.ConsultationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLConsultationController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Button btAjout;
    @FXML
    private TableView<?> tabRef;
    @FXML
    private Button actualiserRef;
    @FXML
    private Button modRef;
    @FXML
    private Button suppRef;
    @FXML
    private JFXButton btnConsultation;
    @FXML
    private Label labrefugie;
    @FXML
    private Label labsujet;
    @FXML
    private Label labcontenu;
    @FXML
    private Label labduree;
    private TextField refugie;
    @FXML
    private TextField sujet;
    @FXML
    private TableColumn<?, ?> colref;
    @FXML
    private TableColumn<?, ?> colsujet;
    @FXML
    private TableColumn<?, ?> colcontenu;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> colduree;
    @FXML
    private JFXTimePicker duree;
    @FXML
    private TextArea contenu;
    
    
     ConsultationService Conslt =new ConsultationService();
    
      private ConsultationService ConsltServ ;
    @FXML
    private JFXButton btnrefugiego;
    @FXML
    private ComboBox<String> listeRefugie;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ConsltServ = new ConsultationService ();
         List<RefConsult> listR=new ArrayList<>();
        listR= Conslt.affConsult();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        colref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tabRef.setItems(observableList);
          List<String> listeC =Conslt.listeRefugie();
                ObservableList reffs = FXCollections.observableArrayList(listeC);
listeRefugie.setValue("liste refugiés");
listeRefugie.setItems(reffs);
        
    }    

    @FXML
    private void ajoutConsult(ActionEvent event) {
        ConsultationService r= new ConsultationService();
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
            LocalTime dur =duree.getValue();
        Time dureeC = java.sql.Time.valueOf(dur);
             RefConsult consultation =new RefConsult(
           // Integer.parseInt( refugie.getText()),
            r.getIdRefugie(listeRefugie.getValue()),
            sujet.getText(),
            contenu.getText(),
                     dureeC 
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
   afficheConslt(event);
   clearConslt();
        
        
        
    }

    @FXML
    private void afficheConslt(ActionEvent event) {
         ConsultationService Conslt=new ConsultationService();
         List<RefConsult> listR=new ArrayList<>();
        listR= Conslt.affConsult();
        ObservableList observableList = FXCollections.observableArrayList(listR);
        colref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        tabRef.setItems(observableList);
    }

    @FXML
    private void modifConsult(ActionEvent event) {
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
       afficheConslt(event);
         clearConslt();
    }

    @FXML
    private void suppConsult(ActionEvent event) {
        
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
         afficheConslt(event);
         clearConslt();
        
    }
    
    
    
     public void clearConslt()
     {
         
        
         sujet.clear();
         contenu.clear();
         
     }

    @FXML
    private void refugiego(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionRefug/Gui/FXMLRefugie.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

    @FXML
    private void consultationgo(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionRefug/Gui/FXMLConsultation.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

    @FXML
    private void modCon(MouseEvent event) {
                    ConsultationService b1= new ConsultationService();

        RefConsult b=(RefConsult) tabRef.getSelectionModel().getSelectedItem();
          listeRefugie.setValue(b1.getPrenomRefugie(b.getIdref()));
         sujet.setText(b.getSujet());
         contenu.setText(b.getContenu());
        
        
        
    }
    
}
