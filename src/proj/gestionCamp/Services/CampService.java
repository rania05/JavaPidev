/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.Services;

import proj.gestionCamp.Entities.Camp;
import proj.gestionRefug.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rania
 */
public class CampService {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public CampService() {
        cnx = MyConnection.getInstance().getCnx();
    }
     
     public void AjouterCamp(Camp c) {
            System.out.println(c.getNomCamp());
        try {
            String req = "INSERT INTO camp (lieu, capacite,nom) VALUES (?,?, ?)";

            pre = cnx.prepareStatement(req);

            pre.setInt(1, c.getLieu());
            pre.setInt(2, c.getCapacite());
            pre.setString(3, c.getNomCamp());
            pre.executeUpdate();

            System.out.println("Insertion 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
       public void ModifierrCamp(Camp c) {
            try {
                System.out.println(c);
              st = cnx.createStatement();
              String requeteInsert ="UPDATE `camp` SET `capacite`='"+c.getCapacite()+"',`nom`='"+c.getNomCamp()+"'WHERE `lieu` = '"+c.getLieu()+"'";
              if(st.executeUpdate(requeteInsert) == 1){
                  System.out.println("modification effectué");
              }} catch (SQLException ex) {
              Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
     
     public List<Camp> afficherAll() {

        List<Camp> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM camp ORDER BY capacite DESC";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Camp p = new Camp();

                p.setId(res.getInt(1));
                p.setLieu(res.getInt(2));
                  p.setNomCamp(res.getString(3));
                p.setCapacite(res.getInt(4));

                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
      public ObservableList<String> ReadLieu()throws SQLException{
           ObservableList<String> list = FXCollections.observableArrayList();
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select co.lieu FROM cord co , camp c where co.id= c.lieu" ); 
        while (rs.next()) {       
              String lieu =rs.getString(1);
              list.add(lieu);
        }
        return list;
    }
        public int ReadIdLieu(String nom)throws SQLException{
        int id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select c.id from camp c,cord co where c.lieu=co.id and co.lieu='" +nom+"'"); 
        while (rs.next()) {       
               id =rs.getInt(1);
        }
        return id;
    }
       public double Longitude (String lieu) throws SQLException
       {
            double id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select c.longitude from cord c , camp cm where cm.lieu=c.id and c.lieu ='" +lieu+"'"); 
        while (rs.next()) {       
               id =rs.getDouble(1);
        }
        return id;
       }
    public double Latitude (String lieu) throws SQLException
       {
          double  id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select  c.latitude from cord c , camp cm where cm.lieu=c.id and c.lieu ='" +lieu+"'"); 
        while (rs.next()) {       
               id =rs.getDouble(1);
        }
        return id;
       }
    
     public int SommeBs (String lieu) throws SQLException
       {
          int  id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("Select SUM( B.quantite) FROM camp A ,besoins B , cord c where B.id_c=A.id and A.lieu=C.id and C.lieu='" +lieu+"'"); 
        while (rs.next()) {       
               id =rs.getInt(1);
        }
        return id;
       }
}
