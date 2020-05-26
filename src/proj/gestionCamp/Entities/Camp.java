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
public class Camp {
    private int id;
    private int lieu;
    
      private String NomCamp;

    public Camp(int lieu, String NomCamp, int capacite) {
              
        this.lieu = lieu;
        this.NomCamp = NomCamp;
        this.capacite = capacite;
       
    }

    public String getNomCamp() {
        return NomCamp;
    }

    public Camp(int id, int lieu, String NomCamp, int capacite) {
        this.id = id;
        this.lieu = lieu;
        this.NomCamp = NomCamp;
        this.capacite = capacite;
    }

    public void setNomCamp(String NomCamp) {
        this.NomCamp = NomCamp;
    }
    private int capacite;

    public Camp() {
    }

    public Camp(int id, int lieu, int capacite) {
        this.id = id;
        this.lieu = lieu;
        this.capacite = capacite;
    }
    
    public Camp( int lieu, int capacite) {
        
        this.lieu = lieu;
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Camp{" + "id=" + id + ", lieu=" + lieu + ", capacite=" + capacite + '}';
    }
    

    public int getId() {
        return id;
    }

    public int getLieu() {
        return lieu;
    }
    
    public String getLieuS() {
        return Integer.toString(lieu);
    }

    public int getCapacite() {
        return capacite;
    }
    
     public String getCapaciteS() {
        return Integer.toString(capacite);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLieu(int lieu) {
        this.lieu = lieu;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.lieu);
        hash = 89 * hash + this.capacite;
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
        final Camp other = (Camp) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.capacite != other.capacite) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        return true;
    }
    
}
