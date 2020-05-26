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
public class Medicaments {
    private int id;
    private String nomM;

    public Medicaments() {
    }

    public Medicaments(String nomM) {
        this.nomM = nomM;
    }

    public Medicaments(int id, String nomM) {
        this.id = id;
        this.nomM = nomM;
    }
    

    @Override
    public String toString() {
        return "Medicaments{" + "id=" + id + ", nomM=" + nomM + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomM() {
        return nomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nomM);
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
        final Medicaments other = (Medicaments) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomM, other.nomM)) {
            return false;
        }
        return true;
    }
    
}
