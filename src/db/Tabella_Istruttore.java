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

import model.Fisioterapista;
import model.Istruttore;
import model.Persona;

public class Tabella_Istruttore extends Persona {
	 
	@SuppressWarnings("unused")
     private DBConnection dataSource;
     private String tableName;
     
     public Tabella_Istruttore () {
         dataSource = new DBConnection();
         tableName="istruttori";  
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
                     + "IDistruttore int not null,"
                     + "Città char(20) not null,"
                     + "Via char(20) not null,"
                     + "NrCivico int not null,"
                     + "CAP int not null,"
                     + "Specializzazione char (20) not null,"
                     + "constraint SID_ISTRUTTORE_ID unique (IDistruttore),"
                     + "constraint ID_ISTRUTTORI_ID primary key (CF));"
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
     
     
     public void persist(Istruttore i) {
         Connection connection = DBConnection.getMsSQLConnection();

         if (findByPrimaryKey(i.getCF())!=null){
             String message = "Istruttore già inserita";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                     JOptionPane.ERROR_MESSAGE);
                  new Exception("Cliente già inserita");
         } 
         else {   
                 
         PreparedStatement statement = null; 
         String insert = "insert into "+ tableName +" (CF, Nome, Cognome, IDistruttore, Città, Via, NrCivico, CAP, Specializzazione) values (?,?,?,?,?,?,?,?,?)";
         try {
             statement = connection.prepareStatement(insert);
             statement.setString (1, i.getCF());
             statement.setString (2, i.getNome());
             statement.setString (3, i.getCognome());
             statement.setInt    (4, i.getIstruttoreID());
             statement.setString (5, i.getCittà());
             statement.setString (6, i.getVia());
			 statement.setInt    (7, i.getNrCivico());
			 statement.setInt    (8, i.getCAP());
			 statement.setString (9, i.getSpecializzazione());
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
     
     public void update(Istruttore i) {
    	  
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement;
         
         Istruttore oldIstruttore = findByPrimaryKey(i.getCF());
         if (oldIstruttore == null){
             String message = "Istruttore non esistente";
             JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
             JOptionPane.ERROR_MESSAGE);
             new Exception("Istruttore non esiste");
          } 
        
         String insert = "update "+ tableName +" set nome=?, cognome=?, IDistruttore=?, città=?, via=?, nrCivico=?, CAP=?, specializzazione=? where CF= ?";
        
         try {
       	  statement = connection.prepareStatement(insert);
             statement.setString(1, i.getNome());
             statement.setString(2, i.getCognome());
             statement.setInt   (3, i.getIstruttoreID());
             statement.setString(4, i.getCittà());
             statement.setString(5, i.getVia());
			 statement.setInt   (6, i.getNrCivico());
			 statement.setInt   (7, i.getCAP());
			 statement.setString(8, i.getSpecializzazione());
             statement.setString(9, i.getCF());
			 statement.executeUpdate();
             
         } catch (SQLException e) {
             e.printStackTrace();
           }
         JOptionPane.showMessageDialog(null, "Istruttore aggiornato");

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
     
     
     public Istruttore findByPrimaryKey(String CF)  {
         Istruttore istruttore = null;
         
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName +" where CF=? ";
         try {
             statement = connection.prepareStatement(query);
             statement.setString(1, CF);
             ResultSet result = statement.executeQuery();
             if (result.next()) {
            	 istruttore = new Istruttore();
            	 istruttore.setCF(result.getString("CF"));
            	 istruttore.setNome(result.getString("nome"));
            	 istruttore.setCognome(result.getString("cognome"));
            	 istruttore.setIstruttoreID(result.getInt("IDistruttore"));
            	 istruttore.setCittà(result.getString("città"));
            	 istruttore.setVia(result.getString("via"));
            	 istruttore.setNrCivico(result.getInt("nrCivico"));
            	 istruttore.setCAP(result.getInt("CAP"));
            	 istruttore.setSpecializzazione(result.getString("specializzazione"));
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
         return istruttore;
     }   
     
     
     public List<Istruttore> findAll()  {
         List<Istruttore> istruttori = null;
         Istruttore istruttore = null;
         Connection connection = DBConnection.getMsSQLConnection();
         PreparedStatement statement = null;
         String query = "select * from "+ tableName ;
         try {
             statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery();
             if(result.next()) {
            	 istruttori = new LinkedList<Istruttore>();
            	 istruttore = new Istruttore();
            	 istruttore.setCF(result.getString("CF"));
            	 istruttore.setNome(result.getString("nome"));
            	 istruttore.setCognome(result.getString("cognome"));
            	 istruttore.setIstruttoreID(result.getInt("IDistruttore"));
            	 istruttore.setCittà(result.getString("città"));
            	 istruttore.setVia(result.getString("via"));
            	 istruttore.setNrCivico(result.getInt("nrCivico"));
            	 istruttore.setCAP(result.getInt("CAP"));
            	 istruttore.setSpecializzazione(result.getString("specializzazione"));
             }
             while(result.next()) {
            	 istruttore = new Istruttore();
            	 istruttore.setCF(result.getString("CF"));
            	 istruttore.setNome(result.getString("nome"));
            	 istruttore.setCognome(result.getString("cognome"));
            	 istruttore.setCittà(result.getString("città"));
            	 istruttore.setVia(result.getString("via"));
            	 istruttore.setNrCivico(result.getInt("nrCivico"));
            	 istruttore.setCAP(result.getInt("CAP"));
            	 istruttore.setSpecializzazione(result.getString("specializzazione"));
            	 istruttori.add(istruttore);
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
         return istruttori;
     }



}

