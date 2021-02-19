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

import model.Cliente;
import model.Fisioterapista;
import model.Persona;

public class Tabella_Fisioterapista extends Persona{
	  
		 @SuppressWarnings("unused")
	      private DBConnection dataSource;
	      private String tableName;
	      
	      public Tabella_Fisioterapista () {
	          dataSource = new DBConnection();
	          tableName="fisioterapisti";  
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
	                      + "IDfisioterapista int not null,"
	                      + "Città char(20) not null,"
	                      + "Via char(20) not null,"
	                      + "NrCivico int not null,"
	                      + "CAP int not null,"
	                      + "constraint SID_FISIOTERAPISTI_ID unique (IDfisioterapista),"
	                      + "constraint ID_FISIOTERAPISTI_ID primary key (CF));"
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
	      
	      
	      public void persist(Fisioterapista f) {
	          Connection connection = DBConnection.getMsSQLConnection();

	          if (findByPrimaryKey(f.getCF())!=null){
	              String message = "Fisioterapista già inserita";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	                      JOptionPane.ERROR_MESSAGE);
	                   new Exception("Fisioterapista già inserita");
	          } 
	            
	          else {
	                  
	          PreparedStatement statement = null; 
	          String insert = "insert into "+ tableName +" (CF, Nome, Cognome, IDfisioterapista, Città, Via, NrCivico, CAP) values (?,?,?,?,?,?,?,?)";
	          try {
	              statement = connection.prepareStatement(insert);
	              statement.setString(1, f.getCF());
	              statement.setString(2, f.getNome());
	              statement.setString(3, f.getCognome());
	              statement.setInt   (4, f.getFisioterapistaID());
	              statement.setString(5, f.getCittà());
	              statement.setString(6, f.getVia());
				  statement.setInt   (7, f.getNrCivico());
				  statement.setInt   (8, f.getCAP());
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
	      
	  public void update(Fisioterapista f) {
	          
	          Connection connection = DBConnection.getMsSQLConnection();
	          PreparedStatement statement;
	          
	          Fisioterapista oldFisioterapista = findByPrimaryKey(f.getCF());
	          if (oldFisioterapista == null){
	              String message = "Fisioterapista non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Fisioterapista non esiste");
	           } 
	         
	          String insert = "update "+ tableName +" set nome=?, cognome=?, IDfisioterapista=?, città=?, via=?, nrCivico=?, CAP=? where CF= ?";
	         
	          try {
	    
	        	  statement = connection.prepareStatement(insert);
	              statement.setString(1, f.getNome());
	              statement.setString(2, f.getCognome());
	              statement.setInt   (3, f.getFisioterapistaID());
	              statement.setString(4, f.getCittà());
	              statement.setString(5, f.getVia());
				  statement.setInt   (6, f.getNrCivico());
				  statement.setInt   (7, f.getCAP());
	              statement.setString(8, f.getCF());
	              statement.executeUpdate();
	              
	          } catch (SQLException e) {
	              e.printStackTrace();
	           }
	         JOptionPane.showMessageDialog(null, "Fisioterapista aggiornato");

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
	      
	      public Fisioterapista findByPrimaryKey(String CF)  {
	          Fisioterapista f = null;
	          
	          Connection connection = DBConnection.getMsSQLConnection();
	          PreparedStatement statement = null;
	          String query = "select * from "+ tableName +" where CF=? ";
	          try {
	              statement = connection.prepareStatement(query);
	              statement.setString(1, CF);
	              ResultSet result = statement.executeQuery();
	              if (result.next()) {
	                  f = new Fisioterapista();
	                  f.setCF(result.getString("CF"));
	                  f.setNome(result.getString("nome"));
	                  f.setCognome(result.getString("cognome"));
	                  f.setFisioterapistaID(result.getInt("IDfisioterapista"));
	                  f.setCittà(result.getString("città"));
	                  f.setVia(result.getString("via"));
	                  f.setNrCivico(result.getInt("nrCivico"));
	                  f.setCAP(result.getInt("CAP"));
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
	          return f;
	      }   
	      
	      
	      public List<Fisioterapista> findAll()  {
	          List<Fisioterapista> fisioterapisti = null;
	          Fisioterapista f = null;
	          Connection connection = DBConnection.getMsSQLConnection();
	          PreparedStatement statement = null;
	          String query = "select * from "+ tableName ;
	          try {
	              statement = connection.prepareStatement(query);
	              ResultSet result = statement.executeQuery();
	              if(result.next()) {
	                  fisioterapisti = new LinkedList<Fisioterapista>();
	                  f = new Fisioterapista();
	                  f.setCF(result.getString("CF"));
	                  f.setNome(result.getString("nome"));
	                  f.setCognome(result.getString("cognome"));
	                  f.setFisioterapistaID(result.getInt("IDfisioterapista"));
	                  f.setCittà(result.getString("città"));
	                  f.setVia(result.getString("via"));
	                  f.setNrCivico(result.getInt("nrCivico"));
	                  f.setCAP(result.getInt("CAP"));
	              }
	              while(result.next()) {
	            	  f = new Fisioterapista();
	                  f.setCF(result.getString("CF"));
	                  f.setNome(result.getString("nome"));
	                  f.setCognome(result.getString("cognome"));
	                  f.setFisioterapistaID(result.getInt("IDfisioterapista"));
	                  f.setCittà(result.getString("città"));
	                  f.setVia(result.getString("via"));
	                  f.setNrCivico(result.getInt("nrCivico"));
	                  f.setCAP(result.getInt("CAP"));
	            	  fisioterapisti.add(f);
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
	          return fisioterapisti;
	      } 
}
