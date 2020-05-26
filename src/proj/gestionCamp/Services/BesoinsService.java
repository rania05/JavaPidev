/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.Services;

import proj.gestionCamp.Entities.Besoins;
import proj.gestionCamp.Entities.Cord;

import proj.gestionRefug.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rania
 */
public class BesoinsService {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    
     public BesoinsService() {
        cnx = MyConnection.getInstance().getCnx();
    }
     
     public void AjouterBesoin(Besoins b) {
         System.out.println(b.getId());

        try {
            String req = "INSERT INTO besoins (id_c,nom, quantite) VALUES (?, ?,?)";

            pre = cnx.prepareStatement(req);
 pre.setInt(1, b.getId_c());
            pre.setInt(2, b.getNom_bs());
            pre.setInt(3, b.getQuantite());

            pre.executeUpdate();
 
            System.out.println("Insertion 2 Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     
     public List<Besoins> afficherAll() {

        List<Besoins> listP = new ArrayList<>();

        try {

            String req = "SELECT * FROM besoins";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                Besoins p = new Besoins();

                p.setId(res.getInt(1));
                p.setNom_bs(res.getInt(2));
                p.setQuantite(res.getInt(3));

                listP.add(p);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
     public ObservableList<Besoins>  ReadBesoinet() {

        ObservableList<Besoins> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT  b.id, co.lieu , ct.nom , b.quantite FROM cord co ,categorie ct ,besoins b , camp c where b.id_c=c.Id and ct.id =b.nom AND c.lieu = co.id ";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               
int id =res.getInt(1);
               int quantite = res.getInt(4);
               String lieuC = res.getString(2);
               String Cat = res.getString(3);
               
               
               Besoins b = new Besoins(id,lieuC,Cat,quantite);
                
                listP.add(b);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
      public void ModifierBesoins(Besoins c) {
            try {
                System.out.println(c);
              st = cnx.createStatement();
              String requeteInsert ="UPDATE `besoins` SET `id_c`='"+c.getId_c()+"',`nom`='"+c.getNom_bs()+"',`quantite`='"+c.getQuantite()+"' WHERE `id` = '"+c.getId()+"'";
              if(st.executeUpdate(requeteInsert) == 1){
                  System.out.println("modification effectué");
              }} catch (SQLException ex) {
                System.out.println(ex);
            }
       }
      public boolean deleteBs(Besoins c) throws SQLException {
        st = cnx.createStatement();
        String requeteInsert = "DELETE FROM `besoins` WHERE `id` = '"+c.getId()+"'";
        if(st.executeUpdate(requeteInsert) == 1){
            System.out.println("Suppression effectué");
            return true;
        };
        System.out.println("Suppression non effectué");
        return false;
    }
        public void QuantiteUpdateBesoins(Besoins c,int q ,int qnt) {
            try {
                System.out.println(c);
              st = cnx.createStatement();
             
              String requeteInsert ="UPDATE `besoins` SET quantite= "+ q +" WHERE id = "+c.getId()+" and (SELECT d.objet FROM dons d WHERE d.objet = (SELECT dd.objet FROM dons dd , categorie c WHERE dd.objet = c.id AND c.id ="+ c.getNom_bs()+" and dd.quantite >="+qnt+"))";
                System.out.println(requeteInsert);
              if(st.executeUpdate(requeteInsert) == 1){
                  System.out.println("modification effectué");
              }} catch (SQLException ex) {
                System.out.println(ex);
            }
       }
         
          public int  QuantiteDon(String name) throws SQLException {
            
               
             int id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select d.quantite from dons d , categorie c where d.objet =c.id and c.nom='"+name+"'");
               
              while (rs.next()) {       
               id =rs.getInt(1);
        }
        return id;
       }
          
        
        public void QuantiteUpdateDon(Besoins c,int q ) {
            try {
                System.out.println(c);
              st = cnx.createStatement();
             
              String requeteInsert ="UPDATE dons dd  SET dd.quantite=dd.quantite-"+q+"  WHERE dd.objet="+c.getNom_bs()+"";
                System.out.println(requeteInsert);
              if(st.executeUpdate(requeteInsert) == 1){
                  System.out.println("modification effectué");
              }} catch (SQLException ex) {
                System.out.println(ex);
            }
       }
        public int ReadIdCat(String nom)throws SQLException{
            System.out.println(nom);
        int id = -1;
        st=cnx.createStatement();
        ResultSet rs=st.executeQuery("select b.nom from   besoins b, categorie  c where c.id = b.nom  and c.nom='" +nom+"'"); 
        while (rs.next()) {       
               id =rs.getInt(1);
        }
        return id;
    }
        
            public ObservableList<Besoins>  ReadBesoins(int  Name ) {

        ObservableList<Besoins> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT c.lieu , ct.nom , b.quantite FROM besoins b , cord c , categorie ct , camp cm where  b.id_c= cm.id and cm.lieu = c.id and b.nom = ct.id and  b.nom= '"+Name+"'ORDER BY b.quantite DESC";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               
               int quantite = res.getInt(3);
               String Cat = res.getString(2);
               String lieuC = res.getString(1);
               Besoins c = new Besoins(lieuC,Cat,quantite);
                listP.add(c);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
             public ObservableList<Besoins>  StatBesoins(int  Name ) {
                    System.out.println(Name);
        ObservableList<Besoins> listP = FXCollections.observableArrayList();

        try {

            String req = "SELECT co.lieu,b.quantite FROM besoins b , cord co , camp c where b.id_c=c.id and c.lieu=co.id and b.nom= '"+Name+"'";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               
                int quantite = res.getInt(2);
                String lieuC = res.getString(1);
               
                Besoins c = new Besoins(lieuC,quantite);
                
                listP.add(c);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }
}
