/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.GUI;

import proj.gestionCamp.Entities.Cord;
import proj.gestionCamp.Services.CordService;
import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class GestionCordController implements Initializable {

    private Tab ajoutCord;
    @FXML
    private TextField AjoutLieuC;
    @FXML
    private TextField AjoutLong;
    @FXML
    private TextField AjoutLat;
    @FXML
    private Button AjoutCord;
    @FXML
    private TableView<Cord> affichCord;
    @FXML
    private Button affichCordClick;
    @FXML
    private TableColumn<Cord,String> lieu;
    @FXML
    private TableColumn<Cord,String> longitude;
    @FXML
    private TableColumn<Cord,String> latitude;
    
    
 CordService cs = new CordService();
 Cord data;
    @FXML
    private TableColumn<Cord,String> idCord;
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
    private TabPane GCord;
    @FXML
    private TextField searchCord;
    
    DecimalFormat formatter = new DecimalFormat("###.00000");
    @FXML
    private TextField searchCord1;
    @FXML
    private Tab TabAjoutCord;
    @FXML
    private AnchorPane Cord1;
    @FXML
    private JFXButton addNew;
    @FXML
    private GoogleMapView Map;
    @FXML
    private Tab add;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         addButtonToTable();
             Map.addMapInializedListener(()->configureMap());
       GCord.getTabs().remove(1);
        
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
                .zoom(12);
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
    private void affCord(Event event) {
       
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
    
    
      private void ModificationCord(Cord data){
          
            
            GCord.getSelectionModel().select(modifCord);
           lieuMod.setText(data.getLieu());
           longMod.setText(data.getLong());
           latMod.setText(data.getLat());
            
            
        }

    @FXML
    private void searchCordAction(ActionEvent event) {
         lieu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLieu()));
       
        ObservableList<Cord> list1 = cs.ReadLieuCord(searchCord.getText());
        affichCord.setItems(list1);
    }
@FXML
    private void InterfaceCord(ActionEvent event) throws IOException {
     ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionCamp/GUI/GestionCord.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

     @FXML
    private void InterfaceCamp(ActionEvent event) throws IOException {
     ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionCamp/GUI/AddCamp.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }

    @FXML
    private void InterfaceBesoins(ActionEvent event) throws IOException {
   ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionCamp/GUI/GestionBesoins.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }



    @FXML
    private void InterfaceAffectation(ActionEvent event) {
    }

    @FXML
    private void ajoutercoordo(Event event) {
    }

    @FXML
    private void addNewAction(ActionEvent event) {
          GCord.getSelectionModel().select(add);
    }
      
    
}
