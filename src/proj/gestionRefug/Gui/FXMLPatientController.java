/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import proj.gestionEvent.Service.EventService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import proj.gestionCamp.Entities.Cord;
import proj.gestionCamp.Services.CordService;
import proj.gestionEvent.Service.EventService;
import proj.gestionRefug.entities.RefConsult;
import proj.gestionRefug.entities.Refugie;
import proj.gestionRefug.services.ConsultationService;
import proj.gestionRefug.services.RefugieService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLPatientController implements Initializable, MapComponentInitializedListener {
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
    private TableView<RefConsult> tabRef;
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
    private TableView<Refugie> tabRef1;
    @FXML
    private ComboBox<String> listeRefugie;
    @FXML
    private ComboBox<String> listeCamp;
    @FXML
    private JFXTextField zoneRech;
    @FXML
    private JFXTextField zoneRecher;
    @FXML
    private TabPane GCord;
    @FXML
    private Tab TabAjoutCord;
    @FXML
    private AnchorPane Cord1;
    @FXML
    private TextField searchCord;
    @FXML
    private JFXButton addNew;
    @FXML
    private Button affichCordClick;
    @FXML
    private TableView<Cord> affichCord;
    @FXML
    private TableColumn<Cord, String> lieu;
    @FXML
    private TableColumn<Cord, String> longitude;
    @FXML
    private TableColumn<Cord, String> latitude;
    @FXML
    private TableColumn<Cord, String> idCord;
    @FXML
    private Tab modifCord;
    @FXML
    private TextField lieuMod;
    @FXML
    private TextField longMod;
    @FXML
    private TextField latMod;
    @FXML
    private Button modCord;
    @FXML
    private Tab add;
    @FXML
    private TextField AjoutLieuC;
    @FXML
    private TextField AjoutLong;
    @FXML
    private TextField AjoutLat;
    @FXML
    private Button AjoutCord;
    @FXML
    private GoogleMapView Map;
     CordService cs = new CordService();
     GoogleMap map;
 Cord data;
    @FXML
    private AnchorPane Eventt;
    @FXML
    private DatePicker labeldate;
    @FXML
    private TextField labeltitre;
    @FXML
    private TextField labelaffiche;
    @FXML
    private TextField labellieu;
    @FXML
    private TextArea labeldescription;
    @FXML
    private TextField labelprix;
     @FXML
    private TableView<proj.gestionEvent.Entite.Event> table;
    @FXML
    private TableColumn<proj.gestionEvent.Entite.Event, String> colotitre;
    @FXML
    private TableColumn<proj.gestionEvent.Entite.Event, String> coloaffiche;
    @FXML
    private TableColumn<proj.gestionEvent.Entite.Event, String> cololieu;
    @FXML
    private TableColumn<proj.gestionEvent.Entite.Event, Date> colodate;
    @FXML
    private TableColumn<proj.gestionEvent.Entite.Event, Float> coloprix;
    @FXML
    private TableColumn<proj.gestionEvent.Entite.Event, String> colodescription;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private CheckBox trier;
    @FXML
    private TextField recherche;
    @FXML
    private Button pdf;
    @FXML
    private ImageView image;
    @FXML
    private ImageView pdff;
    @FXML
    private ImageView rech;
    @FXML
    private Button stat;
  EventService c = new EventService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                buildTableviewAssociationData();
              
       
               
     table.setRowFactory( (TableView<proj.gestionEvent.Entite.Event> tv) -> {
    TableRow<proj.gestionEvent.Entite.Event> row = new TableRow<>();
     
    row.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
             proj.gestionEvent.Entite.Event C = row.getItem();
           
                       changeinputs(C);
                    
        }
    });
    return row ;
});
         addButtonToTable();
             Map.addMapInializedListener(this);
       GCord.getTabs().remove(1);
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
    
    private void refreshB() throws SQLException{
        List<Refugie> listB=new ArrayList<>();
        RefugieService   cr = new RefugieService();
        listB = cr.afficherRefugie();
        ObservableList <Refugie> data = FXCollections.observableArrayList(listB);
        tabRef1.setItems(data);
    }
    

    @FXML
    private void rechercher(KeyEvent event) {
        
         RefugieService RefugieService = new RefugieService();
        zoneRech.setOnKeyReleased((KeyEvent e)
                -> {
            if (zoneRech.getText().equals("") ) {

                try {
                    refreshB();
                } catch (SQLException ex) {
                    Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            } else {

                try {
       nomref.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageref.setCellValueFactory(new PropertyValueFactory<>("age"));
        origineref.setCellValueFactory(new PropertyValueFactory<>("origine"));
        campref.setCellValueFactory(new PropertyValueFactory<>("nomCamp")); 

                    tabRef1.getItems().clear();

                    tabRef1.setItems(RefugieService.rechercheRefugie(zoneRech.getText()));

                } catch (SQLException ex) {
                Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);

                }
        

            }
        }
        );
        
    }

     private void refreshC() throws SQLException{
        List<RefConsult> listB=new ArrayList<>();
        ConsultationService   cr = new ConsultationService();
        listB = cr.affConsult();
        ObservableList <RefConsult> data = FXCollections.observableArrayList(listB);
        tabRef.setItems(data);
    }
    
    @FXML
    private void rechercherConst(KeyEvent event) {
        
        ConsultationService ConsultationService = new ConsultationService();
        zoneRecher.setOnKeyReleased((KeyEvent e)
                -> {
            if (zoneRecher.getText().equals("") ) {

                try {
                    refreshC();
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            } else {

                try {
      ref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sujetConslt.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenuConslt.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateConslt.setCellValueFactory(new PropertyValueFactory<>("date"));
        dureeConslt.setCellValueFactory(new PropertyValueFactory<>("duree"));
                    tabRef.getItems().clear();

                    tabRef.setItems(ConsultationService.rechercheConsultation(zoneRecher.getText()));

                } catch (SQLException ex) {
                Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);

                }
        

            }
        }
        );
        
    }

    @FXML
    private void ConsAjout(ActionEvent event) throws IOException, NexmoClientException {
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
   ConsultAfficher(event);
   clearConslt();
        
    NexmoClient client = new NexmoClient.Builder()
                .apiKey("1878f1e9")
                .apiSecret("7JTSfyTXikC4JZ1A")
                .build();
       TextMessage message = new TextMessage("Heart2Hold"," 21623334418",
        "Bonjour Mr. l'administrateur, A cet instant une consultation est entain de se derouler  ");

    SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
           
        
       
        
        
    }
    
     /********gestion cord********
         * 
         */
        @Override
    public void mapInitialized() {
    MapOptions mapOptions = new MapOptions();
        
    mapOptions.center(new LatLong(33.8439408,9.400138))
            .mapType(MapTypeIdEnum.ROADMAP)
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(8);
                   
        map = Map.createMap(mapOptions);
    }
    public void configureMap() {
         MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(33.8439408, 9.400138))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(8);
        GoogleMap map = Map.createMap(mapOptions);
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            
   LatLong latLong = event.getLatLong();
  AjoutLong.setText(Double.toString(latLong.getLongitude()));
  AjoutLat.setText(Double.toString(latLong.getLatitude()));
   MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(latLong);
        Marker joeSmithMarker = new Marker(markerOptions1);
      //  map.removeMarker(joeSmithMarker);
      map.clearMarkers();
        map.addMarker( joeSmithMarker );
          
  
       
});
    }

    @FXML
    private void searchCordAction(ActionEvent event) {
          lieu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLieu()));
       
        ObservableList<Cord> list1 = cs.ReadLieuCord(searchCord.getText());
        affichCord.setItems(list1);
    }

    @FXML
    private void addNewAction(ActionEvent event) {
          GCord.getSelectionModel().select(add);
          configureMap();
    }

    @FXML
    private void affCord(ActionEvent event) {
         lieu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLieu()));
        longitude.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLong()));
        latitude.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLat()));
        idCord.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIDD()));
        idCord.setVisible(false);
        ObservableList<Cord> list = cs.afficherAll();
        System.out.println(list);
        affichCord.setItems(list);
    }
         private void addButtonToTable() {
         
        TableColumn<Cord, Void> colBtn = new TableColumn("Action");
        TableColumn<Cord, Void> colBtnS = new TableColumn("Action");
        colBtn.setMinWidth(140);
        colBtnS.setMinWidth(140);
        Callback<TableColumn<Cord, Void>, TableCell<Cord, Void>> cellFactory = new Callback<TableColumn<Cord, Void>, TableCell<Cord, Void>>() {
            @Override
            public TableCell<Cord, Void> call(final TableColumn<Cord, Void> param) {
                final TableCell<Cord, Void> cell = new TableCell<Cord, Void>() {

                    private final Button btn = new Button("Modifier");
                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            GCord.getTabs().add(modifCord);
                            ModificationCord(data);
                        });
                    
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        
        Callback<TableColumn<Cord, Void>, TableCell<Cord, Void>> cellFactorySupp = new Callback<TableColumn<Cord, Void>, TableCell<Cord, Void>>() {
            @Override
            public TableCell<Cord, Void> call(final TableColumn<Cord, Void> param) {
                final TableCell<Cord, Void> cell = new TableCell<Cord, Void>() {

                    private final Button btnS = new Button("Supprimer");
                    

                    {
                        btnS.setOnAction((ActionEvent event) -> {
                            data = getTableView().getItems().get(getIndex());
                            CordService cs = new CordService();
                            try {
                                cs.delete(data);
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                            
                        });
                    
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnS);
                        }
                    }
                };
                return cell;
            }
        };
        
        colBtn.setCellFactory(cellFactory);
        colBtnS.setCellFactory(cellFactorySupp);
       
        affichCord.getColumns().addAll(colBtn,colBtnS);
    }
             private void ModificationCord(Cord data){
          
            
            GCord.getSelectionModel().select(modifCord);
           lieuMod.setText(data.getLieu());
           longMod.setText(data.getLong());
           latMod.setText(data.getLat());
            
            
        }

    @FXML
    private void ajoutercoordo(Event event) {
    }

    @FXML
    private void modifierCord(ActionEvent event) {
           String lieu = lieuMod.getText();
        double longitude = Double.parseDouble(longMod.getText());
        double latitude = Double.parseDouble(latMod.getText());
        Cord c = new Cord(data.getId(),lieu,longitude,latitude);
        cs.ModifierrCord(c);
        GCord.getTabs().remove(1);
        GCord.getSelectionModel().select(TabAjoutCord);
    }

    @FXML
    private void modCord(Event event) {
    }

    @FXML
    private void ajouterCord(ActionEvent event) {
         String lieu = AjoutLieuC.getText();
         
         
          if( (AjoutLieuC.getText().matches("^[a-zA-Z]+$"))  && cs.NomLieu(lieu).isEmpty()){
           
        
       double longitude=Double.parseDouble(AjoutLong.getText());
        double latitude=Double.parseDouble(AjoutLat.getText());
        Cord c = new Cord(lieu,longitude,latitude);
        CordService cs=new CordService();
        cs.AjouterCord(c);
              GCord.getSelectionModel().select(TabAjoutCord);
              
           
          }
          else{
            Alert   alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Attention!");
            alert.setContentText("ce lieu existe déjà");

            alert.showAndWait();
            }
        
        
    }

    @FXML
    private void logoutAction(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Login.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        EventService r= new EventService();
    if( labeltitre.getText().isEmpty() || labelaffiche.getText().isEmpty() || labeldescription.getText().isEmpty() 
            || labellieu.getText().isEmpty() ){
    Alert alertt=new Alert(Alert.AlertType.ERROR);
    alertt.setTitle("WARNING!");
    alertt.setHeaderText(null);
    alertt.setContentText("Merci de vérifier que vous avez remplit tout les champs");
    alertt.showAndWait();
    }else if(!labelprix.getText().matches("^([1-9][0-9]{0,4}|31)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider l'prix");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier lE PRIX  ");
            alert1.showAndWait();
         }
    else if(!labeltitre.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre nom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ nom accepte que les lettres ");
            alert1.showAndWait();
         } 
  
    else{
        proj.gestionEvent.Entite.Event e=new proj.gestionEvent.Entite.Event(
            labeltitre.getText(),
            labelaffiche.getText(),
            labeldescription.getText(),
            Integer.parseInt(labelprix.getText()),
            Date.valueOf(labeldate.getValue()),
            labellieu.getText()
            );   
           EventService c= new EventService();
           c.ajouter(e);
          buildTableviewAssociationData1();
            
                    
        TrayNotification tray = new TrayNotification();
     
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
   }

    }

    @FXML
    private void Supprimer(ActionEvent event) {
         proj.gestionEvent.Entite.Event ev = table.getSelectionModel().getSelectedItem();

     

    final ObservableList<proj.gestionEvent.Entite.Event> listEvenement2 = FXCollections.observableArrayList();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText(null);
    alert.setContentText("Vous voulez supprimer l'Evenement ?");

     ButtonType deleteGame = new ButtonType("Supprimer Evenement ");
     ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
     alert.getButtonTypes().setAll(deleteGame, buttonTypeCancel);

     Optional<ButtonType> result = alert.showAndWait();
    if (result.get()  == deleteGame){
       
         c.delete(ev);
          buildTableviewAssociationData();
          listEvenement2.addAll(c.getAll());
         table.setItems(listEvenement2);
         System.out.println("yeessss etape1");
           TrayNotification tray = new TrayNotification();
     
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }
    }

    @FXML
    private void Modifier(ActionEvent event) {
         int x=0;
        proj.gestionEvent.Entite.Event ev = table.getSelectionModel().getSelectedItem();

      EventService b=new EventService();
      float f = Float.parseFloat(labelprix.getText());
      proj.gestionEvent.Entite.Event lm=new proj.gestionEvent.Entite.Event();
      lm.setAffiche(labelaffiche.getText());
      lm.setLieu(labellieu.getText());
      lm.setDescription(labeldescription.getText());
      lm.setPrix(f);
      lm.setId(ev.getId());
      lm.setTitre(labeltitre.getText());
      lm.setDate(Date.valueOf(labeldate.getValue()));
        b.modifierev(lm,lm.getId()); 
         buildTableviewAssociationData();
     TrayNotification tray = new TrayNotification();
     
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }
    public void clearEvent()
     {
         
     labeltitre.clear();
 
    labelaffiche.clear();

      labellieu.clear();
    
      labeldescription.clear();
    
      labelprix.clear();
      labelprix.clear();
       
         
     }
    @FXML
    private void trier(ActionEvent event) {
         
   if (trier.isSelected()) {
            buildTableviewAssociationData1();
        } else {
            buildTableviewAssociationData();
        }
    }
   
    public void changeinputs(proj.gestionEvent.Entite.Event x){
      labeltitre.setText(x.getTitre());
        // labeldate.setText(x.getDate().)
      
        labeldate.setValue(x.getDate().toLocalDate()); 
        
      labelaffiche.setText(x.getAffiche());
      labellieu.setText(x.getLieu());
      labeldescription.setText(x.getDescription());
      String t=Float.toString(x.getPrix());
      labelprix.setText(t);
    }
         
   public void buildTableviewRecherche() {

        if (recherche.getText().equals(" ")) {
            buildTableviewAssociationData();
            System.out.println("null");
        } else {
            EventService sc = new EventService();

            ObservableList<proj.gestionEvent.Entite.Event> data = sc.listerRecherche(recherche.getText());

            table.setItems(data);

        }
    }
    @FXML
    private void Rch(KeyEvent event) {
        buildTableviewRecherche();
    }

    @FXML
    private void convertirEnPdf(ActionEvent event) {
         Alert a2 = new Alert(Alert.AlertType.CONFIRMATION);
        a2.setTitle("Conversion PDF !");
        a2.setContentText("PDF telecharge avec succés!");
        a2.show();
        proj.gestionEvent.Entite.Event clubSelect = table.getSelectionModel().getSelectedItem();
        pdf(clubSelect.getTitre(), clubSelect.getAffiche(), clubSelect.getLieu() ,clubSelect.getDate() , clubSelect.getPrix(), clubSelect.getDescription());
    }
      public void pdf(String titre , String affiche , String lieu , Date date , Float prix ,String Description ) {
       Document document = new Document() {};
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\nesrinepdf\\" + titre + ".pdf"));
            document.open();

            Font f = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.UNDERLINE));
            f.setColor(0, 153, 255);

            Font f2 = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD));
            f2.setColor(0, 0, 0);

            Paragraph p1 = new Paragraph("Event " + titre + ":", f);
            p1.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph p12 = new Paragraph("    ");
            Paragraph p13 = new Paragraph("    ");
            Paragraph p14 = new Paragraph("     ");

            Paragraph p15 = new Paragraph("     ");
            Paragraph p16 = new Paragraph("     ");
            Paragraph p17 = new Paragraph("     ");
            Paragraph p2 = new Paragraph("Titre : ");
            p2.add(titre);

            Paragraph p3 = new Paragraph("Lieu : ");
            p3.add(lieu);
            
            
           Paragraph p4 = new Paragraph("description  :   ");
       p4.add(Description);
            Paragraph p5 = new Paragraph(" prix  :  ");
            String prixx = Float.toString(prix);
            p5.add(prixx);
           document.add(p1);

            document.add(p12);
            document.add(p13);
            document.add(p14);

            document.add(p2);

            document.add(p15);
            document.add(p15);
            document.add(p15);

            document.add(p3);

            document.add(p16);
            document.add(p16);
            document.add(p16);

           document.add(p4);

            document.add(p17);
            document.add(p17);
            document.add(p17);

           document.add(p5);

        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
    }
        


    @FXML
    private void voirstat(ActionEvent event) throws IOException {
       
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionEvent/FXML/stat2.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }
     private void buildTableviewAssociationData() {
           //   EventService dev = new EventService();
       // ArrayList<String> images = new ArrayList<>();

        colotitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coloaffiche.setCellValueFactory(new PropertyValueFactory<>("affiche"));
        cololieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        coloprix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
         colodate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colodescription.setCellValueFactory(new PropertyValueFactory<>("description"));
         EventService evs=new EventService();
       ObservableList<proj.gestionEvent.Entite.Event> x=evs.getAll();
 
        table.setItems(x);
        
        
        
        
        
    // pagination.setPageCount(images.size());
    // pagination.setPageFactory(n -> new ImageView(images.get(n)));

    }
        
        private void buildTableviewAssociationData1() {
        colotitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coloaffiche.setCellValueFactory(new PropertyValueFactory<>("affiche"));
        cololieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        coloprix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      colodate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colodescription.setCellValueFactory(new PropertyValueFactory<>("description"));
         EventService evs=new EventService();
       ObservableList<proj.gestionEvent.Entite.Event> x=evs.getAllTRR();
 
        table.setItems(x);

    }
    
    
}