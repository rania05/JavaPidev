/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionCamp.Entities;

import java.util.Objects;

/**
 *
 * @author Rania
 */
public class don {
    private int id;
    private String volentaire;
    private String objet;
   
    private String description;
    private int quantite;

    public don(int id, String volentaire, String objet,  String description, int quantite) {
        this.id = id;
        this.volentaire = volentaire;
        this.objet = objet;
       
        this.description = description;
        this.quantite = quantite;
    }

    public don(String volentaire, String objet,  String description, int quantite) {
        this.volentaire = volentaire;
        this.objet = objet;
       
        this.description = description;
        this.quantite = quantite;
    }

    
    public don() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVolentaire() {
        return volentaire;
    }

    public void setVolentaire(String volentaire) {
        this.volentaire = volentaire;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.volentaire);
        hash = 41 * hash + Objects.hashCode(this.objet);
       
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + this.quantite;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final don other = (don) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.volentaire, other.volentaire)) {
            return false;
        }
        if (!Objects.equals(this.objet, other.objet)) {
            return false;
        }
       
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    
}
