/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.GUI;


import com.jfoenix.controls.JFXButton;
import proj.gestionCamp.Entities.Besoins;
import proj.gestionCamp.Entities.Camp;
import proj.gestionCamp.Entities.Cord;
import proj.gestionCamp.Entities.affectation;
import proj.gestionCamp.Entities.don;

import proj.gestionCamp.Services.BesoinsService;
import proj.gestionCamp.Services.CampService;
import proj.gestionCamp.Services.CategorieService;
import proj.gestionCamp.Services.CordService;
import proj.gestionCamp.Services.affectationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
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
public class GestionBesoinsController implements Initializable {

    @FXML
    private TabPane GestionBesoin;
    @FXML
    private Tab AjoutBesoin;
    @FXML
    private ChoiceBox<String> ChoiceCamp;
    @FXML
    private ChoiceBox<String> ChoiceCategorie;
    @FXML
    private TextField Quantite;
    @FXML
    private Button AjouterBS;
    @FXML
    private Button AfficheBesoin;
    @FXML
    private TableView<Besoins> AfficherBesoin;
    @FXML
    private TableColumn<Besoins, String> CampAff;
    @FXML
    private TableColumn<Besoins, String> CatAff;
    @FXML
    private TableColumn<Besoins, String> QuantiteAff;
    BesoinsService bs = new BesoinsService();
     CampService cs = new CampService();
     CategorieService cts =  new CategorieService();
     CordService cos = new CordService();
     Besoins data;
     don data1;
    @FXML
    private Tab modifier;
    @FXML
    private Button Modifier;
    @FXML
    private TextField QuantMod;
    @FXML
    private ChoiceBox<String> CatMod;
    @FXML
    private ChoiceBox<String> CampMod;
 ObservableList<String> listC = null;
 ObservableList<String> listCat = null;
    private TableColumn<Besoins, String> id;
    @FXML
    private TableColumn<Besoins, String> idB;
    @FXML
    private Button Affectation;
    @FXML
    private TextField CategorieAffe;
    @FXML
    private TextField QuantiteAffe;
    @FXML
    private TextField LieuAffe;
    @FXML
    private Tab AffecterTab;
    @FXML
    private TextField SearchBs;
    @FXML
    private BarChart<String, Integer> BarChart;
    @FXML
    private ChoiceBox<String> ChoiceStat;
    @FXML
    private NumberAxis Yaxis;
    @FXML
    private CategoryAxis Xaxis;
    @FXML
    private Tab AffSuivC;
    @FXML
    private TableColumn<affectation, String> nomCampAff;
    @FXML
    private TableColumn<affectation, String> CategorieAff;
    @FXML
    private TableColumn<affectation, String>quantiteAff;
    @FXML
    private TableColumn<affectation, String> dateAff;
    @FXML
    private TableView<affectation> tableSuivie;
    @FXML
    private Button BtnRetourStat;
    @FXML
    private Button RetourAff;
    @FXML
    private Button indexAff;
    @FXML
    private Button StatA;
    @FXML
    private Tab tabStatistiques;
    @FXML
    private JFXButton btnrefgo;
    @FXML
    private JFXButton btnConsultation;
    @FXML
    private JFXButton Event;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            String name = null;
        try {
            listC = cs.ReadLieu();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        addButtonToTable();
        ChoiceCamp.setItems(listC);
       // GestionBesoin.getTabs().remove(1);
       listCat = cts.ReadCat();
        ChoiceCategorie.setItems(listCat);
       /* GestionBesoin.getTabs().remove(2);
        GestionBesoin.getTabs().remove(2);*/
        /**************************************Statistique*********************************************/
        Xaxis.setLabel("Camp");
        Yaxis.setLabel("Quantité");
        ChoiceStat.setItems(listCat);
        XYChart.Series<String , Integer> series  = new XYChart.Series<>();
        ChoiceStat.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number t1) {
                try {
                    series.getData().clear();
                    ObservableList<Besoins> listB = bs.StatBesoins(bs.ReadIdCat(listCat.get(t1.intValue())));
                    for (int i = 0; i < listB.size(); i++) {
                        series.getData().add(new XYChart.Data<>(listB.get(i).getLieuC(), listB.get(i).getQuantite()));
                    }
                    
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                
                
                
            }
        });
        BarChart.getData().add(series);
    }    
  public void clearCamp()
     {
         
         Quantite.clear();
 
       
 
     }
    
    @FXML
    private void AjoutBsButton(ActionEvent event) throws SQLException {
        try{
          if(Quantite.getText().matches("[0-9]+")&& ChoiceCamp.getSelectionModel().getSelectedItem()!= null && ChoiceCategorie.getSelectionModel().getSelectedItem()!= null){
         String id_cc = ChoiceCamp.getSelectionModel().getSelectedItem();
           String nom_bss = ChoiceCategorie.getSelectionModel().getSelectedItem();
       
         int id_c;
         int nom_bs;
        
            id_c = cs.ReadIdLieu(id_cc);
            nom_bs=cts.ReadIdCat(nom_bss);
            int quantite= Integer.parseInt(Quantite.getText());
          
            Besoins b = new Besoins(id_c,nom_bs,quantite);
           
            bs.AjouterBesoin(b);
             Alert   alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("DONE");
            alert.setContentText("ajout réussi");

            alert.showAndWait();
            }
          
          
            else{
            Alert   alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Attention!");
            alert.setContentText("verifiez vos champs");

            alert.showAndWait();
            }}
         catch (SQLException ex) {
            System.out.println(ex);
        }
          AffBs(event);
        clearCamp();
        
        }
 private void addButtonToTable() {
         
        TableColumn<Besoins, Void> colBtn = new TableColumn("Action");
        TableColumn<Besoins, Void> colBtnS = new TableColumn("Action");
        TableColumn<Besoins, Void> colBtnAff = new TableColumn("Action");
        colBtn.setMinWidth(140);
        colBtnS.setMinWidth(140);
        colBtnAff.setMinWidth(140);
        Callback<TableColumn<Besoins, Void>, TableCell<Besoins, Void>> cellFactory = (final TableColumn<Besoins, Void> param) -> {
            final TableCell<Besoins, Void> cell = new TableCell<Besoins, Void>() {
                
                private final Button btn = new Button("Modifier");
                
                
                {
                    btn.setOnAction((ActionEvent event) -> {
                        data = getTableView().getItems().get(getIndex());
                        System.out.println("selectedData: " + data);
                        GestionBesoin.getTabs().add(modifier);
                         ModificationBesoin(data);
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
        };
        
        Callback<TableColumn<Besoins, Void>, TableCell<Besoins, Void>> cellFactorySupp = (final TableColumn<Besoins, Void> param) -> {
            final TableCell<Besoins, Void> cell = new TableCell<Besoins, Void>() {
                
                private final Button btnS = new Button("Supprimer");
                
                
                {
                    btnS.setOnAction((ActionEvent event) -> {
                        data = getTableView().getItems().get(getIndex());
                        CordService cs = new CordService();
                                  try {
                        bs.deleteBs(data);
                        } catch (SQLException ex) {
                        System.out.println(ex);
                        }
                          AffBs(event);
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
        };
        
        Callback<TableColumn<Besoins, Void>, TableCell<Besoins, Void>> cellFactoryAff = (final TableColumn<Besoins, Void> param) -> {
            final TableCell<Besoins, Void> cell = new TableCell<Besoins, Void>() {
                
                private final Button btA = new Button("Affecter");
                
                
                {
                    btA.setOnAction((ActionEvent event) -> {
                        System.out.println("getTableView().getItems().get(getIndex()) = "+getTableView().getItems().get(getIndex()));
                        data = getTableView().getItems().get(getIndex());
                        System.out.println("selectedData: " + data.getId_cc());
                        GestionBesoin.getTabs().add(AffecterTab);
                        AffectationBesoin(data);
                    });
                    
                    
                }                       
                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btA);
                    }
                }
            };
            return cell;
        };
        
        colBtn.setCellFactory(cellFactory);
        colBtnS.setCellFactory(cellFactorySupp);
        colBtnAff.setCellFactory(cellFactoryAff);
       
        AfficherBesoin.getColumns().addAll(colBtn,colBtnS,colBtnAff);
    }
    @FXML
    private void AffBs(Event event) {
        idB.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIDB()));
        idB.setVisible(false);
        CampAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getLieuC()));
        CatAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCat()));
        QuantiteAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getQuantitee()));
        ObservableList<Besoins> list = bs.ReadBesoinet();
        
        System.out.println(list);
        AfficherBesoin.setItems(list);
    }
     private void ModificationBesoin(Besoins data){
            
            GestionBesoin.getSelectionModel().select(modifier);
           CampMod.setItems(listC);
           CampMod.setValue(data.getLieuC());
           CatMod.setItems(listCat);
           CatMod.setValue(data.getCat());
           QuantMod.setText(data.getQuantitee());
            
            
        }

    @FXML
    private void ModifBs(ActionEvent event) {
        String lieu = CampMod.getSelectionModel().getSelectedItem();
        String cat = CatMod.getSelectionModel().getSelectedItem();
       
        int id_c;
         int nom_bs;
        try {
           
            id_c = cs.ReadIdLieu(lieu);
            nom_bs=cts.ReadIdCat(cat);
            int quantite = Integer.parseInt(QuantMod.getText());
            Besoins b = new Besoins(data.getId(),id_c,nom_bs,quantite);
           
            bs.ModifierBesoins(b);
          //  GestionBesoin.getTabs().remove(2);
              GestionBesoin.getSelectionModel().select(AjoutBesoin);
             
               
        } catch (SQLException ex) {
            System.out.println(ex);
        }
          AffBs(event);
        clearCamp();
    }
        private void AffectationBesoin(Besoins data){
            GestionBesoin.getSelectionModel().select(AffecterTab);
           LieuAffe.setText(data.getLieuC());
           CategorieAffe.setText(data.getCat());
           LieuAffe.setDisable(true);
           CategorieAffe.setDisable(true);
           
           
            
            
        }

    @FXML
    private void AffecterButton(ActionEvent event) {
       String lieu = LieuAffe.getText();
        String cat = CategorieAffe.getText();
       
        int id_c;
         int nom_bs;
        try {
                  
            if(QuantiteAffe.getText().matches("[0-9]+")){
            id_c = cs.ReadIdLieu(lieu);
            nom_bs=cts.ReadIdCat(cat);
            
            int quantite = Integer.parseInt(QuantiteAffe.getText());
            int nouvQuan =data.getQuantite() - quantite; 
                System.out.println(bs.QuantiteDon(cat));
            if (nouvQuan>0 && (bs.QuantiteDon(cat)>= quantite)){
            affectation a = new affectation(lieu,cat,quantite);
            affectationService ass=new affectationService();
            Besoins b = new Besoins(data.getId(),id_c,nom_bs,quantite);
           
            bs.QuantiteUpdateBesoins(b, nouvQuan, quantite);
            bs.QuantiteUpdateDon(b, quantite);
           // GestionBesoin.getTabs().remove(4);
            
            
            ass.AjouterAffectation(a);
                GestionBesoin.getSelectionModel().select(AjoutBesoin);
                Alert   alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("DONE");
            alert.setContentText("ajout réussi");

            alert.showAndWait();
            }
            else{
            Alert   alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Attention!");
            alert.setContentText("il n'y a pas suffisement de dons pour cette quantitée ");

            alert.showAndWait();
            }
            
        }
            else{
            Alert   alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Attention!");
            alert.setContentText("verifiez vos champs");

            alert.showAndWait();
            }
        }
        
        catch (SQLException ex) {
            System.out.println(ex);
        } 
    }

    @FXML
    private void SearchBsAction(ActionEvent event) {
        CatAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCat()));
       
        try {
            int CAT = bs.ReadIdCat(SearchBs.getText());
            ObservableList<Besoins> list1 = bs.ReadBesoins(CAT);
            AfficherBesoin.setItems(list1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    @FXML
    private void AffSuivCAction(Event event) {
        affectationService ass=new affectationService();
        nomCampAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCamp()));
        CategorieAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCategorie()));
        quantiteAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getQuantity()));
          dateAff.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDateAff()));
        ObservableList<affectation> list = ass.afficherAll();
        
        System.out.println(list);
        tableSuivie.setItems(list);
    }

    @FXML
    private void RetourStatAction(ActionEvent event) {
            GestionBesoin.getSelectionModel().select(AjoutBesoin);
    }

    @FXML
    private void RetourAffAction(ActionEvent event) {
            GestionBesoin.getSelectionModel().select(AjoutBesoin);
    }
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

    private void InterfaceBesoins(ActionEvent event) throws IOException {
   ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionCamp/Gui/GestionBesoins.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }




    @FXML
    private void indexAffAction(ActionEvent event) {
            GestionBesoin.getSelectionModel().select(AffSuivC);
    }

    @FXML
    private void StatAbtn(ActionEvent event) {
         GestionBesoin.getSelectionModel().select(tabStatistiques);
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
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/proj/gestionEvent/FXML/FXMLEventFront.fxml"));
        Stage stage = new Stage ();
        Scene scene = new Scene (parent);
        stage.setScene(scene);
        stage.setTitle("main");
        stage.show();
    }
}