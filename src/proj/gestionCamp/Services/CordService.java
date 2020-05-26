/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.Services;


import proj.gestionCamp.Entities.Camp;
import proj.gestionCamp.Entities.Cord;
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
public class CordService {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public CordService() {
        cnx = MyConnection.getInstance().getCnx();
    }
     
     public void AjouterCord(Cord co) {

        try {
            System.out.println(co.getLieu() +" " +co.getLatitude());
            String req = "INSERT INTO cord (lieu, latitude,longitude) VALUES (?, ? ,?)";

            pre = cnx.prepareStatement(req);

            pre.setString(1, co.getLieu());
            pre.setDouble(3, co.getLongitude());
             pre.setDouble(2, co.getLatitude());

            pre.executeUpdate();

            System.out.println("Insertion dans cord Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public void ModifierrCord(Cord c) {
            try {
                System.out.println(c);
              st = cnx.createStatement();
              String requeteInsert ="UPDATE `cord` SET `lieu`='"+c.getLieu()+"',`longitude`='"+c.getLongitude()+"',`latitude`='"+c.getLatitude()+"' WHERE `id` = '"+c.getId()+"'";
              if(st.executeUpdate(requeteInsert) == 1){
                  System.out.println("modification effectué");
              }} catch (SQLException ex) {
                System.out.println(ex);
            }
       }
     
     public ObservableList<Cord> afficherAll() {

       ObservableList<Cord> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT * FROM cord";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Cord p = new Cord();

                p.setId(res.getInt(1));
                p.setLieu(res.getString(2));
                p.setLongitude(res.getDouble(3));
  p.setLatitude(res.getDouble(4));
                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
     
     
    
    public boolean delete(Cord a) throws SQLException {
        st = cnx.createStatement();
        String requeteInsert = "DELETE FROM `cord` WHERE `id` = '"+a.getId()+"'";
        if(st.executeUpdate(requeteInsert) == 1){
            System.out.println("Suppression effectué");
            return true;
        };
        System.out.println("Suppression non effectué");
        return false;
    }
    
      public boolean deleteCamp(Cord p) throws SQLException {
        st = cnx.createStatement();
        String requeteInsert = "DELETE FROM `camp` WHERE lieu = (SELECT id from cord WHERE lieu = '"+p.getLieu()+"')";
        if(st.executeUpdate(requeteInsert) == 1){
            System.out.println("Suppression effectué");
            return true;
        };
        System.out.println("Suppression non effectué");
        return false;
    }

  
    public boolean update(Cord a) throws SQLException {
         st = cnx.createStatement();
        
         String requeteInsert ="UPDATE `cord` SET `lieu`='"+a.getLieu()+"',longitude='"+ a.getLongitude()+"',latitude='"+a.getLatitude()+"WHERE `id` = '"+a.getId()+"'";
         if(st.executeUpdate(requeteInsert) == 1){
            System.out.println("modification effectué");
            return true;
        };
        System.out.println("modification non effectué");
        return false;
    }
    
     public ObservableList<String>  ReadNameLieu() {

        ObservableList<String> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT c.lieu FROM cord c";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               

                
               String lieu = res.getString(1);
               
                listP.add(lieu);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
       public ObservableList<String>  NomLieu(String location) {

        ObservableList<String> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT  UPPER(c.lieu) FROM cord c where UPPER(c.lieu) =UPPER('"+location+"')";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               

                
               String lieu = res.getString(1);
               
                listP.add(lieu);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
      public ObservableList<Cord>  ReadLieuCapacite() {

        ObservableList<Cord> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT c.lieu , co.capacite, co.nom FROM cord c, camp co where co.lieu = c.id ORDER BY co.capacite desc";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               
                  int capacite = res.getInt(2);
                String nom = res.getString(1);
               String lieu = res.getString(3);
               Cord c = new Cord(capacite,lieu,nom);
                listP.add(c);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
      
      public ObservableList<Cord>  ReadLieuCapacite(String lieuName) {

        ObservableList<Cord> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT c.lieu , co.capacite , co.nom FROM cord c, camp co where co.lieu = c.id and c.lieu = '"+lieuName+"'";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               

               int capacite = res.getInt(2);
                String nom = res.getString(1);
               String lieu = res.getString(3);
               Cord c = new Cord(capacite,lieu,nom);
                listP.add(c);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
    
    public int ReadIdLieu(String nom)throws SQLException{
        int id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select co.id FROM cord co where co.lieu ='" +nom+"'"); 
        while (rs.next()) {       
               id =rs.getInt(1);
        }
        return id;
    }
    public ObservableList<Cord>  ReadLieuCord(String lieuName) {

        ObservableList<Cord> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT c.lieu , c.longitude, c.latitude FROM cord c where c.lieu = '"+lieuName+"'";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               
 int latitude = res.getInt(3);
               int longitude = res.getInt(2);
               String lieu = res.getString(1);
               Cord c = new Cord(lieu,longitude,latitude);
                listP.add(c);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
    
    
}
