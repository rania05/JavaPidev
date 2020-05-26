/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import proj.gestionRefug.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class FXMLStatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private PieChart piechart;
  MyConnection connection = null;
        private final Connection cnx= MyConnection.getInstance().getCnx();

    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              String req ="SELECT UPPER(p.origine) as Origine ,COUNT(p.idcamp) as Camp FROM refugie p INNER join camp e where e.id=p.idcamp GROUP BY p.origine";
           
            PreparedStatement ste;
        try {
            ste = (PreparedStatement) cnx.prepareStatement(req);
              ResultSet rs = ste.executeQuery();
            while (rs.next()){
              //  System.out.println(rs.getString("trajet"));
                 // System.out.println(rs.getInt("num"));
                 piechart.getData().add(new  PieChart.Data(rs.getString("Origine"), rs.getInt("Camp")));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(FXMLStatController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }    
    
}
