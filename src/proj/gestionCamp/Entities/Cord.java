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
public class Cord {
    private int id;
    private int cap;
    private String NomCamps;
    private String lieu;
    private double latitude;
    private double longitude;

    public Cord(String lieu, double latitude, double longitude) {
        this.lieu = lieu;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Cord(int capacite, String NomCamps, String lieu) {
        this.cap = capacite;
        this.lieu = lieu;
        this.NomCamps=NomCamps;
    }
 public String getNomCamps() {
        return NomCamps;
    }
    
    public Cord(int cap, String lieu) {
        this.cap = cap;
        this.lieu = lieu;
    }
    
    public Cord() {
    }

    public Cord(int id, String lieu, double latitude, double longitude) {
        this.id = id;
        this.lieu = lieu;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }
    public String getIDD() {
        return Integer.toString(id);
    }
public String getLong() {
        return Double.toString(longitude);
    }
public String getLat() {
        return Double.toString(latitude);
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }
    
    public String getCapaciteS() {
       return Integer.toString(cap);
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.lieu);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
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
        final Cord other = (Cord) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        return true;
    }

    

   
    
}
