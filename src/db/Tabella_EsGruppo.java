package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Abbonamento;
import model.Cliente;
import model.EsGruppo;
import model.Fisioterapista;
import model.Metodo_Es;


 public class Tabella_EsGruppo {
	  
	 @SuppressWarnings("unused")
      private DBConnection dataSource;
      private String tableName;
      
      public Tabella_EsGruppo () {
          dataSource = new DBConnection();
          tableName="esercitazioni_gruppo";  
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
                	  + "Codice_Esercitazione int not null,"
                	  + "CF_cliente char (16) not null,"
                      + "Codice_Gruppo int  not null,"
                      + "Codice_Settore int not null,"
                      + "constraint  SID_ESGRUPPO_ID unique (CF_cliente),"
                      + "constraint ID_ESGRUPPO_ID primary key (Codice_Esercitazione));"
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
      
      
      public void persist(EsGruppo  es) {
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
         
          Tabella_Cliente ct = new Tabella_Cliente();
          Tabella_Settore st = new Tabella_Settore();
          Tabella_MetodoEsercitazione met = new Tabella_MetodoEsercitazione();
          Metodo_Es mes = new Metodo_Es();
          Tabella_Gruppo gt = new Tabella_Gruppo();
          if (findByPrimaryKey(es.getCodiceEsercitazione())!=null || gt.findByPrimaryKey(es.getCodiceGruppo()) == null|| st.findByPrimaryKey(es.getCodiceSettore()) == null|| ct.findByPrimaryKey(es.getCFc()) == null){
              String message = "Esercitazione già inserita oppure gruppo / settore / cliente non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                      JOptionPane.ERROR_MESSAGE);
                   new Exception("Esercitazione già inserita oppure gruppo / settore / cliente non esistente");
          } 
      
          else { 
 
          String insert = "insert into "+ tableName +" (Codice_Esercitazione, CF_cliente,Codice_Gruppo, Codice_Settore) values (?,?,?,?)";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt   (1, es.getCodiceEsercitazione());
              statement.setString(2, es.getCFc());
              statement.setInt	 (3, es.getCodiceGruppo());
              statement.setInt	 (4, es.getCodiceSettore());
              mes = met.findByPrimaryKey(es.getCFc(), "da solo");
              if(mes == null) {
                  statement.executeUpdate();
              }
              else {
            	  String message = "Cliente ha scelto di esercitarsi sa solo";
                  JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                          JOptionPane.ERROR_MESSAGE);
                       new Exception("Cliente ha scelto di esercitarsi sa solo");
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
      
      
      public void update(EsGruppo es) {
	          
	          Connection connection = DBConnection.getMsSQLConnection();
	          PreparedStatement statement;
	          
	          EsGruppo oldEsercitazione = findByPrimaryKey(es.getCodiceEsercitazione());
	          Tabella_Cliente ct = new Tabella_Cliente();
	          Tabella_Gruppo gt = new Tabella_Gruppo();
	          Tabella_Settore st = new Tabella_Settore();
	          if (oldEsercitazione == null){
	              String message = "Esercitazione non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Esercitazione non esiste");
	           } 
	         
	      String insert = "update " + tableName +" set CF_cliente=?, Codice_Gruppo=?, Codice_Settore=? where Codice_Esercitazione= ?";
         
          try {
    
              statement = connection.prepareStatement(insert);
              statement.setString(1, es.getCFc());
              statement.setInt	 (2, es.getCodiceGruppo());
              statement.setInt	 (3, es.getCodiceSettore());
              statement.setInt   (4, es.getCodiceEsercitazione());
              statement.executeUpdate();
        	  if(ct.findByPrimaryKey(es.getCFc()) == null) {
        		  String message = "Cliente non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Cliente non esiste");
        	  }
        	  else if(gt.findByPrimaryKey(es.getCodiceGruppo()) == null) {
        		  String message = "Gruppo non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Gruppo non esiste");
        	  }
        	  else if(st.findByPrimaryKey(es.getCodiceSettore()) == null) {
        		  String message = "Settore non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Settore non esiste");
        	  }
        	  else {
              statement.executeUpdate();
    	      JOptionPane.showMessageDialog(null, "Esercitazione aggiornato");
        	  }
              
          } catch (SQLException e) {
              e.printStackTrace();
            }
      }
      
      
      public void delete(Integer codiceEsercitazione) {
          Connection connection = DBConnection.getMsSQLConnection();

          PreparedStatement statement = null;
          String insert = "delete from "+ tableName +" where Codice_Esercitazione = ?";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt(1, codiceEsercitazione);
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
      
      public EsGruppo findByPrimaryKey(Integer codiceEsercitazione)  {
    	  EsGruppo es = null;
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName +" where Codice_Esercitazione=? ";
          try {
              statement = connection.prepareStatement(query);
              statement.setInt(1, codiceEsercitazione);
              ResultSet result = statement.executeQuery();
              if (result.next()) {
            	  es = new EsGruppo();
            	  es.setCodiceEsercitazione(result.getInt("Codice_Esercitazione"));
            	  es.setCFc(result.getString("CF_Cliente"));
            	  es.setCodiceGruppo(result.getInt("Codice_Gruppo"));
            	  es.setCodiceSettore(result.getInt("Codice_Settore"));
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
          return es;
      }   
      
      
      public List<EsGruppo> findAll()  {
          List<EsGruppo> esl = null;
          EsGruppo es = null;
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName ;
          try {
              statement = connection.prepareStatement(query);
              ResultSet result = statement.executeQuery();
              if(result.next()) {
                  esl = new LinkedList<EsGruppo>();
            	  es = new EsGruppo();
            	  es.setCodiceEsercitazione(result.getInt("Codice_Esercitazione"));
            	  es.setCFc(result.getString("CF_Cliente"));
            	  es.setCodiceGruppo(result.getInt("Codice_Gruppo"));
            	  es.setCodiceSettore(result.getInt("Codice_Settore"));
              }
              while(result.next()) {
            	  es = new EsGruppo();
            	  es.setCodiceEsercitazione(result.getInt("Codice_Esercitazione"));
            	  es.setCFc(result.getString("CF_Cliente"));
            	  es.setCodiceGruppo(result.getInt("Codice_Gruppo"));
            	  es.setCodiceSettore(result.getInt("Codice_Settore"));
            	  esl.add(es);
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
          return esl;
      } 
}


