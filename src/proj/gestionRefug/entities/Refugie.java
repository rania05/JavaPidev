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
public class Refugie {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String origine ;
    private int idcamp;
private String nomCamp;

    public String getNomCamp() {
        return nomCamp;
    }

    
    
    public void setNomCamp(String nomCamp) {
        this.nomCamp = nomCamp;
    }
    public Refugie() {
    }

    public Refugie(String nom) {
        this.nom = nom;
    }

    public Refugie(String nom, String prenom, int age, String origine) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.origine = origine;
    }

    public Refugie(String nom, String prenom, int age, String origine, int idcamp) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.origine = origine;
        this.idcamp = idcamp;
    }
    
    

    
    public Refugie(int id, String nom, String prenom, int age, String origine, int idcamp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.origine = origine;
        this.idcamp = idcamp;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public String getOrigine() {
        return origine;
    }

    public int getIdcamp() {
        return idcamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public void setIdcamp(int idcamp) {
        this.idcamp = idcamp;
    }

    @Override
    public String toString() {
        return "Refugie{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", origine=" + origine + ", idcamp=" + idcamp + '}';
    }
    
    
    
    
    
    

    
}
