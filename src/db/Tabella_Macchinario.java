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

import model.Macchinario;
import model.Settore;

public class Tabella_Macchinario {
	 @SuppressWarnings("unused")
     private DBConnection dataSource;
     private String tableName;
     
     public Tabella_Macchinario () {
         dataSource = new DBConnection();
         tableName="macchinari";  
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
                     + "Codice_Mac int not null,"
                     + "Nome char(20) not null,"
                     + "Grado char(20)  not null,"
                     + "Anno int not null,"
                     + "Codice_Settore int not null,"
                     + "constraint ID_MACCHINATIO_ID primary key (Codice_Mac));"
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
     
     
     public void persist(Macchinario m) {
         Connection connection = DBConnection.getMsSQLConnection();
         
         Tabella_Settore st = new Tabella_Settore();
         if (findByPrimaryKey(m.getCodiceMac())!=null || st.findByPrimaryKey(m.getCodiceSet()) == null){
             String message = "Macchinario già inserito oppure settore non esiste";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                     JOptionPane.ERROR_MESSAGE);
                  new Exception("Macchinario già inserito oppure settore non esiste");
         } 
         else { 
                 
        	 PreparedStatement statement = null; 
        	 String insert = "insert into "+ tableName +" (Codice_Mac, Nome, Grado, Anno, Codice_Settore) values (?,?,?,?,?)";
        	 try {
        		 statement = connection.prepareStatement(insert);
        		 statement.setInt   (1, m.getCodiceMac());
             	 statement.setString(2, m.getNome());
             	 statement.setString(3, m.getGrado());
             	 statement.setInt   (4, m.getAnno());
             	 statement.setInt   (5, m.getCodiceSet());
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
     
     
     public void update(Macchinario m) {
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement;
         
         Macchinario oldMacchinario = findByPrimaryKey(m.getCodiceMac());
         Tabella_Settore st = new Tabella_Settore();
         if (oldMacchinario == null){
             String message = "Macchinario non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
             JOptionPane.ERROR_MESSAGE);
             new Exception("Macchinario non esiste");
          } 
        
         String insert = "update "+ tableName +" set Nome=?, Grado=?, Anno=?, Codice_Settore=? where Codice_Mac= ?";
        
         try {
   
       	  statement = connection.prepareStatement(insert);
          statement.setString(1, m.getNome());
          statement.setString(2, m.getGrado());
          statement.setInt   (3, m.getAnno());
          statement.setInt   (4, m.getCodiceSet());
          statement.setInt   (5, m.getCodiceMac());
          if(st.findByPrimaryKey(m.getCodiceSet()) == null) {
        	  String message = "Settore non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
              JOptionPane.ERROR_MESSAGE);
              new Exception("Settore non esiste");
          }
          else {
          statement.executeUpdate();
          JOptionPane.showMessageDialog(null, "Macchinario aggiornato");
          } 
         } catch (SQLException e) {
             e.printStackTrace();
          }

      }
    	      
     public void delete(Integer codiceMac) {
         Connection connection = DBConnection.getMsSQLConnection();

         PreparedStatement statement = null;
         String insert = "delete from "+ tableName +" where Codice_Mac = ?";
         try {
             statement = connection.prepareStatement(insert);
             statement.setInt(1, codiceMac);
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
     
     public Macchinario findByPrimaryKey(Integer codiceMac)  {
    	 Macchinario m = null;
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName +" where Codice_Mac=? ";
         try {
             statement = connection.prepareStatement(query);
             statement.setInt(1, codiceMac);
             ResultSet result = statement.executeQuery();
             if (result.next()) {
                 m = new Macchinario();
                 m.setCodiceMac(result.getInt("Codice_Mac"));
                 m.setNome(result.getString("Nome"));
                 m.setGrado(result.getString("Grado"));
                 m.setAnno(result.getInt("Anno"));
                 m.setCodiceSet(result.getInt("Codice_Settore"));
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
         return m;
     }   
     
     
     public List<Macchinario> findAll()  {
         List<Macchinario> ml = null;
         Macchinario m = null;
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName ;
         try {
             statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery();
             if(result.next()) {
                 ml = new LinkedList<Macchinario>();
                 m = new Macchinario();
                 m.setCodiceMac(result.getInt("Codice_Mac"));
                 m.setNome(result.getString("Nome"));
                 m.setGrado(result.getString("Grado"));
                 m.setAnno(result.getInt("Anno"));
                 m.setCodiceSet(result.getInt("Codice_Settore"));
             }
             while(result.next()) {
                 m = new Macchinario();
                 m.setCodiceMac(result.getInt("Codice_Mac"));
                 m.setNome(result.getString("Nome"));
                 m.setGrado(result.getString("Grado"));
                 m.setAnno(result.getInt("Anno"));
                 m.setCodiceSet(result.getInt("Codice_Settore"));
           	  ml.add(m);
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
         return ml;
     } 
}


