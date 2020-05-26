/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionEvent.Service;

import proj.gestionEvent.Entite.Event;
import proj.gestionRefug.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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

//import org.controlsfx.control.Notifications;

//import weboss.BD.Database;
//import weboss.Entities.Evenement;

/**
 *
 * @author Nesrine
 */
public class EventService {
     
    private ResultSet rs;
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public EventService() {
        cnx = MyConnection.getInstance().getCnx();
    }


      public void ajouter(Event e) throws SQLException{
       /*						
        PreparedStatement pre = cnx.prepareStatement("INSERT INTO `event` (`id`,`titre`,`affiche`,`Lieu`,`date`,`lieu`,`description`)  VALUES (?,?,?,?,?,?,?) ;");
        pre.setInt(1, e.getId());
        pre.setString(2, e.getTitre());
        pre.setString(3, e.getAffiche());
        pre.setString(4, e.getDescription());
        pre.setFloat(5, e.getPrix());
        pre.setDate(6, new java.sql.Date(System.currentTimeMillis()));

        pre.setString(7,e.getLieu());

           pre.executeUpdate();
        System.out.println("event ajoutée !!"); */
       
     /*    String req = "INSERT INTO Event (id,titre,affiche,Lieu,date,lieu,description) values(?,?,?,?,?,?,?)";
        pre = cnx.prepareStatement(req);
       //   pre.setInt(1, e.getId());
        pre.setString(1, e.getTitre());
        pre.setString(2, e.getAffiche());
        pre.setString(6, e.getDescription());
        pre.setFloat(5, e.getPrix());
       pre.setDate(4, new java.sql.Date(System.currentTimeMillis()));

        pre.setString(3,e.getLieu());
       
        pre.execute();
     System.out.println("event ajoutée !!");   
*/
        try{
            String req="INSERT INTO Event(titre,affiche,description,prix,date ,lieu) VALUES "
                    +"('" + e.getTitre() + "', '" + e.getAffiche() +"', '" +e.getDescription()+"', '" +e.getPrix()+"', '" +e.getDate()+ "', '"+e.getLieu()+   "')";
       
             st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
    
     
     

    }
    


    
   /* 
        @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `Evenement` WHERE `Evenement`.`idEvenement` = " + id + "";
        pst = cnx.prepareStatement(req);

        pst.execute();
    }
    */


     

    public ObservableList<Event> getAll() {
       ObservableList <Event> list = FXCollections.observableArrayList();
        String req="select * from Event";
          
        try {
            st=cnx.createStatement();
            
            rs=st.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String titre =rs.getString("titre");
                
               String affiche =rs.getString("affiche");
              
               Float prix=rs.getFloat("prix");
              
               String description = rs.getString("description");
               Date dt=rs.getDate("date");
               String lieu = rs.getString("lieu");
        
              Event A = new Event(Id,titre, affiche,description ,prix,dt, lieu);
                
      list.add(A);
  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return list;   
    }
      public ObservableList<Event> getAllTRR() {
       ObservableList <Event> list = FXCollections.observableArrayList();
        String req="select * from Event Order by titre ASC";
          
        try {
            st=cnx.createStatement();
            
            rs=st.executeQuery(req);
            while(rs.next()){
               int Id= rs.getInt("id");
               String titre =rs.getString("titre");
                
               String affiche =rs.getString("affiche");
              
               Float prix=rs.getFloat("prix");
              
               String description = rs.getString("description");
                Date dt=rs.getDate("date");
               String lieu = rs.getString("lieu");
        
              Event A = new Event(Id,titre, affiche,description ,prix,dt, lieu);
                
      list.add(A);
  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return list;   
    }
 
    
    

   public void delete(Event t) {
        String req="delete from Event where id = '"+t.getId()+"'";

          try {
             st=cnx.createStatement();
            st.executeUpdate(req);
           
        } catch (SQLException ex) {
       Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
        
          
     public ObservableList<Event> listerRecherche(String recherche) {
       ObservableList <Event> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM Event WHERE id like '%" + recherche + "%' or titre  like '%" + recherche + "%' or affiche  like '%" + recherche + "%'or prix  like '%" + recherche + "%'or description  like '%" + recherche + "%'or date  like '%" +recherche+ "%' or lieu  like '%" + recherche+"%' ";
         
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while(rs.next()){
                  int Id= rs.getInt("id");
               String titre =rs.getString("titre");
                
               String affiche =rs.getString("affiche");
              
               Float prix=rs.getFloat("prix");
              
               String description = rs.getString("description");
               Date dt=rs.getDate("date");
               String lieu = rs.getString("lieu");
        
              Event A = new Event(Id,titre, affiche,description ,prix,dt, lieu);
                
               list.add(A);
               
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
         
         return list;   
    }  
    

   

    
    /* public ObservableList<Enseignant> recherche(String nom) throws SQLException {

        ObservableList<Enseignant> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from users where roleUser='Enseignant' and nomUser like '%"+nom+"%'");
        while (rs.next()) {
            System.out.println(rs.getString("idUser"));
            String idUser = rs.getString("idUser");
            int cinUser = rs.getInt("cinUser");
            String nomUser = rs.getString("nomUser");
            String prenomUser = rs.getString("prenomUser");
            Date dateNaissanceUser = rs.getDate("dateNaissanceUser");
            String sexeUser = rs.getString("sexeUser");
            String emailUser = rs.getString("emailUser");
            String adresseUser = rs.getString("adresseUser");
            int numTelUser = rs.getInt("numTelUser");           
            String motDePasseUser = rs.getString("motDePasseUser");
            String roleUser = rs.getString("roleUser");
            String statutEnsg = rs.getString("statutUser");
            double salaireEnsg = rs.getDouble("salaireUser");
            Date dateEmbaucheEnsg = rs.getDate("dateEmbaucheUser");
            String domaineEnsg = rs.getString("domaineUser");
            String picUser = rs.getString("picUser");
           

            Enseignant u = new Enseignant(idUser, cinUser, nomUser, prenomUser, emailUser, adresseUser, numTelUser, dateNaissanceUser, sexeUser, motDePasseUser,roleUser,picUser, statutEnsg,salaireEnsg,dateEmbaucheEnsg,domaineEnsg);

            arr.add(u);
        }
        return arr;
    } 
*/
    
    
    
    
        public  void supprimerConsult( int id){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from Event where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    
        

        
      public void modifierev( Event ev,int id ){
      
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update Event set titre=?, affiche=?, description=?,prix=?, date=?, lieu=? where id=? ");
          //  pt.setInt(1, id); 
            pt.setString(1, ev.getTitre());
            pt.setString(2, ev.getAffiche());
           pt.setString(3, ev.getDescription()); 
           pt.setFloat(4, ev.getPrix());
           pt.setDate(5, ev.getDate());
            pt.setString(6, ev.getLieu());
            
     pt.setInt(7, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }  
      
      
      
      
      
       public List<Integer> getState() {
        String req11 = "Select id from Event";
        List<Integer> liste = new ArrayList<Integer>();
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req11);

            while (rs.next()) {

                liste.add(rs.getInt(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public String getState1(int x) {
        String g = "";
      String req11 = "Select lieu  From Event where id=? ";
        //String req11 = "SELECT  lieu  as l ,COUNT(lieu) as ql FROM Event  GROUP BY lieu ";

        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req11);

            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g = rs.getString(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    } 
  
  /*   public ObservableList<Event>  StatBesoins( ) {
                    System.out.println(Name);
        ObservableList<Event> listP = FXCollections.observableArrayList();

        try {
          

            String req = "Select lieu  From Event ";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
               
               
                String lieu = res.getString(1);
               
                Event c = new Event(lieu);
                
                listP.add(c);
            }
            
            System.out.println(listP);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listP;
    }*/

    public Integer getState12(int x) {
        int g = 0;
       String req11 = "Select lieu From Event where id=?  ";
//String req11 = "SELECT  lieu  as l ,COUNT(lieu) as ql FROM Event  GROUP BY lieu ";
 // String req11 = "Select lieu  From Event ";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req11);

            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }
      
      
      
      
      
} 
