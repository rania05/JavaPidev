/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.Services;

import proj.gestionCamp.Entities.Besoins;
import proj.gestionCamp.Entities.Cord;
import proj.gestionCamp.Entities.affectation;
import proj.gestionRefug.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rania
 */
public class affectationService {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public affectationService() {
        cnx = MyConnection.getInstance().getCnx();
    }
     
     public void AjouterAffectation(affectation b) {


        try {
            String req = "INSERT INTO affecttation (id_bs,id_don, qnt,date) VALUES (?, ?,?,?)";

            pre = cnx.prepareStatement(req);
 pre.setString(1, b.getCamp());
            pre.setString(2, b.getCategorie());
            pre.setInt(3, b.getQuantite());
  pre.setTimestamp(4, b.getDate());
            pre.executeUpdate();
 
            System.out.println("Insertion 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
      public ObservableList<affectation> afficherAll() {

       ObservableList<affectation> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT * FROM affecttation";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               affectation p = new affectation();

              
              p.setCamp(res.getString(2));
               p.setCategorie(res.getString(3));
                p.setQuantite(res.getInt(4));
                 p.setDate(res.getTimestamp(5));
                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
     
}
