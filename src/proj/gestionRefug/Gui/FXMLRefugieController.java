/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import proj.gestionRefug.entities.Refugie;
import proj.gestionRefug.services.RefugieService;
import proj.gestionRefug.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLRefugieController implements Initializable {
    
    RefugieService ref=new RefugieService();
    
      private RefugieService refServ ; 
      
    @FXML
    private Button btAjout;
    @FXML
    private Label labNom;
    @FXML
    private Label LabPrenom;
    @FXML
    private Label LabAge;
    @FXML
    private Label lborigine;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField origine;
    @FXML
    private Label lbcamp;
    @FXML
    private TableView<Refugie> tabRef;
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
    private Button suppRef;
    @FXML
    private JFXButton btnConsultation;
    @FXML
    private JFXButton btnrefgo;
    @FXML
    private Button btnmodRef;
    @FXML
    private ComboBox<String> listeCamp;
    @FXML
    private JFXTextField zoneRech;
    @FXML
    private Pagination Pagination;
    int from = 0, to = 0;
    int itemPerPage = 5;
    @FXML
    private Button btnstat;
    @FXML
    private JFXButton Event;

    /**
     * Initializes the controller class.
     */
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
        campref.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
        tabRef.setItems(observableList);
        List<String> listeC =ref.listeCamp();
                ObservableList campss = FXCollections.observableArrayList(listeC);
listeCamp.setValue("liste camps");
listeCamp.setItems(campss);

int count = 0;
        String req = "SELECT count(*) FROM Refugie";
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
      nomref.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageref.setCellValueFactory(new PropertyValueFactory<>("age"));
        origineref.setCellValueFactory(new PropertyValueFactory<>("origine"));
        campref.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
       

        int pageCount = (count / itemPerPage) + 1;
        Pagination.setPageCount(pageCount);
        Pagination.setPageFactory(this::createPage);

        
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
         }else if(!prenom.getText().matches("^[a-zA-Z\\s]*$") )
         {
              Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider votre prenom");
            alert1.setHeaderText(null);
            alert1.setContentText("Le champ prenom accepte que les lettres ");
            alert1.showAndWait();
         }else if(!age.getText().matches("^([1-9][0-9]{0,4}|31)$")){    
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Valider l'age");
            alert1.setHeaderText(null);
            alert1.setContentText("Veuillez verifier l'age ");
            alert1.showAndWait();
         }  else if(!origine.getText().matches("^[a-zA-Z\\s]*$") )
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
  
    
int count = 0;
        String req = "SELECT count(*) FROM Refugie";
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
      nomref.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageref.setCellValueFactory(new PropertyValueFactory<>("age"));
        origineref.setCellValueFactory(new PropertyValueFactory<>("origine"));
        campref.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
        int pageCount = (count / itemPerPage) + 1;
        Pagination.setPageCount(pageCount);
        Pagination.setPageFactory(this::createPage); 
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
        tabRef.setItems(observableList);
        
    }

    @FXML
    private void RefSupp(ActionEvent event) {
        
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
    private void consultationgo(ActionEvent event) throws IOException  {
          Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
        
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
    private void modifAff(MouseEvent event) {
    Refugie b=(Refugie) tabRef.getSelectionModel().getSelectedItem();
            RefugieService b1= new RefugieService();
    nom.setText(b.getNom());
    prenom.setText(b.getPrenom());
    age.setText(String.valueOf(b.getAge()));
    origine.setText(b.getOrigine());
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
        
      Refugie b=(Refugie) tabRef.getSelectionModel().getSelectedItem();
      b1.modifierRefugie(
              nom.getText(), 
              prenom.getText(),
              Integer.parseInt(age.getText()),
              origine.getText(),
              b1.getIdCamps(listeCamp.getValue())
      );}
    
int count = 0;
        String req = "SELECT count(*) FROM Refugie";
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
      nomref.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomref.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageref.setCellValueFactory(new PropertyValueFactory<>("age"));
        origineref.setCellValueFactory(new PropertyValueFactory<>("origine"));
        campref.setCellValueFactory(new PropertyValueFactory<>("nomCamp"));
        int pageCount = (count / itemPerPage) + 1;
        Pagination.setPageCount(pageCount);
        Pagination.setPageFactory(this::createPage); 

        
    }
    
private void refreshB() throws SQLException{
        List<Refugie> listB=new ArrayList<>();
        RefugieService   cr = new RefugieService();
        listB = cr.afficherRefugie();
        ObservableList <Refugie> data = FXCollections.observableArrayList(listB);
        tabRef.setItems(data);
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

                    tabRef.getItems().clear();

                    tabRef.setItems(RefugieService.rechercheRefugie(zoneRech.getText()));

                } catch (SQLException ex) {
                Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        );
    }
    public ObservableList<Refugie> getTableData() {
        ObservableList<Refugie> data = FXCollections.observableArrayList();
        try {
            String req = "Select r.nom,r.prenom,r.age,r.origine,c.id,c.nom from refugie r INNER JOIN camp c on r.idcamp = c.id limit "+ from + "," + to;
            try (Statement pst = MyConnection.getInstance().getCnx().createStatement()) {
             ResultSet rs = pst.executeQuery(req);
                while (rs.next()) {
                  Refugie r = new Refugie(
                        
                      rs.getString(1),
                      rs.getString(2),
                      rs.getInt(3),
                      rs.getString(4),
                      rs.getInt(5));
                      r.setNomCamp(rs.getString(6));
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
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionCamp/GUI/AddCamp.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

    @FXML
    private void voirStat(ActionEvent event) throws IOException {
                Scene scene;
            Stage stage = new Stage();
         
                
                scene = new Scene(FXMLLoader.load(getClass().getResource("/proj/gestionRefug/Gui/FXMLStat.fxml")));
                 stage.setScene(scene);
            stage.show();
            
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
    private void CasSociale(ActionEvent event) throws IOException {
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

