package db;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Abbonamento;
import model.Cliente;
import model.Controllo_Macchinario;
import model.Fisioterapista;


 public class Tabella_Controllo  {
	  
	 @SuppressWarnings("unused")
      private DBConnection dataSource;
      private String tableName;
      
      public Tabella_Controllo () {
          dataSource = new DBConnection();
          tableName="controlli_Macchinari";  
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
                	  + "Codice_Controllo int not null,"
                      + "Codice_Fabbricazione int not null,"
                      + "Esito char (20) not null,"
                      + "Data  date not null,"
                      + "constraint ID_ABBONAMENTO_ID primary key (Codice_Controllo, Codice_Fabbricazione));"
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
      
      
      public void persist(Controllo_Macchinario c) {
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
         
          Tabella_Controllo at = new Tabella_Controllo();
          Tabella_Macchinario mt = new Tabella_Macchinario();

          if (findByPrimaryKey(c.getCodiceControllo())!=null || mt.findByPrimaryKey(c.getCodiceFabbricazione()) == null){
              String message = "Controllo già inserita oppure macchinario non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                      JOptionPane.ERROR_MESSAGE);
                   new Exception("Controllo già inserita oppure macchinario non esistente");
          } 
      
          else { 
 
          String insert = "insert into "+ tableName +" (Codice_Controllo, Codice_Fabbricazione, Esito, Data) values (?,?,?,?)";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt   (1, c.getCodiceControllo());
              statement.setInt	 (2, c.getCodiceFabbricazione());
              statement.setString(3, c.getEsito());
              statement.setDate  (4, c.getData());;
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
      
      
      public void update(Controllo_Macchinario c) {
	          
	          Connection connection = DBConnection.getMsSQLConnection();
	          PreparedStatement statement;
	          
	          Controllo_Macchinario oldControllo = findByPrimaryKey(c.getCodiceControllo());
	          Tabella_Macchinario mt = new Tabella_Macchinario();
	          if (oldControllo == null){
	              String message = "Controllo non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Controllo non esiste");
	           } 
	         
	      String insert = "update " + tableName +" set Codice_Fabbricazione= ?, Esito= ?, Data= ? where Codice_Controllo= ?";
         
          try {
        	  
              statement = connection.prepareStatement(insert);
              statement.setInt	 (1, c.getCodiceFabbricazione());
              statement.setString(2, c.getEsito());
              statement.setDate  (3, c.getData());;
              statement.setInt   (4, c.getCodiceControllo());

              if(mt.findByPrimaryKey(c.getCodiceFabbricazione()) == null) {
        		  String message = "Macchinario non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Macchinario non esiste");
        	  }
        	  else {
              statement.executeUpdate();
    	      JOptionPane.showMessageDialog(null, "Controllo del macchianrio è aggiornato");
        	  }
              
          } catch (SQLException e) {
              e.printStackTrace();
            }
      }
      
      
      public void delete(Integer codiceControllo) {
          Connection connection = DBConnection.getMsSQLConnection();

          PreparedStatement statement = null;
          String insert = "delete from "+ tableName +" where Codice_Controllo = ?";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt(1, codiceControllo);
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
      
      public Controllo_Macchinario findByPrimaryKey(Integer codiceControllo)  {
    	  Controllo_Macchinario cm = null;
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName +" where Codice_Controllo=? ";
          try {
              statement = connection.prepareStatement(query);
              statement.setInt(1, codiceControllo);
              ResultSet result = statement.executeQuery();
              if (result.next()) {
            	  cm = new Controllo_Macchinario();
            	  cm.setCodiceControllo(result.getInt("Codice_Controllo"));
            	  cm.setCodiceFabbricazione(result.getInt("Codice_Fabbricazione"));
            	  cm.setEsito(result.getString("esito"));
            	  cm.setData(result.getDate("data"));
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
          return cm;
      }   
      
      
      public List<Controllo_Macchinario> findAll()  {
          List<Controllo_Macchinario> cml = null;
          Controllo_Macchinario cm = null;
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName ;
          try {
              statement = connection.prepareStatement(query);
              ResultSet result = statement.executeQuery();
              if(result.next()) {
                  cml = new LinkedList<Controllo_Macchinario>();
            	  cm = new Controllo_Macchinario();
            	  cm.setCodiceControllo(result.getInt("Codice_Controllo"));
            	  cm.setCodiceFabbricazione(result.getInt("Codice_Fabbricazione"));
            	  cm.setEsito(result.getString("esito"));
            	  cm.setData(result.getDate("data"));
              }
              while(result.next()) {
            	  cm = new Controllo_Macchinario();
            	  cm.setCodiceControllo(result.getInt("Codice_Controllo"));
            	  cm.setCodiceFabbricazione(result.getInt("Codice_Fabbricazione"));
            	  cm.setEsito(result.getString("esito"));
            	  cm.setData(result.getDate("data"));
            	  cml.add(cm);
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
          return cml;
      } 
}


