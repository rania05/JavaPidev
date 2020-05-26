/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.Entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Rania
 */
public class affectation {
    private int id;
    private String camp;
    private String categorie;
    private  int quantite ;
    private Timestamp date;

    public affectation() {
       
    }

    

   

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Timestamp getDate() {
        return date;
    }

      public String getDateAff() {
        return date.toString();
    }
      public String getQuantity() {
        return Integer.toString(quantite);
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public affectation(String camp, String categorie, int quantite) {
        Date dateNow = new Date();
        this.camp = camp;
        this.categorie = categorie;
        this.quantite = quantite;
        this.date = new Timestamp(dateNow.getTime());
    }
    
    
    
    
}
