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

import model.Settore;

public class Tabella_Settore {
	 @SuppressWarnings("unused")
     private DBConnection dataSource;
     private String tableName;
     
     public Tabella_Settore () {
         dataSource = new DBConnection();
         tableName="settori";  
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
                     + "Codice_Settore int not null,"
                     + "Tipo char(20) not null,"
                     + "Numero_Macchinari int not null,"
                     + "Area int not null,"
                     + "constraint ID_SETTORE_ID primary key (Codice_Settore));"
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
     
     
     public void persist(Settore s) {
         Connection connection = DBConnection.getMsSQLConnection();
         Tabella_Settore st = new Tabella_Settore();
         
         if (findByPrimaryKey(s.getCodiceSett())!=null){
             String message = "Settore già inserito";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                     JOptionPane.ERROR_MESSAGE);
                  new Exception("Cliente già inserita");
         } 
         else { 
        	
                 
         PreparedStatement statement = null; 
         String insert = "insert into "+ tableName +" (Codice_Settore, Tipo, Numero_Macchinari, Area) values (?,?,?,?)";
         try {
             statement = connection.prepareStatement(insert);
             statement.setInt   (1, s.getCodiceSett());
             statement.setString(2, s.getTipo());
             statement.setInt   (3, s.getNumeroMacchinari());
             statement.setInt   (4, s.getArea());
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
     
     public void update(Settore s) {
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement;
         
         Settore oldSettore = findByPrimaryKey(s.getCodiceSett());
         if (oldSettore == null){
             String message = "Settore non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
             JOptionPane.ERROR_MESSAGE);
             new Exception("Reception non esiste");
          } 
        
         String insert = "update "+ tableName +" set tipo=?, Numero_Macchinari=?, Area=? where Codice_Settore= ?";
        
         try {
   
       	  statement = connection.prepareStatement(insert);
          statement.setString(1, s.getTipo());
          statement.setInt   (2, s.getNumeroMacchinari());
          statement.setInt   (3, s.getArea());
          statement.setInt   (4, s.getCodiceSett());
          statement.executeUpdate();
             
         } catch (SQLException e) {
             e.printStackTrace();
          }
        JOptionPane.showMessageDialog(null, "Settore aggiornato");

      }
    	      
     public void delete(Integer codiceSettore) {
         Connection connection = DBConnection.getMsSQLConnection();

         PreparedStatement statement = null;
         String insert = "delete from "+ tableName +" where Codice_Settore = ?";
         try {
             statement = connection.prepareStatement(insert);
             statement.setInt(1, codiceSettore);
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
     
     public Settore findByPrimaryKey(Integer codiceSettore)  {
    	 Settore s = null;
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName +" where Codice_Settore=? ";
         try {
             statement = connection.prepareStatement(query);
             statement.setInt(1, codiceSettore);
             ResultSet result = statement.executeQuery();
             if (result.next()) {
                 s = new Settore();
                 s.setCodiceSett(result.getInt("Codice_Settore"));
                 s.setTipo(result.getString("tipo"));
                 s.setNumeroMacchinari(result.getInt("Numero_Macchinari"));
                 s.setArea(result.getInt("area"));
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
         return s;
     }   
     
     
     public List<Settore> findAll()  {
         List<Settore> sl = null;
         Settore s = null;
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName ;
         try {
             statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery();
             if(result.next()) {
                 sl = new LinkedList<Settore>();
                 s = new Settore();
                 s.setCodiceSett(result.getInt("Codice_Settore"));
                 s.setTipo(result.getString("tipo"));
                 s.setNumeroMacchinari(result.getInt("Numero_Macchinari"));
                 s.setArea(result.getInt("area"));
             }
             while(result.next()) {
            	 s = new Settore();
                 s.setCodiceSett(result.getInt("Codice_Settore"));
                 s.setTipo(result.getString("tipo"));
                 s.setNumeroMacchinari(result.getInt("Numero_Macchinari"));
                 s.setArea(result.getInt("area"));
           	  sl.add(s);
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
         return sl;
     } 
}


