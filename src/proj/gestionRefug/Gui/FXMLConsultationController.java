/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import proj.gestionRefug.entities.RefConsult;
import proj.gestionRefug.entities.Refugie;
import proj.gestionRefug.services.ConsultationService;
import proj.gestionRefug.services.RefugieService;
import proj.gestionRefug.utils.MyConnection;

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
    private TableView<RefConsult> tabRef;
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
    @FXML
    private JFXTextField zoneRechC;
    @FXML
    private Pagination pagination;
  
    int from = 0, to = 0;
    int itemPerPage = 5;
    @FXML
    private JFXButton Event;
    
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
        int count = 0;
        String req = "SELECT count(*) FROM Ref_consult";
        try {

            try (Statement pst1 = MyConnection.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                count = rs1.getInt(1);
                rs1.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       colref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
       

        int pageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);

        
    }    

    @FXML
    private void ajoutConsult(ActionEvent event) throws NexmoClientException, IOException{
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
    int count = 0;
        String req = "SELECT count(*) FROM Ref_consult";
        try {

            try (Statement pst1 = MyConnection.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                count = rs1.getInt(1);
                rs1.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       colref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
       

        int pageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);

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
       int count = 0;
        String req = "SELECT count(*) FROM Ref_consult";
        try {

            try (Statement pst1 = MyConnection.getInstance().getCnx().createStatement()) {
                ResultSet rs1 = pst1.executeQuery(req);
                rs1.first();
                count = rs1.getInt(1);
                rs1.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       colref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
       

        int pageCount = (count / itemPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(this::createPage);
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
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
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

    private void refreshB() throws SQLException{
        List<RefConsult> listB=new ArrayList<>();
        ConsultationService   cr = new ConsultationService();
        listB = cr.affConsult();
        ObservableList <RefConsult> data = FXCollections.observableArrayList(listB);
        tabRef.setItems(data);
    }
    
    
    @FXML
    private void rechercherC(KeyEvent event) {
         ConsultationService ConsultationService = new ConsultationService();
        zoneRechC.setOnKeyReleased((KeyEvent e)
                -> {
            if (zoneRechC.getText().equals("") ) {

                try {
                    refreshB();
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            } else {

                try {
       colref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
                    tabRef.getItems().clear();

                    tabRef.setItems(ConsultationService.rechercheConsultation(zoneRechC.getText()));

                } catch (SQLException ex) {
                Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);

                }
        

            }
        }
        );
    }
    
    public ObservableList<RefConsult> getTableData() {
        ObservableList<RefConsult> data = FXCollections.observableArrayList();
        try {
            String req = "Select r.id,rc.sujet,rc.contenu,rc.date,rc.duree,r.prenom from Ref_consult rc INNER JOIN refugie r on rc.idref=r.id limit "+ from + "," + to;
            try (Statement pst = MyConnection.getInstance().getCnx().createStatement()) {
             ResultSet rs = pst.executeQuery(req);
                while (rs.next()) {
                    RefConsult r = new RefConsult(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getDate(4),
                      rs.getTime(5));                      
              r.setPrenom(rs.getString(6));
                  data.add(r);
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    private Node createPage(int pageIndex) {
        from = pageIndex * itemPerPage;
        to = itemPerPage;
        tabRef.setItems(FXCollections.observableArrayList(getTableData()));
        return tabRef;
    }

    @FXML
    private void dongo(ActionEvent event) throws IOException {
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
    }

    private void Compte(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/utilisateur.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

    @FXML
    private void CasSociale(ActionEvent event) {
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
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
    private void EventAction(ActionEvent event) {
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
    }
    
}
