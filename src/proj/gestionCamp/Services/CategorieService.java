/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.Services;

import proj.gestionCamp.Entities.Camp;
import proj.gestionCamp.Entities.Categorie;
import proj.gestionCamp.Entities.Cord;
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
public class CategorieService {
    
       
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public CategorieService() {
        cnx = MyConnection.getInstance().getCnx();
    }
 public void AjouterCategorie(Categorie c) {
        try {
            String req = "INSERT INTO categorie (nom) VALUES ( ?)";

            pre = cnx.prepareStatement(req);

            pre.setString(1, c.getNom());
        

            pre.executeUpdate();

            System.out.println("Insertion  categorie Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     public int ReadIdCat (String nom)throws SQLException{
        int id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select co.id FROM categorie co where co.nom ='" +nom+"'"); 
        while (rs.next()) {       
               id =rs.getInt(1);
        }
        return id;
    }
 
      public void ModifierrCategorie(Categorie c) {
            try {
                System.out.println(c);
              st = cnx.createStatement();
              String requeteInsert ="UPDATE `categorie` SET `nom`='"+c.getNom()+"' WHERE `id` = '"+c.getId()+"'";
              if(st.executeUpdate(requeteInsert) == 1){
                  System.out.println("modification effectué");
              }} catch (SQLException ex) {
                System.out.println(ex);
            }
       }
       public ObservableList<Categorie> afficherAll() {

       ObservableList<Categorie> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT * FROM categorie";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Categorie p = new Categorie();

                p.setId(res.getInt(1));
                p.setNom(res.getString(2));
               

                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
     
 public boolean delete(Categorie a) throws SQLException {
        st = cnx.createStatement();
        String requeteInsert = "DELETE FROM `categorie` WHERE `id` = '"+a.getId()+"'";
        if(st.executeUpdate(requeteInsert) == 1){
            System.out.println("Suppression effectué");
            return true;
        };
        System.out.println("Suppression non effectué");
        return false;
    }
    public ObservableList<Categorie>  ReadCat(String name) {

        ObservableList<Categorie> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT c.nom from categorie c where c.nom = '"+name+"'";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               

               String nom = res.getString(1);
               Categorie c = new Categorie(nom);
                listP.add(c);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
    

     
         public ObservableList<String>  ReadCat() {

        ObservableList<String> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT c.nom FROM categorie c";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               

                
               String nom = res.getString(1);
               
                listP.add(nom);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
     
}
