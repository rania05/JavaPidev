/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Cassociale {
    private int id;
    private String lieu;
    private String date;

    public Cassociale(int id, String lieu, String date) {
        this.id = id;
        this.lieu = lieu;
        this.date = date;
    }

      public Cassociale( String lieu, String date) {
      
        this.lieu = lieu;
        this.date = date;
    }
    public Cassociale() {
                        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Cassociale other = (Cassociale) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cassociale{" + "lieu=" + lieu + ", date=" + date + '}';
    }
    
    
}
