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
public class Besoins {
    private int id;
     private int id_c;
    private  int nom_bs;
    private String lieuC;
    private String Cat;
   
    private int quantite;

    public Besoins() {
    }

    public Besoins(int id,  String lieuC, String Cat, int quantite) {
        this.id = id;
       
        this.lieuC = lieuC;
        this.Cat = Cat;
        this.quantite = quantite;
    }
     public Besoins(  String lieuC, String Cat, int quantite) {
     
       
        this.lieuC = lieuC;
        this.Cat = Cat;
        this.quantite = quantite;
    }
    
   public Besoins(String lieuC,  int quantite) {
        this.lieuC = lieuC;
        this.quantite = quantite;
    }
    

    public Besoins(int id_c, int nom_bs, int quantite) {
        this.id_c = id_c;
        this.nom_bs = nom_bs;
       
        this.quantite = quantite;
    }

    public Besoins(int id, int id_c, int nom_bs, int quantite) {
        this.id = id;
        this.id_c = id_c;
        this.nom_bs = nom_bs;
      
        this.quantite = quantite;
    }

    public String getLieuC() {
        return lieuC;
    }
 public String getIDB() {
        return Integer.toString(id);
    }
    public void setLieuC(String lieuC) {
        this.lieuC = lieuC;
    }

    public String getCat() {
        return Cat;
    }

    public void setCat(String Cat) {
        this.Cat = Cat;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNom_bs() {
        return nom_bs;
    }
      public String getNom_bss() {
        return Integer.toString(nom_bs);
    }
        public String getId_cc() {
        return Integer.toString(id_c);
    }
         public String getQuantitee() {
        return Integer.toString(quantite);
    }

    public void setNom_bs(int nom_bs) {
        this.nom_bs = nom_bs;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Besoins{" + "id=" + id + ", id_c=" + id_c + ", nom_bs=" + nom_bs +  ", quantite=" + quantite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.id_c;
        hash = 97 * hash + Objects.hashCode(this.nom_bs);
    
        hash = 97 * hash + this.quantite;
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
        final Besoins other = (Besoins) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_c != other.id_c) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.nom_bs, other.nom_bs)) {
            return false;
        }
       
        return true;
    }

    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
