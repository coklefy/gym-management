package db;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Cliente;
import model.Persona;

 public class Tabella_Cliente extends Persona{
	  
	 @SuppressWarnings("unused")
      private DBConnection dataSource;
      private String tableName;
      
      public Tabella_Cliente () {
          dataSource = new DBConnection();
          tableName="clienti";  
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
                      + "IDcliente int not null,"
                      + "Città char(20) not null,"
                      + "Via char(20) not null,"
                      + "NrCivico int not null,"
                      + "CAP int not null,"
                      + "constraint ID_CLIENTE_ID primary key (CF));"
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
      
      
      public void persist(Cliente c) {
          Connection connection = DBConnection.getMsSQLConnection();

          if (findByPrimaryKey(c.getCF())!=null){
              String message = "Cliente già inserita";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                      JOptionPane.ERROR_MESSAGE);
                   new Exception("Cliente già inserita");
          } 
             
          else {        
          PreparedStatement statement = null; 
          String insert = "insert into "+ tableName +" (CF, Nome, Cognome, IDcliente, Città, Via, NrCivico, CAP) values (?,?,?,?,?,?,?,?)";
          try {
              statement = connection.prepareStatement(insert);
              statement.setString(1, c.getCF());
              statement.setString(2, c.getNome());
              statement.setString(3, c.getCognome());
              statement.setInt   (4, c.getClienteID());
              statement.setString(5, c.getCittà());
              statement.setString(6, c.getVia());
			  statement.setInt   (7, c.getNrCivico());
			  statement.setInt   (8, c.getCAP());
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
      
      
      public void update(Cliente c) {
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement;
          
          Cliente oldCliente = findByPrimaryKey(c.getCF());
          if (oldCliente == null){
              String message = "Cliente non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
              JOptionPane.ERROR_MESSAGE);
              new Exception("Cliente non esiste");
           } 
         
          String insert = "update "+ tableName +" set nome=?, cognome=?, IDcliente=?, città=?, via=?, nrCivico=?, CAP=? where CF= ?";
         
          try {
    
        	  statement = connection.prepareStatement(insert);
              statement.setString(1, c.getNome());
              statement.setString(2, c.getCognome());
              statement.setInt   (3, c.getClienteID());
              statement.setString(4, c.getCittà());
              statement.setString(5, c.getVia());
			  statement.setInt   (6, c.getNrCivico());
			  statement.setInt   (7, c.getCAP());
              statement.setString(8, c.getCF());

              statement.executeUpdate();
              
          } catch (SQLException e) {
              e.printStackTrace();
            }
          
          JOptionPane.showMessageDialog(null, "Cliente aggiornato");

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
      
      public Cliente findByPrimaryKey(String CF)  {
          Cliente cliente = null;
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName +" where CF=? ";
          try {
              statement = connection.prepareStatement(query);
              statement.setString(1, CF);
              ResultSet result = statement.executeQuery();
              if (result.next()) {
                  cliente = new Cliente();
                  cliente.setCF(result.getString("CF"));
                  cliente.setNome(result.getString("nome"));
                  cliente.setCognome(result.getString("cognome"));
                  cliente.setClienteID(result.getInt("IDcliente"));
                  cliente.setCittà(result.getString("città"));
                  cliente.setVia(result.getString("via"));
                  cliente.setNrCivico(result.getInt("nrCivico"));
                  cliente.setCAP(result.getInt("CAP"));
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
          return cliente;
      }   
      
      
      public List<Cliente> findAll()  {
          List<Cliente> clienti = null;
          Cliente cliente = null;
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName ;
          try {
              statement = connection.prepareStatement(query);
              ResultSet result = statement.executeQuery();
              if(result.next()) {
                  clienti = new LinkedList<Cliente>();
                  cliente = new Cliente();
                  cliente.setCF(result.getString("CF"));
                  cliente.setNome(result.getString("nome"));
                  cliente.setCognome(result.getString("cognome"));
                  cliente.setClienteID(result.getInt("IDcliente"));
                  cliente.setCittà(result.getString("città"));
                  cliente.setVia(result.getString("via"));
                  cliente.setNrCivico(result.getInt("nrCivico"));
                  cliente.setCAP(result.getInt("CAP"));
              }
              while(result.next()) {
            	  cliente = new Cliente();
            	  cliente.setCF(result.getString("CF"));
            	  cliente.setNome(result.getString("nome"));
            	  cliente.setCognome(result.getString("cognome"));
            	  cliente.setCittà(result.getString("città"));
            	  cliente.setVia(result.getString("via"));
            	  cliente.setNrCivico(result.getInt("nrCivico"));
            	  cliente.setCAP(result.getInt("CAP"));
            	  clienti.add(cliente);
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
          return clienti;
      } 
}


