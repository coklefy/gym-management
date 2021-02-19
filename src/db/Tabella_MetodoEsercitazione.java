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

import model.Metodo_Es;

public class Tabella_MetodoEsercitazione {
	 @SuppressWarnings("unused")
     private DBConnection dataSource;
     private String tableName;
     
     public Tabella_MetodoEsercitazione () {
         dataSource = new DBConnection();
         tableName="metodi_esercitazione";  
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
                     + "CF_cliente char(16) not null,"
                     + "Metodo char(20) not null,"
                     + "Codice_Settore int not null,"
                     + "constraint SID_METODESERCITAZIONE_ID unique (CF_cliente)," 
                     + "constraint ID_METODO_ESERCITAZIONE_ID primary key (metodo, CF_cliente ));"
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
     
     
     public void persist(Metodo_Es me) {
         Connection connection = DBConnection.getMsSQLConnection();

         if (findByPrimaryKey(me.getCF(), me.getMetodo()) !=null){
             String message = "Metodo esercitazione del cliente già inserito";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                     JOptionPane.ERROR_MESSAGE);
                  new Exception("Metodo esercitazione del cliente già inserito");
         } 
         else {   
                 
         PreparedStatement statement = null; 
         String insert = "insert into "+ tableName +" (CF_cliente, Metodo, Codice_Settore) values (?,?,?)";
         try {
             statement = connection.prepareStatement(insert);
             statement.setString(1, me.getCF());
             statement.setString(2, me.getMetodo());
             statement.setInt   (3, me.getCodiceSettore());
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
     
     public void update(Metodo_Es me) {
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement;
         
         Metodo_Es oldMetodo = findByPrimaryKey(me.getCF(), me.getMetodo());
         if (oldMetodo == null){
             String message = "Metodo esercitazione del cliente non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
             JOptionPane.ERROR_MESSAGE);
             new Exception("Metodo esercitazione del cliente non esistente");
          } 
        
         String insert = "update "+ tableName +" set Codice_Settore where CF_cliente= ? && Metodo= ?";
        
         try {
   
             statement = connection.prepareStatement(insert);
             statement.setInt   (1, me.getCodiceSettore());
             statement.setString(2, me.getCF());
             statement.setString(3, me.getMetodo());
          statement.executeUpdate();
             
         } catch (SQLException e) {
             e.printStackTrace();
          }
        JOptionPane.showMessageDialog(null, "Metodo aggiornato aggiornato");

      }
    	      
     public void delete (String CF, String metodo) {
         Connection connection = DBConnection.getMsSQLConnection();

         PreparedStatement statement = null;
         String insert = "delete from "+ tableName +" where CF_cliente= ? && Metodo= ?";
         try {
             statement = connection.prepareStatement(insert);
             statement.setString(1, CF);
             statement.setString(2,  metodo);
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
     
     public Metodo_Es findByPrimaryKey(String CF, String metodo)  {
    	 Metodo_Es me = null;
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName +"  where CF_cliente= ? && Metodo= ?";
         try {
             statement = connection.prepareStatement(query);
             statement.setString(1, CF);
             statement.setString(2,  metodo);
             ResultSet result = statement.executeQuery();
             if (result.next()) {
                 me = new Metodo_Es();
                 me.setCF(result.getString("CF"));
                 me.setMetodo(result.getString("Metodo"));
                 me.setCodiceSettore(result.getInt("Codice_Settore"));
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
         return me;
     }   
     
     
     public List<Metodo_Es> findAll()  {
         List<Metodo_Es> mel = null;
         Metodo_Es me = null;
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName ;
         try {
             statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery();
             if(result.next()) {
                 mel = new LinkedList<Metodo_Es>();
                 me = new Metodo_Es();
                 me.setCF(result.getString("CF"));
                 me.setMetodo(result.getString("Metodo"));
                 me.setCodiceSettore(result.getInt("Codice_Settore"));
             }
             while(result.next()) {
                 me = new Metodo_Es();
                 me.setCF(result.getString("CF"));
                 me.setMetodo(result.getString("Metodo"));
                 me.setCodiceSettore(result.getInt("Codice_Settore"));
           	  mel.add(me);
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
         return mel;
     } 
}


