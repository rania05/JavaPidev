/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionEvent.Entite;

import proj.gestionRefug.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Nesrine
 */
public class Event {


      private int id;
       private String titre;
       private String affiche;
       private String description;
       private float prix;
       private Date date;
       private String lieu; 

   public Event(int id, String titre, String affiche, String description, Float prix,String lieu,Date date ) {
        this.id = id;
        this.titre = titre;
        this.affiche = affiche;
        this.description = description;
        this.prix = prix;
        this.date = date;
        this.lieu = lieu;
    }
    
    
       public Event() {
       
  }

         
     public Event( String lieu) {
     
        this.lieu = lieu;
    }       
      
    public Event(String titre, String affiche, String description, int prix, Date date, String lieu) {
      
    
       this.titre = titre;
        this.affiche = affiche;
        this.description = description;
        this.prix = prix;
         this.date = date;
        this.lieu = lieu;
    }

    public Event(String titre, String affiche, String description, float prix, String lieu) {
        this.titre = titre;
        this.affiche = affiche;
        this.description = description;
        this.prix = prix;
        this.lieu = lieu;
    }

   

    public Event(int Id, String titre, String affiche, String description, Float prix, Date dt, String lieu) {
         this.id=Id;
    
       this.titre = titre;
        this.affiche = affiche;
        this.description = description;
        this.prix = prix;
         this.date = dt;
        this.lieu = lieu;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", titre=" + titre + ", affiche=" + affiche + ", description=" + description + ", prix=" + prix + ", date=" + date + ", lieu=" + lieu + '}';
    }

   

}
