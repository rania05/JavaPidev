/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionEvent.FXML;

import proj.gestionEvent.Entite.Event;

import proj.gestionEvent.Service.EventService;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
//import javax.swing.text.Document;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import com.itextpdf.text.Document;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class FXMLPageController implements Initializable {

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
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, String> colotitre;
    @FXML
    private TableColumn<Event, String> coloaffiche;
    @FXML
    private TableColumn<Event, String> cololieu;
    @FXML
    private TableColumn<Event, Date> colodate;
    @FXML
    private TableColumn<Event, Float> coloprix;
    @FXML
    private TableColumn<Event, String> colodescription;
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
    private ImageView image;
     @FXML
    private ImageView pdff;
     @FXML
    private AnchorPane Eventt;
        @FXML
    private Button stat;
      //@FXML
  //  private Pagination pagination;
    EventService cs = new EventService();

    /**
     * Initializes the controller class.
     */
    

        

    
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException{

 
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
        Event e=new Event(
            labeltitre.getText(),
            labelaffiche.getText(),
            labeldescription.getText(),
            Integer.parseInt(labelprix.getText()),
            Date.valueOf(labeldate.getValue()),
            labellieu.getText()
            );   
           EventService b= new EventService();
           b.ajouter(e);
          buildTableviewAssociationData1();
            
                    
        TrayNotification tray = new TrayNotification();
     
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
   }

}
    


    

    @FXML 
    private void Supprimer(ActionEvent event) throws SQLException{
        Event ev = table.getSelectionModel().getSelectedItem();

     

    final ObservableList<Event> listEvenement2 = FXCollections.observableArrayList();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText(null);
    alert.setContentText("Vous voulez supprimer l'Evenement ?");

     ButtonType deleteGame = new ButtonType("Supprimer Evenement ");
     ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
     alert.getButtonTypes().setAll(deleteGame, buttonTypeCancel);

     Optional<ButtonType> result = alert.showAndWait();
    if (result.get()  == deleteGame){
       
         cs.delete(ev);
          buildTableviewAssociationData();
          listEvenement2.addAll(cs.getAll());
         table.setItems(listEvenement2);
         System.out.println("yeessss etape1");
           TrayNotification tray = new TrayNotification();
     
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
   
    }
}
    
     
   public void buildTableviewRecherche() {

        if (recherche.getText().equals(" ")) {
            buildTableviewAssociationData();
            System.out.println("null");
        } else {
            EventService sc = new EventService();

            ObservableList<Event> data = sc.listerRecherche(recherche.getText());

            table.setItems(data);

        }
    }
    
    

    
    public void changeinputs(Event x){
      labeltitre.setText(x.getTitre());
        // labeldate.setText(x.getDate().)
      
        labeldate.setValue(x.getDate().toLocalDate()); 
        
      labelaffiche.setText(x.getAffiche());
      labellieu.setText(x.getLieu());
      labeldescription.setText(x.getDescription());
      String t=Float.toString(x.getPrix());
      labelprix.setText(t);
    }
    



    @FXML
    private void Modifier(ActionEvent  event) {
        int x=0;
        Event ev = table.getSelectionModel().getSelectedItem();

      EventService b=new EventService();
      float f = Float.parseFloat(labelprix.getText());
      Event lm=new Event();
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
    void trier(ActionEvent event) {
   if (trier.isSelected()) {
            buildTableviewAssociationData1();
        } else {
            buildTableviewAssociationData();
        }

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
       ObservableList<Event> x=evs.getAll();
 
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
       ObservableList<Event> x=evs.getAllTRR();
 
        table.setItems(x);

    }
        @FXML
    void Rch(KeyEvent event) {
buildTableviewRecherche();
    }

        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
              buildTableviewAssociationData();
              
        /*   Image image = new Image("/css/nn.jpg");  
             ImageView iv  = new  ImageView(image);
            // iv.setImage(image);
             Group root = new Group(iv);
             
           root.getChildren().add(iv);*/
               
     table.setRowFactory( (TableView<Event> tv) -> {
    TableRow<Event> row = new TableRow<>();
     
    row.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
             Event C = row.getItem();
           
                       changeinputs(C);
                    
        }
    });
    return row ;
});   
    }




    @FXML
    private void convertirEnPdf(ActionEvent event) {
        Alert a2 = new Alert(Alert.AlertType.CONFIRMATION);
        a2.setTitle("Conversion PDF !");
        a2.setContentText("PDF telecharge avec succés!");
        a2.show();
        Event clubSelect = table.getSelectionModel().getSelectedItem();
        pdf(clubSelect.getTitre(), clubSelect.getAffiche(), clubSelect.getLieu() ,clubSelect.getDate() , clubSelect.getPrix(), clubSelect.getDescription());
    }
    
    public void pdf(String titre , String affiche , String lieu , Date date , Float prix ,String Description ) {
       Document document = new Document() {};
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Nesrine\\Desktop\\" + titre + ".pdf"));
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
    void voirstat(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/stat2.fxml"));
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    
}