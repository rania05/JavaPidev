/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.Services;

import proj.gestionUser.IServices.ICassocialeService;
import proj.gestionUser.entities.Cassociale;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proj.gestionUser.utils.ConnexionBD;

/**
 *
 * @author Asus
 */
public class CassocialeService implements ICassocialeService {


    
    Connection cn = ConnexionBD.getinstance().getcnx();
    PreparedStatement pt;
    ResultSet rs;
    
    
    
    

    @Override
    public void addCas(Cassociale cs) throws SQLException {
        
        String req = "INSERT INTO cassociale (lieu,Date) VALUES (?,?)";
        try {
            pt = cn.prepareStatement(req);
          pt.setString(1, cs.getLieu());
            pt.setString(2, cs.getDate());
                        
            pt.executeUpdate();
            System.out.println("ajout etablie");

        } catch (SQLException ex) {

        }
       
    }

    @Override
    public List<Cassociale> getAllCas() throws SQLException {
         List<Cassociale> list = new ArrayList();
        Connection cnx = ConnexionBD.getinstance().getcnx();
   
        try {
            String sql = "select * from cassociale ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cassociale cs = new Cassociale();

                cs.setLieu(resultSet.getString("lieu"));
                cs.setDate(resultSet.getString("date"));
               
                list.add(cs);
                cs.toString();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;

    }


    @Override
    public void deleteCas(String lieu) throws SQLException {
         
          pt = cn.prepareStatement("delete * from cassociale where (lieu = ?)" );
       
         try {
          
            pt.setString(1,lieu);
            pt.executeUpdate();
             
        } catch (SQLException ex) {
        }

    }

   

    public void updateCas(Cassociale c) throws SQLException {
        Connection cnx = ConnexionBD.getinstance().getcnx();
      //  ResultSet rs;
       //PreparedStatement pst ;
       // String req = "UPDATE user set nom = ? ,prenom=?,age = ?, email=?, numero = ?, Role = ? WHERE id=?";

        try {
           
PreparedStatement pst = cnx.prepareStatement("UPDATE cassociale set lieu = ? ,date= ? WHERE id=?");

            //pst = cnx.prepareStatement(req);
            pst.setString(1,c.getLieu());
            pst.setString(2, c.getDate());
            pst.setInt(3,c.getId());
            
            
            
            pst.execute();
             
        } catch (SQLException se) {

        }    
    }
    
    
 @Override
    public List<Cassociale> RechercherCas(String lieu) throws SQLException {
        List<Cassociale> list = new ArrayList();
        Connection cnx = ConnexionBD.getinstance().getcnx();
   
        try {
            String sql = "select * from cassociale where lieu LIKE ? ";
            String ch="%"+lieu+"%";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setString(1,ch);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cassociale cs = new Cassociale();

                cs.setLieu(resultSet.getString("lieu"));
                cs.setDate(resultSet.getString("date"));
               
                list.add(cs);
                cs.toString();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

public static void supprimer(String lieu) {
Connection cnx = ConnexionBD.getinstance().getcnx();
        PreparedStatement pt;

        String req = "delete from cassociale where lieu =?";
        try {
            pt = cnx.prepareStatement(req);
            pt.setString(1, lieu);
            pt.executeUpdate();
          System.out.println("Suppression terminé avec succes ");
           
        } catch (SQLException ex) {
            Logger.getLogger(CassocialeService.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("not ok");
        }    }   



public static List<Cassociale> getAllsoc() {
        List<Cassociale> list = new ArrayList();
        Connection cnx = ConnexionBD.getinstance().getcnx();
   
        try {
            String sql = "select * from user ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cassociale utilisateur = new Cassociale();

                utilisateur.setLieu(resultSet.getString("nom"));
                utilisateur.setDate(resultSet.getString("prenom"));
              
                list.add(utilisateur);
                utilisateur.toString();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
     }
    
    
    
    /*@Override
    public List<Cassociale> RechercherCas(String lieu) throws SQLException {
        String requete="select * FROM cassociale where (lieu LIKE ? )";
      
        String ch="%"+lieu;
        ArrayList<Cassociale> myList = new ArrayList();
        try {
            
             
        
             pt.setString(1,ch);
              
            
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                Cassociale a = new Cassociale();
                a.setLieu(rs.getString(2)); 
                a.setDate(rs.getDate(3));
                
              
                
           
                myList.add(a);
                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }    */

    @Override
    public void updateCas(String lieu, java.util.Date date, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    



