/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import proj.gestionUser.Services.CassocialeService;
import proj.gestionUser.entities.Cassociale;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CassocialeController implements Initializable {

    @FXML
    private TableView<Cassociale> table;
    @FXML
    private TableColumn<Cassociale, String> clLieu;
    @FXML
    private TableColumn<Cassociale, Date> clDate;
    @FXML
    private TextField txtLieu;
    @FXML
    private DatePicker txtDate;
    @FXML
    private Button btn;
    @FXML
    private Button vider;
    @FXML
    private Button suppr;
    @FXML
    private TextField recherche;
    @FXML
    private DatePicker dateCas;
    @FXML
    private TextField LieuCas;

    
    ObservableList<Cassociale> dataEvent = FXCollections.observableArrayList();
        
        
        Stage dialogStage = new Stage();
    Scene scene;
    @FXML
    private JFXButton refId;
    @FXML
    private JFXButton consid;
    @FXML
    private JFXButton cmpid;
    @FXML
    private JFXButton csid;
    @FXML
    private Tab ajoutTab;
    @FXML
    private TabPane tabCS;
    @FXML
    private Tab listeCas;
    @FXML
    private JFXButton Event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      clLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        clDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        
        CassocialeService srv =new CassocialeService();
        try {
            dataEvent =FXCollections.observableArrayList(srv.getAllCas());
        } catch (SQLException ex) {
            Logger.getLogger(CassocialeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    table.setItems(dataEvent);  
        setCellValue();
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        try {
         
            String lieu=LieuCas.getText();
            String date=dateCas.getValue().toString();
           
            Cassociale cs = new Cassociale(lieu,date);
        CassocialeService srv =new CassocialeService();
        
        cs.setLieu(LieuCas.getText());
        cs.setDate(dateCas.getValue().toString());
           srv.updateCas(cs);
            tabCS.getSelectionModel().select(listeCas);
        
            
            
            }catch (SQLException ex) {
            System.out.println(ex);
        }
        
      
        
        testAff(event);
        Clear(event);
        
        
    }
    @FXML
    private void Clear(ActionEvent event) {
       txtLieu.clear();
    }
    
     public void testAff(ActionEvent event) throws SQLException
     {
         ArrayList<Cassociale> le = (ArrayList<Cassociale>) CassocialeService.getAllsoc();

        for(Cassociale e:le)
        {
            dataEvent.add(e);
        }  
        clLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        clDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        
        CassocialeService srv =new CassocialeService();
        dataEvent =FXCollections.observableArrayList(srv.getAllCas());
        table.setItems(dataEvent); 
 
    }

    @FXML
    private void validerSuppr(ActionEvent event) throws SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppresion");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment supprimer ce Utilisateur ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            
                            if (action.get() == ButtonType.OK)
                            {CassocialeService srv=new CassocialeService();
                           
                            srv.deleteCas(clLieu.getText());
       
        String index = table.getSelectionModel().getSelectedItem().getLieu();
        //System.out.println(index);
        CassocialeService.supprimer(index);
         dataEvent =FXCollections.observableArrayList(srv.getAllCas());
         testAff(event);
        
    }
    }

    @FXML
    private void recher(ActionEvent event) throws SQLException {
        clLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        clDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
         CassocialeService srv =new CassocialeService();
         
         dataEvent=FXCollections.observableArrayList(srv.RechercherCas(recherche.getText()));
        
        table.setItems(dataEvent);
        
    }

    @FXML
    private void addCas(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Ajout");
                            alert.setHeaderText(null);
                            alert.setContentText("Ajput succées");
                             Optional<ButtonType> action = alert.showAndWait();
        Cassociale cs = new Cassociale();
        CassocialeService srv =new CassocialeService();
        
        cs.setLieu(LieuCas.getText());
        cs.setDate(dateCas.getValue().toString());
        
        srv.addCas(cs);
         
        testAff(event);
              tabCS.getSelectionModel().select(listeCas);
        
    }
    @FXML
    private void ClearAdd(ActionEvent event) {
        LieuCas.clear();
        dateCas.setValue(LocalDate.now());
    }

    public void setCellValue() {
        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Cassociale e= table.getItems().get(table.getSelectionModel().getSelectedIndex());
                    txtLieu.setText(e.getLieu());
                    txtDate.setValue(LocalDate.now());
                    
                          
                            
            }
            
            
            
        });
    
    
    }

    @FXML
    private void ajouterCas(ActionEvent event) {
           tabCS.getSelectionModel().select(ajoutTab);
    }

  
     @FXML
    private void InterfaceCamp(ActionEvent event)  {
       Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
    }

    private void InterfaceBesoins(ActionEvent event)  {
     Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
          
    }


    @FXML
    private void refugiego(ActionEvent event) {
           Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
          
    }

    @FXML
    private void consultationgo(ActionEvent event)  {
         Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
          
    }

 

    @FXML
    private void CasSociale(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionUser/GUI/Cassociale.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
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
    private void EventAction(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionEvent/FXML/FXMLEventFront.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
        
    }
    
}
