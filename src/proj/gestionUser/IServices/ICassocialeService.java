package proj.gestionUser.IServices;





import proj.gestionUser.Services.*;
import proj.gestionUser.entities.Cassociale;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;



public interface ICassocialeService {
    
     public void addCas(Cassociale cs) throws SQLException;
     
     
     public List<Cassociale> getAllCas() throws SQLException;
     
     
  
    
    
    public void deleteCas(String lieu) throws SQLException;
    
    public void updateCas(String lieu, Date date , int id) throws SQLException;
    
    public List<Cassociale> RechercherCas(String lieu) throws SQLException;
}
