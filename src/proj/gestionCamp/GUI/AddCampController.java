/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.GUI;

import com.jfoenix.controls.JFXButton;
import proj.gestionCamp.Entities.Camp;
import proj.gestionCamp.Entities.Cord;
import proj.gestionCamp.Services.CampService;
import proj.gestionCamp.Services.CordService;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class AddCampController implements Initializable, MapComponentInitializedListener {

    @FXML
    private TextField TextCapacite;
    @FXML
    private Button CampAjout;
    @FXML
    private ChoiceBox<String> ChoiceLieu;

    ObservableList<String> listM;
   CampService cms = new CampService();
    CordService cs = new CordService();
    @FXML
    private Tab ajoutCamp;
    @FXML
    private TextField LieuModif;
    @FXML
    private TextField CapaciteModif;
    @FXML
    private Button ModifCamp;
    @FXML
    private Tab modifierCamp;
    @FXML
    private TableView<Cord> TableCamp;
    @FXML
    private TableColumn<Cord, String> ColumnLieu;
    @FXML
    private TableColumn<Cord, String> ColumnCap;
    GoogleMap map;
    Cord data;
     int EnterSearch =0;
    @FXML
    private TabPane TabGestion;
    @FXML
    private TextField SearchPlace;
    @FXML
    private GoogleMapView GMap;
    @FXML
    private Button Retour;
    @FXML
    private Tab MapTab;
    @FXML
    private Button Afficher;
    @FXML
    private TableColumn<Cord, String> NomCampTab;
    @FXML
    private TextField nomCamp;
    @FXML
    private TextField nomCampMod;
    @FXML
    private JFXButton refId;
    @FXML
    private JFXButton consid;
    @FXML
    private JFXButton cmpid;
    @FXML
    private JFXButton csid;
    @FXML
    private JFXButton Event;

     AddCampController ac = this;
    
    GoogleMapView mapV;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButtonToTable();
        listM = cs.ReadNameLieu();
        ChoiceLieu.setItems(listM);
       // TabGestion.getTabs().remove(1);
      GMap.addMapInializedListener(this);
      //GMap.addMapInializedListener(()->configureMap(data));
     
        
    }    
   public void clearCamp()
     {
         
         nomCamp.clear();
  // ChoiceLieu.clear(); 
         TextCapacite.clear();
       
 
     }
    @Override
    public void mapInitialized() {
      MapOptions mapOptions = new MapOptions();
        
        
           mapOptions.center(new LatLong(33.8439408, 9.400138))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(7);
      
     map = GMap.createMap(mapOptions);
                 System.out.println("initialisation terminé"); 
    }
    @FXML 
    private void AjouterCamp(ActionEvent event) {
        
        try {
           if(TextCapacite.getText().matches("[0-9]+")&&  nomCamp.getText().matches("^[a-zA-Z]+$") && ChoiceLieu.getSelectionModel().getSelectedItem()!= null){
                System.out.println(TextCapacite.getText());
                int capacite= Integer.parseInt(TextCapacite.getText());
                String nom = nomCamp.getText();
                System.out.println(capacite);
                System.out.println(nom);
                if(capacite>=50){
                    String lieuS = ChoiceLieu.getSelectionModel().getSelectedItem();
                    int lieu;
                    lieu = cs.ReadIdLieu(lieuS);
                    
                    Camp c = new Camp(lieu,nom,capacite);
                    CampService cs = new CampService();
                    cs.AjouterCamp(c);
                    Alert   alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("DONE");
                    alert.setContentText("ajout réussi");

                    alert.showAndWait();
                }
                else{
                    Alert   alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Attention!");
                    alert.setContentText("La capacité doit etre supèrieure ou égale  à 50");

                    alert.showAndWait();
                }   
            }else{
                Alert   alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Attention!");
                alert.setContentText("verifiez vos champs");

                alert.showAndWait();
            }
           
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        
        AfficherClicked(event);
        clearCamp();
       
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
          try {
         
            String nom = nomCampMod.getText();
            String lieuModif = LieuModif.getText();
            int lieu;
            lieu = cs.ReadIdLieu(lieuModif);
            
            int capacite= Integer.parseInt(CapaciteModif.getText());
                 if(capacite>=50){
            Camp c = new Camp(lieu,nom,capacite);
            CampService cs = new CampService();
            cs.ModifierrCamp(c);
            TabGestion.getSelectionModel().select(ajoutCamp);
        }
            
            
            }catch (SQLException ex) {
            System.out.println(ex);
        }
        
      
        
        AfficherClicked(event);
        clearCamp();
    }

    @FXML
    private void modifierClicked(Event event) {
        
    }

    @FXML
    private void AfficherClicked(Event event) {
         NomCampTab.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNomCamps()));
     
        ColumnCap.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCapaciteS()));
        ColumnLieu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLieu()));
        ObservableList<Cord> list = cs.ReadLieuCapacite();
        System.out.println(list);
        TableCamp.setItems(list);
    }
    
     private void addButtonToTable() {
        TableColumn<Cord, Void> colBtn = new TableColumn("Action");
        TableColumn<Cord, Void> colBtnS = new TableColumn("Action");
           TableColumn<Cord, Void> location = new TableColumn("Action");
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
                            TabGestion.getTabs().add(modifierCamp);
                            ModifierCamp(data);
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
                                cs.deleteCamp(data);
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                              AfficherClicked(event);
        clearCamp();  
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
           Callback<TableColumn<Cord, Void>, TableCell<Cord, Void>> cellFactoryLocation = new Callback<TableColumn<Cord, Void>, TableCell<Cord, Void>>() {
            @Override
            public TableCell<Cord, Void> call(final TableColumn<Cord, Void> param) {
                final TableCell<Cord, Void> cell = new TableCell<Cord, Void>() {

                    private final Button btnLoc = new Button("Localisation");
                    

                    {
                        btnLoc.setOnAction((ActionEvent event) -> {
                            data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data.toString());
                             
                             configureMap(data);
                               TabGestion.getSelectionModel().select(MapTab);
                                                 
                    });
                    
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnLoc);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        colBtnS.setCellFactory(cellFactorySupp);
        location.setCellFactory(cellFactoryLocation);
        TableCamp.getColumns().addAll(colBtn,colBtnS,location);
    }
     
      private void ModifierCamp(Cord data){
            
            TabGestion.getSelectionModel().select(modifierCamp);
           LieuModif.setText(data.getLieu());
           LieuModif.setDisable(true);
           CapaciteModif.setText(data.getCapaciteS());
             nomCampMod.setText(data.getNomCamps());
         
            
        }

    @FXML
    private void SearchPlace(ActionEvent event) {
        /*if(EnterSearch <1){
            addButtonToTable();
            EnterSearch++;
        }*/
        ColumnCap.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCapaciteS()));
        ColumnLieu.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLieu()));
          NomCampTab.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNomCamps()));
        ObservableList<Cord> list1 = cs.ReadLieuCapacite(SearchPlace.getText());
        TableCamp.setItems(list1);
    }

    
     public void configureMap(Cord data) {
       
         MapOptions mapOptions = new MapOptions();
        
        
        try {
            mapOptions.center(new LatLong(cms.Latitude(data.getLieu()),cms.Longitude(data.getLieu())))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(7);
        } catch (SQLException ex) {
            Logger.getLogger(AddCampController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     map = GMap.createMap(mapOptions);
        
        try {
            LatLong joeSmithLocation;
            joeSmithLocation = new LatLong(cms.Latitude(data.getLieu()),cms.Longitude(data.getLieu()) );
            System.out.println(joeSmithLocation);
   System.out.println("joe position");
   
   System.out.println(data.getLieu());
    
    
//                 map =mapV.createMap(mapOptions);
      
       
        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);
          System.out.println("joe location");
         
        
        Marker joeSmithMarker = new Marker(markerOptions1);
       
        
     map.addMarker( joeSmithMarker );
      System.out.println("joe marker");
         InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
       infoWindowOptions.content("<h2>"+data.getLieu()+"</h2>"
                                +"Capacité  = "+data.getCapaciteS()+"<br>"
                +"Somme des besoins  = "+cms.SommeBs(data.getLieu())
                
                                 );
       InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map);
          System.out.println("joe map");
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        //Set the initial properties of the map.
      }

    @FXML
    private void RetourAction(ActionEvent event) { 
            TabGestion.getSelectionModel().select(ajoutCamp);
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
    private void EventAction(ActionEvent event) throws IOException {
           Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Désolé");
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez pas accèes ");
            alert1.showAndWait();
        
        
    }



}
