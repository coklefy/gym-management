package db;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Cliente;
import model.Persona;
import model.Visita;

 public class Tabella_Visita {
	  
	 @SuppressWarnings("unused")
      private DBConnection dataSource;
      private String tableName;
      
      public Tabella_Visita () {
          dataSource = new DBConnection();
          tableName="visite";  
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
                  	  + "Codice_Visita int not null,"
                      + "CF_fisioterapista char(16) not null,"
                      + "CF_cliente char(16) not null,"
                      + "Esito char(20) not null,"
                      + "Data date not null,"
                      + "constraint ID_VISITA_ID primary key (Codice_Visita));"
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
      
      
      public void persist(Visita v) {
          Connection connection = DBConnection.getMsSQLConnection();
          Tabella_Cliente ct = new Tabella_Cliente();
          Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
          
          if (findByPrimaryKey(v.getCodiceVisita())!=null || ct.findByPrimaryKey(v.getCFc()) == null || ft.findByPrimaryKey(v.getCFf()) == null) {
              String message = "Visita già inserita oppure cliente/fisioterapista non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                      JOptionPane.ERROR_MESSAGE);
                   new Exception("Visita già inserita oppure cliente/fisioterapista non esistente");
          } 
             
          else {        
          PreparedStatement statement = null; 
          String insert = "insert into "+ tableName +" (Codice_Visita, CF_fisioterapista, CF_cliente, Esito, Data) values (?,?,?,?,?)";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt   (1, v.getCodiceVisita());
              statement.setString(2, v.getCFf());
              statement.setString(3, v.getCFc());
              statement.setString(4, v.getEsito());
              statement.setDate  (5, v.getData());
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
      
      
      public void update(Visita v) {
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement;
          Tabella_Cliente ct = new Tabella_Cliente();
          Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
          
          Visita oldVisita = findByPrimaryKey(v.getCodiceVisita());
          if (oldVisita == null){
              String message = "Visita non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
              JOptionPane.ERROR_MESSAGE);
              new Exception("Visita non esiste");
           } 
          else {
          String insert = "update "+ tableName +" set Codice_Visita, CF_fisioterapista=? , CF_cliente=?, Esito=?, Data=? where Codice_Visita= ?";
         
          try {
    
              statement = connection.prepareStatement(insert);
              statement.setString(1, v.getCFf());
              statement.setString(2, v.getCFc());
              statement.setString(3, v.getEsito());
              statement.setDate  (4, v.getData());
              statement.setInt   (5, v.getCodiceVisita());
              statement.executeUpdate();
              
              if(ct.findByPrimaryKey(v.getCFc()) == null || ft.findByPrimaryKey(v.getCFf()) == null) {
            	  String message = "Cliente oppure fisioterapista non esistente.";
                  JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                  JOptionPane.ERROR_MESSAGE);
                  new Exception("Cliente oppure fisioterapista non esistente.");
              }
              else {
                  statement.executeUpdate();
                  JOptionPane.showMessageDialog(null, "Visita aggiornato");
              }
              
          } catch (SQLException e) {
              e.printStackTrace();
            }
          }
      }
      
      public void delete(Integer codiceVista) {
          Connection connection = DBConnection.getMsSQLConnection();

          PreparedStatement statement = null;
          String insert = "delete from "+ tableName +" where Codice_Visita = ?";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt(1, codiceVista);
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
      
      public Visita findByPrimaryKey(Integer codiceVista)  {
          Visita v = null;
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName +" where Codice_Visita=? ";
          try {
              statement = connection.prepareStatement(query);
              statement.setInt(1, codiceVista);
              ResultSet result = statement.executeQuery();
              if (result.next()) {
                  v = new Visita();
                  v.setCodiceVisita(result.getInt("Codice_Visita"));
                  v.setCFf(result.getString("CF_fisioterapista"));
                  v.setCFc(result.getString("CF_cliente"));
                  v.setEsito(result.getString("Esito"));
                  v.setData(result.getDate("Data"));
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
          return v;
      }   
      
      
      public List<Visita> findAll()  {
          List<Visita> vl = null;
          Visita v = null;
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName ;
          try {
              statement = connection.prepareStatement(query);
              ResultSet result = statement.executeQuery();
              if(result.next()) {
                  vl = new LinkedList<Visita>();
                  v = new Visita();
                  v.setCodiceVisita(result.getInt("Codice_Visita"));
                  v.setCFf(result.getString("CF_fisioterapista"));
                  v.setCFc(result.getString("CF_cliente"));
                  v.setEsito(result.getString("Esito"));
                  v.setData(result.getDate("Data"));
             }
              while(result.next()) {
                  v = new Visita();
                  v.setCodiceVisita(result.getInt("Codice_Visita"));
                  v.setCFf(result.getString("CF_fisioterapista"));
                  v.setCFc(result.getString("CF_cliente"));
                  v.setEsito(result.getString("Esito"));
                  v.setData(result.getDate("Data"));
            	  vl.add(v);
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
          return vl;
      } 
}


