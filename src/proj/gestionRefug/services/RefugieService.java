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
import proj.gestionRefug.entities.Refugie;
import proj.gestionRefug.utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class RefugieService {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public RefugieService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    
    
    
    public void ajouterRefugie(Refugie R){
        try{
            String req="INSERT INTO Refugie(nom,prenom,age,origine,idcamp) VALUES "
                    +"('" + R.getNom() + "', '" + R.getPrenom() +"', '" +R.getAge()+"', '" +R.getOrigine()+"', '" +R.getIdcamp()+ "')";
       
             st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
    }
     public List<String> listeCamp()
   {ArrayList<String> list = new ArrayList<>() ;
String ch="";
         try {
            Statement st=cnx.createStatement();
            String req="Select nomCamp from camps";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             ch=rs.getString(1); 
           System.out.println(ch);
            list.add(ch);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
   }
   public int getIdCamps(String nom)
   {
   int x=0;
         try {
            Statement st=cnx.createStatement();
            String req="Select idCamp from camps where nomCamp ='"+nom+"'";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             x=rs.getInt(1); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x ;
   
   }
     public void modifierRefugie(String nom, String prenom, int age,String origine, int camp){
      
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update Refugie set nom= ?, prenom=?, age=?, origine=? , idcamp=? where nom=? ");
            pt.setString(1, nom); 
            pt.setString(2, prenom);
            pt.setInt(3, age);
            pt.setString(4,origine);
            pt.setInt(5, camp); 
           pt.setString(6, nom); 
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }  
    
    public String getNomCamp(int id)
    {String x="";
         try {
            Statement st=cnx.createStatement();
            String req="Select nomCamp from camps where idCamp ='"+id+"'";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             x=rs.getString(1); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x ;
    }
    
    
     public ArrayList<Refugie> afficherRefugie(){
          ArrayList<Refugie> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select r.nom,r.prenom,r.age,r.origine,c.idcamp,c.nomCamp from refugie r INNER JOIN camps c on r.idcamp = c.idCamp ";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              Refugie r = new Refugie(
                      rs.getString(1),
                      rs.getString(2),
                      rs.getInt(3),
                      rs.getString(4),
                      rs.getInt(5));
                      r.setNomCamp(rs.getString(6));
            list.add(r);            
            }
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
    public  void supprimerRefugie(String nom){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from refugie where nom=? ");
            pt.setString(1, nom);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    
   
    
    
    
    
    
}
