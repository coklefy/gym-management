package db;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Scheda_Allenamento;

 public class Tabella_Scheda{
	  
	 @SuppressWarnings("unused")
      private DBConnection dataSource;
      private String tableName;
      
      public Tabella_Scheda () {
          dataSource = new DBConnection();
          tableName="schede_allenamento";  
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
                	  + "Codice_Sch int not null,"
                      + "CF_Cliente char(20) not null,"
                      + "CF_Istruttore char(20) not null,"
                      + "Tipo char(20) not null,"
                      + "Altezza  int not null,"
                      + "Peso int not null,"
                      + "Grado char(20) not null,"
                      + "constraint SID_ABBONAMENTO_ID unique (CF_cliente),"
                      + "constraint ID_ABBONAMENTO_ID primary key (Codice_Sch, CF_Cliente));"
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
      
      
      public void persist(Scheda_Allenamento sa) {
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
         
          Tabella_Cliente ct = new Tabella_Cliente();
          Tabella_Istruttore it = new Tabella_Istruttore();
          if (findByPrimaryKey(sa.getCodiceScheda())!=null || ct.findByPrimaryKey(sa.getCFc()) == null || it.findByPrimaryKey(sa.getCFi()) == null){
              String message = "Scheda allenamento già compilata oppure cliente/istruttore non esistente";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                      JOptionPane.ERROR_MESSAGE);
                   new Exception("Scheda allenamento già compilata oppure cliente/istruttore non esistente");
          } 
      
          else { 
 
          String insert = "insert into "+ tableName +" (Codice_Sch, CF_Cliente, CF_Istruttore, Tipo, Altezza,  Peso, Grado) values (?,?,?,?,?,?,?)";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt   (1, sa.getCodiceScheda());
              statement.setString(2, sa.getCFc());
              statement.setString(3, sa.getCFi());
              statement.setString(4, sa.getTipo());
              statement.setInt   (5, sa.getAltezza());
              statement.setInt   (6, sa.getPeso());
              statement.setString(7, sa.getGrado());
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
      
      
      public void update(Scheda_Allenamento sa) {
	          
	          Connection connection = DBConnection.getMsSQLConnection();
	          PreparedStatement statement;
	          
	          Scheda_Allenamento oldScheda = findByPrimaryKey(sa.getCodiceScheda());
	          Tabella_Cliente ct = new Tabella_Cliente();
	          Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
	          if (oldScheda == null){
	              String message = "Scheda allenamento non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Scheda allenamento non esistente");
	           } 
	         
	      String insert = "update " + tableName +" set CF_Cliente=?, CF_Istruttore=?, Tipo=?, Peso=?, Grado=? where Codice_Sch= ?";
         
          try {
    
              statement = connection.prepareStatement(insert);
              statement.setString(1, sa.getCFc());
              statement.setString(2, sa.getCFi());
              statement.setString(3, sa.getTipo());
              statement.setInt   (4, sa.getAltezza());
              statement.setInt   (5, sa.getPeso());
              statement.setString(6, sa.getGrado());
              statement.setInt   (7, sa.getCodiceScheda());

              statement.executeUpdate();
        	  if(ct.findByPrimaryKey(sa.getCFc()) == null) {
        		  String message = "Cliente non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Cliente non esiste");
        	  }
        	  else if(ft.findByPrimaryKey(sa.getCFi()) == null) {
        		  String message = "Fisioterapista non esistente";
	              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	              JOptionPane.ERROR_MESSAGE);
	              new Exception("Fisioterapista non esiste");
        	  } 
        	  else {
              statement.executeUpdate();
    	      JOptionPane.showMessageDialog(null, "Abbonamento aggiornato");
        	  }
              
          } catch (SQLException e) {
              e.printStackTrace();
            }
      }
      
      
      public void delete(Integer codiceScheda) {
          Connection connection = DBConnection.getMsSQLConnection();

          PreparedStatement statement = null;
          String insert = "delete from "+ tableName +" where Codice_Sch = ?";
          try {
              statement = connection.prepareStatement(insert);
              statement.setInt(1, codiceScheda);
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
      
      public Scheda_Allenamento findByPrimaryKey(Integer codiceScheda)  {
          Scheda_Allenamento sa = null;
          
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          String query = "select * from "+ tableName +" where Codice_Sch=? ";
          try {
              statement = connection.prepareStatement(query);
              statement.setInt(1, codiceScheda);
              ResultSet result = statement.executeQuery();
              if (result.next()) {
                  sa = new Scheda_Allenamento();
                  sa.setCodiceScheda(result.getInt("Codice_Sch"));
                  sa.setCFc(result.getString("CF_cliente"));
                  sa.setCFi(result.getString("CF_istruttore"));
                  sa.setTipo(result.getString("Tipo"));
                  sa.setAltezza(result.getInt("Altezza"));
                  sa.setPeso(result.getInt("Peso"));
                  sa.setGrado(result.getString("Grado"));
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
          return sa;
      }   
      
      
      public List<Scheda_Allenamento> findAll()  {
         
    	  List<Scheda_Allenamento> sal = null;
          Scheda_Allenamento sa = null;
          Connection connection = DBConnection.getMsSQLConnection();
          PreparedStatement statement = null;
          
          String query = "select * from "+ tableName ;
          try {
              statement = connection.prepareStatement(query);
              ResultSet result = statement.executeQuery();
              if(result.next()) {
            	  sal = new LinkedList<Scheda_Allenamento>();
                  sa = new Scheda_Allenamento();
                  sa.setCodiceScheda(result.getInt("Codice_Sch"));
                  sa.setCFc(result.getString("CF_cliente"));
                  sa.setCFi(result.getString("CF_istruttore"));
                  sa.setTipo(result.getString("Tipo"));
                  sa.setAltezza(result.getInt("Altezza"));
                  sa.setPeso(result.getInt("Peso"));
                  sa.setGrado(result.getString("Grado"));
              }
              while(result.next()) {
            	  sa = new Scheda_Allenamento();
                  sa.setCodiceScheda(result.getInt("Codice_Sch"));
                  sa.setCFc(result.getString("CF_cliente"));
                  sa.setCFi(result.getString("CF_istruttore"));
                  sa.setTipo(result.getString("Tipo"));
                  sa.setAltezza(result.getInt("Altezza"));
                  sa.setPeso(result.getInt("Peso"));
                  sa.setGrado(result.getString("Grado"));
            	  sal.add(sa);
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
          return sal;
      } 
}



