/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.services;

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
import proj.gestionRefug.entities.RefConsult;
import proj.gestionRefug.entities.Refugie;
import proj.gestionRefug.utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class ConsultationService {
       private Connection cnx;
   // private Statement st;
    private PreparedStatement st;

    public ConsultationService() {
          cnx = MyConnection.getInstance().getCnx();
    }

    
     
    public void ajouterConsultation(RefConsult RC){
        try{
            String req="INSERT INTO Ref_consult(idref,sujet,contenu,date,duree) VALUES "
                    +"('" + RC.getIdref() + "', '" + RC.getSujet() +"', '"+RC.getContenu()+"',NOW(), '" +RC.getDuree()+ "')"; 
            st = cnx.prepareStatement(req); 
            st.executeUpdate();
            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
    }
    

        public List<String> listeRefugie()
   {ArrayList<String> list = new ArrayList<>() ;
String ch="";
         try {
            Statement st=cnx.createStatement();
            String req="Select prenom from refugie";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             ch=rs.getString(1); 
           System.out.println(ch);
            list.add(ch);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
   }
    
     public int getIdRefugie(String prenom)
   {
   int x=0;
         try {
            Statement st=cnx.createStatement();
            String req="Select id from refugie where prenom ='"+prenom+"'";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             x=rs.getInt(1); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x ;
   
   }
    
    public  void supprimerConsult(String sujet){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from Ref_consult where sujet=? ");
            pt.setString(1, sujet);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    
    
       public String getPrenomRefugie(int id)
    {String x="";
         try {
            Statement st=cnx.createStatement();
            String req="Select prenom from refugie where id ='"+id+"'";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             x=rs.getString(1); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x ;
    }
    
    
    
     public ArrayList<RefConsult> affConsult(){
          ArrayList<RefConsult> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select r.id,rc.sujet,rc.contenu,rc.date,rc.duree,r.prenom from Ref_consult rc INNER JOIN refugie r on rc.idref=r.id ";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              RefConsult r = new RefConsult(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getDate(4),
                      rs.getTime(5));                      
              r.setPrenom(rs.getString(6));

            list.add(r);            
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
    
     
       public ObservableList<RefConsult> rechercheConsultation(String recherche) throws SQLException {
        ObservableList<RefConsult> list = FXCollections.observableArrayList();
        String requete = "Select r.id,rc.sujet,rc.contenu,rc.date,rc.duree,r.prenom from Ref_consult rc INNER JOIN refugie r on rc.idref=r.id WHERE r.prenom LIKE '%"+recherche+"%' OR rc.sujet LIKE '%"+recherche+"%' OR rc.date LIKE '%"+recherche+"%' Or rc.duree LIKE '%"+recherche+"%' or rc.contenu LIKE '%"+recherche+"%' ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 RefConsult r = new RefConsult(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getString(3),
                      rs.getDate(4),
                      rs.getTime(5));                      
              r.setPrenom(rs.getString(6));
            list.add(r);          
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
     }
     
     
      public void modifierConsultation( int id,String sujet, String contenu){
      
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update Ref_consult set idref= ?, sujet=?, contenu=? where sujet=? ");
            pt.setInt(1, id); 
            pt.setString(2, sujet);
            pt.setString(3, contenu);
           pt.setString(4, sujet); 
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }  
    
    
    
}
