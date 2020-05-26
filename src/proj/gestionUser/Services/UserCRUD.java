/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.gestionUser.Services;

/**
 *
 * @author ASUS
 */
//import edu.EcoSystem.entities.User;
//import edu.EcoSystem.touls.MyConnection;
import proj.gestionUser.utils.ConnexionBD;
import proj.gestionUser.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
        

/**
 *
 * @author zied
 */
public class UserCRUD {
    
    public boolean verifpassword(String username, String password ) throws SQLException {
        if (!username.isEmpty() && !password.isEmpty() ) {
            String requete = "SELECT password , roles FROM user WHERE username = '" + username +"'";
            System.out.println("requete = " +requete);
            Statement s = ConnexionBD.getinstance().getcnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
                String pw = rs.getString(1);
                String roles=rs.getString(2);
                System.out.println("pw1 = " +pw);
                pw = pw.replace("$2y$", "$2a$");
                System.out.println("pw2 = " +pw);
                return (BCrypt.checkpw(password, pw));
            }
            else return false ;
        }  
        else {
            return false;
        }

    }
    
    
   
    
    
    public boolean bloqué( int enable){
        if(enable==1){
            return true;
        }
        return false;
    }
    
    
    
    
    public void ajoutUser(Utilisateur u, String password ){
        try {
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
             hashedpw = hashedpw.replace("$2a$", "$2y$");
            String UserRole = "a:1:{i:0;s:11:" + "ROLE_CLIENT " + ";}";
        String requete = "INSERT INTO user ( username, username_canonical, email , email_canonical ,enabled, password , roles , nom , prenom) VALUES "
                + "('" + u.getUsername() + "','"+  u.getUsername() + "','" + u.getEmail() + "','" + u.getEmail() +"','" + 1 + "','" + hashedpw + "','" + u.getRoles() + "','" + u.getNom()+ "','"+ u.getPrenom()+"')";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
   public void ajoutAdmin(Utilisateur u, String password ){
        try {
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
             hashedpw = hashedpw.replace("$2a$", "$2y$");
            String UserRole = "a:1:{i:0;s:10:\"ROLE_AGENT\";}";
        String requete = "INSERT INTO user ( username, username_canonical, email , email_canonical ,enabled, password , roles , nom , prenom) VALUES "
                + "('" + u.getUsername() + "','"+  u.getUsername() + "','" + u.getEmail() + "','" + u.getEmail() +"','" + 1 + "','" + hashedpw + "','" + u.getRoles() + "','" + u.getNom()+ "','"+ u.getPrenom()+"')";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
   
   public List<Utilisateur> getallUtilisateur() {
        ArrayList<Utilisateur> myList = new ArrayList();
        Connection cn = ConnexionBD.getinstance().getcnx();

     
        
        try {
           
            PreparedStatement pt =cn.prepareStatement("select * from user ");
            ResultSet rs= pt.executeQuery();
            
           
            while (rs.next()) {
                 Utilisateur a = new Utilisateur();
                a.setUsername(rs.getString(2)); 
                a.setNom(rs.getString(13));
                a.setPrenom(rs.getString(14));
                a.setEmail(rs.getString(4));
                
               
                 myList.add(a);
            }
//            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return myList;
    }
   
   
   
   
   
 
    public List<Utilisateur> chercher(String s) throws SQLException {
		List<Utilisateur> users = new ArrayList<>();
String rq = "select * from user where username like'"+s+"%' or email like'"+s+"%' or nom like'"+s+"%'" ;
			
			Statement st = ConnexionBD.getinstance().getcnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("nom"));
                                users.add(u);
			}
		
		return users;
	}
    
    public List<Utilisateur> chercherEnabled(String s) throws SQLException {
		List<Utilisateur> users = new ArrayList<>();
String rq = "select * from user where enabled ='"+ 1 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st = ConnexionBD.getinstance().getcnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    public List<Utilisateur> chercherDisabled(String s) throws SQLException {
		List<Utilisateur> users = new ArrayList<>();
String rq = "select * from user where enabled ='"+ 0 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st = ConnexionBD.getinstance().getcnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    
    
     public List<Utilisateur> getAllUsers() throws SQLException
    {   
        List <Utilisateur> products= new ArrayList<>();
        String req="SELECT * FROM user";
	Statement stm = ConnexionBD.getinstance().getcnx().createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while (rst.next()){
              Utilisateur u=new Utilisateur(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
            products.add(u);
        }
        return products;
    }

    
    
    
    
    
    
    
    
    
    public boolean modifierUser(Utilisateur u){
        try{
            String cpass=BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        String requete = "UPDATE user SET "
                + "nom = ?,"
                + "prenom = ?,"
                + "email = ?"
                + "WHERE id=?";
        PreparedStatement pst = ConnexionBD.getinstance().getcnx().prepareStatement(requete);
        pst.setString(1, u.getUsername());
        pst.setString(2, u.getEmail());
        pst.setString(3, cpass);
        pst.setInt(4, u.getId());
        pst.executeUpdate();
        System.out.println(u.getEmail()+ u.getUsername());
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return true;
}
    
    
    
    public void modifierUsername(String email, String username) throws SQLException{
        String requete= "UPDATE user SET username='"+ username +"'"+"WHERE email='"+ email+"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierEmail(String email, String mail) throws SQLException{
        String requete= "UPDATE user SET email='"+ mail +"'"+"WHERE email='"+ email+"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierPassword(String email, String pass) throws SQLException{
       String cpass=BCrypt.hashpw(pass, BCrypt.gensalt());
        String requete= "UPDATE user SET password='"+ cpass +"'"+"WHERE email='"+ email+"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierRole(String email, String role) throws SQLException{
        String requete= "UPDATE user SET roles='"+ role +"'"+"WHERE email='"+ email+"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierAvatar(String email, String avatars) throws SQLException{
        String requete= "UPDATE user SET avatar='"+ avatars +"'"+"WHERE email='"+ email+"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierEtat(int id, int enable ) throws SQLException{
        System.out.println(id);
        String requete= "UPDATE ser SET enabled='"+ enable +"'"+"WHERE id='"+ id +"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        st.executeUpdate(requete);
    }
    
    
    
    public void supprimerUser(int id ){
        try{
        String requete = "DELETE FROM user WHERE id=?";
        PreparedStatement pst = ConnexionBD.getinstance().getcnx().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public List<Utilisateur> afficherUser() {
        
        List<Utilisateur> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    public List<Utilisateur> afficherUserEnabled() {
        
        List<Utilisateur> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM user where enabled='"+ 1 +"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    public List<Utilisateur> afficherUserDisabled() {
        
        List<Utilisateur> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user where enabled='"+ 0 +"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    
    
    public Utilisateur getUserByUsername(String username) throws SQLException{
        String requete="SELECT * FROM fos_user WHERE username='"+username+"'";
        Statement st = ConnexionBD.getinstance().getcnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        Utilisateur u = new Utilisateur();
       while(rs.next()){
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));  
            }
       return u ;
    }
    
    
    
    
     public boolean existUser (String email) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE email = '" + email +"'";
            Statement s = ConnexionBD.getinstance().getcnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
    
     
     public boolean existUsername (String username) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE username = '" + username +"'";
            Statement s = ConnexionBD.getinstance().getcnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
    
    
    
    
    public Utilisateur recuperer_type_compte(String username, String password) throws SQLException {
		String rq = "select * from user where username = '"+username+"'";
                Statement s = ConnexionBD.getinstance().getcnx().createStatement();
            ResultSet rs = s.executeQuery(rq);
                
                Utilisateur b = null;
             
		while (rs.next()) {
			int id = rs.getInt(1);
			String usernameT = rs.getString(2);
			String email1 = rs.getString(3);
			String password1 = rs.getString(8);
                        System.out.println("pw1 = " +password1);
                password1 = password1.replace("$2y$", "$2a$");
                System.out.println("pw2 = " +password1);
                boolean crypt = BCrypt.checkpw(password, password1);
                if(crypt){
                    String roles = rs.getString(12);
                    int enable = rs.getInt(6);
                    String nom = rs.getString(13);
                    String prenom = rs.getString(14);
                    b = new Utilisateur( id, usernameT, password1, email1,  enable,  nom ,  prenom,  roles);
                }	
		}
                System.out.println(b);
		return b;
	}
    
    
    
    
    
    
    
    
    
    
}
