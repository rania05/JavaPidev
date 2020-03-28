/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ASUS
 */
public class RefConsult {
    
    private int id;
    private int idref;
    private String sujet;
    private String contenu;
    private Date date;
    private Time duree; 
    private String Medicament;
    private String prenom;

    public RefConsult() {
    }

    public RefConsult(int idref, String sujet, String contenu, Time duree) {
        this.idref = idref;
        this.sujet = sujet;
        this.contenu = contenu;
        this.duree = duree;
    } 

    public RefConsult(int idref, String sujet, String contenu, Date date, Time duree) {
        this.idref = idref;
        this.sujet = sujet;
        this.contenu = contenu;
        this.date = date;
        this.duree = duree;
    }

    public RefConsult(int idref, String sujet, String contenu) {
        this.idref = idref;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public int getIdref() {
        return idref;
    }

    public String getSujet() {
        return sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return date;
    }

    public Time getDuree() {
        return duree;
    }

    public String getMedicament() {
        return Medicament;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdref(int idref) {
        this.idref = idref;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public void setMedicament(String Medicament) {
        this.Medicament = Medicament;
    }

    @Override
    public String toString() {
        return "RefConsult{" + "id=" + id + ", idref=" + idref + ", sujet=" + sujet + ", contenu=" + contenu + ", date=" + date + ", duree=" + duree + ", Medicament=" + Medicament + '}';
    }
    
    
    
    
    
}
