/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.entities;


/**
 *
 * @author USER
 */
public class Utilisateur {
    private  int id;
    private  String username;
    
   
    
    private  String email;
   
    
    private  String password;
    private  String roles;
    private  int enable;
    
     private  String nom;
    private  String prenom;

    public Utilisateur(int id, String username, String email, int enable, String nom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enable = enable;
        this.nom = nom;
    }
 public Utilisateur(int id, String username, String password,String email, int enable, String nom , String prenom, String roles) {
        this.id = id;
        this.username = username;
         this.password = password;
        this.email = email;
        this.enable = enable;
         this.nom = prenom;
        this.nom = nom;
          this.roles = roles;
    }

    

    public Utilisateur() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Utilisateur(int id, String username, String nom, String prenom, String email, String password, String roles, int enable) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", roles=" + roles + ", enable=" + enable + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
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
        final Utilisateur other = (Utilisateur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

    
    

    
    
    
    

}