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

import model.Carta_Cliente;
import model.Settore;

public class Tabella_CartaCliente {
	 @SuppressWarnings("unused")
     private DBConnection dataSource;
     private String tableName;
     
     public Tabella_CartaCliente () {
         dataSource = new DBConnection();
         tableName="carte_clienti";  
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
                     + "Codice_Carta int not null,"
                     + "CF_cliente char (16) not null,"
                     + "Punti int not null,"
                     + "constraint SID_CLIENTE_ID unique (CF_cliente),"
                     + "constraint FKPER_CLI_ID primary key (Codice_Carta));"
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
     
     
     public void persist(Carta_Cliente cl) {
         Connection connection = DBConnection.getMsSQLConnection();
         
         Tabella_Cliente ct = new Tabella_Cliente();
         Tabella_CartaCliente cct = new Tabella_CartaCliente();
         if (findByPrimaryKey(cl.getCodiceCarta())!=null || ct.findByPrimaryKey(cl.getCF()) == null){
             String message = "Carta cliente già inserito oppure cliente non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                     JOptionPane.ERROR_MESSAGE);
                  new Exception("Carta cliente già inserito oppure cliente non esistente");
         } 
         else {
  
        	 PreparedStatement statement = null; 
        	 String insert = "insert into "+ tableName +" (Codice_Carta, CF_cliente, Punti) values (?,?,?)";
        	 	try {
        	 		statement = connection.prepareStatement(insert);
        	 		statement.setInt   (1, cl.getCodiceCarta());
        	 		statement.setString(2, cl.getCF());
        	 		statement.setInt   (3, cl.getPunti());
        	 		if (ct.findByPrimaryKey(cl.getCF()) == null){
        	             String message = "Carta cliente non esistente";
        	             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        	             JOptionPane.ERROR_MESSAGE);
        	             new Exception("Carta cliente non esiste");
        	          } 
        	 		
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
     
     
     public void update(Carta_Cliente cl) {
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement;
         Tabella_Cliente ct = new Tabella_Cliente();

         Carta_Cliente oldCarta = findByPrimaryKey(cl.getCodiceCarta());
         if (oldCarta == null){
             String message = "Carta cliente non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
             JOptionPane.ERROR_MESSAGE);
             new Exception("Carta cliente non esiste");
          } 
         else {
         String insert = "update "+ tableName +" set CF_cliente=?, Punti=? where Codice_Carta= ?";
        
         try {
   
       	  	statement = connection.prepareStatement(insert);
       	  	statement.setString(1, cl.getCF());
       	  	statement.setInt   (2, cl.getPunti());
       	  	statement.setInt   (3, cl.getCodiceCarta());

       	  	if(ct.findByPrimaryKey(cl.getCF()) == null) {
       	  		String message = "Cliente non esistente";
        	  	JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        	  			JOptionPane.ERROR_MESSAGE);
        	  	new Exception("Cliente non esiste");
       	  	}
          	else {
          		statement.executeUpdate();
        	  	JOptionPane.showMessageDialog(null, "Gruppo aggiornato");
          	} 
         }catch (SQLException e) {
          			e.printStackTrace();
          	}
           	
          }
      }
    	      
     public void delete(Integer codiceCarta) {
         Connection connection = DBConnection.getMsSQLConnection();

         PreparedStatement statement = null;
         String insert = "delete from "+ tableName +" where Codice_Carta = ?";
         try {
             statement = connection.prepareStatement(insert);
             statement.setInt(1, codiceCarta);
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
     
     public Carta_Cliente findByPrimaryKey(Integer codiceCarta)  {
    	 Carta_Cliente cl = null;
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName +" where Codice_Carta= ? ";
         try {
             statement = connection.prepareStatement(query);
             statement.setInt(1, codiceCarta);
             ResultSet result = statement.executeQuery();
             if (result.next()) {
                 cl = new Carta_Cliente();
                 cl.setCodiceCarta(result.getInt("Codice_Carta"));
                 cl.setCF(result.getString("CF_Cliente"));
                 cl.setPunti(result.getInt("Punti"));
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
         return cl;
     }   
     
     
     public List<Carta_Cliente> findAll()  {
         List<Carta_Cliente> cll = null;
         Carta_Cliente cl = null;
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName ;
         try {
             statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery();
             if(result.next()) {
                 cll = new LinkedList<Carta_Cliente>();
                 cl = new Carta_Cliente();
                 cl.setCodiceCarta(result.getInt("Codice_Carta"));
                 cl.setCF(result.getString("CF_Cliente"));
                 cl.setPunti(result.getInt("Punti"));
             }
             while(result.next()) {
                 cl = new Carta_Cliente();
                 cl.setCodiceCarta(result.getInt("Codice_Carta"));
                 cl.setCF(result.getString("CF_Cliente"));
                 cl.setPunti(result.getInt("Punti"));
           	  cll.add(cl);
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
         return cll;
     } 
}


