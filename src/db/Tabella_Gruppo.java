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

import model.Gruppo;
import model.Istruttore;
import model.Macchinario;
import model.Settore;

public class Tabella_Gruppo {
	 @SuppressWarnings("unused")
     private DBConnection dataSource;
     private String tableName;
     
     public Tabella_Gruppo () {
         dataSource = new DBConnection();
         tableName="gruppi";  
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
                     + "Codice_Gruppo int not null,"
                     + "Tipo char(20)  not null,"
                     + "CF_istruttore char(20) not null,"
                     + "constraint ID_MACCHINATIO_ID primary key (Codice_Gruppo));"
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
     
     
     public void persist(Gruppo g) {
         Connection connection = DBConnection.getMsSQLConnection();
         
         Tabella_Gruppo gt = new Tabella_Gruppo();
         Tabella_Istruttore it = new Tabella_Istruttore();
         Tabella_Settore st = new Tabella_Settore();
         Istruttore i = new Istruttore();
         if (findByPrimaryKey(g.getCodiceGr())!=null ||  it.findByPrimaryKey(g.getCFi()) == null){
             String message = "Gruppo già inserito oppure istruttore non esiste";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                     JOptionPane.ERROR_MESSAGE);
                  new Exception("Gruppo già inserito oppure istruttore non esiste");
         } 
         else { 
                 
        	 PreparedStatement statement = null; 
        	 String insert = "insert into "+ tableName +" (Codice_Gruppo, Tipo, CF_istruttore) values (?,?,?,?)";
        	 try {
        		 statement = connection.prepareStatement(insert);
        		 statement.setInt   (1, g.getCodiceGr());
             	 statement.setString(2, g.getTipo());
             	 statement.setString(3, g.getCFi());
             	 i = it.findByPrimaryKey(g.getCFi());
             	 if(g.getTipo().compareTo("muscolare") == 0 &&  i.getSpecializzazione().compareTo("muscolare") == 0) {
                 	 statement.executeUpdate();
             	 }
             	 else if (g.getTipo().compareTo("cardio") == 0 &&  i.getSpecializzazione().compareTo("cardio") == 0) {
                 	 statement.executeUpdate();

             	 }
             	 else {
                     String message = "Istruttore con specializzazione non corretta";
                     JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                             JOptionPane.ERROR_MESSAGE);
                          new Exception("Istruttore con specializzazione non corretta");
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
        		 }
        		 catch (SQLException e) {
        			 new Exception(e.getMessage());
        			 	System.out.println("Errore"+ e.getMessage());
        		 }
        	 }
         }
     }
     
     
     public void update(Gruppo g) {
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement;
         
         Gruppo oldGruppo = findByPrimaryKey(g.getCodiceGr());
         Tabella_Istruttore it = new Tabella_Istruttore();
         if (oldGruppo == null){
             String message = "Gruppo non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
             JOptionPane.ERROR_MESSAGE);
             new Exception("Gruppo non esiste");
          } 
        
         else { 
         String insert = "update "+ tableName +" set  Tipo=?, CF_istruttore=? where Codice_Gruppo= ?";
        
         try {
   
    		 statement = connection.prepareStatement(insert);
         	 statement.setString(1, g.getTipo());
         	 statement.setString(2, g.getCFi());
    		 statement.setInt   (3, g.getCodiceGr());

          if(it.findByPrimaryKey(g.getCFi()) == null) {
        	  String message = "Istruttore non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
              JOptionPane.ERROR_MESSAGE);
              new Exception("Istruttore non esiste");
          }
          else {
          statement.executeUpdate();
          JOptionPane.showMessageDialog(null, "Gruppo aggiornato");
          } 
         } catch (SQLException e) {
             e.printStackTrace();
          }
         }
      }
    	      
     public void delete(Integer codiceGr) {
         Connection connection = DBConnection.getMsSQLConnection();

         PreparedStatement statement = null;
         String insert = "delete from "+ tableName +" where Codice_Gruppo = ?";
         try {
             statement = connection.prepareStatement(insert);
             statement.setInt(1, codiceGr);
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
     
     public Gruppo findByPrimaryKey(Integer codiceGr)  {
    	 Gruppo g = null;
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName +" where Codice_Gruppo=? ";
         try {
             statement = connection.prepareStatement(query);
             statement.setInt(1, codiceGr);
             ResultSet result = statement.executeQuery();
             if (result.next()) {
                 g = new Gruppo();
                 g.setCodiceGr(result.getInt("Codice_Gruppo"));
                 g.setTipo(result.getString("Tipo"));
                 g.setCFi(result.getString("CF_istruttore"));
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
         return g;
     }   
     
     
     public List<Gruppo> findAll()  {
         List<Gruppo> gl = null;
         Gruppo g = null;
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName ;
         try {
             statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery();
             if(result.next()) {
                 gl = new LinkedList<Gruppo>();
                 g = new Gruppo();
                 g.setCodiceGr(result.getInt("Codice_Gruppo"));
                 g.setTipo(result.getString("Tipo"));
                 g.setCFi(result.getString("CF_istruttore"));
             }
             while(result.next()) {
                 g = new Gruppo();
                 g.setCodiceGr(result.getInt("Codice_Gruppo"));
                 g.setTipo(result.getString("Tipo"));
                 g.setCFi(result.getString("CF_istruttore"));
           	  gl.add(g);
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
         return gl;
     } 
}


