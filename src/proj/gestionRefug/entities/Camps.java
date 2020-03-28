/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionRefug.entities;

/**
 *
 * @author ASUS
 */
public class Camps {
    private int idcamp;
    private int nbrmax;
    private String nomCamp;
    private String adresse; 

    public Camps(int nbrmax, String nomCamp, String adresse) {
        this.nbrmax = nbrmax;
        this.nomCamp = nomCamp;
        this.adresse = adresse;
    }

    public Camps(int idcamp, int nbrmax, String nomCamp, String adresse) {
        this.idcamp = idcamp;
        this.nbrmax = nbrmax;
        this.nomCamp = nomCamp;
        this.adresse = adresse;
    }

    public int getIdcamp() {
        return idcamp;
    }

    public int getNbrmax() {
        return nbrmax;
    }

    public String getNomCamp() {
        return nomCamp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setIdcamp(int idcamp) {
        this.idcamp = idcamp;
    }

    public void setNbrmax(int nbrmax) {
        this.nbrmax = nbrmax;
    }

    public void setNomCamp(String nomCamp) {
        this.nomCamp = nomCamp;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Camps{" + "idcamp=" + idcamp + ", nbrmax=" + nbrmax + ", nomCamp=" + nomCamp + ", adresse=" + adresse + '}';
    }
    
    
    
    
    
    
    
}
