package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Fisioterapista;
import model.Persona;
import model.Reception;

public class Tabella_Reception extends Persona{
	 @SuppressWarnings("unused")
     private DBConnection dataSource;
     private String tableName;
     
     public Tabella_Reception () {
         dataSource = new DBConnection();
         tableName="reception";  
     }
     
     public void dropAndCreateTable() {
         Connection connection = DBConnection.getMsSQLConnection();
         Statement statement = null;
         try {
             statement = connection.createStatement ();
             try{
                 statement.executeUpdate ("DROP TABLE " + tableName);
             }
             catch (SQLException e) {
                 // the table does not exist
             }
             statement.executeUpdate (
                 "CREATE TABLE "+ tableName +" ("
                     + "CF char(16) not null,"
                     + "Nome char(20) not null,"
                     + "Cognome char(20) not null,"
                     + "IDreception int not null,"
                     + "Città char(20) not null,"
                     + "Via char(20) not null,"
                     + "NrCivico int not null,"
                     + "CAP int not null,"
                     + "constraint SID_RECEPTION_ID unique (IDreception),"
                     + "constraint ID_RECEPTION_ID primary key (CF));"
             );
             statement.close ();
         }
         catch (SQLException e) {
              new Exception(e.getMessage());
              System.out.println("Errore "+ e.getMessage());
         }
         finally {
             try {
                 if (statement != null) 
                     statement.close();
                 if (connection!= null)
                     connection.close();
             }
             catch (SQLException e) {
                      new Exception(e.getMessage());
                  System.out.println("Errore "+ e.getMessage());
             }
         }
     }
     
     
     public void persist(Reception r) {
         Connection connection = DBConnection.getMsSQLConnection();

         if (findByPrimaryKey(r.getCF())!=null){
             String message = "Reception già inserita";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                     JOptionPane.ERROR_MESSAGE);
                  new Exception("Cliente già inserita");
         } 
         
         else {
                 
         PreparedStatement statement = null; 
         String insert = "insert into "+ tableName +" (CF, Nome, Cognome, IDreception, Città, Via, NrCivico, CAP) values (?,?,?,?,?,?,?,?)";
         try {
             statement = connection.prepareStatement(insert);
             statement.setString(1, r.getCF());
             statement.setString(2, r.getNome());
             statement.setString(3, r.getCognome());
             statement.setInt   (4, r.getReceptionID());
             statement.setString(5, r.getCittà());
             statement.setString(6, r.getVia());
			 statement.setInt   (7, r.getNrCivico());
			 statement.setInt   (8, r.getCAP());
             statement.executeUpdate();
         }
         catch (SQLException e) {
                 new Exception(e.getMessage());
             System.out.println("Errore"+ e.getMessage());
         }
         finally {
             try {
                 if (statement != null) 
                     statement.close();
                 if (connection!= null)
                     connection.close();
             }
             catch (SQLException e) {
                 new Exception(e.getMessage());
                     System.out.println("Errore"+ e.getMessage());
             }
         }
     }
   }   
     
     public void update(Reception r) {
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement;
         
         Reception oldReception = findByPrimaryKey(r.getCF());
         if (oldReception == null){
             String message = "Reception non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
             JOptionPane.ERROR_MESSAGE);
             new Exception("Reception non esiste");
          } 
        
         String insert = "update "+ tableName +" set nome=?, cognome=?, IDreception=?, città=?, via=?, nrCivico=?, CAP=? where CF= ?";
        
         try {
   
       	  statement = connection.prepareStatement(insert);
             statement.setString (1, r.getNome());
             statement.setString (2, r.getCognome());
             statement.setInt    (3, r.getReceptionID());
             statement.setString (4, r.getCittà());
             statement.setString (5, r.getVia());
			 statement.setInt    (6, r.getNrCivico());
		     statement.setInt    (7, r.getCAP());
             statement.setString (8, r.getCF());
             statement.executeUpdate();
             
         } catch (SQLException e) {
             e.printStackTrace();
          }
        JOptionPane.showMessageDialog(null, "Reception aggiornato");

      }
    	      
     public void delete(String CF) {
         Connection connection = DBConnection.getMsSQLConnection();

         PreparedStatement statement = null;
         String insert = "delete from "+ tableName +" where CF = ?";
         try {
             statement = connection.prepareStatement(insert);
             statement.setString(1, CF);
             statement.executeUpdate();
         }
         catch (SQLException e) {
                 new Exception(e.getMessage());
             System.out.println("Errore"+ e.getMessage());
         }
         finally {
             try {
                 if (statement != null) 
                     statement.close();
                 if (connection!= null)
                     connection.close();
             }
             catch (SQLException e) {
                 new Exception(e.getMessage());
                     System.out.println("Errore"+ e.getMessage());
             }
         }
     }
     
     public Reception findByPrimaryKey(String CF)  {
         Reception r = null;
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName +" where CF=? ";
         try {
             statement = connection.prepareStatement(query);
             statement.setString(1, CF);
             ResultSet result = statement.executeQuery();
             if (result.next()) {
                 r = new Reception();
                 r.setCF(result.getString("CF"));
                 r.setNome(result.getString("nome"));
                 r.setCognome(result.getString("cognome"));
                 r.setReceptionID(result.getInt("IDreception"));
                 r.setCittà(result.getString("città"));
                 r.setVia(result.getString("via"));
                 r.setNrCivico(result.getInt("nrCivico"));
                 r.setCAP(result.getInt("CAP"));
            }
         }
         catch (SQLException e) {
                 new Exception(e.getMessage());
             System.out.println("Errore"+ e.getMessage());
         }
         finally {
             try {
                 if (statement != null) 
                     statement.close();
                 if (connection!= null)
                     connection.close();
             } catch (SQLException e) {
                 new Exception(e.getMessage());
                      System.out.println("Errore"+ e.getMessage());
             }
         }
         return r;
     }   
     
     
     public List<Reception> findAll()  {
         List<Reception> rl = null;
         Reception r = null;
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName ;
         try {
             statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery();
             if(result.next()) {
                 rl = new LinkedList<Reception>();
                 r = new Reception();
                 r.setCF(result.getString("CF"));
                 r.setNome(result.getString("nome"));
                 r.setCognome(result.getString("cognome"));
                 r.setReceptionID(result.getInt("IDreception"));
                 r.setCittà(result.getString("città"));
                 r.setVia(result.getString("via"));
                 r.setNrCivico(result.getInt("nrCivico"));
                 r.setCAP(result.getInt("CAP"));
             }
             while(result.next()) {
           	  r = new Reception();
           	  r.setCF(result.getString("CF"));
           	  r.setNome(result.getString("nome"));
           	  r.setCognome(result.getString("cognome"));
              r.setReceptionID(result.getInt("IDreception"));
        	  r.setCittà(result.getString("città"));
           	  r.setVia(result.getString("via"));
           	  r.setNrCivico(result.getInt("nrCivico"));
           	  r.setCAP(result.getInt("CAP"));
           	  rl.add(r);
             }
         }
         catch (SQLException e) {
                 new Exception(e.getMessage());
             System.out.println("Errore"+ e.getMessage());
         }
         finally {
             try {
                 if (statement != null) 
                     statement.close();
                 if (connection!= null)
                     connection.close();
             } catch (SQLException e) {
                 new Exception(e.getMessage());
                      System.out.println("Errore"+ e.getMessage());
             }
         }
         return rl;
     } 
}


