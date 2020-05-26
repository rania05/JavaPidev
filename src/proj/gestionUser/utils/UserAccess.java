/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.utils;

import proj.gestionUser.entities.Utilisateur;
//import entities.Fos_userr;

/**
 *
 * @author Laptop Center
 */
public class UserAccess {
    
    private static int User_ID ; 
    public static Utilisateur User ; 
    private static String Username ; 

    public static int getUser_ID() {
        return User_ID;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUser_ID(int User_ID) {
        UserAccess.User_ID = User_ID;
    }

    public static void setUsername(String Username) {
        UserAccess.Username = Username;
    }

    public static Utilisateur getUser() {
        return User;
    }

    public static void setUser(Utilisateur User) {
        UserAccess.User = User;
    }
    
    
    
}
