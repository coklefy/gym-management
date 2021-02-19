package db;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Abbonamento;
import model.Cliente;
import model.Fisioterapista;


 public class Tabella_Abbonamento {
	  
	 @SuppressWarnings("unused")
      private DBConnection dataSource;
      private String tableName;
      
      public Tabella_Abbonamento () {
          dataSource = new DBConnection();
          tableName="abbonamenti";  
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
                	  + "ID_Abbonamento int not null,"
                      + "CF_Cliente char(20) not null,"
                      + "Durata_Iscrizione int not null,"
                      + "Data  date not null,"
                      + "Tipo char(20) not null,"
                      + "Prezzo int not null,"
                      + "constraint ID_ABBONAMENTO_ID primary key (ID_Abbonamento, CF_Cliente));"
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
      
      
      public void persist(Abbonamento a) {
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
         
          Tabella_Abbonamento at = new Tabella_Abbonamento();
          Tabella_Cliente ct = new Tabella_Cliente();
          Cliente c = new Cliente();
          if (findByPrimaryKey(a.getIDabbonamento())!=null || ct.findByPrimaryKey(a.getCFcliente()) == null){
              String message = "Abbonamento già inserita oppure cliente non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                      JOptionPane.ERROR_MESSAGE);
                   new Exception("Abbonamento già inserita oppure cliente non esistente");
          } 
      
          else { 
 
          String insert = "insert into "+ tableName +" (ID_Abbonamento, CF_Cliente, Durata_Iscrizione, Data, Tipo, Prezzo) values (?,?,?,?,?,?)";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt   (1, a.getIDabbonamento());
              statement.setString(2, a.getCFcliente());
              statement.setInt   (3, a.getDurata());
              statement.setDate  (4, a.getData());
              statement.setString(5, a.getTipo());
              statement.setInt   (6, a.getPrezzo());
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
      
      
      public void update(Abbonamento a) {
	          
	          Connection connection = DBConnection.getMsSQLConnection();
	          PreparedStatement statement;
	          
	          Abbonamento oldAbbonamento= findByPrimaryKey(a.getIDabbonamento());
	          Tabella_Cliente ct = new Tabella_Cliente();
	          if (oldAbbonamento == null){
	              String message = "Abbonamento non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Abbonamento non esiste");
	           } 
	         
	      String insert = "update " + tableName +" set CF_Cliente=?, Durata_Iscrizione=?, Data=?, Tipo=?, Prezzo=? where ID_Abbonamento= ?";
         
          try {
    
        	  statement = connection.prepareStatement(insert);
              statement.setString(1, a.getCFcliente());
              statement.setInt   (2, a.getDurata());
              statement.setDate  (3, a.getData());
              statement.setString(4, a.getTipo());
              statement.setInt   (5, a.getPrezzo());
        	  statement.setInt   (6, a.getIDabbonamento());
        	  if(ct.findByPrimaryKey(a.getCFcliente()) == null) {
        		  String message = "Cliente non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Cliente non esiste");
        	  }
        	  else {
              statement.executeUpdate();
    	      JOptionPane.showMessageDialog(null, "Abbonamento aggiornato");
        	  }
              
          } catch (SQLException e) {
              e.printStackTrace();
            }
      }
      
      
      public void delete(Integer IDabbonamento) {
          Connection connection = DBConnection.getMsSQLConnection();

          PreparedStatement statement = null;
          String insert = "delete from "+ tableName +" where ID_Abbonamento = ?";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt(1, IDabbonamento);
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
      
      public Abbonamento findByPrimaryKey(Integer IDabbonamento)  {
          Abbonamento ab = null;
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName +" where ID_Abbonamento=? ";
          try {
              statement = connection.prepareStatement(query);
              statement.setInt(1, IDabbonamento);
              ResultSet result = statement.executeQuery();
              if (result.next()) {
                  ab = new Abbonamento();
                  ab.setIDabbonamento(result.getInt("IDabbonamento"));
                  ab.setCFcliente(result.getString("CFcliente"));
                  ab.setDurata(result.getInt("durata"));
                  ab.setData(result.getDate("data"));
                  ab.setTipo(result.getString("tipo"));
                  ab.setPrezzo(result.getInt("prezzo"));
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
          return ab;
      }   
      
      
      public List<Abbonamento> findAll()  {
          List<Abbonamento> abbonamenti = null;
          Abbonamento ab = null;
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName ;
          try {
              statement = connection.prepareStatement(query);
              ResultSet result = statement.executeQuery();
              if(result.next()) {
                  abbonamenti = new LinkedList<Abbonamento>();
                  ab = new Abbonamento();
                  ab.setIDabbonamento(result.getInt("IDabbonamento"));
                  ab.setCFcliente(result.getString("CFcliente"));
                  ab.setDurata(result.getInt("durata"));
                  ab.setData(result.getDate("data"));
                  ab.setTipo(result.getString("tipo"));
                  ab.setPrezzo(result.getInt("prezzo"));
              }
              while(result.next()) {
                  ab = new Abbonamento();
                  ab.setIDabbonamento(result.getInt("IDabbonamento"));
                  ab.setCFcliente(result.getString("CFcliente"));
                  ab.setDurata(result.getInt("durata"));
                  ab.setData(result.getDate("data"));
                  ab.setTipo(result.getString("tipo"));
                  ab.setPrezzo(result.getInt("prezzo"));
            	  abbonamenti.add(ab);
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
          return abbonamenti;
      } 
}


