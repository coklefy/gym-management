package db;

import java.awt.BorderLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Abbonamento;
import model.Carta_Cliente;
import model.Cliente;
import model.Controllo_Macchinario;
import model.EsGruppo;
import model.Fisioterapista;
import model.Gruppo;
import db.Tabella_Istruttore;
import model.Istruttore;
import model.Macchinario;
import model.Metodo_Es;
import model.Reception;
import model.Scheda_Allenamento;
import model.Settore;
import model.Visita;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabbedPane extends JFrame {

	private static final long serialVersionUID = 7979097674893931055L;
	private JTextField nome;
	private JPasswordField passwordField;
	
	  @SuppressWarnings("deprecation")
	
	  public TabbedPane(){
		  
		DBConnection dataSource = null;
        @SuppressWarnings({ "static-access", "unused" }) 
        Connection connection = (Connection) dataSource.getMsSQLConnection();
        
        JPanel firstPanel = new JPanel();
        JLabel username = new JLabel("Username /");
        username.setFont(new Font("Time", Font.BOLD,20));
        username.setBounds(200, 100, 178, 31);
        JLabel password = new JLabel("Password");
        password.setFont(new Font("Time", Font.BOLD, 20));
        password.setBounds(320, 100, 162, 42);
        firstPanel.add(username);
        firstPanel.add(password);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(60, 20, 1032, 850);

        panel.add(tabbedPane);
        panel.setSize(1000, 1000);
        
        nome = new JTextField();
        nome.setBounds(500, 500, 105, 35);
        firstPanel.add(nome);
        nome.setColumns(15);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 500, 105, 35);
        firstPanel.add(passwordField);
        passwordField.setColumns(15);

        JLabel lblNewLabel = new JLabel("");
        Image img = new ImageIcon(this.getClass().getResource("/technogym.png")).getImage();
        lblNewLabel.setIcon(new ImageIcon(img));
        lblNewLabel.setBounds(0, 0, 600, 500);
        firstPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        JButton login = new JButton("");
        Image img1 = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
        login.setIcon(new ImageIcon(img1));
        login.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        login.setBounds(639, 25, 86, 48);
        firstPanel.add(login);
        lblNewLabel_1.setBounds(20, 20, 20, 20);
        firstPanel.add(lblNewLabel_1);
        
       

        
        /***********************  CLIENTE  ******************************/
        
        JPanel jpP = new JPanel();
        jpP.setLayout(null);
        
        JScrollPane scrollP = new JScrollPane();
        scrollP.setBounds(6, 31, 950, 330);
        jpP.add(scrollP);
        
        JTable tableC = new JTable();
        tableC.setRowHeight(23);
        scrollP.setViewportView(tableC);

        
        jpP.add(tableC.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadC = new JButton("Load dati");
        loadC.setFont(new Font("Arial", Font.BOLD, 16));
        loadC.setBounds(530, 730, 126, 23);
        JButton updateC = new JButton("Aggiorna dati");
        updateC.setFont(new Font("Arial", Font.BOLD, 16));
        updateC.setBounds(350, 730, 160, 23);
        JButton delC = new JButton("Cancella dati");
        delC.setFont(new Font("Arial", Font.BOLD, 16));
        delC.setBounds(185, 730, 145, 23);
        JButton insertC = new JButton("Inserisci cliente");
        insertC.setFont(new Font("Arial", Font.BOLD, 16));
        insertC.setBounds(16, 730, 160, 23);
        
        JLabel jlWelcome = new JLabel("Compila per inserire / cancellare dati: ");
        jlWelcome.setFont(new Font("Times", Font.BOLD,14));
        jlWelcome.setBounds(100, 380, 400, 20);
        JLabel jlCF = new JLabel("CF");
        jlCF.setFont(new Font("Times", Font.BOLD,14));
        jlCF.setBounds(20, 410, 97, 20);
        JTextField jtfP = new JTextField();
        jtfP.setBounds(140, 410, 97, 20);
        jtfP.setText("");
        
        JLabel jlNome = new JLabel("NOME");
        jlNome.setFont(new Font("Times", Font.BOLD,14));
        jlNome.setBounds(20, 430, 97, 20);
        JTextField jtfP1 = new JTextField();
        jtfP1.setBounds(140, 430, 97, 20);
        jtfP1.setText("");
        
        JLabel jlCognome = new JLabel("COGNOME");
        jlCognome.setFont(new Font("Times", Font.BOLD,14));
        jlCognome.setBounds(20, 450, 97, 20);
        JTextField jtfP2 = new JTextField();
        jtfP2.setBounds(140, 450, 97, 20);
        jtfP2.setText("");
        
        JLabel jlIDcliente = new JLabel("ID_CLIENTE");
        jlIDcliente.setFont(new Font("Times", Font.BOLD,14));
        jlIDcliente.setBounds(20, 470, 97, 20);
        JTextField jtfP3 = new JTextField();
        jtfP3.setBounds(140, 470, 97, 20);
        jtfP3.setText("");
        
        JLabel jlCitt‡ = new JLabel("CITTA");
        jlCitt‡.setFont(new Font("Times", Font.BOLD,14));
        jlCitt‡.setBounds(20, 490, 97, 20);
        JTextField jtfP4 = new JTextField();
        jtfP4.setBounds(140, 490, 97, 20);
        jtfP4.setText("");
        
        JLabel jlVia = new JLabel("VIA");
        jlVia.setFont(new Font("Times", Font.BOLD,14));
        jlVia.setBounds(20, 510, 97, 20);
        JTextField jtfP5 = new JTextField();
        jtfP5.setBounds(140, 510, 97, 20);
        jtfP5.setText("");
        
        JLabel jlNrCivico = new JLabel("NrCIVICO");
        jlNrCivico.setFont(new Font("Times", Font.BOLD,14));
        jlNrCivico.setBounds(20, 530, 97, 20);
        JTextField jtfP6 = new JTextField();
        jtfP6.setBounds(140, 530, 97, 20);
        jtfP6.setText("");
        
        JLabel jlCAP = new JLabel("CAP");
        jlCAP.setFont(new Font("Times", Font.BOLD,14));
        jlCAP.setBounds(20, 550, 97, 20);
        JTextField jtfP7 = new JTextField();
        jtfP7.setBounds(140, 550, 97, 20);
        jtfP7.setText("");
        
        // Button to clear the text fields
        JLabel jlClear = new JLabel("Clica per pulire le aree di inseriemto");
        jlClear.setFont(new Font("Times", Font.BOLD,14));
        jlClear.setBounds(650, 570, 400, 20);
        JButton b = new JButton("CLEAR");
        b.setFont(new Font("Times", Font.BOLD,14));
        b.setBounds(700, 600, 100, 70);
		b.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfP.setText("");
		        jtfP1.setText("");
		        jtfP2.setText("");
		        jtfP3.setText("");
		        jtfP4.setText("");
		        jtfP5.setText("");
		        jtfP6.setText("");		        
		        jtfP7.setText("");
		    }
		});
		
		jpP.add(jlClear);
        jpP.add(b); // buttore per pulire 
        jpP.add(jlCF);
        jpP.add(jlNome);
        jpP.add(jlCognome);
        jpP.add(jlIDcliente);
        jpP.add(jlCitt‡);
        jpP.add(jlVia);
        jpP.add(jlNrCivico);
        jpP.add(jlCAP);
        
        jpP.add(jlWelcome);
        jpP.add(jtfP);
        jpP.add(jtfP1);
        jpP.add(jtfP2);
        jpP.add(jtfP3);
        jpP.add(jtfP4);
        jpP.add(jtfP5);
        jpP.add(jtfP6);
        jpP.add(jtfP7);
        
        jpP.add(insertC);
        jpP.add(delC);
        jpP.add(updateC);
        jpP.add(loadC);
        
        
        delC.addActionListener(e->{
            
        	jtfP7.setVisible(false);
        	jtfP6.setVisible(false);
        	jtfP5.setVisible(false);
        	jtfP4.setVisible(false);
        	jtfP3.setVisible(false);
        	jtfP2.setVisible(false);
        	jtfP1.setVisible(false);
        	
        	jlCAP.setVisible(false);
        	jlNrCivico.setVisible(false);
        	jlVia.setVisible(false);
        	jlCitt‡.setVisible(false);
        	jlIDcliente.setVisible(false);
        	jlCognome.setVisible(false);
        	jlNome.setVisible(false);
        	
            Tabella_Cliente c= new Tabella_Cliente();
            c.delete(jtfP.getText());
            PreparedStatement statement = null;
            String query = "select * from clienti";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableC.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableC.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableC.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableC.getColumnModel().getColumn(7).setPreferredWidth(30);        	       

                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadC.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from clienti";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableC.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableC.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableC.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableC.getColumnModel().getColumn(7).setPreferredWidth(30);        	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateC.addActionListener(e->{
        	jtfP7.setVisible(true);
        	jtfP6.setVisible(true);
        	jtfP5.setVisible(true);
        	jtfP4.setVisible(true);
        	jtfP3.setVisible(true);
        	jtfP2.setVisible(true);
        	jtfP1.setVisible(true);
        	
        	jlCAP.setVisible(true);
        	jlNrCivico.setVisible(true);
        	jlVia.setVisible(true);
        	jlCitt‡.setVisible(true);
        	jlIDcliente.setVisible(true);
        	jlCognome.setVisible(true);
        	jlNome.setVisible(true);
        	
             if (jtfP.getText().length()!=16 || check(jtfP1.getText()) || check(jtfP2.getText())  || !check(jtfP3.getText()) 
            		|| check(jtfP4.getText())|| check(jtfP5.getText()) || !check(jtfP6.getText()) || !check(jtfP7.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else {
              Tabella_Cliente ct = new Tabella_Cliente();
              Cliente cl = new Cliente();
            	cl.setCF(jtfP.getText());
          		cl.setNome(jtfP1.getText());
          		cl.setCognome(jtfP2.getText());
          		cl.setClienteID(Integer.parseInt(jtfP3.getText()));
          		cl.setCitt‡(jtfP4.getText());
          		cl.setVia(jtfP5.getText());
          		cl.setNrCivico(Integer.parseInt(jtfP6.getText()));
          		cl.setCAP(Integer.parseInt(jtfP7.getText()));

          		ct.update(cl);
   
              PreparedStatement statement = null;
              String query = "select * from clienti";
              
                  try {
                  	statement = (PreparedStatement) connection.prepareStatement(query);
          			ResultSet rs = statement.executeQuery();
          			tableC.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableC.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableC.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableC.getColumnModel().getColumn(7).setPreferredWidth(30); 
                    
                  } catch (Exception e1) {
                      e1.printStackTrace();
                  }   
            }
		        jtfP.setText("");
		        jtfP1.setText("");
		        jtfP2.setText("");
		        jtfP3.setText("");
		        jtfP4.setText("");
		        jtfP5.setText("");
		        jtfP6.setText("");		        
		        jtfP7.setText("");
          });
        
        insertC.addActionListener(e->{
        	jtfP7.setVisible(true);
        	jtfP6.setVisible(true);
        	jtfP5.setVisible(true);
        	jtfP4.setVisible(true);
        	jtfP3.setVisible(true);
        	jtfP2.setVisible(true);
        	jtfP1.setVisible(true);
        	
        	jlCAP.setVisible(true);
        	jlNrCivico.setVisible(true);
        	jlVia.setVisible(true);
        	jlCitt‡.setVisible(true);
        	jlIDcliente.setVisible(true);
        	jlCognome.setVisible(true);
        	jlNome.setVisible(true);
        	  
        	if (jtfP.getText().length()>=16 || check(jtfP1.getText()) || check(jtfP2.getText())  || !check(jtfP3.getText()) 
              		|| check(jtfP4.getText())|| check(jtfP5.getText()) || !check(jtfP6.getText()) || !check(jtfP7.getText())){
                  String message = "Errore nel inserimento dei campi ";
                  JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                          JOptionPane.ERROR_MESSAGE);
            }
        	else {	
            	Tabella_Cliente c = new Tabella_Cliente();
            	Cliente cl = new Cliente();
            	cl.setCF(jtfP.getText());
            	cl.setNome(jtfP1.getText());
            	cl.setCognome(jtfP2.getText());
            	cl.setClienteID(Integer.parseInt(jtfP3.getText()));
            	cl.setCitt‡(jtfP4.getText());
            	cl.setVia(jtfP5.getText());
            	cl.setNrCivico(Integer.parseInt(jtfP6.getText()));
            	cl.setCAP(Integer.parseInt(jtfP7.getText()));
            	
            	c.persist(cl);
            	
            	PreparedStatement statement = null;
            	String query = "select * from clienti";
            	
            		try {
            			statement = (PreparedStatement) connection.prepareStatement(query);
            			ResultSet rs = statement.executeQuery();
            			tableC.setModel(DbUtils.resultSetToTableModel(rs));
            	        tableC.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
            	        tableC.getColumnModel().getColumn(6).setPreferredWidth(30);
            	        tableC.getColumnModel().getColumn(7).setPreferredWidth(30); 
            		} catch ( Exception e1) {
            			e1.printStackTrace();
            		}
    		     jtfP.setText("");
    		     jtfP1.setText("");
    		     jtfP2.setText("");
    		     jtfP3.setText("");
    		     jtfP4.setText("");
    		     jtfP5.setText("");
    		     jtfP6.setText("");		        
    		     jtfP7.setText("");	
        	  }
           });

/**********************************************************************************************************************/

        
/******************************  FISIOTERAPISTI *************************************************************************/        
        JPanel jpF = new JPanel();
        jpF.setLayout(null);
        
        JScrollPane scrollF = new JScrollPane();
        scrollF.setBounds(6, 31, 950, 330);
        jpF.add(scrollF);
        
        JTable tableF = new JTable();
        tableF.setRowHeight(23);
        scrollF.setViewportView(tableF);

        jpF.add(tableF.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadF = new JButton("Load dati");
        loadF.setFont(new Font("Arial", Font.BOLD, 16));
        loadF.setBounds(550, 730, 126, 23);
        JButton updateF = new JButton("Aggiorna dati");
        updateF.setFont(new Font("Arial", Font.BOLD, 16));
        updateF.setBounds(370, 730, 160, 23);
        JButton delF = new JButton("Cancella dati");
        delF.setFont(new Font("Arial", Font.BOLD, 16));
        delF.setBounds(205, 730, 145, 23);
        JButton insertF = new JButton("Inserisci fisioterapista");
        insertF.setFont(new Font("Arial", Font.BOLD, 16));
        insertF.setBounds(16, 730, 180, 23);
        
        JLabel jlWelcomeF = new JLabel("Compila per inserire / cancellare dati: ");
        jlWelcomeF.setFont(new Font("Times", Font.BOLD,14));
        jlWelcomeF.setBounds(100, 380, 400, 20);
        JLabel jlCFF = new JLabel("CF");
        jlCFF.setFont(new Font("Times", Font.BOLD,14));
        jlCFF.setBounds(20, 410, 97, 20);
        JTextField jtfF = new JTextField();
        jtfF.setBounds(140, 410, 97, 20);
        jtfF.setText("");
        
        JLabel jlNomeF = new JLabel("NOME");
        jlNomeF.setFont(new Font("Times", Font.BOLD,14));
        jlNomeF.setBounds(20, 430, 97, 20);
        JTextField jtfF1 = new JTextField();
        jtfF1.setBounds(140, 430, 97, 20);
        jtfF1.setText("");
        
        JLabel jlCognomeF = new JLabel("COGNOME");
        jlCognomeF.setFont(new Font("Times", Font.BOLD,14));
        jlCognomeF.setBounds(20, 450, 97, 20);
        JTextField jtfF2 = new JTextField();
        jtfF2.setBounds(140, 450, 97, 20);
        jtfF2.setText("");
        
        JLabel jlIDfisioterapista = new JLabel("ID_FISIOTERAP.");
        jlIDfisioterapista.setFont(new Font("Times", Font.BOLD,14));
        jlIDfisioterapista.setBounds(20, 470, 135, 20);
        JTextField jtfF3 = new JTextField();
        jtfF3.setBounds(140, 470, 97, 20);
        jtfF3.setText("");
        
        JLabel jlCitt‡F = new JLabel("CITTA");
        jlCitt‡F.setFont(new Font("Times", Font.BOLD,14));
        jlCitt‡F.setBounds(20, 490, 97, 20);
        JTextField jtfF4 = new JTextField();
        jtfF4.setBounds(140, 490, 97, 20);
        jtfF4.setText("");
        
        JLabel jlViaF = new JLabel("VIA");
        jlViaF.setFont(new Font("Times", Font.BOLD,14));
        jlViaF.setBounds(20, 510, 97, 20);
        JTextField jtfF5 = new JTextField();
        jtfF5.setBounds(140, 510, 97, 20);
        jtfF5.setText("");
        
        JLabel jlNrCivicoF = new JLabel("NrCIVICO");
        jlNrCivicoF.setFont(new Font("Times", Font.BOLD,14));
        jlNrCivicoF.setBounds(20, 530, 97, 20);
        JTextField jtfF6 = new JTextField();
        jtfF6.setBounds(140, 530, 97, 20);
        jtfF6.setText("");
        
        JLabel jlCAPF = new JLabel("CAP");
        jlCAPF.setFont(new Font("Times", Font.BOLD,14));
        jlCAPF.setBounds(20, 550, 97, 20);
        JTextField jtfF7 = new JTextField();
        jtfF7.setBounds(140, 550, 97, 20);
        jtfF7.setText("");
        
        JLabel jlClearF = new JLabel("Clica per pulire le aree di inseriemto");
        jlClearF.setFont(new Font("Times", Font.BOLD,14));
        jlClearF.setBounds(650, 570, 400, 20);
        JButton bF = new JButton("CLEAR");
        bF.setFont(new Font("Times", Font.BOLD,14));
        bF.setBounds(700, 600, 100, 70);
		bF.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfF.setText("");
		        jtfF1.setText("");
		        jtfF2.setText("");
		        jtfF3.setText("");
		        jtfF4.setText("");
		        jtfF5.setText("");
		        jtfF6.setText("");		        
		        jtfF7.setText("");
		    }
		});
		
		jpF.add(jlClearF);
		jpF.add(bF); // buttore per pulire 
		jpF.add(jlCFF);
		jpF.add(jlNomeF);
		jpF.add(jlCognomeF);
		jpF.add(jlIDfisioterapista);
		jpF.add(jlCitt‡F);
		jpF.add(jlViaF);
		jpF.add(jlNrCivicoF);
		jpF.add(jlCAPF);
		jpF.add(jtfF);
       
		jpF.add(jlWelcomeF);
		jpF.add(jtfF);
		jpF.add(jtfF1);
		jpF.add(jtfF2);
		jpF.add(jtfF3);
		jpF.add(jtfF4);
		jpF.add(jtfF5);
		jpF.add(jtfF6);
		jpF.add(jtfF7);

		
		jpF.add(insertF);
		jpF.add(delF);
		jpF.add(updateF);
		jpF.add(loadF);
        
        
        delF.addActionListener(e->{
            
        	jtfF7.setVisible(false);
        	jtfF6.setVisible(false);
        	jtfF5.setVisible(false);
        	jtfF4.setVisible(false);
        	jtfF3.setVisible(false);
        	jtfF2.setVisible(false);
        	jtfF1.setVisible(false);
        
        	jlCAPF.setVisible(false);
        	jlNrCivicoF.setVisible(false);
        	jlViaF.setVisible(false);
        	jlCitt‡F.setVisible(false);
        	jlIDfisioterapista.setVisible(false);
        	jlCognomeF.setVisible(false);
        	jlNomeF.setVisible(false);
        	
            Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
            ft.delete(jtfF.getText());
            PreparedStatement statement = null;
            String query = "select * from fisioterapisti";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableF.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableF.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableF.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableF.getColumnModel().getColumn(7).setPreferredWidth(30);        	       

                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadF.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from fisioterapisti";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableF.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableF.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableF.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableF.getColumnModel().getColumn(7).setPreferredWidth(30);        	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateF.addActionListener(e->{
        	jtfF7.setVisible(true);
        	jtfF6.setVisible(true);
        	jtfF5.setVisible(true);
        	jtfF4.setVisible(true);
        	jtfF3.setVisible(true);
        	jtfF2.setVisible(true);
        	jtfF1.setVisible(true);
        	
        	jlCAPF.setVisible(true);
        	jlNrCivicoF.setVisible(true);
        	jlViaF.setVisible(true);
        	jlCitt‡F.setVisible(true);
        	jlIDfisioterapista.setVisible(true);
        	jlCognomeF.setVisible(true);
        	jlNomeF.setVisible(true);
        	
             if (jtfF.getText().length()>=16 || check(jtfF1.getText()) || check(jtfF2.getText())  || !check(jtfF3.getText()) 
            		|| check(jtfF4.getText())|| check(jtfF5.getText()) || !check(jtfF6.getText()) || !check(jtfF7.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else {
              Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
              Fisioterapista f = new Fisioterapista();
            	f.setCF(jtfF.getText());
          		f.setNome(jtfF1.getText());
          		f.setCognome(jtfF2.getText());
          		f.setFisioterapistaID(Integer.parseInt(jtfF3.getText()));
          		f.setCitt‡(jtfF4.getText());
          		f.setVia(jtfF5.getText());
          		f.setNrCivico(Integer.parseInt(jtfF6.getText()));
          		f.setCAP(Integer.parseInt(jtfF7.getText()));
          		ft.update(f);
   
              PreparedStatement statement = null;
              String query = "select * from fisioterapisti";
              
                  try {
                  	statement = (PreparedStatement) connection.prepareStatement(query);
          			ResultSet rs = statement.executeQuery();
          			tableF.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableF.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableF.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableF.getColumnModel().getColumn(7).setPreferredWidth(30); 
                    
                  } catch (Exception e1) {
                      e1.printStackTrace();
                  }   
            }
		        jtfF.setText("");
		        jtfF1.setText("");
		        jtfF2.setText("");
		        jtfF3.setText("");
		        jtfF4.setText("");
		        jtfF5.setText("");
		        jtfF6.setText("");		        
		        jtfF7.setText("");
          });
        
        insertF.addActionListener(e->{
        	jtfF.setVisible(true);
        	jtfF7.setVisible(true);
        	jtfF6.setVisible(true);
        	jtfF5.setVisible(true);
        	jtfF4.setVisible(true);
        	jtfF3.setVisible(true);
        	jtfF2.setVisible(true);
        	jtfF1.setVisible(true);
        	
        	jlCAPF.setVisible(true);
        	jlNrCivicoF.setVisible(true);
        	jlViaF.setVisible(true);
        	jlCitt‡F.setVisible(true);
        	jlIDfisioterapista.setVisible(true);
        	jlCognomeF.setVisible(true);
        	jlNomeF.setVisible(true);
        	
        	if (jtfF.getText().length()>=16 || check(jtfF1.getText()) || check(jtfF2.getText())  || !check(jtfF3.getText()) 
              		|| check(jtfF4.getText())|| check(jtfF5.getText()) || !check(jtfF6.getText()) || !check(jtfF7.getText())){
                  String message = "Errore nel inserimento dei campi ";
                  JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                          JOptionPane.ERROR_MESSAGE);
            }
        	else {
            	Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
            	Fisioterapista f = new Fisioterapista();
            	f.setCF(jtfF.getText());
            	f.setNome(jtfF1.getText());
            	f.setCognome(jtfF2.getText());
            	f.setFisioterapistaID(Integer.parseInt(jtfF3.getText()));
            	f.setCitt‡(jtfF4.getText());
            	f.setVia(jtfF5.getText());
            	f.setNrCivico(Integer.parseInt(jtfF6.getText()));
            	f.setCAP(Integer.parseInt(jtfF7.getText()));
            	
            	ft.persist(f);
            	
            	PreparedStatement statement = null;
            	String query = "select * from fisioterapisti";
            	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableF.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableF.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableF.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableF.getColumnModel().getColumn(7).setPreferredWidth(30);        	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        		
		        jtfF.setText("");
		        jtfF1.setText("");
		        jtfF2.setText("");
		        jtfF3.setText("");
		        jtfF4.setText("");
		        jtfF5.setText("");
		        jtfF6.setText("");		        
		        jtfF7.setText("");
        	 }
           });
/*************************************************************************************************************************/
        
        /******************************  ISTRUTTORE *************************************************************************/        
        JPanel jpI = new JPanel();
        jpI.setLayout(null);
        
        JScrollPane scrollI = new JScrollPane();
        scrollI.setBounds(6, 31, 950, 330);
        jpI.add(scrollI);
        
        JTable tableI = new JTable();
        tableI.setRowHeight(23);
        scrollI.setViewportView(tableI);

        jpI.add(tableI.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadI = new JButton("Load dati");
        loadI.setFont(new Font("Arial", Font.BOLD, 16));
        loadI.setBounds(550, 730, 126, 23);
        JButton updateI = new JButton("Aggiorna dati");
        updateI.setFont(new Font("Arial", Font.BOLD, 16));
        updateI.setBounds(370, 730, 160, 23);
        JButton delI = new JButton("Cancella dati");
        delI.setFont(new Font("Arial", Font.BOLD, 16));
        delI.setBounds(205, 730, 145, 23);
        JButton insertI = new JButton("Inserisci istruttore");
        insertI.setFont(new Font("Arial", Font.BOLD, 16));
        insertI.setBounds(16, 730, 180, 23);
        
        JLabel jlWelcomeI = new JLabel("Compila per inserire / cancellare dati: ");
        jlWelcomeI.setFont(new Font("Times", Font.BOLD,14));
        jlWelcomeI.setBounds(100, 380, 400, 20);
        JLabel jlCFI = new JLabel("CF");
        jlCFI.setFont(new Font("Times", Font.BOLD,14));
        jlCFI.setBounds(20, 410, 97, 20);
        JTextField jtfI = new JTextField();
        jtfI.setBounds(140, 410, 97, 20);
        jtfI.setText("");
        
        JLabel jlNomeI = new JLabel("NOME");
        jlNomeI.setFont(new Font("Times", Font.BOLD,14));
        jlNomeI.setBounds(20, 430, 97, 20);
        JTextField jtfI1 = new JTextField();
        jtfI1.setBounds(140, 430, 97, 20);
        jtfI1.setText("");
        
        JLabel jlCognomeI = new JLabel("COGNOME");
        jlCognomeI.setFont(new Font("Times", Font.BOLD,14));
        jlCognomeI.setBounds(20, 450, 97, 20);
        JTextField jtfI2 = new JTextField();
        jtfI2.setBounds(140, 450, 97, 20);
        jtfI2.setText("");
        
        JLabel jlIDistruttore = new JLabel("ID_ISTRUTTORE");
        jlIDistruttore.setFont(new Font("Times", Font.BOLD,14));
        jlIDistruttore.setBounds(20, 470, 125, 20);
        JTextField jtfI3 = new JTextField();
        jtfI3.setBounds(140, 470, 97, 20);
        jtfI3.setText("");
        
        JLabel jlCitt‡I = new JLabel("CITTA");
        jlCitt‡I.setFont(new Font("Times", Font.BOLD,14));
        jlCitt‡I.setBounds(20, 490, 97, 20);
        JTextField jtfI4 = new JTextField();
        jtfI4.setBounds(140, 490, 97, 20);
        jtfI4.setText("");
        
        JLabel jlViaI = new JLabel("VIA");
        jlViaI.setFont(new Font("Times", Font.BOLD,14));
        jlViaI.setBounds(20, 510, 97, 20);
        JTextField jtfI5 = new JTextField();
        jtfI5.setBounds(140, 510, 97, 20);
        jtfI5.setText("");
        
        JLabel jlNrCivicoI = new JLabel("NrCIVICO");
        jlNrCivicoI.setFont(new Font("Times", Font.BOLD,14));
        jlNrCivicoI.setBounds(20, 530, 97, 20);
        JTextField jtfI6 = new JTextField();
        jtfI6.setBounds(140, 530, 97, 20);
        jtfI6.setText("");
        
        JLabel jlCAPI = new JLabel("CAP");
        jlCAPI.setFont(new Font("Times", Font.BOLD,14));
        jlCAPI.setBounds(20, 550, 97, 20);
        JTextField jtfI7 = new JTextField();
        jtfI7.setBounds(140, 550, 97, 20);
        jtfI7.setText("");
        
        JLabel jlSpecI = new JLabel("Specializzazione");
        jlSpecI.setFont(new Font("Times", Font.BOLD,14));
        jlSpecI.setBounds(20, 570, 125, 20);
        
        
        
        String[] spec = new String[] { " ","muscolare", "cardio"};
        JComboBox<String> jtfI8 = new JComboBox<>(spec);
        jtfI8.setBounds(140, 570, 97, 20);
        
        JLabel jlClearI = new JLabel("Clica per pulire le aree di inseriemto");
        jlClearI.setFont(new Font("Times", Font.BOLD,14));
        jlClearI.setBounds(650, 570, 400, 20);
        JButton bI = new JButton("CLEAR");
        bI.setFont(new Font("Times", Font.BOLD,14));
        bI.setBounds(700, 600, 100, 70);
		bI.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfI.setText("");
		        jtfI1.setText("");
		        jtfI2.setText("");
		        jtfI3.setText("");
		        jtfI4.setText("");
		        jtfI5.setText("");
		        jtfI6.setText("");		        
		        jtfI7.setText("");
		        jtfI8.setSelectedIndex(0);
		    }
		});
		
		
		jpI.add(jlClearI);
		jpI.add(bI); // buttore per pulire 
		jpI.add(jlCFI);
		jpI.add(jlNomeI);
		jpI.add(jlCognomeI);
		jpI.add(jlIDistruttore);
		jpI.add(jlCitt‡I);
		jpI.add(jlViaI);
		jpI.add(jlNrCivicoI);
		jpI.add(jlCAPI);
        jpI.add(jlSpecI);
		jpI.add(jtfI);
       
		jpI.add(jlWelcomeI);
		jpI.add(jtfI);
		jpI.add(jtfI1);
		jpI.add(jtfI2);
		jpI.add(jtfI3);
		jpI.add(jtfI4);
		jpI.add(jtfI5);
		jpI.add(jtfI6);
		jpI.add(jtfI7);
        jpI.add(jtfI8);

		
		jpI.add(insertI);
		jpI.add(delI);
		jpI.add(updateI);
		jpI.add(loadI);
        
        
        delI.addActionListener(e->{
            
        	jtfI8.setVisible(false);
        	jtfI7.setVisible(false);
        	jtfI6.setVisible(false);
        	jtfI5.setVisible(false);
        	jtfI4.setVisible(false);
        	jtfI3.setVisible(false);
        	jtfI2.setVisible(false);
        	jtfI1.setVisible(false);
        	
        	jlSpecI.setVisible(false);
        	jlCAPI.setVisible(false);
        	jlNrCivicoI.setVisible(false);
        	jlViaI.setVisible(false);
        	jlCitt‡I.setVisible(false);
        	jlIDistruttore.setVisible(false);
        	jlCognomeI.setVisible(false);
        	jlNomeI.setVisible(false);
        	
            Tabella_Istruttore it = new Tabella_Istruttore();
            it.delete(jtfI.getText());
            PreparedStatement statement = null;
            String query = "select * from istruttori";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableI.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableI.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableI.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableI.getColumnModel().getColumn(7).setPreferredWidth(30);        	       

                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadI.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from istruttori";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableI.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableI.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableI.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableI.getColumnModel().getColumn(7).setPreferredWidth(30);        	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateI.addActionListener(e->{
        	jtfI8.setVisible(true);
        	jtfI7.setVisible(true);
        	jtfI6.setVisible(true);
        	jtfI5.setVisible(true);
        	jtfI4.setVisible(true);
        	jtfI3.setVisible(true);
        	jtfI2.setVisible(true);
        	jtfI1.setVisible(true);
        	
        	jlCAPI.setVisible(true);
        	jlNrCivicoI.setVisible(true);
        	jlViaI.setVisible(true);
        	jlCitt‡I.setVisible(true);
        	jlIDistruttore.setVisible(true);
        	jlCognomeI.setVisible(true);
        	jlNomeI.setVisible(true);
        	jlSpecI.setVisible(true);
        	
             if (jtfI.getText().length()>=16 || check(jtfI1.getText()) || check(jtfI2.getText())  || !check(jtfI3.getText()) 
            		|| check(jtfI4.getText())|| check(jtfI5.getText()) || !check(jtfI6.getText()) || !check(jtfI7.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else {
              Tabella_Istruttore it = new Tabella_Istruttore();
              Istruttore i = new Istruttore();
            	i.setCF(jtfI.getText());
          		i.setNome(jtfI1.getText());
          		i.setCognome(jtfI2.getText());
          		i.setIstruttoreID(Integer.parseInt(jtfI3.getText()));
          		i.setCitt‡(jtfI4.getText());
          		i.setVia(jtfI5.getText());
          		i.setNrCivico(Integer.parseInt(jtfI6.getText()));
          		i.setCAP(Integer.parseInt(jtfI7.getText()));
            	i.setSpecializzazione(jtfI8.getSelectedItem().toString());
          		it.update(i);
   
              PreparedStatement statement = null;
              String query = "select * from istruttori";
              
                  try {
                  	statement = (PreparedStatement) connection.prepareStatement(query);
          			ResultSet rs = statement.executeQuery();
          			tableI.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableI.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableI.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableI.getColumnModel().getColumn(7).setPreferredWidth(30); 
                    
                  } catch (Exception e1) {
                      e1.printStackTrace();
                  }   
            }
		        jtfI.setText("");
		        jtfI1.setText("");
		        jtfI2.setText("");
		        jtfI3.setText("");
		        jtfI4.setText("");
		        jtfI5.setText("");
		        jtfI6.setText("");		        
		        jtfI7.setText("");
          });
        
        
        insertI.addActionListener(e->{
        	jtfI.setVisible(true);
        	jtfI8.setVisible(true);
        	jtfI7.setVisible(true);
        	jtfI6.setVisible(true);
        	jtfI5.setVisible(true);
        	jtfI4.setVisible(true);
        	jtfI3.setVisible(true);
        	jtfI2.setVisible(true);
        	jtfI1.setVisible(true);
        	
        	jlSpecI.setVisible(true);
        	jlCAPI.setVisible(true);
        	jlNrCivicoI.setVisible(true);
        	jlViaI.setVisible(true);
        	jlCitt‡I.setVisible(true);
        	jlIDistruttore.setVisible(true);
        	jlCognomeI.setVisible(true);
        	jlNomeI.setVisible(true);
        	
        	 if (jtfI.getText().length()>=16 || check(jtfI1.getText()) || check(jtfI2.getText())  || !check(jtfI3.getText()) 
             		|| check(jtfI4.getText())|| check(jtfI5.getText()) || !check(jtfI6.getText()) || !check(jtfI7.getText())){
                 String message = "Errore nel inserimento dei campi ";
                 JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                         JOptionPane.ERROR_MESSAGE);
             } 
        	 else {
            	Tabella_Istruttore it = new Tabella_Istruttore();
            	Istruttore i = new Istruttore();
            	i.setCF(jtfI.getText());
            	i.setNome(jtfI1.getText());
            	i.setCognome(jtfI2.getText());
            	i.setIstruttoreID(Integer.parseInt(jtfI3.getText()));
            	i.setCitt‡(jtfI4.getText());
            	i.setVia(jtfI5.getText());
            	i.setNrCivico(Integer.parseInt(jtfI6.getText()));
            	i.setCAP(Integer.parseInt(jtfI7.getText()));
            	i.setSpecializzazione(jtfI8.getSelectedItem().toString());
            	it.persist(i);
            	
            	PreparedStatement statement = null;
            	String query = "select * from istruttori";
            	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableI.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableI.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableI.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableI.getColumnModel().getColumn(7).setPreferredWidth(30);        	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        		
		        jtfI.setText("");
		        jtfI1.setText("");
		        jtfI2.setText("");
		        jtfI3.setText("");
		        jtfI4.setText("");
		        jtfI5.setText("");
		        jtfI6.setText("");		        
		        jtfI7.setText("");
		        jtfI8.setSelectedIndex(0);
        	 }
           });       
        
        
        
        
/*************************************************************************************************************************/       
        
        /***********************  RECEPTION  ******************************/
        
        JPanel jpR = new JPanel();
        jpR.setLayout(null);
        
        JScrollPane scrollR = new JScrollPane();
        scrollR.setBounds(6, 31, 950, 330);
        jpR.add(scrollR);
        
        JTable tableR = new JTable();
        tableR.setRowHeight(23);
        scrollR.setViewportView(tableR);

        jpR.add(tableR.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadR = new JButton("Load dati");
        loadR.setFont(new Font("Arial", Font.BOLD, 16));
        loadR.setBounds(530, 730, 126, 23);
        JButton updateR = new JButton("Aggiorna dati");
        updateR.setFont(new Font("Arial", Font.BOLD, 16));
        updateR.setBounds(350, 730, 160, 23);
        JButton delR = new JButton("Cancella dati");
        delR.setFont(new Font("Arial", Font.BOLD, 16));
        delR.setBounds(185, 730, 145, 23);
        JButton insertR = new JButton("Inserisci reception");
        insertR.setFont(new Font("Arial", Font.BOLD, 16));
        insertR.setBounds(16, 730, 160, 23);
        
        JLabel jlWelcomeR = new JLabel("Compila per inserire / cancellare dati: ");
        jlWelcomeR.setFont(new Font("Times", Font.BOLD,14));
        jlWelcomeR.setBounds(100, 380, 400, 20);
        JLabel jlCFR = new JLabel("CF");
        jlCFR.setFont(new Font("Times", Font.BOLD,14));
        jlCFR.setBounds(20, 410, 97, 20);
        JTextField jtfR = new JTextField();
        jtfR.setBounds(140, 410, 97, 20);
        jtfR.setText("");
        
        JLabel jlNomeR = new JLabel("NOME");
        jlNomeR.setFont(new Font("Times", Font.BOLD,14));
        jlNomeR.setBounds(20, 430, 97, 20);
        JTextField jtfR1 = new JTextField();
        jtfR1.setBounds(140, 430, 97, 20);
        jtfR1.setText("");
        
        JLabel jlCognomeR = new JLabel("COGNOME");
        jlCognomeR.setFont(new Font("Times", Font.BOLD,14));
        jlCognomeR.setBounds(20, 450, 97, 20);
        JTextField jtfR2 = new JTextField();
        jtfR2.setBounds(140, 450, 97, 20);
        jtfR2.setText("");
        
        JLabel jlIDreception = new JLabel("ID_RECEPTION");
        jlIDreception.setFont(new Font("Times", Font.BOLD,14));
        jlIDreception.setBounds(20, 470, 120, 20);
        JTextField jtfR3 = new JTextField();
        jtfR3.setBounds(140, 470, 97, 20);
        jtfR3.setText("");
        
        JLabel jlCitt‡R = new JLabel("CITTA");
        jlCitt‡R.setFont(new Font("Times", Font.BOLD,14));
        jlCitt‡R.setBounds(20, 490, 97, 20);
        JTextField jtfR4 = new JTextField();
        jtfR4.setBounds(140, 490, 97, 20);
        jtfR4.setText("");
        
        JLabel jlViaR = new JLabel("VIA");
        jlViaR.setFont(new Font("Times", Font.BOLD,14));
        jlViaR.setBounds(20, 510, 97, 20);
        JTextField jtfR5 = new JTextField();
        jtfR5.setBounds(140, 510, 97, 20);
        jtfR5.setText("");
        
        JLabel jlNrCivicoR = new JLabel("NrCIVICO");
        jlNrCivicoR.setFont(new Font("Times", Font.BOLD,14));
        jlNrCivicoR.setBounds(20, 530, 97, 20);
        JTextField jtfR6 = new JTextField();
        jtfR6.setBounds(140, 530, 97, 20);
        jtfR6.setText("");
        
        JLabel jlCAPR = new JLabel("CAP");
        jlCAPR.setFont(new Font("Times", Font.BOLD,14));
        jlCAPR.setBounds(20, 550, 97, 20);
        JTextField jtfR7 = new JTextField();
        jtfR7.setBounds(140, 550, 97, 20);
        jtfR7.setText("");
        
        // Button to clear the text fields
        JLabel jlClearR = new JLabel("Clica per pulire le aree di inseriemto");
        jlClearR.setFont(new Font("Times", Font.BOLD,14));
        jlClearR.setBounds(650, 570, 400, 20);
        JButton bR = new JButton("CLEAR");
        bR.setFont(new Font("Times", Font.BOLD,14));
        bR.setBounds(700, 600, 100, 70);
		bR.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfR.setText("");
		        jtfR1.setText("");
		        jtfR2.setText("");
		        jtfR3.setText("");
		        jtfR4.setText("");
		        jtfR5.setText("");
		        jtfR6.setText("");		        
		        jtfR7.setText("");
		    }
		});
        
		jpR.add(jlClearR);
        jpR.add(bR); // buttore per pulire 
        jpR.add(jlCFR);
        jpR.add(jlNomeR);
        jpR.add(jlCognomeR);
        jpR.add(jlIDreception);
        jpR.add(jlCitt‡R);
        jpR.add(jlViaR);
        jpR.add(jlNrCivicoR);
        jpR.add(jlCAPR);
        
        jpR.add(jlWelcomeR);
        jpR.add(jtfR);
        jpR.add(jtfR1);
        jpR.add(jtfR2);
        jpR.add(jtfR3);
        jpR.add(jtfR4);
        jpR.add(jtfR5);
        jpR.add(jtfR6);
        jpR.add(jtfR7);
        
        jpR.add(insertR);
        jpR.add(delR);
        jpR.add(updateR);
        jpR.add(loadR);
        
        
        delR.addActionListener(e->{
            
        	jtfR7.setVisible(false);
        	jtfR6.setVisible(false);
        	jtfR5.setVisible(false);
        	jtfR4.setVisible(false);
        	jtfR3.setVisible(false);
        	jtfR2.setVisible(false);
        	jtfR1.setVisible(false);
        	
        	jlCAPR.setVisible(false);
        	jlNrCivicoR.setVisible(false);
        	jlViaR.setVisible(false);
        	jlCitt‡R.setVisible(false);
        	jlIDreception.setVisible(false);
        	jlCognomeR.setVisible(false);
        	jlNomeR.setVisible(false);
        	
            Tabella_Reception rt = new Tabella_Reception();
            rt.delete(jtfR.getText());
            PreparedStatement statement = null;
            String query = "select * from reception";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableR.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableR.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableR.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableR.getColumnModel().getColumn(7).setPreferredWidth(30);        	       

                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadR.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from reception";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableR.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableR.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableR.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableR.getColumnModel().getColumn(7).setPreferredWidth(30);        	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateR.addActionListener(e->{
        	jtfR7.setVisible(true);
        	jtfR6.setVisible(true);
        	jtfR5.setVisible(true);
        	jtfR4.setVisible(true);
        	jtfR3.setVisible(true);
        	jtfR2.setVisible(true);
        	jtfR1.setVisible(true);
        	
        	jlCAPR.setVisible(true);
        	jlNrCivicoR.setVisible(true);
        	jlViaR.setVisible(true);
        	jlCitt‡R.setVisible(true);
        	jlIDreception.setVisible(true);
        	jlCognomeR.setVisible(true);
        	jlNomeR.setVisible(true);
        	
             if (jtfR.getText().length()>=16 || check(jtfR1.getText()) || check(jtfR2.getText())  || !check(jtfR3.getText()) 
            		|| check(jtfR4.getText())|| check(jtfR5.getText()) || !check(jtfR6.getText()) || !check(jtfR7.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else {
              Tabella_Reception rt = new Tabella_Reception();
              Reception r = new Reception();
            	r.setCF(jtfR.getText());
          		r.setNome(jtfR1.getText());
          		r.setCognome(jtfR2.getText());
          		r.setReceptionID(Integer.parseInt(jtfR3.getText()));
          		r.setCitt‡(jtfR4.getText());
          		r.setVia(jtfR5.getText());
          		r.setNrCivico(Integer.parseInt(jtfR6.getText()));
          		r.setCAP(Integer.parseInt(jtfR7.getText()));
          		rt.update(r);
   
              PreparedStatement statement = null;
              String query = "select * from reception";
              
                  try {
                  	statement = (PreparedStatement) connection.prepareStatement(query);
          			ResultSet rs = statement.executeQuery();
          			tableR.setModel(DbUtils.resultSetToTableModel(rs));
        	        tableR.getColumnModel().getColumn(0).setPreferredWidth(100);        	       
        	        tableR.getColumnModel().getColumn(6).setPreferredWidth(30);
        	        tableR.getColumnModel().getColumn(7).setPreferredWidth(30); 
                    
                  } catch (Exception e1) {
                      e1.printStackTrace();
                  }   
            }
		        jtfI.setText("");
		        jtfI1.setText("");
		        jtfI2.setText("");
		        jtfI3.setText("");
		        jtfI4.setText("");
		        jtfI5.setText("");
		        jtfI6.setText("");		        
		        jtfI7.setText("");
          });
        
        
        insertR.addActionListener(e->{
        	jtfR7.setVisible(true);
        	jtfR6.setVisible(true);
        	jtfR5.setVisible(true);
        	jtfR4.setVisible(true);
        	jtfR3.setVisible(true);
        	jtfR2.setVisible(true);
        	jtfR1.setVisible(true);
        	
        	jlCAPR.setVisible(true);
        	jlNrCivicoR.setVisible(true);
        	jlViaR.setVisible(true);
        	jlCitt‡R.setVisible(true);
        	jlIDreception.setVisible(true);
        	jlCognomeR.setVisible(true);
        	jlNomeR.setVisible(true);
        	
        	if (jtfR.getText().length()>=16 || check(jtfR1.getText()) || check(jtfR2.getText())  || !check(jtfR3.getText()) 
            		|| check(jtfR4.getText())|| check(jtfR5.getText()) || !check(jtfR6.getText()) || !check(jtfR7.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
        	else {
            	Tabella_Reception rt = new Tabella_Reception();
            	Reception r = new Reception();
            	r.setCF(jtfR.getText());
            	r.setNome(jtfR1.getText());
            	r.setCognome(jtfR2.getText());
            	r.setReceptionID(Integer.parseInt(jtfR3.getText()));
            	r.setCitt‡(jtfR4.getText());
            	r.setVia(jtfR5.getText());
            	r.setNrCivico(Integer.parseInt(jtfR6.getText()));
            	r.setCAP(Integer.parseInt(jtfR7.getText()));
            	
            	rt.persist(r);
            	
            	PreparedStatement statement = null;
            	String query = "select * from reception";
            	
            		try {
            			statement = (PreparedStatement) connection.prepareStatement(query);
            			ResultSet rs = statement.executeQuery();
            			tableC.setModel(DbUtils.resultSetToTableModel(rs));
            		} catch ( Exception e1) {
            			e1.printStackTrace();
            		}
            		
    		        jtfR.setText("");
    		        jtfR1.setText("");
    		        jtfR2.setText("");
    		        jtfR3.setText("");
    		        jtfR4.setText("");
    		        jtfR5.setText("");
    		        jtfR6.setText("");		        
    		        jtfR7.setText("");
        	 }
           });

/**********************************************************************************************************************/
     
/***********************  ABBONAMENTO  ******************************/
        
        JPanel jpA = new JPanel();
        jpA.setLayout(null);
        
        JScrollPane scrollA = new JScrollPane();
        scrollA.setBounds(6, 31, 950, 330);
        jpA.add(scrollA);
        
        JTable tableA = new JTable();
        tableA.setRowHeight(23);
        scrollA.setViewportView(tableA);

        jpA.add(tableA.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadA = new JButton("Load dati");
        loadA.setFont(new Font("Arial", Font.BOLD, 16));
        loadA.setBounds(530, 730, 126, 23);
        JButton updateA = new JButton("Aggiorna dati");
        updateA.setFont(new Font("Arial", Font.BOLD, 16));
        updateA.setBounds(350, 730, 160, 23);
        JButton delA = new JButton("Cancella dati");
        delA.setFont(new Font("Arial", Font.BOLD, 16));
        delA.setBounds(185, 730, 145, 23);
        JButton insertA = new JButton("Inserisci abbonamento");
        insertA.setFont(new Font("Arial", Font.BOLD, 16));
        insertA.setBounds(16, 730, 160, 23);
        
        JLabel jlWelcomeA = new JLabel("Compila per inserire / cancellare abbonamento: ");
        jlWelcomeA.setFont(new Font("Times", Font.BOLD,14));
        jlWelcomeA.setBounds(100, 380, 400, 20);
        
        JLabel jlIDabbonamento = new JLabel("ID_ABBONAMENTO");
        jlIDabbonamento.setFont(new Font("Times", Font.BOLD,14));
        jlIDabbonamento.setBounds(20, 415, 135, 20);
        JTextField jtfA1 = new JTextField();
        jtfA1.setBounds(168, 415, 105, 20);
        jtfA1.setText("");
        
        JLabel jlCFcliente = new JLabel("CF cliente");
        jlCFcliente.setFont(new Font("Times", Font.BOLD,14));
        jlCFcliente.setBounds(20, 435, 160, 20);
        JTextField jtfA2 = new JTextField();
        jtfA2.setBounds(168, 435, 105, 20);
        jtfA2.setText("");
        
        Integer[] maxDurata = new Integer[]{null,1,15,30,180};
        JLabel jlDurata = new JLabel("Durata (maxGiorni)");
        jlDurata.setFont(new Font("Times", Font.BOLD,14));
        jlDurata.setBounds(20, 455, 135, 20);
        JComboBox<Integer> jtfA3 = new JComboBox<Integer>(maxDurata);
        jtfA3.setBounds(168, 455, 105, 20);
        
        JLabel jlDataIn = new JLabel("Data (gg/mm/aa)");
        jlDataIn.setFont(new Font("Times", Font.BOLD,14));
        jlDataIn.setBounds(20, 475, 135, 20);
        JTextField jtfA4 = new JTextField();
        jtfA4.setBounds(168, 478, 105, 20);

        String[] spec2 = new String[] { " ","muscolare", "cardio"};
        JLabel jlTipo = new JLabel("Tipo");
        jlTipo.setFont(new Font("Times", Font.BOLD,14));
        jlTipo.setBounds(20, 498, 135, 20);
        JComboBox<String> jtfA7 = new JComboBox<String>(spec2);
        jtfA7.setBounds(168, 500, 105, 20);
        
        JLabel jlprezzo = new JLabel("Prezzo");
        jlprezzo.setFont(new Font("Times", Font.BOLD,14));
        jlprezzo.setBounds(20, 520, 135, 20);
        JTextField jtfA8 = new JTextField();
        jtfA8.setBounds(168, 520, 105, 20);
        jtfA8.setText("");
       
        
        // Button to clear the text fields
        JLabel jlClearA = new JLabel("Clica per pulire le aree di inseriemto");
        jlClearA.setFont(new Font("Times", Font.BOLD,14));
        jlClearA.setBounds(650, 570, 400, 20);
        JButton bA = new JButton("CLEAR");
        bA.setFont(new Font("Times", Font.BOLD,14));
        bA.setBounds(700, 600, 100, 70);
		bA.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfA1.setText("");
		        jtfA2.setText("");
		        jtfA3.setSelectedIndex(0);		        
		        jtfA4.setText("");		        
		        jtfA7.setSelectedIndex(0);		
		        jtfA8.setText("");

		    }
		});
        
		jpA.add(jlClearA);
        jpA.add(bA); // buttore per pulire 
        jpA.add(jlCFcliente);
        jpA.add(jlIDabbonamento);
        jpA.add(jlDurata);
        jpA.add(jlDataIn);
        jpA.add(jlTipo);
        
        jpA.add(jlprezzo);
        jpA.add(jlWelcomeA);
        jpA.add(jtfA1);
        jpA.add(jtfA2);
        jpA.add(jtfA3);
        jpA.add(jtfA4);
        jpA.add(jtfA7);
		jpA.add(jtfA8);
		
        jpA.add(insertA);
        jpA.add(delA);
        jpA.add(updateA);
        jpA.add(loadA);
        
        
        delA.addActionListener(e->{
            jtfA8.setVisible(false);
        	jtfA7.setVisible(false);
        	jtfA4.setVisible(false);
        	jtfA3.setVisible(false);
        	jtfA2.setVisible(false);
        	
        	jlCFcliente.setVisible(false);
        	jlDurata.setVisible(false);
        	jlDataIn.setVisible(false);
        	jlTipo.setVisible(false);
        	jlprezzo.setVisible(false);
        	
        	if (!check(jtfA1.getText()) || jtfA2.getText().length()>16 || !check(jtfA8.getText()) ){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
        	
            Tabella_Abbonamento at = new Tabella_Abbonamento();
            at.delete(Integer.parseInt(jtfA1.getText()));
            PreparedStatement statement = null;
            String query = "select * from abbonamenti";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableA.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadA.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from abbonamenti";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableA.setModel(DbUtils.resultSetToTableModel(rs));

        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateA.addActionListener(e->{
        	
        	jtfA8.setVisible(true);
        	jtfA7.setVisible(true);
        	jtfA4.setVisible(true);
        	jtfA3.setVisible(true);
        	jtfA2.setVisible(true);
        	jtfA1.setVisible(true);
        	
        	jlCFcliente.setVisible(true);
        	jlDurata.setVisible(true);
        	jlDataIn.setVisible(true);
        	jlTipo.setVisible(true);
        	jlprezzo.setVisible(true);
          
        	if (!check(jtfA1.getText()) || jtfA2.getText().length()>16 || !check(jtfA8.getText()) ){
              String message = "Errore nel inserimento dei campi ";
              JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                      JOptionPane.ERROR_MESSAGE);
          }
          else {
        	  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
              java.util.Date parsed = null;              
              try {
                  parsed = format.parse(jtfA4.getText());
                  
              } catch (Exception e2) {
                  // TODO Auto-generated catch block
                  e2.printStackTrace();
              }
              java.sql.Date sql = new java.sql.Date(parsed.getTime());
                 
            	
            Tabella_Abbonamento at = new Tabella_Abbonamento();
            Abbonamento a = new Abbonamento();
            	a.setIDabbonamento(Integer.parseInt(jtfA1.getText()));
            	a.setCFcliente(jtfA2.getText());
            	a.setDurata(Integer.parseInt(jtfA3.getSelectedItem().toString()));
            	a.setData(sql);
            	a.setTipo(jtfA7.getSelectedItem().toString());
            	a.setPrezzo(Integer.parseInt(jtfA8.getText()));
            	at.update(a);
 
            PreparedStatement statement = null;
            String query = "select * from abbonamenti";
            
                try {
                	statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableA.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                }   
            }		    
            	jtfA1.setText("");
           		jtfA2.setText("");
	        	jtfA3.setSelectedIndex(0);		        
	        	jtfA4.setText("");		        
	        	jtfA7.setSelectedIndex(0);		
	        	jtfA8.setText("");
        });
        
        
        insertA.addActionListener(e->{
        	jtfA8.setVisible(true);
        	jtfA7.setVisible(true);
        	jtfA4.setVisible(true);
        	jtfA3.setVisible(true);
        	jtfA2.setVisible(true);
        	jtfA1.setVisible(true);
        	
        	jlCFcliente.setVisible(true);
        	jlDurata.setVisible(true);
        	jlDataIn.setVisible(true);
        	jlTipo.setVisible(true);
        	jlprezzo.setVisible(true);
        	
        	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = null;
           
        	if (!check(jtfA1.getText()) || jtfA2.getText().length()>16 || !check(jtfA8.getText()) ){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
            	 
            	try {
                     parsed = format.parse(jtfA4.getText());   
                   } catch (Exception e2) {
               	  // TODO Auto-generated catch block
                     e2.printStackTrace();
                   }
                 
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
            
        		Tabella_Abbonamento at = new Tabella_Abbonamento();
            	Abbonamento a = new Abbonamento();
            	a.setIDabbonamento(Integer.parseInt(jtfA1.getText()));
            	a.setCFcliente(jtfA2.getText());
            	a.setDurata(Integer.parseInt(jtfA3.getSelectedItem().toString()));
            	a.setData(sql);
            	a.setTipo(jtfA7.getSelectedItem().toString());
            	a.setPrezzo(Integer.parseInt(jtfA8.getText()));
            	at.persist(a);
            	
            	PreparedStatement statement = null;
            	String query = "select * from abbonamenti";
            	
            		try {
            			statement = (PreparedStatement) connection.prepareStatement(query);
            			ResultSet rs = statement.executeQuery();
            			tableA.setModel(DbUtils.resultSetToTableModel(rs));
            	        } catch ( Exception e1) {
            			e1.printStackTrace();
            		}
            		
    		        jtfA1.setText("");
    		        jtfA2.setText("");
    		        jtfA3.setSelectedIndex(0);		        
    		        jtfA4.setText("");
    		        jtfA7.setSelectedIndex(0);		
    		        jtfA8.setText("");
             }
           });

 /*******************************************************************************************************************************/
        
       /******************************  SETTORE *************************************************************************/        
        JPanel jpS = new JPanel();
        jpS.setLayout(null);
        
        JScrollPane scrollS = new JScrollPane();
        scrollS.setBounds(6, 31, 950, 330);
        jpS.add(scrollS);
        
        JTable tableS = new JTable();
        tableS.setRowHeight(23);
        scrollS.setViewportView(tableS);

        jpS.add(tableS.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadS = new JButton("Load dati");
        loadS.setFont(new Font("Arial", Font.BOLD, 16));
        loadS.setBounds(550, 730, 126, 23);
        JButton updateS = new JButton("Aggiorna dati");
        updateS.setFont(new Font("Arial", Font.BOLD, 16));
        updateS.setBounds(370, 730, 160, 23);
        JButton delS = new JButton("Cancella dati");
        delS.setFont(new Font("Arial", Font.BOLD, 16));
        delS.setBounds(205, 730, 145, 23);
        JButton insertS = new JButton("Inserisci settore");
        insertS.setFont(new Font("Arial", Font.BOLD, 16));
        insertS.setBounds(16, 730, 180, 23);
        
        JLabel jlWelcomeS = new JLabel("Compila per inserire / cancellare dati: ");
        jlWelcomeS.setFont(new Font("Times", Font.BOLD,14));
        jlWelcomeS.setBounds(100, 380, 400, 20);
       
        JLabel jlCodiceS = new JLabel("Codice Settore");
        jlCodiceS.setFont(new Font("Times", Font.BOLD,14));
        jlCodiceS.setBounds(20, 410, 115, 20);
        JTextField jtfS = new JTextField();
        jtfS.setBounds(140, 410, 97, 20);
        jtfS.setText("");
        
        JLabel jlTipoS = new JLabel("Tipo");
        jlTipoS.setFont(new Font("Times", Font.BOLD,14));
        jlTipoS.setBounds(20, 430, 115, 20);
        JComboBox<String> jtfS1 = new JComboBox<>(spec);
        jtfS1.setBounds(140, 430, 97, 20);
        
        JLabel jlNrM = new JLabel("Numero macchinari");
        jlNrM.setFont(new Font("Times", Font.BOLD,14));
        jlNrM.setBounds(20, 450, 115, 20);
        JTextField jtfS2 = new JTextField();
        jtfS2.setBounds(140, 450, 97, 20);
        jtfS2.setText("");
        
        JLabel jlArea = new JLabel("Area");
        jlArea.setFont(new Font("Times", Font.BOLD,14));
        jlArea.setBounds(20, 470, 115, 20);
        JTextField jtfS3 = new JTextField();
        jtfS3.setBounds(140, 470, 97, 20);
        jtfS3.setText("");
        
        
        JLabel jlClearS = new JLabel("Clica per pulire le aree di inseriemto");
        jlClearS.setFont(new Font("Times", Font.BOLD,14));
        jlClearS.setBounds(650, 570, 400, 20);
        JButton bS = new JButton("CLEAR");
        bS.setFont(new Font("Times", Font.BOLD,14));
        bS.setBounds(700, 600, 100, 70);
		bS.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfS .setText("");
		        jtfS1.setSelectedIndex(0);
		        jtfS2.setText("");
		        jtfS3.setText("");
		    }
		});
		
		
		jpS.add(jlClearS);
		jpS.add(bS); // buttore per pulire 
		jpS.add(jlCodiceS);
		jpS.add(jlTipoS);
		jpS.add(jlNrM);
		jpS.add(jlArea);
   
		jpS.add(jlWelcomeS);
		jpS.add(jtfS);
		jpS.add(jtfS1);
		jpS.add(jtfS2);
		jpS.add(jtfS3);

		jpS.add(insertS);
		jpS.add(delS);
		jpS.add(updateS);
		jpS.add(loadS);
        
        
        delS.addActionListener(e->{
            
        	jtfS3.setVisible(false);
        	jtfS2.setVisible(false);
        	jtfS1.setVisible(false);
        	jtfS.setVisible(true);

        	jlArea.setVisible(false);
        	jlNrM.setVisible(false);
        	jlTipoS.setVisible(false);
        	
            Tabella_Settore st = new Tabella_Settore();
            st.delete(Integer.parseInt(jtfS.getText().toString()));
            PreparedStatement statement = null;
            String query = "select * from settori";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableS.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadS.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from settori";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();  
                    tableS.setModel(DbUtils.resultSetToTableModel(rs));
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateS.addActionListener(e->{
        	
        	jtfS3.setVisible(true);
        	jtfS2.setVisible(true);
        	jtfS1.setVisible(true);
        	jtfS.setVisible(true);

        	jlArea.setVisible(true);
        	jlNrM.setVisible(true);
        	jlTipoS.setVisible(true);
        	
             if ( !check(jtfS.getText()) || !check(jtfS2.getText()) || !check(jtfS3.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else {
              Tabella_Settore st = new Tabella_Settore();
              Settore s = new Settore();
                s.setCodiceSett(Integer.parseInt(jtfS.getText()));
                s.setTipo(jtfS1.getSelectedItem().toString());
                s.setNumeroMacchinari(Integer.parseInt(jtfS2.getText()));
                s.setArea(Integer.parseInt(jtfS3.getText()));
          		st.update(s);
   
              PreparedStatement statement = null;
              String query = "select * from settori";
              
                  try {
                  	statement = (PreparedStatement) connection.prepareStatement(query);
          			ResultSet rs = statement.executeQuery();
          			tableS.setModel(DbUtils.resultSetToTableModel(rs));
                    
                  } catch (Exception e1) {
                      e1.printStackTrace();
                  }   
            }
		        jtfS .setText("");
		        jtfS1.setSelectedIndex(0);
		        jtfS2.setText("");
		        jtfS3.setText("");
          });
        
        
        insertS.addActionListener(e->{
        	
        	jtfS3.setVisible(true);
        	jtfS2.setVisible(true);
        	jtfS1.setVisible(true);
        	jtfS.setVisible(true);

        	jlArea.setVisible(true);
        	jlNrM.setVisible(true);
        	jlTipoS.setVisible(true);
        	jlNomeI.setVisible(true);
        	
        	   if ( !check(jtfS.getText()) || !check(jtfS2.getText()) || !check(jtfS3.getText())){
                   String message = "Errore nel inserimento dei campi ";
                   JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                           JOptionPane.ERROR_MESSAGE);
               } 
               else {
            	Tabella_Settore st = new Tabella_Settore();
            	Settore s = new Settore();
            	 s.setCodiceSett(Integer.parseInt(jtfS.getText()));
                 s.setTipo(jtfS1.getSelectedItem().toString());
                 s.setNumeroMacchinari(Integer.parseInt(jtfS2.getText()));
                 s.setArea(Integer.parseInt(jtfS3.getText()));
            	st.persist(s);
            	
            	PreparedStatement statement = null;
            	String query = "select * from settori";
            	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableS.setModel(DbUtils.resultSetToTableModel(rs));      	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        		
		        jtfS .setText("");
		        jtfS1.setSelectedIndex(0);
		        jtfS2.setText("");
		        jtfS3.setText("");
        	 }
           });       
        
        
        
        
/*************************************************************************************************************************/       
        
        /******************************  MACCHINARIO *************************************************************************/        
        JPanel jpM = new JPanel();
        jpM.setLayout(null);
        
        JScrollPane scrollM = new JScrollPane();
        scrollM.setBounds(6, 31, 950, 330);
        jpM.add(scrollM);
        
        JTable tableM = new JTable();
        tableM.setRowHeight(23);
        scrollM.setViewportView(tableM);

        jpM.add(tableM.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadM = new JButton("Load dati");
        loadM.setFont(new Font("Arial", Font.BOLD, 16));
        loadM.setBounds(550, 730, 126, 23);
        JButton updateM = new JButton("Aggiorna dati");
        updateM.setFont(new Font("Arial", Font.BOLD, 16));
        updateM.setBounds(370, 730, 160, 23);
        JButton delM = new JButton("Cancella dati");
        delM.setFont(new Font("Arial", Font.BOLD, 16));
        delM.setBounds(205, 730, 145, 23);
        JButton insertM = new JButton("Inserisci macchinario");
        insertM.setFont(new Font("Arial", Font.BOLD, 16));
        insertM.setBounds(16, 730, 180, 23);
        
        JLabel jlWelcomeM = new JLabel("Compila per inserire / cancellare dati: ");
        jlWelcomeM.setFont(new Font("Times", Font.BOLD,14));
        jlWelcomeM.setBounds(100, 380, 400, 20);
       
        JLabel jlCodiceM = new JLabel("Codice Macchinario");
        jlCodiceM.setFont(new Font("Times", Font.BOLD,14));
        jlCodiceM.setBounds(20, 410, 160, 20);
        JTextField jtfM = new JTextField();
        jtfM.setBounds(167, 410, 97, 20);
        jtfM.setText("");
        
        JLabel jlNomeM = new JLabel("Nome");
        jlNomeM.setFont(new Font("Times", Font.BOLD,14));
        jlNomeM.setBounds(20, 430, 135, 20);
        JTextField jtfM1 = new JTextField();
        jtfM1.setBounds(167, 430, 97, 20);
        
        JLabel jlGradoM = new JLabel("Grado");
        jlGradoM.setFont(new Font("Times", Font.BOLD,14));
        jlGradoM.setBounds(20, 450, 135, 20);
        String[] grado = new String[] {null, "A", "B", "C"};
        JComboBox<String> jtfM2 = new JComboBox(grado);
        jtfM2.setBounds(167, 450, 97, 20);
        
        JLabel jlAnnoM = new JLabel("Anno");
        jlAnnoM.setFont(new Font("Times", Font.BOLD,14));
        jlAnnoM.setBounds(20, 470, 135, 20);
        JTextField jtfM3 = new JTextField();
        jtfM3.setBounds(167, 470, 97, 20);
        jtfM3.setText("");
        
        JLabel jlCodiceMS = new JLabel("Codice Settore");
        jlCodiceMS.setFont(new Font("Times", Font.BOLD,14));
        jlCodiceMS.setBounds(20, 490, 135, 20);
        JTextField jtfM4 = new JTextField();
        jtfM4.setBounds(167, 490, 97, 20);
        jtfM4.setText("");
        
        JLabel jlClearM = new JLabel("Clica per pulire le aree di inseriemto");
        jlClearM.setFont(new Font("Times", Font.BOLD,14));
        jlClearM.setBounds(650, 570, 400, 20);
        JButton bM = new JButton("CLEAR");
        bM.setFont(new Font("Times", Font.BOLD,14));
        bM.setBounds(700, 600, 100, 70);
		bM.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfM .setText("");
		        jtfM1.setText("");
		        jtfM2.setSelectedIndex(0);
		        jtfM3.setText("");
		        jtfM4.setText("");
		    }
		});
		
		
		jpM.add(jlClearM);
		jpM.add(bM); // buttore per pulire 
		jpM.add(jlCodiceM);
		jpM.add(jlNomeM);
		jpM.add(jlGradoM);
		jpM.add(jlAnnoM);
		jpM.add(jlCodiceMS);

		jpM.add(jlWelcomeM);
		jpM.add(jtfM);
		jpM.add(jtfM1);
		jpM.add(jtfM2);
		jpM.add(jtfM3);
		jpM.add(jtfM4);

		jpM.add(insertM);
		jpM.add(delM);
		jpM.add(updateM);
		jpM.add(loadM);
        
        
        delM.addActionListener(e->{
        	jtfM4.setVisible(false);
        	jtfM3.setVisible(false);
        	jtfM2.setVisible(false);
        	jtfM1.setVisible(false);
        	jtfM .setVisible(true);

        	jlNomeM.setVisible(false);
        	jlGradoM.setVisible(false);
        	jlAnnoM.setVisible(false);
        	jlCodiceMS.setVisible(false);
        	jlCodiceMS.setVisible(true);

        	
            Tabella_Macchinario mt = new Tabella_Macchinario();
            mt.delete(Integer.parseInt(jtfM.getText().toString()));
            PreparedStatement statement = null;
            String query = "select * from macchinari";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableM.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadM.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from macchinari";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();  
                    tableM.setModel(DbUtils.resultSetToTableModel(rs));
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateM.addActionListener(e->{
        	
        	jtfM4.setVisible(true);
        	jtfM3.setVisible(true);
        	jtfM2.setVisible(true);
        	jtfM1.setVisible(true);
        	jtfM .setVisible(true);

        	jlNomeM.setVisible(true);
        	jlGradoM.setVisible(true);
        	jlAnnoM.setVisible(true);
        	jlCodiceMS.setVisible(true);
        	jlCodiceMS.setVisible(true);
        	
        	if ( !check(jtfM.getText()) || check(jtfM1.getText()) || !check(jtfM3.getText()) || !check(jtfM4.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else {
              Tabella_Macchinario mt = new Tabella_Macchinario();
              Macchinario m = new Macchinario();
                m.setCodiceMac(Integer.parseInt(jtfM.getText()));
                m.setNome(jtfM1.getText());
                m.setGrado(jtfM2.getSelectedItem().toString());
                m.setAnno(Integer.parseInt(jtfM3.getText()));
                m.setCodiceSet(Integer.parseInt(jtfM4.getText()));
          		mt.update(m);
   
              PreparedStatement statement = null;
              String query = "select * from macchinari";
              
                  try {
                  	statement = (PreparedStatement) connection.prepareStatement(query);
          			ResultSet rs = statement.executeQuery();
          			tableM.setModel(DbUtils.resultSetToTableModel(rs));
                    
                  } catch (Exception e1) {
                      e1.printStackTrace();
                  }   
            }
		        jtfM .setText("");
		        jtfM1.setText("");
		        jtfM2.setSelectedIndex(0);
		        jtfM3.setText("");
		        jtfM4.setText("");
          });
        
        
        insertM.addActionListener(e->{
        	
        	jtfM4.setVisible(true);
        	jtfM3.setVisible(true);
        	jtfM2.setVisible(true);
        	jtfM1.setVisible(true);
        	jtfM .setVisible(true);

        	jlNomeM.setVisible(true);
        	jlGradoM.setVisible(true);
        	jlAnnoM.setVisible(true);
        	jlCodiceMS.setVisible(true);
        	jlCodiceMS.setVisible(true);
        	
        	if ( !check(jtfM.getText()) || check(jtfM1.getText()) || !check(jtfM3.getText()) || !check(jtfM4.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
        	else {
            	Tabella_Macchinario mt = new Tabella_Macchinario();
            	Macchinario m = new Macchinario();
                m.setCodiceMac(Integer.parseInt(jtfM.getText()));
                m.setNome(jtfM1.getText());
                m.setGrado(jtfM2.getSelectedItem().toString());
                m.setAnno(Integer.parseInt(jtfM3.getText()));
                m.setCodiceSet(Integer.parseInt(jtfM4.getText()));
            	mt.persist(m);
            	
            	PreparedStatement statement = null;
            	String query = "select * from macchinari";
            	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableM.setModel(DbUtils.resultSetToTableModel(rs));      	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        		
		        jtfM .setText("");
		        jtfM1.setText("");
		        jtfM2.setSelectedIndex(0);
		        jtfM3.setText("");
		        jtfM4.setText("");
        	 }
           });       
        
        
        
        
/*************************************************************************************************************************/             
       
     /******************************  GRUPPO   *************************************************************************/        
        JPanel jpG = new JPanel();
        jpG.setLayout(null);
        
        JScrollPane scrollG = new JScrollPane();
        scrollG.setBounds(6, 31, 950, 330);
        jpG.add(scrollG);
        
        JTable tableG = new JTable();
        tableG.setRowHeight(23);
        scrollG.setViewportView(tableG);

        jpG.add(tableG.getTableHeader(), BorderLayout.NORTH);
        
        JButton loadG = new JButton("Load dati");
        loadG.setFont(new Font("Arial", Font.BOLD, 16));
        loadG.setBounds(550, 730, 126, 23);
        JButton updateG = new JButton("Aggiorna dati");
        updateG.setFont(new Font("Arial", Font.BOLD, 16));
        updateG.setBounds(370, 730, 160, 23);
        JButton delG = new JButton("Cancella dati");
        delG.setFont(new Font("Arial", Font.BOLD, 16));
        delG.setBounds(205, 730, 145, 23);
        JButton insertG = new JButton("Inserisci gruppo");
        insertG.setFont(new Font("Arial", Font.BOLD, 16));
        insertG.setBounds(16, 730, 180, 23);
        
        JLabel jlWelcomeG = new JLabel("Compila per inserire / cancellare dati: ");
        jlWelcomeG.setFont(new Font("Times", Font.BOLD,14));
        jlWelcomeG.setBounds(100, 380, 400, 20);
       
        JLabel jlCodiceG = new JLabel("Codice gruppo");
        jlCodiceG.setFont(new Font("Times", Font.BOLD,14));
        jlCodiceG.setBounds(20, 410, 160, 20);
        JTextField jtfG = new JTextField();
        jtfG.setBounds(167, 410, 97, 20);
        jtfG.setText("");
        
        
        JLabel jlTipoM = new JLabel("Tipo");
        jlTipoM.setFont(new Font("Times", Font.BOLD,14));
        jlTipoM.setBounds(20, 430, 135, 20);
        JComboBox<String> jtfG2 = new JComboBox<String>(spec);
        jtfG2.setBounds(167, 430, 97, 20);
           
        JLabel jlCFIG = new JLabel("CF_istruttore");
        jlCFIG.setFont(new Font("Times", Font.BOLD,14));
        jlCFIG.setBounds(20, 450, 135, 20);
        JTextField jtfG3 = new JTextField();
        jtfG3.setBounds(167, 450, 97, 20);
        
        JLabel jlClearG = new JLabel("Clica per pulire le aree di inseriemto");
        jlClearG.setFont(new Font("Times", Font.BOLD,14));
        jlClearG.setBounds(650, 570, 400, 20);
        JButton bG = new JButton("CLEAR");
        bG.setFont(new Font("Times", Font.BOLD,14));
        bG.setBounds(700, 600, 100, 70);
		bG.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        jtfG .setText("");
		        jtfG2.setSelectedIndex(0);
		        jtfG3.setText("");
		    }
		});
		
		
		jpG.add(jlClearG);
		jpG.add(bG); // buttore per pulire 
		jpG.add(jlCodiceG);
		jpG.add(jlTipoM);
		jpG.add(jlCFIG);

		jpG.add(jlWelcomeG);
		jpG.add(jtfG);
		jpG.add(jtfG2);
		jpG.add(jtfG3);

		jpG.add(insertG);
		jpG.add(delG);
		jpG.add(updateG);
		jpG.add(loadG);
        
        
        delG.addActionListener(e->{
        	
        	jtfG3.setVisible(false);
        	jtfG2.setVisible(false);
        	jtfG .setVisible(true);

        	jlTipoM.setVisible(false);
        	jlCFIG.setVisible(false);
        	jlCodiceG.setVisible(true);

        	
            Tabella_Gruppo gt = new Tabella_Gruppo();
            gt.delete(Integer.parseInt(jtfG.getText().toString()));
            PreparedStatement statement = null;
            String query = "select * from gruppi";
            
                try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    tableG.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
        });
        
        
        loadG.addActionListener(e->{
        	PreparedStatement statement = null; 
        	String query = "select * from gruppi";
        	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();  
                    tableG.setModel(DbUtils.resultSetToTableModel(rs));
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        });
        
        updateG.addActionListener(e->{
        	
        	jtfG3.setVisible(true);
        	jtfG2.setVisible(true);
        	jtfG .setVisible(true);

        	jlTipoM.setVisible(true);
        	jlCFIG.setVisible(true);
        	jlCodiceG.setVisible(true);
        	
        	if ( !check(jtfG.getText())  || check(jtfM4.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
            else {
            	Tabella_Gruppo gt = new Tabella_Gruppo();
            	Gruppo  g = new Gruppo ();
                g.setCodiceGr(Integer.parseInt(jtfG.getText()));
                g.setTipo(jtfG2.getSelectedItem().toString());
                g.setCFi(jtfG3.getText());
        		gt.update(g);
   
              PreparedStatement statement = null;
              String query = "select * from gruppi";
              
                  try {
                  	statement = (PreparedStatement) connection.prepareStatement(query);
          			ResultSet rs = statement.executeQuery();
          			tableG.setModel(DbUtils.resultSetToTableModel(rs));
                    
                  } catch (Exception e1) {
                      e1.printStackTrace();
                  }   
            }
        		jtfG .setText("");
        		jtfG2.setSelectedIndex(0);
        		jtfG3.setText("");
          });
        
        
        insertG.addActionListener(e->{
        	
        	jtfG3.setVisible(true);
        	jtfG2.setVisible(true);
        	jtfG .setVisible(true);

        	jlTipoM.setVisible(true);
        	jlCFIG.setVisible(true);
        	jlCodiceG.setVisible(true);
        	
        	if ( !check(jtfG.getText()) || check(jtfM4.getText())){
                String message = "Errore nel inserimento dei campi ";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            } 
        	else {
        		Tabella_Gruppo gt = new Tabella_Gruppo();
            	Gruppo  g = new Gruppo ();
                g.setCodiceGr(Integer.parseInt(jtfG.getText()));
                g.setTipo(jtfG2.getSelectedItem().toString());
                g.setCFi(jtfG3.getText());
        		gt.persist(g);
            	
            	PreparedStatement statement = null;
            	String query = "select * from gruppi";
            	
        		try {
        			statement = (PreparedStatement) connection.prepareStatement(query);
        			ResultSet rs = statement.executeQuery();
        			tableG.setModel(DbUtils.resultSetToTableModel(rs));      	       
        		} catch (Exception e1){
        				e1.printStackTrace();
        		}
        		
        		jtfG .setText("");
        		jtfG2.setSelectedIndex(0);
        		jtfG3.setText("");
        	 }
           });       
        
/****************************************************************************************************************************/
        
        /******************************  CARTA CLIENTE  *************************************************************************/        
           JPanel jpCC = new JPanel();
           jpCC.setLayout(null);
           
           JScrollPane scrollCC = new JScrollPane();
           scrollCC.setBounds(6, 31, 800, 300);
           jpCC.add(scrollCC);
           
           JTable tableCC = new JTable();
           tableCC.setRowHeight(23);
           scrollCC.setViewportView(tableCC);

           jpCC.add(tableCC.getTableHeader(), BorderLayout.NORTH);
           
           JButton loadCC = new JButton("Load dati");
           loadCC.setFont(new Font("Arial", Font.BOLD, 16));
           loadCC.setBounds(550, 730, 126, 23);
           JButton updateCC = new JButton("Aggiorna dati");
           updateCC.setFont(new Font("Arial", Font.BOLD, 16));
           updateCC.setBounds(370, 730, 160, 23);
           JButton delCC = new JButton("Cancella dati");
           delCC.setFont(new Font("Arial", Font.BOLD, 16));
           delCC.setBounds(205, 730, 145, 23);
           JButton insertCC = new JButton("Inserisci carta");
           insertCC.setFont(new Font("Arial", Font.BOLD, 16));
           insertCC.setBounds(16, 730, 180, 23);
           
           JLabel jlWelcomeCC = new JLabel("Compila per inserire / cancellare dati: ");
           jlWelcomeCC.setFont(new Font("Times", Font.BOLD,14));
           jlWelcomeCC.setBounds(100, 380, 400, 20);
          
           JLabel jlCodiceCC = new JLabel("Codice Carta");
           jlCodiceCC.setFont(new Font("Times", Font.BOLD,14));
           jlCodiceCC.setBounds(20, 410, 160, 20);
           JTextField jtfCC = new JTextField();
           jtfCC.setBounds(167, 410, 97, 20);
           jtfCC.setText("");
           
           JLabel jlCFC= new JLabel("CF_Cliente");
           jlCFC.setFont(new Font("Times", Font.BOLD,14));
           jlCFC.setBounds(20, 430, 135, 20);
           JTextField jtfCC1 = new JTextField();
           jtfCC1.setBounds(167, 430, 97, 20);
           
           JLabel jlPuntiCC = new JLabel("Punti");
           jlPuntiCC.setFont(new Font("Times", Font.BOLD,14));
           jlPuntiCC.setBounds(20, 450, 135, 20);
           JTextField jtfCC2 = new  JTextField();
           jtfCC2.setBounds(167, 450, 97, 20);
              
           
           JLabel jlClearCC = new JLabel("Clica per pulire le aree di inseriemto");
           jlClearCC.setFont(new Font("Times", Font.BOLD,14));
           jlClearCC.setBounds(650, 570, 400, 20);
           JButton bCC = new JButton("CLEAR");
           bCC.setFont(new Font("Times", Font.BOLD,14));
           bCC.setBounds(700, 600, 100, 70);
   		bCC.addActionListener(new ActionListener(){
   		    public void actionPerformed(ActionEvent e){
   		        jtfCC .setText("");
   		        jtfCC1.setText("");
   		        jtfCC2.setText("");
   		    }
   		});
   		
   		
   		jpCC.add(jlClearCC);
   		jpCC.add(bCC); // buttore per pulire 
   		jpCC.add(jlCodiceCC);
   		jpCC.add(jlCFC);
   		jpCC.add(jlPuntiCC);

   		jpCC.add(jlWelcomeCC);
   		jpCC.add(jtfCC);
   		jpCC.add(jtfCC1);
   		jpCC.add(jtfCC2);

   		jpCC.add(insertCC);
   		jpCC.add(delCC);
   		jpCC.add(updateCC);
   		jpCC.add(loadCC);
           
           
           delCC.addActionListener(e->{
           	
           	jtfCC2.setVisible(false);
           	jtfCC1.setVisible(false);
           	jtfCC .setVisible(true);

           	jlPuntiCC.setVisible(false);
           	jlCFC.setVisible(false);
           	jlCodiceCC.setVisible(true);

           	
               Tabella_CartaCliente cct = new Tabella_CartaCliente();
               cct.delete(Integer.parseInt(jtfCC.getText().toString()));
               PreparedStatement statement = null;
               String query = "select * from carte_clienti";
               
                   try {
                       statement =  (PreparedStatement) connection.prepareStatement(query);  
                       ResultSet rs = statement.executeQuery();    
                       tableCC.setModel(DbUtils.resultSetToTableModel(rs));
                     
                   } catch (Exception e1) {
                       e1.printStackTrace();
                   } 
           });
           
           
           loadCC.addActionListener(e->{
           	PreparedStatement statement = null; 
           	String query = "select * from carte_clienti";
           	
           		try {
           			statement = (PreparedStatement) connection.prepareStatement(query);
           			ResultSet rs = statement.executeQuery();  
                       tableCC.setModel(DbUtils.resultSetToTableModel(rs));
           		} catch (Exception e1){
           				e1.printStackTrace();
           		}
           });
           
           updateCC.addActionListener(e->{
           	
           	jtfCC2.setVisible(true);
           	jtfCC1.setVisible(true);
           	jtfCC .setVisible(true);

           	jlPuntiCC.setVisible(true);
           	jlCFC.setVisible(true);
           	jlCodiceCC.setVisible(true);

           	
           	if ( !check(jtfCC.getText()) || check(jtfCC1.getText()) || !check(jtfCC2.getText())){
                   String message = "Errore nel inserimento dei campi ";
                   JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                           JOptionPane.ERROR_MESSAGE);
               } 
               else {
               	Tabella_CartaCliente cct = new Tabella_CartaCliente();
               	Carta_Cliente  cc = new Carta_Cliente ();
                   cc.setCodiceCarta(Integer.parseInt(jtfCC.getText()));
                   cc.setCF(jtfCC1.getText());
                   cc.setPunti(Integer.parseInt(jtfCC2.getText()));
           		cct.update(cc);
      
                 PreparedStatement statement = null;
                 String query = "select * from carte_clienti";
                 
                     try {
                     	statement = (PreparedStatement) connection.prepareStatement(query);
             			ResultSet rs = statement.executeQuery();
             			tableCC.setModel(DbUtils.resultSetToTableModel(rs));
                       
                     } catch (Exception e1) {
                         e1.printStackTrace();
                     }   
               }
   	        jtfCC .setText("");
   	        jtfCC1.setText("");
   	        jtfCC2.setText("");
             });
           
           
           insertCC.addActionListener(e->{
           	
           	jtfCC2.setVisible(true);
           	jtfCC1.setVisible(true);
           	jtfCC .setVisible(true);

           	jlPuntiCC.setVisible(true);
           	jlCFC.setVisible(true);
           	jlCodiceCC.setVisible(true);

           	if ( !check(jtfCC.getText()) || check(jtfCC1.getText()) || !check(jtfCC2.getText())){
                   String message = "Errore nel inserimento dei campi ";
                   JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                           JOptionPane.ERROR_MESSAGE);
               } 
           	else {
               	Tabella_CartaCliente cct = new Tabella_CartaCliente();
               	Carta_Cliente  cc = new Carta_Cliente ();
                   cc.setCodiceCarta(Integer.parseInt(jtfCC.getText()));
                   cc.setCF(jtfCC1.getText());
                   cc.setPunti(Integer.parseInt(jtfCC2.getText()));
           		cct.persist(cc);
               	
               	PreparedStatement statement = null;
               	String query = "select * from carte_clienti";
               	
           		try {
           			statement = (PreparedStatement) connection.prepareStatement(query);
           			ResultSet rs = statement.executeQuery();
           			tableCC.setModel(DbUtils.resultSetToTableModel(rs));      	       
           		} catch (Exception e1){
           				e1.printStackTrace();
           		}
           		
       	        jtfCC .setText("");
       	        jtfCC1.setText("");
       	        jtfCC2.setText("");
           	 }
              });      
        
 /*********************************************************************************************************************************/
      
           /******************************  SCHEDA ALLENAMENTO  *************************************************************************/        
           JPanel jpSA = new JPanel();
           jpSA.setLayout(null);
           
           JScrollPane scrollSA = new JScrollPane();
           scrollSA.setBounds(6, 31, 800, 330);
           jpSA.add(scrollSA);
           
           JTable tableSA = new JTable();
           tableSA.setRowHeight(23);
           scrollSA.setViewportView(tableSA);

           jpSA.add(tableSA.getTableHeader(), BorderLayout.NORTH);
           
           JButton loadSA = new JButton("Load dati");
           loadSA.setFont(new Font("Arial", Font.BOLD, 16));
           loadSA.setBounds(550, 730, 126, 23);
           JButton updateSA = new JButton("Aggiorna dati");
           updateSA.setFont(new Font("Arial", Font.BOLD, 16));
           updateSA.setBounds(370, 730, 160, 23);
           JButton delSA = new JButton("Cancella dati");
           delSA.setFont(new Font("Arial", Font.BOLD, 16));
           delSA.setBounds(205, 730, 145, 23);
           JButton insertSA = new JButton("Inserisci carta");
           insertSA.setFont(new Font("Arial", Font.BOLD, 16));
           insertSA.setBounds(16, 730, 180, 23);
           
           JLabel jlWelcomeSA = new JLabel("Compila per inserire / cancellare dati: ");
           jlWelcomeSA.setFont(new Font("Times", Font.BOLD,14));
           jlWelcomeSA.setBounds(100, 380, 400, 20);
          
           JLabel jlCodiceSA = new JLabel("Codice Scheda");
           jlCodiceSA.setFont(new Font("Times", Font.BOLD,14));
           jlCodiceSA.setBounds(20, 410, 160, 20);
           JTextField jtfSA = new JTextField();
           jtfSA.setBounds(167, 410, 97, 20);
           jtfSA.setText("");
           
           JLabel jlCFSA= new JLabel("CF_Cliente");
           jlCFSA.setFont(new Font("Times", Font.BOLD,14));
           jlCFSA.setBounds(20, 430, 135, 20);
           JTextField jtfSA1 = new JTextField();
           jtfSA1.setBounds(167, 430, 97, 20);
           
           JLabel jlCFISA= new JLabel("CF_Istruttore");
           jlCFISA.setFont(new Font("Times", Font.BOLD,14));
           jlCFISA.setBounds(20, 450, 135, 20);
           JTextField jtfSA2 = new  JTextField();
           jtfSA2.setBounds(167, 450, 97, 20);
           
           JLabel jlTipoSA= new JLabel("Tipo");
           jlTipoSA.setFont(new Font("Times", Font.BOLD,14));
           jlTipoSA.setBounds(20, 470, 135, 20);
           JComboBox<String> jtfSA3 = new JComboBox<String>(spec);
           jtfSA3.setBounds(167, 470, 97, 20);
           
           JLabel jlAltSA= new JLabel("Altezza");
           jlAltSA.setFont(new Font("Times", Font.BOLD,14));
           jlAltSA.setBounds(20, 490, 135, 20);
           JTextField jtfSA4 = new  JTextField();
           jtfSA4.setBounds(167, 490, 97, 20);
           
           JLabel jlPesoSA= new JLabel("Peso");
           jlPesoSA.setFont(new Font("Times", Font.BOLD,14));
           jlPesoSA.setBounds(20, 510, 135, 20);
           JTextField jtfSA5 = new  JTextField();
           jtfSA5.setBounds(167, 510, 97, 20);
                 
           JLabel jlGradoA= new JLabel("Grado muscolare");
           jlGradoA.setFont(new Font("Times", Font.BOLD,14));
           jlGradoA.setBounds(20, 530, 135, 20);
           String[] gradoM = new String [] { " ", "beginner", "amateur", "developed"};
           JComboBox<String> jtfSA6 = new JComboBox<String>(gradoM);
           jtfSA6.setBounds(167, 530, 97, 20);
           
           JLabel jlClearSA = new JLabel("Clica per pulire le aree di inseriemto");
           jlClearSA.setFont(new Font("Times", Font.BOLD,14));
           jlClearSA.setBounds(650, 570, 400, 20);
           JButton bSA = new JButton("CLEAR");
           bSA.setFont(new Font("Times", Font.BOLD,14));
           bSA.setBounds(700, 600, 100, 70);
   		   bSA.addActionListener(new ActionListener(){
   		    public void actionPerformed(ActionEvent e){
   		        jtfSA .setText("");
   		        jtfSA1.setText("");
   		        jtfSA2.setText("");
   		        jtfSA3.setSelectedIndex(0);
   		        jtfSA4.setText("");
   		        jtfSA5.setText("");
   		        jtfSA6.setSelectedIndex(0);

   		    }
   		});
   		
   		
   		jpSA.add(jlClearSA);
   		jpSA.add(bSA); // buttore per pulire 
   		jpSA.add(jlCodiceSA);
   		jpSA.add(jlCFSA);
   		jpSA.add(jlCFISA);
   		jpSA.add(jlTipoSA);
   		jpSA.add(jlAltSA);
   		jpSA.add(jlPesoSA);
   		jpSA.add(jlGradoA);

		jpSA.add(jlWelcomeSA);
   		jpSA.add(jtfSA);
   		jpSA.add(jtfSA1);
   		jpSA.add(jtfSA2);
   		jpSA.add(jtfSA3);
   		jpSA.add(jtfSA4);
   		jpSA.add(jtfSA5);
   		jpSA.add(jtfSA6);

   		jpSA.add(insertSA);
   		jpSA.add(delSA);
   		jpSA.add(updateSA);
   		jpSA.add(loadSA);
           
           
           delSA.addActionListener(e->{
            
        	jtfSA6.setVisible(false);
           	jtfSA5.setVisible(false);
           	jtfSA4.setVisible(false);
           	jtfSA3.setVisible(false);
           	jtfSA2.setVisible(false);
           	jtfSA1.setVisible(false);
           	jtfSA .setVisible(true);

       		jlCodiceSA.setVisible(true);
       		jlCFSA.setVisible(false);
       		jlCFISA.setVisible(false);
       		jlTipoSA.setVisible(false);
       		jlAltSA.setVisible(false);
       		jlPesoSA.setVisible(false);
       		jlGradoA.setVisible(false);

           	
               Tabella_Scheda sat = new Tabella_Scheda();
               sat.delete(Integer.parseInt(jtfSA.getText().toString()));
               PreparedStatement statement = null;
               String query = "select * from schede_allenamento";
               
                   try {
                       statement =  (PreparedStatement) connection.prepareStatement(query);  
                       ResultSet rs = statement.executeQuery();    
                       tableSA.setModel(DbUtils.resultSetToTableModel(rs));
                     
                   } catch (Exception e1) {
                       e1.printStackTrace();
                   } 
           });
           
           
           loadSA.addActionListener(e->{
           	PreparedStatement statement = null; 
           	String query = "select * from schede_allenamento";
           	
           		try {
           			statement = (PreparedStatement) connection.prepareStatement(query);
           			ResultSet rs = statement.executeQuery();  
                       tableSA.setModel(DbUtils.resultSetToTableModel(rs));
           		} catch (Exception e1){
           				e1.printStackTrace();
           		}
           });
           
           updateSA.addActionListener(e->{
           	
           	jtfSA6.setVisible(true);
           	jtfSA5.setVisible(true);
           	jtfSA4.setVisible(true);
           	jtfSA3.setVisible(true);
           	jtfSA2.setVisible(true);
           	jtfSA1.setVisible(true);
           	jtfSA .setVisible(true);

       		jlCodiceSA.setVisible(true);
       		jlCFSA.    setVisible(true);
       		jlCFISA.   setVisible(true);
       		jlTipoSA.  setVisible(true);
       		jlAltSA.   setVisible(true);
       		jlPesoSA.  setVisible(true);
       		jlGradoA.  setVisible(true);

           	
           	if ( !check(jtfSA.getText()) || check(jtfSA1.getText()) || check(jtfSA2.getText()) || !check(jtfSA4.getText()) || !check(jtfSA5.getText())){
                   String message = "Errore nel inserimento dei campi ";
                   JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                           JOptionPane.ERROR_MESSAGE);
               } 
               else {
               	Tabella_Scheda sat = new Tabella_Scheda();
               	Scheda_Allenamento  sa = new Scheda_Allenamento ();
                   sa.setCodiceScheda(Integer.parseInt(jtfSA.getText()));
                   sa.setCFc(jtfSA1.getText());
                   sa.setCFi(jtfSA2.getText());
                   sa.setTipo(jtfSA3.getSelectedItem().toString());
                   sa.setAltezza(Integer.parseInt(jtfSA4.getText()));
                   sa.setPeso(Integer.parseInt(jtfSA5.getText()));
                   sa.setGrado(jtfSA6.getSelectedItem().toString());
           		sat.update(sa);
      
                 PreparedStatement statement = null;
                 String query = "select * from schede_allenamento";
                 
                     try {
                     	statement = (PreparedStatement) connection.prepareStatement(query);
             			ResultSet rs = statement.executeQuery();
             			tableSA.setModel(DbUtils.resultSetToTableModel(rs));
                       
                     } catch (Exception e1) {
                         e1.printStackTrace();
                     }   
               }
		        jtfSA .setText("");
		        jtfSA1.setText("");
		        jtfSA2.setText("");
		        jtfSA3.setSelectedIndex(0);
		        jtfSA4.setText("");
		        jtfSA5.setText("");
		        jtfSA6.setSelectedIndex(0);
             });
           
           
           insertSA.addActionListener(e->{
           	
              	jtfSA6.setVisible(true);
               	jtfSA5.setVisible(true);
               	jtfSA4.setVisible(true);
               	jtfSA3.setVisible(true);
               	jtfSA2.setVisible(true);
               	jtfSA1.setVisible(true);
               	jtfSA .setVisible(true);

           		jlCodiceSA.setVisible(true);
           		jlCFSA.    setVisible(true);
           		jlCFISA.   setVisible(true);
           		jlTipoSA.  setVisible(true);
           		jlAltSA.   setVisible(true);
           		jlPesoSA.  setVisible(true);
           		jlGradoA.  setVisible(true);


            if ( !check(jtfSA.getText()) || check(jtfSA1.getText()) || check(jtfSA2.getText()) || !check(jtfSA4.getText()) || !check(jtfSA5.getText())){
                       String message = "Errore nel inserimento dei campi ";
                       JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                               JOptionPane.ERROR_MESSAGE);
                   } 
           	else {
               	Tabella_Scheda sat = new Tabella_Scheda();
               	Scheda_Allenamento  sa = new Scheda_Allenamento ();
                   sa.setCodiceScheda(Integer.parseInt(jtfSA.getText()));
                   sa.setCFc(jtfSA1.getText());
                   sa.setCFi(jtfSA2.getText());
                   sa.setTipo(jtfSA3.getSelectedItem().toString());
                   sa.setAltezza(Integer.parseInt(jtfSA4.getText()));
                   sa.setPeso(Integer.parseInt(jtfSA5.getText()));
                   sa.setGrado(jtfSA6.getSelectedItem().toString());
           		sat.persist(sa);
               	
               	PreparedStatement statement = null;
               	String query = "select * from schede_allenamento";
               	
           		try {
           			statement = (PreparedStatement) connection.prepareStatement(query);
           			ResultSet rs = statement.executeQuery();
           			tableSA.setModel(DbUtils.resultSetToTableModel(rs));      	       
           		} catch (Exception e1){
           				e1.printStackTrace();
           		}
           		
		        jtfSA .setText("");
		        jtfSA1.setText("");
		        jtfSA2.setText("");
		        jtfSA3.setSelectedIndex(0);
		        jtfSA4.setText("");
		        jtfSA5.setText("");
		        jtfSA6.setSelectedIndex(0);
           	 }
              });      
        
 /*********************************************************************************************************************************/        
           
           /****************************** VISITA *************************************************************************/        
              JPanel jpV = new JPanel();
              jpV.setLayout(null);
              
              JScrollPane scrollV = new JScrollPane();
              scrollV.setBounds(6, 31, 800, 300);
              jpV.add(scrollV);
              
              JTable tableV = new JTable();
              tableV.setRowHeight(23);
              scrollV.setViewportView(tableV);

              jpV.add(tableV.getTableHeader(), BorderLayout.NORTH);
              
              JButton loadV = new JButton("Load dati");
              loadV.setFont(new Font("Arial", Font.BOLD, 16));
              loadV.setBounds(550, 730, 126, 23);
              JButton updateV = new JButton("Aggiorna dati");
              updateV.setFont(new Font("Arial", Font.BOLD, 16));
              updateV.setBounds(370, 730, 160, 23);
              JButton delV = new JButton("Cancella dati");
              delV.setFont(new Font("Arial", Font.BOLD, 16));
              delV.setBounds(205, 730, 145, 23);
              JButton insertV = new JButton("Inserisci visita");
              insertV.setFont(new Font("Arial", Font.BOLD, 16));
              insertV.setBounds(16, 730, 180, 23);
              
              JLabel jlWelcomeV = new JLabel("Compila per inserire / cancellare dati: ");
              jlWelcomeV.setFont(new Font("Times", Font.BOLD,14));
              jlWelcomeV.setBounds(100, 380, 400, 20);
             
              JLabel jlCodiceV = new JLabel("Codice visita");
              jlCodiceV.setFont(new Font("Times", Font.BOLD,14));
              jlCodiceV.setBounds(20, 410, 160, 20);
              JTextField jtfV = new JTextField();
              jtfV.setBounds(167, 410, 97, 20);
              jtfV.setText("");
              
              JLabel jlCFFV= new JLabel("CF_Fisioterapista");
              jlCFFV.setFont(new Font("Times", Font.BOLD,14));
              jlCFFV.setBounds(20, 430, 135, 20);
              JTextField jtfV1 = new JTextField();
              jtfV1.setBounds(167, 430, 97, 20);
              
              JLabel jlCFCV = new JLabel("CF_Cliente");
              jlCFCV.setFont(new Font("Times", Font.BOLD,14));
              jlCFCV.setBounds(20, 450, 135, 20);
              JTextField jtfV2 = new  JTextField();
              jtfV2.setBounds(167, 450, 97, 20);
              
              JLabel jlEsitoV = new JLabel("Esito");
              jlEsitoV.setFont(new Font("Times", Font.BOLD,14));
              jlEsitoV.setBounds(20, 470, 135, 20);
              String[] esitoV = new String[] {" ", "Good", "Bad"};
              JComboBox<String> jtfV3 = new  JComboBox<String> (esitoV);
              jtfV3.setBounds(167, 470, 97, 20);
              
              JLabel jlDataV = new JLabel("Data");
              jlDataV.setFont(new Font("Times", Font.BOLD,14));
              jlDataV.setBounds(20, 490, 135, 20);
              JTextField jtfV4 = new  JTextField();
              jtfV4.setBounds(167, 490, 97, 20);
              
                 
              
              JLabel jlClearV = new JLabel("Clica per pulire le aree di inseriemto");
              jlClearV.setFont(new Font("Times", Font.BOLD,14));
              jlClearV.setBounds(650, 570, 400, 20);
              JButton bV = new JButton("CLEAR");
              bV.setFont(new Font("Times", Font.BOLD,14));
              bV.setBounds(700, 600, 100, 70);
              bV.addActionListener(new ActionListener(){
      		    public void actionPerformed(ActionEvent e){
      		        jtfV .setText("");
      		        jtfV1.setText("");
      		        jtfV2.setText("");
      		        jtfV3.setSelectedIndex(0);
      		        jtfV4.setText("");

      		    }
      		});
      		
      		
      		jpV.add(jlClearV);
      		jpV.add(bV); // buttore per pulire 
      		jpV.add(jlCodiceV);
      		jpV.add(jlCFCV);
      		jpV.add(jlCFFV);
      		jpV.add(jlEsitoV);
      		jpV.add(jlDataV);

      		jpV.add(jlWelcomeV);
      		jpV.add(jtfV);
      		jpV.add(jtfV1);
      		jpV.add(jtfV2);
      		jpV.add(jtfV3);
      		jpV.add(jtfV4);

      		jpV.add(insertV);
      		jpV.add(delV);
      		jpV.add(updateV);
      		jpV.add(loadV);
              
              
              delV.addActionListener(e->{
                
            	jtfV4.setVisible(false);
                jtfV3.setVisible(false);
              	jtfV2.setVisible(false);
              	jtfV1.setVisible(false);
              	jtfV .setVisible(true);

              	jlDataV.setVisible(false);
              	jlEsitoV.setVisible(false);
              	jlCFCV.setVisible(false);
              	jlCFFV.setVisible(false);
              	jlCodiceV.setVisible(true);

              	
                  Tabella_Visita vt = new Tabella_Visita();
                  vt.delete(Integer.parseInt(jtfV.getText().toString()));
                  PreparedStatement statement = null;
                  String query = "select * from visite";
                  
                      try {
                          statement =  (PreparedStatement) connection.prepareStatement(query);  
                          ResultSet rs = statement.executeQuery();    
                          tableV.setModel(DbUtils.resultSetToTableModel(rs));
                        
                      } catch (Exception e1) {
                          e1.printStackTrace();
                      } 
              });
              
              
              loadV.addActionListener(e->{
              	PreparedStatement statement = null; 
              	String query = "select * from visite";
              	
              		try {
              			statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();  
                          tableV.setModel(DbUtils.resultSetToTableModel(rs));
              		} catch (Exception e1){
              				e1.printStackTrace();
              		}
              });
              
              updateV.addActionListener(e->{
              	
            	jtfV4.setVisible(true);
                jtfV3.setVisible(true);
              	jtfV2.setVisible(true);
              	jtfV1.setVisible(true);
              	jtfV .setVisible(true);

              	jlDataV.setVisible(true);
              	jlEsitoV.setVisible(true);
              	jlCFCV.setVisible(true);
              	jlCFFV.setVisible(true);
              	jlCodiceV.setVisible(true);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsed = null;    
              	
              	if ( !check(jtfV.getText()) || check(jtfV1.getText()) || check(jtfV2.getText())){
                      String message = "Errore nel inserimento dei campi ";
                      JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                              JOptionPane.ERROR_MESSAGE);
                  } 
                  else {        	  
          
                  try {
                      parsed = format.parse(jtfV4.getText());     
                  } catch (Exception e2) {
                      // TODO Auto-generated catch block
                      e2.printStackTrace();
                  }
                  java.sql.Date sql = new java.sql.Date(parsed.getTime());
                  	
                  Tabella_Visita vt = new Tabella_Visita();
                  	Visita  v = new Visita();
                      v.setCodiceVisita(Integer.parseInt(jtfV.getText()));
                      v.setCFc(jtfV1.getText());
                      v.setCFf(jtfV2.getText());
                      v.setEsito(jtfV3.getSelectedItem().toString());
                      v.setData(sql);

              		vt.update(v);
         
                    PreparedStatement statement = null;
                    String query = "select * from visite";
                    
                        try {
                        	statement = (PreparedStatement) connection.prepareStatement(query);
                			ResultSet rs = statement.executeQuery();
                			tableV.setModel(DbUtils.resultSetToTableModel(rs));
                          
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }   
                  }
              		
              		jtfV .setText("");
              		jtfV1.setText("");
              		jtfV2.setText("");
              		jtfV3.setSelectedIndex(0);
              		jtfV4.setText("");
                });
              
              
              insertV.addActionListener(e->{
              	
              	jtfV4.setVisible(true);
                jtfV3.setVisible(true);
              	jtfV2.setVisible(true);
              	jtfV1.setVisible(true);
              	jtfV .setVisible(true);

              	jlDataV.setVisible(true);
              	jlEsitoV.setVisible(true);
              	jlCFCV.setVisible(true);
              	jlCFFV.setVisible(true);
              	jlCodiceV.setVisible(true);
              	
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsed = null;  

              	if ( !check(jtfV.getText()) || check(jtfV1.getText()) || check(jtfV2.getText())){
                    String message = "Errore nel inserimento dei campi ";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                } 
              	else {
                    
                    try {
                        parsed = format.parse(jtfV4.getText());     
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    	
                    Tabella_Visita vt = new Tabella_Visita();
                    	Visita  v = new Visita();
                        v.setCodiceVisita(Integer.parseInt(jtfV.getText()));
                        v.setCFc(jtfV1.getText());
                        v.setCFf(jtfV2.getText());
                        v.setEsito(jtfV3.getSelectedItem().toString());
                        v.setData(sql);
                        vt.persist(v);
                  	
                  	PreparedStatement statement = null;
                  	String query = "select * from visite";
                  	
              		try {
              			statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();
              			tableV.setModel(DbUtils.resultSetToTableModel(rs));      	       
              		} catch (Exception e1){
              				e1.printStackTrace();
              		}
              				
              		jtfV .setText("");
              		jtfV1.setText("");
              		jtfV2.setText("");
              		jtfV3.setSelectedIndex(0);
              		jtfV4.setText("");
              	 }
                 });      
           
 /*********************************************************************************************************************************/    
              
             /***********************  CONTROLLO MACCHINARIO  ******************************/
              
              JPanel jpCM = new JPanel();
              jpCM.setLayout(null);
              
              JScrollPane scrollCM = new JScrollPane();
              scrollCM.setBounds(6, 31, 800, 330);
              jpCM.add(scrollCM);
              
              JTable tableCM = new JTable();
              tableCM.setRowHeight(23);
              scrollCM.setViewportView(tableCM);

              jpCM.add(tableCM.getTableHeader(), BorderLayout.NORTH);
              
              JButton loadCM = new JButton("Load dati");
              loadCM.setFont(new Font("Arial", Font.BOLD, 16));
              loadCM.setBounds(530, 730, 126, 23);
              JButton updateCM = new JButton("Aggiorna dati");
              updateCM.setFont(new Font("Arial", Font.BOLD, 16));
              updateCM.setBounds(350, 730, 160, 23);
              JButton delCM = new JButton("Cancella dati");
              delCM.setFont(new Font("Arial", Font.BOLD, 16));
              delCM.setBounds(185, 730, 145, 23);
              JButton insertCM = new JButton("Inserisci controllo ");
              insertCM.setFont(new Font("Arial", Font.BOLD, 16));
              insertCM.setBounds(16, 730, 160, 23);
              
              JLabel jlWelcomeCM = new JLabel("Compila per inserire / cancellare controllo: ");
              jlWelcomeCM.setFont(new Font("Times", Font.BOLD,14));
              jlWelcomeCM.setBounds(100, 380, 400, 20);
              
              JLabel jlCodiceC = new JLabel("Codice controllo");
              jlCodiceC.setFont(new Font("Times", Font.BOLD,14));
              jlCodiceC.setBounds(20, 415, 135, 20);
              JTextField jtfCM1 = new JTextField();
              jtfCM1.setBounds(168, 415, 105, 20);
              jtfCM1.setText("");
              
              JLabel jlCodiceCM = new JLabel("Codice fabbricazione M.");
              jlCodiceCM.setFont(new Font("Times", Font.BOLD,14));
              jlCodiceCM.setBounds(20, 435, 160, 20);
              JTextField jtfCM2 = new JTextField();
              jtfCM2.setBounds(168, 435, 105, 20);
              jtfCM2.setText("");
              
              String[] esitoC = new String[]{" ", "Bad", "Average", "Good"};
              JLabel jlEsitoCM = new JLabel("Esito controllo");
              jlEsitoCM.setFont(new Font("Times", Font.BOLD,14));
              jlEsitoCM.setBounds(20, 455, 135, 20);
              JComboBox<String> jtfCM3 = new JComboBox<String>(esitoC);
              jtfCM3.setBounds(168, 455, 105, 20);
              
              JLabel jlDataCM = new JLabel("Data (gg/mm/aa)");
              jlDataCM.setFont(new Font("Times", Font.BOLD,14));
              jlDataCM.setBounds(20, 475, 135, 20);
              JTextField jtfCM4 = new JTextField();
              jtfCM4.setBounds(168, 478, 105, 20);
             
              
              // Button to clear the text fields
              JLabel jlClearCM = new JLabel("Clica per pulire le aree di inseriemto");
              jlClearCM.setFont(new Font("Times", Font.BOLD,14));
              jlClearCM.setBounds(650, 570, 400, 20);
              JButton bCM = new JButton("CLEAR");
              bCM.setFont(new Font("Times", Font.BOLD,14));
              bCM.setBounds(700, 600, 100, 70);
      		  bCM.addActionListener(new ActionListener(){
      		    public void actionPerformed(ActionEvent e){
      		    	jtfCM1.setText("");
      		        jtfCM2.setText("");
      		        jtfCM3.setSelectedIndex(0);		        
      		        jtfCM4.setText("");		        
      		    }
      		});
              
      		jpCM.add(jlClearCM);
      		jpCM.add(bCM); // buttore per pulire 
      		jpCM.add(jlCodiceC);
      		jpCM.add(jlCodiceCM);
      		jpCM.add(jlEsitoCM);
      		jpCM.add(jlDataCM);
              
      		jpCM.add(jlWelcomeCM);
      		jpCM.add(jtfCM1);
      		jpCM.add(jtfCM2);
      		jpCM.add(jtfCM3);
      		jpCM.add(jtfCM4);
      		
      		jpCM.add(insertCM);
      		jpCM.add(delCM);
      		jpCM.add(updateCM);
      		jpCM.add(loadCM);
              
              
            delCM.addActionListener(e->{

            	jtfCM4.setVisible(false);
              	jtfCM3.setVisible(false);
              	jtfCM2.setVisible(false);
              	jtfCM1.setVisible(true);

              	jlDataCM.setVisible(false);
              	jlEsitoCM.setVisible(false);
              	jlCodiceCM.setVisible(false);
              	jlCodiceC.setVisible(true);
              	
              	if (!check(jtfCM1.getText()) || !check(jtfCM2.getText())){
                      String message = "Errore nel inserimento dei campi ";
                      JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                              JOptionPane.ERROR_MESSAGE);
                  }
              	
                  Tabella_Controllo ct = new Tabella_Controllo();
                  ct.delete(Integer.parseInt(jtfCM1.getText()));
                  PreparedStatement statement = null;
                  String query = "select * from controlli_macchinari";
                  
                      try {
                          statement =  (PreparedStatement) connection.prepareStatement(query);  
                          ResultSet rs = statement.executeQuery();    
                          tableCM.setModel(DbUtils.resultSetToTableModel(rs));
                        
                      } catch (Exception e1) {
                          e1.printStackTrace();
                      } 
              });
              
              
              loadCM.addActionListener(e->{
              	PreparedStatement statement = null; 
              	String query = "select * from controlli_macchinari";
              	
              		try {
              			statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();
              			tableCM.setModel(DbUtils.resultSetToTableModel(rs));

              		} catch (Exception e1){
              				e1.printStackTrace();
              		}
              });
              
              updateCM.addActionListener(e->{
              	
              	jtfCM4.setVisible(true);
              	jtfCM3.setVisible(true);
              	jtfCM2.setVisible(true);
              	jtfCM1.setVisible(true);

              	jlDataCM.setVisible(true);
              	jlEsitoCM.setVisible(true);
              	jlCodiceCM.setVisible(true);
              	jlCodiceC.setVisible(true);
            	
              	if (!check(jtfCM1.getText()) || !check(jtfCM2.getText())){
                    String message = "Errore nel inserimento dei campi ";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }
            	
                else {
              	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date parsed = null;              
                    try {
                        parsed = format.parse(jtfCM4.getText());
                        
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());                      
                  	
                  Tabella_Controllo cmt = new Tabella_Controllo();
                  Controllo_Macchinario cm = new Controllo_Macchinario();
                  	cm.setCodiceControllo(Integer.parseInt(jtfCM1.getText()));
                  	cm.setCodiceFabbricazione(Integer.parseInt(jtfCM2.getText()));
                  	cm.setEsito(jtfCM3.getSelectedItem().toString());
                  	cm.setData(sql);
                  	cmt.update(cm);
       
                  PreparedStatement statement = null;
                  String query = "select * from controlli_macchinari";
                  
                      try {
                      	statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();
              			tableCM.setModel(DbUtils.resultSetToTableModel(rs));
                        
                      } catch (Exception e1) {
                          e1.printStackTrace();
                      }   
                  }		    
  		    		jtfCM1.setText("");
  		    		jtfCM2.setText("");
  		    		jtfCM3.setSelectedIndex(0);		        
  		    		jtfCM4.setText("");
              });
              
              
              insertCM.addActionListener(e->{
                	
                	jtfCM4.setVisible(true);
                	jtfCM3.setVisible(true);
                	jtfCM2.setVisible(true);
                	jtfCM1.setVisible(true);

                	jlDataCM.setVisible(true);
                	jlEsitoCM.setVisible(true);
                	jlCodiceCM.setVisible(true);
                	jlCodiceC.setVisible(true);
              	
              	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                  java.util.Date parsed = null;
                 
              	if (!check(jtfCM1.getText()) || !check(jtfCM2.getText())){
                    String message = "Errore nel inserimento dei campi ";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }
                  else {
                  	 
                  	try {
                         parsed = format.parse(jtfA4.getText());   
                    } catch (Exception e2) {
                     	 // TODO Auto-generated catch block
                         e2.printStackTrace();
                    }    
                      java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    	
                      Tabella_Controllo cmt = new Tabella_Controllo();
                      Controllo_Macchinario cm = new Controllo_Macchinario();
                      	cm.setCodiceControllo(Integer.parseInt(jtfCM1.getText()));
                      	cm.setCodiceFabbricazione(Integer.parseInt(jtfCM2.getText()));
                      	cm.setEsito(jtfCM3.getSelectedItem().toString());
                      	cm.setData(sql);
                      	cmt.persist(cm);
                  	
                  	PreparedStatement statement = null;
                  	String query = "select * from controlli_macchinari";
                  	
                  		try {
                  			statement = (PreparedStatement) connection.prepareStatement(query);
                  			ResultSet rs = statement.executeQuery();
                  			tableCM.setModel(DbUtils.resultSetToTableModel(rs));
                  	        } catch ( Exception e1) {
                  			e1.printStackTrace();
                  		}
                  		
      		    		jtfCM1.setText("");
      		    		jtfCM2.setText("");
      		    		jtfCM3.setSelectedIndex(0);		        
      		    		jtfCM4.setText("");
                   }
                 });

       /*******************************************************************************************************************************/  
              
              
             /***********************  ESERCITAZIONE GRUPPO ******************************/
              
              JPanel jpES = new JPanel();
              jpES.setLayout(null);
              
              JScrollPane scrollES = new JScrollPane();
              scrollES.setBounds(6, 31, 800, 330);
              jpES.add(scrollES);
              
              JTable tableES = new JTable();
              tableES.setRowHeight(23);
              scrollES.setViewportView(tableES);

              jpES.add(tableES.getTableHeader(), BorderLayout.NORTH);
              
              JButton loadES = new JButton("Load dati");
              loadES.setFont(new Font("Arial", Font.BOLD, 16));
              loadES.setBounds(530, 730, 126, 23);
              JButton updateES = new JButton("Aggiorna dati");
              updateES.setFont(new Font("Arial", Font.BOLD, 16));
              updateES.setBounds(350, 730, 160, 23);
              JButton delES = new JButton("Cancella dati");
              delES.setFont(new Font("Arial", Font.BOLD, 16));
              delES.setBounds(185, 730, 145, 23);
              JButton insertES = new JButton("Inserisci esercitazione ");
              insertES.setFont(new Font("Arial", Font.BOLD, 16));
              insertES.setBounds(16, 730, 160, 23);
              
              JLabel jlWelcomeES = new JLabel("Compila per inserire / cancellare esercitazione: ");
              jlWelcomeES.setFont(new Font("Times", Font.BOLD,14));
              jlWelcomeES.setBounds(100, 380, 400, 20);
              
              JLabel jlCodiceES = new JLabel("Codice esercitazione");
              jlCodiceES.setFont(new Font("Times", Font.BOLD,14));
              jlCodiceES.setBounds(20, 415, 135, 20);
              JTextField jtfES1 = new JTextField();
              jtfES1.setBounds(168, 415, 105, 20);
              jtfES1.setText("");
              
              JLabel jlCFES = new JLabel("CF_Cliente");
              jlCFES.setFont(new Font("Times", Font.BOLD,14));
              jlCFES.setBounds(20, 435, 160, 20);
              JTextField jtfES2 = new JTextField();
              jtfES2.setBounds(168, 435, 105, 20);
              jtfES2.setText("");
              
              String[] metodoES = new String[]{" ", "da solo", "in gruppo"};
              JLabel jlMetodoES = new JLabel("Esercitazione )");
              jlMetodoES.setFont(new Font("Times", Font.BOLD,14));
              jlMetodoES.setBounds(20, 455, 135, 20);
              JComboBox<String> jtfES3 = new JComboBox<String>(metodoES);
              jtfES3.setBounds(168, 455, 105, 20);
              
              JLabel jlGruppoES = new JLabel("Codice gruppo");
              jlGruppoES.setFont(new Font("Times", Font.BOLD,14));
              jlGruppoES.setBounds(20, 475, 160, 20);
              JTextField jtfES4 = new JTextField();
              jtfES4.setBounds(168, 475, 105, 20);
              jtfES4.setText("");
              
              JLabel jlSettoreES = new JLabel("Codice settore");
              jlSettoreES.setFont(new Font("Times", Font.BOLD,14));
              jlSettoreES.setBounds(20, 495, 160, 20);
              JTextField jtfES5 = new JTextField();
              jtfES5.setBounds(168, 495, 105, 20);
              jtfES5.setText("");
             
              
              // Button to clear the text fields
              JLabel jlClearES = new JLabel("Clica per pulire le aree di inseriemto");
              jlClearES.setFont(new Font("Times", Font.BOLD,14));
              jlClearES.setBounds(650, 570, 400, 20);
              JButton bES = new JButton("CLEAR");
              bES.setFont(new Font("Times", Font.BOLD,14));
              bES.setBounds(700, 600, 100, 70);
              bES.addActionListener(new ActionListener(){
      		    public void actionPerformed(ActionEvent e){
      		    	jtfES1.setText("");
      		        jtfES2.setText("");
      		        jtfES3.setSelectedIndex(0);		        
      		        jtfES4.setText("");		
      		        jtfES5.setText("");		        
      		    }
      		});
              
      		jpES.add(jlClearES);
      		jpES.add(bES); // buttore per pulire 
      		jpES.add(jlCodiceES);
      		jpES.add(jlCFES);
      		jpES.add(jlMetodoES);
      		jpES.add(jlGruppoES);
      		jpES.add(jlSettoreES);

              
      		jpES.add(jlWelcomeES);
      		jpES.add(jtfES1);
      		jpES.add(jtfES2);
      		jpES.add(jtfES3);
      		jpES.add(jtfES4);
      		jpES.add(jtfES5);
      		
      		jpES.add(insertES);
      		jpES.add(delES);
      		jpES.add(updateES);
      		jpES.add(loadES);
                           
            delES.addActionListener(e->{

            	jtfES5.setVisible(false);
            	jtfES4.setVisible(false);
              	jtfES3.setVisible(false);
              	jtfES2.setVisible(false);
              	jtfES1.setVisible(true);

              	jlSettoreES.setVisible(false);
              	jlGruppoES.setVisible(false);
              	jlMetodoES.setVisible(false);
              	jlCFES.setVisible(false);
              	jlCodiceES.setVisible(true);
              	
              	if (!check(jtfES1.getText()) || check(jtfES2.getText()) || !check(jtfES5.getText())){
                      String message = "Errore nel inserimento dei campi ";
                      JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                              JOptionPane.ERROR_MESSAGE);
                  }
              	
                  Tabella_EsGruppo est = new Tabella_EsGruppo();
                  est.delete(Integer.parseInt(jtfES1.getText()));
                  PreparedStatement statement = null;
                  String query = "select * from esercitazioni_gruppo";
                  
                      try {
                          statement =  (PreparedStatement) connection.prepareStatement(query);  
                          ResultSet rs = statement.executeQuery();    
                          tableES.setModel(DbUtils.resultSetToTableModel(rs));
                        
                      } catch (Exception e1) {
                          e1.printStackTrace();
                      } 
              });
              
              
              loadES.addActionListener(e->{
              	PreparedStatement statement = null; 
              	String query = "select * from  esercitazioni_gruppo";
              	
              		try {
              			statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();
              			tableES.setModel(DbUtils.resultSetToTableModel(rs));

              		} catch (Exception e1){
              				e1.printStackTrace();
              		}
              });
              
              updateES.addActionListener(e->{

            	  	jtfES5.setVisible(true);
            	  	jtfES4.setVisible(true);
                	jtfES3.setVisible(true);
                	jtfES2.setVisible(true);
                	jtfES1.setVisible(true);

                	jlSettoreES.setVisible(true);
                	jlGruppoES.setVisible(true);
                	jlMetodoES.setVisible(true);
                	jlCFES.setVisible(true);
                	jlCodiceES.setVisible(true);
            	
                  	if (!check(jtfES1.getText()) || check(jtfES2.getText()) || !check(jtfES5.getText())){
                        String message = "Errore nel inserimento dei campi ";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                    }
            	
                else {                    
                  	
                  Tabella_EsGruppo est = new Tabella_EsGruppo();
                  EsGruppo es = new EsGruppo();
                  	es.setCodiceEsercitazione(Integer.parseInt(jtfES1.getText()));
                  	es.setCFc(jtfES2.getText());
                    es.setCodiceGruppo(Integer.parseInt(jtfES4.getText()));
                    es.setCodiceSettore(Integer.parseInt(jtfES5.getText()));
                 	est.update(es);
       
                  PreparedStatement statement = null;
                  String query = "select * from esercitazioni_gruppo";
                  
                      try {
                      	statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();
              			tableES.setModel(DbUtils.resultSetToTableModel(rs));
                        
                      } catch (Exception e1) {
                          e1.printStackTrace();
                      }   
                  }		    
      		    	jtfES1.setText("");
      		        jtfES2.setText("");
      		        jtfES3.setSelectedIndex(0);		        
      		        jtfES4.setText("");		
      		        jtfES5.setText("");	
              });
              
              
              insertES.addActionListener(e->{
                	
          	  	jtfES5.setVisible(true);
          	  	jtfES4.setVisible(true);
              	jtfES3.setVisible(true);
              	jtfES2.setVisible(true);
              	jtfES1.setVisible(true);

              	jlSettoreES.setVisible(true);
              	jlGruppoES.setVisible(true);
              	jlMetodoES.setVisible(true);
              	jlCFES.setVisible(true);
              	jlCodiceES.setVisible(true);
                 
              	if (!check(jtfES1.getText()) || check(jtfES2.getText()) || !check(jtfES5.getText())){
                    String message = "Errore nel inserimento dei campi ";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }
        	
              	else {                    
              	
              		Tabella_EsGruppo est = new Tabella_EsGruppo();
              		EsGruppo es = new EsGruppo();
              			es.setCodiceEsercitazione(Integer.parseInt(jtfES1.getText()));
              			es.setCFc(jtfES2.getText());
              			es.setCodiceGruppo(Integer.parseInt(jtfES4.getText()));
              			es.setCodiceSettore(Integer.parseInt(jtfES5.getText()));
                      	est.persist(es);
                  	
                  	PreparedStatement statement = null;
                  	String query = "select * from esercitazioni_gruppo";
                  	
                  		try {
                  			statement = (PreparedStatement) connection.prepareStatement(query);
                  			ResultSet rs = statement.executeQuery();
                  			tableES.setModel(DbUtils.resultSetToTableModel(rs));
                  	        } catch ( Exception e1) {
                  			e1.printStackTrace();
                  		}
                  		
          		    	jtfES1.setText("");
          		        jtfES2.setText("");
          		        jtfES3.setSelectedIndex(0);		        
          		        jtfES4.setText("");		
          		        jtfES5.setText("");	
                   }
          });

       /*******************************************************************************************************************************/               
              
              
              
             /***********************  METODO ESERCITAZIONE  ******************************/
              
              JPanel jpMES = new JPanel();
              jpMES.setLayout(null);
              
              JScrollPane scrollMES = new JScrollPane();
              scrollMES.setBounds(6, 31, 800, 330);
              jpMES.add(scrollMES);
              
              JTable tableMES = new JTable();
              tableMES.setRowHeight(23);
              scrollMES.setViewportView(tableMES);

              jpMES.add(tableMES.getTableHeader(), BorderLayout.NORTH);
              
              JButton loadMES = new JButton("Load dati");
              loadMES.setFont(new Font("Arial", Font.BOLD, 16));
              loadMES.setBounds(530, 730, 126, 23);
              JButton updateMES = new JButton("Aggiorna dati");
              updateMES.setFont(new Font("Arial", Font.BOLD, 16));
              updateMES.setBounds(350, 730, 160, 23);
              JButton delMES = new JButton("Cancella dati");
              delMES.setFont(new Font("Arial", Font.BOLD, 16));
              delMES.setBounds(185, 730, 145, 23);
              JButton insertMES = new JButton("Inserisci metodo ");
              insertMES.setFont(new Font("Arial", Font.BOLD, 16));
              insertMES.setBounds(16, 730, 160, 23);
              
              JLabel jlWelcomeMES = new JLabel("Compila per inserire / cancellare metodo: ");
              jlWelcomeMES.setFont(new Font("Times", Font.BOLD,14));
              jlWelcomeMES.setBounds(100, 380, 400, 20);
              
              JLabel jlCFMES = new JLabel("CF_Cliente");
              jlCFMES.setFont(new Font("Times", Font.BOLD,14));
              jlCFMES.setBounds(20, 415, 160, 20);
              JTextField jtfMES1 = new JTextField();
              jtfMES1.setBounds(168, 415, 105, 20);
              jtfMES1.setText("");
              
              String[] metodoMES = new String[]{" ", "da solo", "in gruppo"};
              JLabel jlMetodoMES = new JLabel("Esercitazione ");
              jlMetodoMES.setFont(new Font("Times", Font.BOLD,14));
              jlMetodoMES.setBounds(20, 435, 135, 20);
              JComboBox<String> jtfMES2 = new JComboBox<String>(metodoES);
              jtfMES2.setBounds(168, 435, 105, 20);
              
              JLabel jlSettoreMES = new JLabel("Codice settore");
              jlSettoreMES.setFont(new Font("Times", Font.BOLD,14));
              jlSettoreMES.setBounds(20, 455, 160, 20);
              JTextField jtfMES3 = new JTextField();
              jtfMES3.setBounds(168, 455, 105, 20);
              jtfMES3.setText("");
             
              
              // Button to clear the text fields
              JLabel jlClearMES = new JLabel("Clica per pulire le aree di inseriemto");
              jlClearMES.setFont(new Font("Times", Font.BOLD,14));
              jlClearMES.setBounds(650, 570, 400, 20);
              JButton bMES = new JButton("CLEAR");
              bMES.setFont(new Font("Times", Font.BOLD,14));
              bMES.setBounds(700, 600, 100, 70);
              bMES.addActionListener(new ActionListener(){
      		    public void actionPerformed(ActionEvent e){
      		        jtfMES1.setText("");
      		        jtfMES2.setSelectedIndex(0);		        
      		        jtfMES3.setText("");		
      		    }
      		});
              
      		jpMES.add(jlClearMES);
      		jpMES.add(bMES); // buttore per pulire 
      		jpMES.add(jlCFMES);
      		jpMES.add(jlMetodoMES);
      		jpMES.add(jlSettoreMES);

              
      		jpMES.add(jlWelcomeMES);
      		jpMES.add(jtfMES1);
      		jpMES.add(jtfMES2);
      		jpMES.add(jtfMES3);

      		jpMES.add(insertMES);
      		jpMES.add(delMES);
      		jpMES.add(updateMES);
      		jpMES.add(loadMES);
                           
            delMES.addActionListener(e->{
            	
              	jtfES3.setVisible(false);
              	jtfES2.setVisible(true);
              	jtfES1.setVisible(true);

              	jlSettoreMES.setVisible(false);
              	jlMetodoMES.setVisible(true);
              	jlCFMES.setVisible(true);
              	
              	if (check(jtfES1.getText()) || !check(jtfMES3.getText())){
                      String message = "Errore nel inserimento dei campi ";
                      JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                              JOptionPane.ERROR_MESSAGE);
                  }
              	
                  Tabella_MetodoEsercitazione mest = new Tabella_MetodoEsercitazione();
                  mest.delete(jtfMES1.getText(), jtfMES2.getSelectedItem().toString());
                  PreparedStatement statement = null;
                  String query = "select * from metodi_esercitazione";
                  
                      try {
                          statement =  (PreparedStatement) connection.prepareStatement(query);  
                          ResultSet rs = statement.executeQuery();    
                          tableMES.setModel(DbUtils.resultSetToTableModel(rs));
                        
                      } catch (Exception e1) {
                          e1.printStackTrace();
                      } 
              });
              
              
              loadMES.addActionListener(e->{
              	PreparedStatement statement = null; 
              	String query = "select * from  metodi_esercitazione";
              	
              		try {
              			statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();
              			tableMES.setModel(DbUtils.resultSetToTableModel(rs));

              		} catch (Exception e1){
              				e1.printStackTrace();
              		}
              });
              
              updateMES.addActionListener(e->{

                	jtfES3.setVisible(true);
                  	jtfES2.setVisible(true);
                  	jtfES1.setVisible(true);

                  	jlSettoreMES.setVisible(true);
                  	jlMetodoMES.setVisible(true);
                  	jlCFMES.setVisible(true);
                  	
                 if (check(jtfES1.getText()) || !check(jtfMES3.getText())){
                        String message = "Errore nel inserimento dei campi ";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                    }
                else {                    
                  	
                    Tabella_MetodoEsercitazione mest = new Tabella_MetodoEsercitazione();
                    Metodo_Es mes = new Metodo_Es();
                  	mes.setCF(jtfMES1.getText());
                  	mes.setMetodo(jtfMES2.getSelectedItem().toString());
                    mes.setCodiceSettore(Integer.parseInt(jtfMES3.getText()));
                 	mest.update(mes);
       
                  PreparedStatement statement = null;
                  String query = "select * from metodi_esercitazione";
                  
                      try {
                      	statement = (PreparedStatement) connection.prepareStatement(query);
              			ResultSet rs = statement.executeQuery();
              			tableMES.setModel(DbUtils.resultSetToTableModel(rs));
                        
                      } catch (Exception e1) {
                          e1.printStackTrace();
                      }   
                  }		    
                 	jtfMES1.setText("");
                 	jtfMES2.setSelectedIndex(0);		        
                 	jtfMES3.setText("");	
              });
              
              
              insertMES.addActionListener(e->{
                	
              	jtfES3.setVisible(true);
              	jtfES2.setVisible(true);
              	jtfES1.setVisible(true);

              	jlSettoreMES.setVisible(true);
              	jlMetodoMES.setVisible(true);
              	jlCFMES.setVisible(true);
              	
              	if (check(jtfES1.getText()) || !check(jtfMES3.getText())){
                    String message = "Errore nel inserimento dei campi ";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }
              	else {                    
              	
                Tabella_MetodoEsercitazione mest = new Tabella_MetodoEsercitazione();
                Metodo_Es mes = new Metodo_Es();
              		mes.setCF(jtfMES1.getText());
              		mes.setMetodo(jtfMES2.getSelectedItem().toString());
              		mes.setCodiceSettore(Integer.parseInt(jtfMES3.getText()));
                    mest.persist(mes);
                  	
                  	PreparedStatement statement = null;
                  	String query = "select * from metodi_esercitazione";
                  	
                  		try {
                  			statement = (PreparedStatement) connection.prepareStatement(query);
                  			ResultSet rs = statement.executeQuery();
                  			tableMES.setModel(DbUtils.resultSetToTableModel(rs));
                  	        } catch ( Exception e1) {
                  			e1.printStackTrace();
                  		}
                  		
                     	jtfMES1.setText("");
                     	jtfMES2.setSelectedIndex(0);		        
                     	jtfMES3.setText("");	
                   }
          });

       /*******************************************************************************************************************************/        

              
              
/*************************************************************************************************************************************************************/         
              		/* CLIENTE INTERROGRAZIONE */
       JPanel jpClInt = new JPanel();
       jpClInt.setLayout(null);
       
       JScrollPane spClInt = new JScrollPane();
       spClInt.setBounds(6, 31, 800, 330);
       jpClInt.add(spClInt);
       
       JTable bigTable = new JTable();
       spClInt.setViewportView(bigTable);
       jpClInt.add(bigTable.getTableHeader(), BorderLayout.NORTH);

       JButton loadCl = new JButton("Clienti in una citt‡");
       loadCl.setBounds(551, 400, 440, 30);
       
       JLabel jlCitta = new JLabel("Citta ");
       jlCitta.setBounds(25,400, 100, 20);
       JTextField jtfcitt‡ = new JTextField();
       jtfcitt‡.setBounds(100, 400, 97, 20);
       jtfcitt‡.setText("");
       
       
       jpClInt.add(loadCl);
       jpClInt.add(jtfcitt‡);
       jpClInt.add(jlCitta);

       loadCl.addActionListener(e->{

    		   if( check(jtfcitt‡.getText()) ){
                   String message = "Errore nel inserimento dei cf ";
                   JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                           JOptionPane.ERROR_MESSAGE);
               }
    		   else { 
    			   PreparedStatement statement = null;
    			   String query = "select * "
    			   		+ " from clienti C"
    			   		+ " where C.Citt‡ = '"+jtfcitt‡.getText()+"'";
    			   try {
                       statement =  (PreparedStatement) connection.prepareStatement(query);  
                       ResultSet rs = statement.executeQuery();    
                       bigTable.setModel(DbUtils.resultSetToTableModel(rs));
                     
                   } catch (Exception e1) {
                       e1.printStackTrace();
                   } 
    		   }
	  });
       
       JButton loadCl1 = new JButton("Clienti di una citta che pagano pi˘ di ...");
       loadCl1.setBounds(551, 430, 440, 30);
       
       JLabel jlSint = new JLabel("Prezzo ");
       jlSint.setBounds(25,420, 100, 20);
       JTextField jtfSint = new JTextField();
       jtfSint.setBounds(100, 420, 97, 20);
       jtfSint.setText("");
       
       
       jpClInt.add(loadCl1);
       jpClInt.add(jlSint);
       jpClInt.add(jtfSint);

       loadCl1.addActionListener(e->{

    		   if( !check(jtfSint.getText()) || check(jtfcitt‡.getText())){
                   String message = "Errore nel inserimento del prezzo.";
                   JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                           JOptionPane.ERROR_MESSAGE);
               }
    		   else { 
    			   PreparedStatement statement = null;
    			   String query = "select DISTINCT C.CF, C.Nome, C.cognome, C.citt‡ "
    			   		+ " from clienti C, Abbonamenti A"
    			   		+ " where C.CF = A.CF_Cliente AND C.Citt‡ = '"+jtfcitt‡.getText()+"' AND A.prezzo > '"+Integer.parseInt(jtfSint.getText())+"'";
    			   try {
                       statement =  (PreparedStatement) connection.prepareStatement(query);  
                       ResultSet rs = statement.executeQuery();    
                       bigTable.setModel(DbUtils.resultSetToTableModel(rs));
                     
                   } catch (Exception e1) {
                       e1.printStackTrace();
                   } 
    		   }

	  });
      
       JButton loadCl2 = new JButton("Macchinari di grado A che puo usare un cliente");
       loadCl2.setBounds(551, 460, 440, 30);
       
       JLabel jlCFint = new JLabel("CF ");
       jlCFint.setBounds(25,440, 100, 20);
       JTextField jtCFint = new JTextField();
       jtCFint.setBounds(100, 440, 97, 20);
       jtCFint.setText("");
       
       
       jpClInt.add(loadCl2);
       jpClInt.add(jlCFint);
       jpClInt.add(jtCFint);

       
       loadCl2.addActionListener(e->{

 		   if( check(jtCFint.getText()) || jtCFint.getText().length()!=16){
                String message = "Errore nel inserimento del prezzo.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
 		   else { 

 			   PreparedStatement statement = null;
 			   String query = "select M.Codice_Mac , M.Nome , M.Grado, M.Anno "
 			   		+ " from metodi_esercitazione ME , macchinari M "
 			   		+ " where ME.CF_cliente = '"+jtCFint.getText()+"' AND ME.Codice_Settore = M.Codice_Settore AND M.Grado = 'A'";
 			   try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    bigTable.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
 		   }
	  });
              
       
       JButton loadCl3 = new JButton("Clienti con prima lettera del nome uguale che vivono fuori una citt‡");
       loadCl3.setBounds(551, 490, 440, 35); 

       JLabel jlLettera = new JLabel("Lettera");
       jlLettera.setBounds(25, 460, 100, 20);
       JTextField jtfLettera = new JTextField();
       jtfLettera.setBounds(100, 460, 97, 20);
       jtfLettera.setText("");
       
       jpClInt.add(loadCl3);
       jpClInt.add(jtfLettera);
       jpClInt.add(jlLettera);
       
       loadCl3.addActionListener(e->{

 		   if( check(jtfcitt‡.getText()) || jtfLettera.getText().length()!=1 || check(jtfLettera.getText()) ){
                String message = "Errore nel inserimento del prezzo.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
 		   else { 

 			   PreparedStatement statement = null;
 			   String query = "select * "
 			   		+ " from clienti C "
 			   		+ " where C.Nome LIKE '"+jtfLettera.getText()+"%' AND NOT C.Citt‡ = '"+jtfcitt‡.getText()+"'";
 			   try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    bigTable.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
 		   }

	  });
       
       JButton loadCl4 = new JButton("Nr Clienti con pi˘ di TOT  punti nei settori");
       loadCl4.setBounds(551, 520, 440, 35); 

       JLabel jlSettore = new JLabel("Settore");
       jlSettore.setBounds(25, 480, 100, 20);
       JTextField jtfSettore = new JTextField();
       jtfSettore.setBounds(100, 480, 97, 20);
       jtfSettore.setText("");
       
       JLabel jlPunti = new JLabel("Punti");
       jlPunti.setBounds(25, 500, 100, 20);
       JTextField jtfPunti = new JTextField();
       jtfPunti.setBounds(100, 500, 97, 20);
       jtfPunti.setText("");
       
       jpClInt.add(loadCl4);
       jpClInt.add(jlSettore);
       jpClInt.add(jtfSettore);
       jpClInt.add(jlPunti);
       jpClInt.add(jtfPunti);
       
       loadCl4.addActionListener(e->{

 		   if( check(jtfSettore.getText()) || !check(jtfPunti.getText()) ){
                String message = "Errore nel inserimento del prezzo.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
 		   else { 

 			   PreparedStatement statement = null;
 			   String query = "select DISTINCT S.Tipo,  ME.Metodo , COUNT(C.CF_cliente) as NR_Clienti "
 			   		+ " from settori S,  metodi_esercitazione ME, carte_clienti C "
 			   		+ " where S.Codice_Settore = ME.Codice_Settore AND ME.CF_cliente = C.CF_cliente AND C.Punti > '"+Integer.parseInt(jtfPunti.getText())+"'"
 			   		+ " group by S.tipo";
 			   try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    bigTable.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
 		   }

	  });
       
       
       JButton loadCl5 = new JButton("Clienti senza abbonamento valido di una citta");
       loadCl5.setBounds(551, 550, 440, 35); 

       jpClInt.add(loadCl5);
       
       loadCl5.addActionListener(e->{

    	   if( check(jtfcitt‡.getText()) ){
                String message = "Errore nel inserimento del prezzo.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
 		   else { 

 			   PreparedStatement statement = null;
 			   String query = "select * " 
 			   		+ " from clienti C "  
 			   		+ " where NOT EXISTS ( select * "  
 			   		+ "                   from abbonamenti A "  
 			   		+ "                   where C.CF = A.CF_Cliente )AND C.Citt‡ = '"+jtfcitt‡.getText()+"'";
 			   try {
                    statement =  (PreparedStatement) connection.prepareStatement(query);  
                    ResultSet rs = statement.executeQuery();    
                    bigTable.setModel(DbUtils.resultSetToTableModel(rs));
                  
                } catch (Exception e1) {
                    e1.printStackTrace();
                } 
 		   }

	  });
              
/*****************************************************************************************************************************************************/
       
/*************************************************************************************************************************************************************/         
       		
       		/* ISTRUTTORE INTERROGRAZIONE */
       
       JPanel jpInt = new JPanel();
       jpInt.setLayout(null);

       JScrollPane spInt = new JScrollPane();
       spInt.setBounds(6, 31, 800, 330);
       jpInt.add(spInt);

       JTable bigTableI = new JTable();
       spInt.setViewportView(bigTableI);
       jpInt.add(bigTableI.getTableHeader(), BorderLayout.NORTH);

       JButton loadII = new JButton("Istruttori che compilano SCH di clienti di una citt‡ con piu di 60 KG, e alti +180 cm");
       loadII.setBounds(450, 400, 500, 25);

       JLabel jlCittaI = new JLabel("Citta ");
       jlCittaI.setBounds(25,400, 100, 20);
       JTextField jtfcitt‡I = new JTextField();
       jtfcitt‡I.setBounds(110, 400, 97, 20);
       jtfcitt‡I.setText("");
       
       JLabel jlprezzoI = new JLabel("Prezzo ");
       jlprezzoI.setBounds(25,420, 100, 20);
       JTextField jtfprezzoI = new JTextField();
       jtfprezzoI.setBounds(110, 420, 97, 20);
       jtfprezzoI.setText("");

       JLabel jlCFi = new JLabel("CF ");
       jlCFi.setBounds(25,440, 100, 20);
       JTextField jtfCFi = new JTextField();
       jtfCFi.setBounds(110, 440, 97, 20);
       jtfCFi.setText("");
       
       JLabel jlSI = new JLabel("Specializzazione");
       jlSI.setBounds(5, 460, 100, 20);
       JComboBox<String> jtfSI= new JComboBox<String>(spec);
       jtfSI.setBounds(110, 460, 97, 20);
       
       JLabel jlPuntiI = new JLabel("Punti");
       jlPuntiI.setBounds(25, 480, 100, 20);
       JTextField jtfPuntiI = new JTextField();
       jtfPuntiI.setBounds(110, 480, 97, 20);
       jtfPuntiI.setText("");
       
       jpInt.add(loadII);
       
       jpInt.add(jlCittaI);       
       jpInt.add(jlprezzoI);
       jpInt.add(jlSI);
       jpInt.add(jlPuntiI);
       jpInt.add(jlCFi);
       
       jpInt.add(jtfcitt‡I);       
       jpInt.add(jtfprezzoI);
       jpInt.add(jtfSI);
       jpInt.add(jtfPuntiI);
       jpInt.add(jtfCFi);

       loadII.addActionListener(e->{

    	   	if( check(jtfcitt‡.getText()) ){
    	   		String message = "Errore nel inserimento dei cf ";
    	   		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
    	   				JOptionPane.ERROR_MESSAGE);
    	   	}	
		   else { 
			   PreparedStatement statement = null;
			   String query = "select I.CF as CF_Istruttore , I.Nome as Nome_Istruttore , I.Cognome as Cognome_Istruttore , SA.CF_Cliente, "
			   		+ " SA.Peso as Peso_Cliente , SA.Altezza as Altezza_Cliente , SA.Grado as Grado_Cliente "
			   		+ " from  Istruttori I, schede_allenamento SA, Clienti C "
			   		+ " where C.Citt‡ = '"+jtfcitt‡I.getText()+"' AND SA.Peso >60 AND SA.Altezza>180 "
			   		+ " AND SA.CF_Istruttore = I.CF";
			   try {
                statement =  (PreparedStatement) connection.prepareStatement(query);  
                ResultSet rs = statement.executeQuery();    
                bigTableI.setModel(DbUtils.resultSetToTableModel(rs));
              
            } catch (Exception e1) {
                e1.printStackTrace();
            } 
		   }
       });

       
       JButton loadI1 = new JButton("Istruttori di una specializzazione che gestiscono +1 gruppo");
       loadI1.setBounds(450, 430, 500, 25);

       jpInt.add(loadI1);

       
       loadI1.addActionListener(e->{

			   PreparedStatement statement = null;
			   String query = "select I.CF, I.Nome, I.Cognome, I.Specializzazione, COUNT(G.Codice_Gruppo) AS NrGruppi "
			   		+ " from Istruttori I, Gruppi G"
			   		+ " where I.Specializzazione = '"+jtfSI.getSelectedItem()+"' AND  I.CF = G.CF_Istruttore "
			   		+ " group by I.CF "
			   		+ " having COUNT( G.Codice_Gruppo)>1";
			   try {
                statement =  (PreparedStatement) connection.prepareStatement(query);  
                ResultSet rs = statement.executeQuery();    
                bigTableI.setModel(DbUtils.resultSetToTableModel(rs));
              
            } catch (Exception e1) {
                e1.printStackTrace();
            } 
		   

       });
       

       JButton loadI2 = new JButton("Istruttori di una citta che compilano SCH di clienti con piu di TOT punti");
       loadI2.setBounds(450, 460, 500, 25);

       jpInt.add(loadI2);


       loadI2.addActionListener(e->{

	   		if( !check(jtfPuntiI.getText()) || check(jtfcitt‡I.getText())){
         		String message = "Errore nel inserimento del prezzo.";
         		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
               	 	JOptionPane.ERROR_MESSAGE);
     		}
	   		else { 

		   		PreparedStatement statement = null;
		   		String query = "select DISTINCT I.CF, I.Nome, I.Cognome "
		   				+ " from Istruttori I , Schede_allenamento SA, Carte_Clienti CC "
		   				+ " where I.Citt‡ = '"+jtfcitt‡I.getText()+"' AND SA.CF_Istruttore = I.CF"
		   				+ " AND SA.CF_Cliente = CC.CF_Cliente AND CC.Punti > '"+Integer.parseInt(jtfPuntiI.getText())+"' ";
		   		try {
             	statement =  (PreparedStatement) connection.prepareStatement(query);  
             		ResultSet rs = statement.executeQuery();    
             		bigTableI.setModel(DbUtils.resultSetToTableModel(rs));
           
         		} catch (Exception e1) {
             	e1.printStackTrace();
         		} 
	   		}
       });
       
       
/*************************************************************************************************************************************************************/         
       		
       		/* FISIOTERAPISTA  INTERROGRAZIONE */
       JPanel jpFF = new JPanel();
       jpFF.setLayout(null);

       JScrollPane spFF = new JScrollPane();
       spFF.setBounds(6, 31, 800, 330);
       jpFF.add(spFF);

       JTable bigTableF = new JTable();
       spFF.setViewportView(bigTableF);
       jpFF.add(bigTableF.getTableHeader(), BorderLayout.NORTH);

       JButton loadFF = new JButton("Fisioterapisti che hanno dato esito NEGATIVO");
       loadFF.setBounds(450, 400, 500, 25);

       JLabel jlCittaF = new JLabel("Citta ");
       jlCittaF.setBounds(25,400, 100, 20);
       JTextField jtfcitt‡F = new JTextField();
       jtfcitt‡F.setBounds(110, 400, 97, 20);
       jtfcitt‡F.setText("");
   
       jpFF.add(loadFF);
       
       jpFF.add(jlCittaF); 
       jpFF.add(jtfcitt‡F);       

       loadFF.addActionListener(e->{

			   PreparedStatement statement = null;
			   String query = "select F.CF, F.Nome, F.Cognome, C.CF as CF_Paziente,  C.Nome as Nome_Paziente "
			   		+ " from  Visite V, Fisioterapisti F, Clienti C"
			   		+ " where V.CF_Fisioterapista = F.CF AND V.Esito= 'Bad' AND V.CF_Cliente = C.CF";
			   try {
                statement =  (PreparedStatement) connection.prepareStatement(query);  
                ResultSet rs = statement.executeQuery();    
                bigTableF.setModel(DbUtils.resultSetToTableModel(rs));
              
            } catch (Exception e1) {
                e1.printStackTrace();
            } 
       });

       
       JButton loadFF1 = new JButton("Fisioterapisti di un citt‡ che hanno fatto +1 Visita");
       loadFF1.setBounds(450, 430, 500, 25);

       jpFF.add(loadFF1);

       
       loadFF1.addActionListener(e->{

			   PreparedStatement statement = null;
			   String query = "select F.CF, F.Nome, F.Cognome, Count(Codice_Visita) as NrVisite "
				   		+ " from  Visite V, Fisioterapisti F "
				   		+ " where F.Citt‡ = '"+jtfcitt‡F.getText()+"' AND F.CF = V.CF_Fisioterapista "
				   		+ " group by F.CF "
				   		+ " having COUNT(V.Codice_Visita) >1";
			   try {
                statement =  (PreparedStatement) connection.prepareStatement(query);  
                ResultSet rs = statement.executeQuery();    
                bigTableF.setModel(DbUtils.resultSetToTableModel(rs));
              
            } catch (Exception e1) {
                e1.printStackTrace();
            } 
		   

       });
       
/************************************************************************************************************************************************/

  		/* SCHEDA ALLENAMENTO INTERROGRAZIONE */
  
  JPanel jpSAI = new JPanel();
  jpSAI.setLayout(null);

  JScrollPane spSAI = new JScrollPane();
  spSAI.setBounds(6, 31, 800, 330);
  jpSAI.add(spSAI);

  JTable bigTableSA = new JTable();
  spSAI.setViewportView(bigTableSA);
  jpSAI.add(bigTableSA.getTableHeader(), BorderLayout.NORTH);


  JLabel jlCittaSAI = new JLabel("Citta ");
  jlCittaSAI.setBounds(25,400, 100, 20);
  JTextField jtfcitt‡SAI = new JTextField();
  jtfcitt‡SAI.setBounds(110, 400, 97, 20);
  jtfcitt‡SAI.setText("");
  
  JLabel jlprezzoSAI = new JLabel("Prezzo ");
  jlprezzoSAI.setBounds(25,420, 100, 20);
  JTextField jtfprezzoSAI = new JTextField();
  jtfprezzoSAI.setBounds(110, 420, 97, 20);
  jtfprezzoSAI.setText("");

  JLabel jlCFSAI = new JLabel("CF ");
  jlCFSAI.setBounds(25,440, 100, 20);
  JTextField jtfCFSAI = new JTextField();
  jtfCFSAI.setBounds(110, 440, 97, 20);
  jtfCFSAI.setText("");
  
  JLabel jlSAI = new JLabel("Specializzazione");
  jlSAI.setBounds(5, 460, 100, 20);
  JComboBox<String> jtfSAI= new JComboBox<String>(spec);
  jtfSAI.setBounds(110, 460, 97, 20);
  
  JLabel jlPuntiSAI = new JLabel("Punti");
  jlPuntiSAI.setBounds(25, 480, 100, 20);
  JTextField jtfPuntiSAI = new JTextField();
  jtfPuntiSAI.setBounds(110, 480, 97, 20);
  jtfPuntiSAI.setText("");
  
  
  jpSAI.add(jlCittaSAI);       
  jpSAI.add(jlprezzoSAI);
  jpSAI.add(jlSAI);
  jpSAI.add(jlPuntiSAI);
  jpSAI.add(jlCFSAI);
  
  jpSAI.add(jtfcitt‡SAI);       
  jpSAI.add(jtfprezzoSAI);
  jpSAI.add(jtfSAI);
  jpSAI.add(jtfPuntiSAI);
  jpSAI.add(jtfCFSAI);

  JButton loadSAI = new JButton("Clienti di una citta, con +60kg, +170cm alti, Amateur");
  loadSAI.setBounds(450, 400, 500, 30);
  jpSAI.add(loadSAI);
  
  loadSAI.addActionListener(e->{

	   	if( check(jtfcitt‡SAI.getText()) ){
	   		String message = "Errore nel inserimento dei cf ";
	   		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	   				JOptionPane.ERROR_MESSAGE);
	   	}	
	   else { 
		   PreparedStatement statement = null;
		   String query = "select C.CF, C.Nome, C.Cognome, SA.Peso, SA.Altezza, SA.Grado "
		   		+ " from schede_allenamento SA, Clienti C "
		   		+ " where SA.CF_cliente = C.CF AND SA.Peso >60 AND SA.Altezza>170 AND C.Citt‡ = '"+jtfcitt‡SAI.getText()+"'"
		   				+ "AND  SA.Grado = 'amateur'";
		   try {
           statement =  (PreparedStatement) connection.prepareStatement(query);  
           ResultSet rs = statement.executeQuery();    
           bigTableSA.setModel(DbUtils.resultSetToTableModel(rs));
         
       } catch (Exception e1) {
           e1.printStackTrace();
       } 
	   }
  });

  
  JButton loadSAI1 = new JButton("Altezza media, Peso medio per settore ");
  loadSAI1.setBounds(450, 430, 500, 30);

    jpSAI.add(loadSAI1);

  
    loadSAI1.addActionListener(e->{

		   PreparedStatement statement = null;
		   String query = "select S.Codice_Settore, AVG(SA.Peso) as Peso_Medio_KG, AVG(SA.Altezza) as Altezza_Media_CM "
		   		+ " from schede_allenamento SA, Settori S, Metodi_Esercitazione ME "
		   		+ " where ME.Codice_Settore = S.Codice_Settore and SA.CF_cliente = ME.CF_cliente  "
		   		+ " group by S.Codice_Settore";
		   try {
           statement =  (PreparedStatement) connection.prepareStatement(query);  
           ResultSet rs = statement.executeQuery();    
           bigTableSA.setModel(DbUtils.resultSetToTableModel(rs));
         
       } catch (Exception e1) {
           e1.printStackTrace();
       } 
	   

  });
  

  JButton loadSAI2 = new JButton("Istruttori di una speci. che insegnano clienti di una citta con muscoli 'Developed'");
  loadSAI2.setBounds(450, 460, 500, 30);

  jpSAI.add(loadSAI2);


  loadSAI2.addActionListener(e->{

  		if( check(jtfcitt‡SAI.getText())){
    		String message = "Errore nel inserimento del prezzo.";
    		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
          	 	JOptionPane.ERROR_MESSAGE);
		}
  		else { 

	   		PreparedStatement statement = null;
	   		String query = "select  I.CF as CF_Istruttore, I.Nome, I.Cognome, C.CF AS CF_Cliente, C.Nome , C.Citt‡ as Citt‡_Cliente, SA.Grado "
	   				+ " from Istruttori I , Schede_allenamento SA, Clienti C "
	   				+ " where I.Specializzazione = '"+jtfSAI.getSelectedItem().toString()+"' AND SA.CF_Istruttore = I.CF "
	   						+ " AND SA.Grado = 'Developed'  AND SA.CF_Cliente = C.CF AND C.Citt‡ = '"+jtfcitt‡SAI.getText()+"'";
	   		try {
        	statement =  (PreparedStatement) connection.prepareStatement(query);  
        		ResultSet rs = statement.executeQuery();    
        		bigTableSA.setModel(DbUtils.resultSetToTableModel(rs));
      
    		} catch (Exception e1) {
        	e1.printStackTrace();
    		} 
  		}
  });

  JButton loadSAI3 = new JButton("Il grado muscolare dei clienti con esito visita NEGATIVO");
  loadSAI3.setBounds(450, 490, 500, 30);

  jpSAI.add(loadSAI3);
  
  loadSAI3.addActionListener(e->{

	   		PreparedStatement statement = null;
	   		String query = "select  C.CF, C.Nome, C.Cognome, SA.Grado as Grado_Muscolare, V.Esito AS Esito_Visita"
	   				+ " from Visite V , Schede_allenamento SA, Clienti C "
	   				+ " where V.CF_Cliente = SA.CF_Cliente AND V.Esito = 'Bad' AND C.CF = SA.CF_Cliente";
	   		try {
      	statement =  (PreparedStatement) connection.prepareStatement(query);  
      		ResultSet rs = statement.executeQuery();    
      		bigTableSA.setModel(DbUtils.resultSetToTableModel(rs));
    
  		} catch (Exception e1) {
      	e1.printStackTrace();
  		} 
		
});

  

/*****************************************************************************************************************************************************/

	/* MACHINARIO INTERROGRAZIONE */

  	JPanel jpMI = new JPanel();
  	jpMI.setLayout(null);

  	JScrollPane spMI = new JScrollPane();
  	spMI.setBounds(6, 31, 800, 330);
  	jpMI.add(spMI);
	
  	JTable bigTableM = new JTable();
  	spMI.setViewportView(bigTableM);
  	jpMI.add(bigTableM.getTableHeader(), BorderLayout.NORTH);

  	JLabel jlsettMI = new JLabel("Settore");
  	jlsettMI.setBounds(25, 460, 100, 20);
  	JComboBox<String> jtfsetMI = new JComboBox<String>(spec);
  	jtfsetMI.setBounds(110, 460, 97, 20);

  	
    jpMI.add(jlsettMI);
    jpMI.add(jtfsetMI);

  	JButton loadMI = new JButton("Quanti macchinari ha ogni settore");
  	loadMI.setBounds(450, 400, 500, 30);
  	jpMI.add(loadMI);

  	loadMI.addActionListener(e->{

  			PreparedStatement statement = null;
  			String query = "select  M.Codice_Settore, COUNT(M.Codice_Settore) AS NrMacchinari"
  					+ " from macchinari M "
  					+ " group by M.Codice_Settore";
  			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableM.setModel(DbUtils.resultSetToTableModel(rs));
   
  			} catch (Exception e1) {
  				e1.printStackTrace();
  			} 
  	});
  	
  	JButton loadMI1 = new JButton("Manutenzione dei macchinari di un settore fatta nel 2018 con esito grado B");
  	loadMI1.setBounds(450, 430, 500, 30);
  	jpMI.add(loadMI1);

  	loadMI1.addActionListener(e->{

  			PreparedStatement statement = null;
  			String query = "select  CM.Codice_Fabbricazione, M.Nome, CM.Esito, Year(CM.Data) as Anno  "
  					+ " from macchinari M, Controlli_Macchinari CM, Settori S "
  					+ " where  S.Tipo = '"+jtfsetMI.getSelectedItem().toString()+"' AND S.Codice_Settore = M.Codice_Settore"
  				    + " and M.Codice_Mac = CM.Codice_Fabbricazione and Year(CM.Data) = 2018 ";
  			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableM.setModel(DbUtils.resultSetToTableModel(rs));
   
  			} catch (Exception e1) {
  				e1.printStackTrace();
  			} 
  	});



  	JButton loadMI2= new JButton("Nr macchinari e nr clienti per settore");
  	loadMI2.setBounds(450, 460, 500, 30);

  	jpMI.add(loadMI2);


  	loadMI2.addActionListener(e->{
  		
  		PreparedStatement statement = null;
  		String query = "select  M.Codice_Settore, S.Tipo, COUNT(DISTINCT M.Codice_Mac) as NrMacchinari, COUNT(DISTINCT ME.CF_cliente) as nrClienti "
  				+ " from  Macchinari M, Settori S, Metodi_esercitazione ME "
  				+ " where ME.Codice_Settore = M.Codice_Settore "
  				+ " group by M.Codice_Settore ";
  		try {
  			statement =  (PreparedStatement) connection.prepareStatement(query);  
  			ResultSet rs = statement.executeQuery();    
  			bigTableM.setModel(DbUtils.resultSetToTableModel(rs));
   
  		} catch (Exception e1) {
  			e1.printStackTrace();
  		}
  	});


	JButton loadMI3 = new JButton("Esito della manutenzione dei macchinari di un settore");
	loadMI3.setBounds(450, 490, 500, 30);

	jpMI.add(loadMI3);


	loadMI3.addActionListener(e->{

 			PreparedStatement statement = null;
 			String query = "select  M.Codice_Settore, S.Tipo, CM.Data, CM.Esito  "
 					+ " from Macchinari M, Settori S, Controlli_Macchinari CM"
 					+ " where S.Tipo = '"+jtfsetMI.getSelectedItem().toString()+"' and S.Codice_Settore = M.Codice_Settore "
 					+ " and M.Codice_Mac = CM.Codice_Fabbricazione";
 			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableM.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
  			e1.printStackTrace();
			} 
	});

/*****************************************************************************************************************************************************/
	/* ESERCITAZIONE INTERROGRAZIONE */

  	JPanel jpEI = new JPanel();
  	jpEI.setLayout(null);

  	JScrollPane spEI = new JScrollPane();
  	spEI.setBounds(6, 31, 800, 330);
  	jpEI.add(spEI);
	
  	JTable bigTableE = new JTable();
  	spEI.setViewportView(bigTableE);
  	jpEI.add(bigTableE.getTableHeader(), BorderLayout.NORTH);

  	String[] metodiE = new String[] {" ", "da solo", "gruppo"};
  	JLabel jlsettEI = new JLabel("Metodo es");
  	jlsettEI.setBounds(25, 460, 100, 20);
  	JComboBox<String> jtfsetEI = new JComboBox<String>(metodiE);
  	jtfsetEI.setBounds(110, 460, 97, 20);

  	jpEI.add(jlsettEI);       
  	jpEI.add(jtfsetEI);

  	JButton loadEI = new JButton("Clienti che si esercitano da solo");
  	loadEI.setBounds(450, 400, 500, 30);
  	jpEI.add(loadEI);

  	loadEI.addActionListener(e->{

  			PreparedStatement statement = null;
  			String query = "select C.CF, C.Nome, C.Cognome, ME.metodo, S.Codice_Settore , S.Tipo "
  					+ " from  metodi_esercitazione ME, Clienti C, Settori S "
  					+ " where C.CF = ME.CF_cliente AND  ME.Codice_Settore = S.Codice_Settore "
  					+ " and ME.Metodo = 'da solo'";
  			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableE.setModel(DbUtils.resultSetToTableModel(rs));
   
  			} catch (Exception e1) {
  				e1.printStackTrace();
  			} 
  	});
  	
  	JButton loadEI1 = new JButton("Peso/altezza medio/a dei clienti esercitati da solo/gruppo");
  	loadEI1.setBounds(450, 430, 500, 30);
  	jpEI.add(loadEI1);

  	loadEI1.addActionListener(e->{

  			PreparedStatement statement = null;
  			String query = "select  ME.Metodo, AVG(SA.Peso) as Peso_Medio_KG, AVG(SA.Altezza) as Altezza_Media_CM "
  					+ " from Schede_allenamento SA,  metodi_esercitazione ME, Settori S "
  					+ " where  ME.Metodo = '"+jtfsetEI.getSelectedItem().toString()+"' AND SA.CF_cliente = ME.CF_Cliente";

  			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableE.setModel(DbUtils.resultSetToTableModel(rs));
   
  			} catch (Exception e1) {
  				e1.printStackTrace();
  			} 
  	});


/*****************************************************************************************************************************************************/	
	/* VISITE INTERROGRAZIONE */

  	JPanel jpVI = new JPanel();
  	jpVI.setLayout(null);

  	JScrollPane spVI = new JScrollPane();
  	spVI.setBounds(6, 31, 800, 330);
  	jpVI.add(spVI);
	
  	JTable bigTableV = new JTable();
  	spVI.setViewportView(bigTableV);
  	jpVI.add(bigTableV.getTableHeader(), BorderLayout.NORTH);

  	JLabel jlCFEI = new JLabel("CF ");
  	jlCFEI.setBounds(25, 400, 100, 20);
  	JTextField jtfCFEI = new JTextField();
  	jtfCFEI.setBounds(60, 400, 97, 20);

  	jpVI.add(jlCFEI);       
  	jpVI.add(jtfCFEI);

  	JButton loadVI = new JButton("Tutte le visite di un paziente");
  	loadVI.setBounds(450, 400, 500, 30);
  	jpVI.add(loadVI);

  	loadVI.addActionListener(e->{

  			PreparedStatement statement = null;
  			String query = "select V.CF_Cliente, V.Data, V.Esito, SA.Peso, SA.Altezza, SA.Grado  "
  					+ " from  Visite V, Schede_Allenamento SA"
  					+ " where V.CF_cliente = '"+jtfCFEI.getText()+"' and SA.CF_cliente = V.cf_cliente ";
  			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableV.setModel(DbUtils.resultSetToTableModel(rs));
   
  			} catch (Exception e1) {
  				e1.printStackTrace();
  			} 
  	});
  	
  	JButton loadVI1 = new JButton("Visite fatte nel 2018 con esito POSITIVO da fisioterapisti di CESENA");
  	loadVI1.setBounds(450, 430, 500, 30);
  	jpVI.add(loadVI1);

  	loadVI1.addActionListener(e->{

  			PreparedStatement statement = null;
  			String query = "select V.Codice_Visita, F.CF, F.Nome, F.Cognome, V.CF_cliente AS CF_Paziente, V.Esito, YEAR(V.Data) as Anno_Visita "
  					+ " from  Visite V, Fisioterapisti F"
  					+ " where V.CF_fisioterapista = F.CF and V.Esito = 'Good'"
  					+ " and F.Citt‡ = 'Cesena' and YEAR(V.DATA) = 2018 ";
  			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableV.setModel(DbUtils.resultSetToTableModel(rs));
   
  			} catch (Exception e1) {
  				e1.printStackTrace();
  			} 
  	});
  	
  	JButton loadVI2 = new JButton("Fisioterapisti che hanno fatto +1 visita");
  	loadVI2.setBounds(450, 460, 500, 30);
  	jpVI.add(loadVI2);

  	loadVI2.addActionListener(e->{

  			PreparedStatement statement = null;
  			String query = "select  distinct F.CF, F.Nome, F.Cognome, COUNT(V.Codice_Visita) as NrVisite "
  					+ " from  Visite V, Fisioterapisti F"
  					+ " where V.CF_fisioterapista = F.CF"
  					+ " group by F.CF"
  					+ " having COUNT(V.Codice_Visita)>1 ";
  			try {
  				statement =  (PreparedStatement) connection.prepareStatement(query);  
  				ResultSet rs = statement.executeQuery();    
  				bigTableV.setModel(DbUtils.resultSetToTableModel(rs));
   
  			} catch (Exception e1) {
  				e1.printStackTrace();
  			} 
  	});



/*****************************************************************************************************************************************************/	
		
       
        tabbedPane.addTab("Home", null, firstPanel, null);
        tabbedPane.addTab("Clienti", null, jpP, null);
        tabbedPane.addTab("Istruttori", null, jpI, null);
        tabbedPane.addTab("Fisioterapisti", null, jpF, null);
        tabbedPane.addTab("Reception", null, jpR, null);
        tabbedPane.addTab("Abbonamenti", null, jpA, null);
        tabbedPane.addTab("Settore", null, jpS, null);
        tabbedPane.addTab("Macchinari", null, jpM, null);
        tabbedPane.addTab("Gruppi", null, jpG, null);
        tabbedPane.addTab("Carte_Clienti", null, jpCC, null);
        tabbedPane.addTab("Schede_Allenamento", null, jpSA, null);
        tabbedPane.addTab("Visite", null, jpV, null);
        tabbedPane.addTab("Controlli_Macchinari",null, jpCM, null);
        tabbedPane.addTab("Esercitazioni Gruppo",null, jpES, null);
        tabbedPane.addTab("Metodi_Esercitazione", null, jpMES, null);
        tabbedPane.addTab("Clienti Info", null, jpClInt, null);
        tabbedPane.addTab("Istruttori Info", null, jpInt, null);
        tabbedPane.addTab("Fisioterapisti Info", null, jpFF, null);
        tabbedPane.addTab("Schede Allenameto Info", null, jpSAI, null);
        tabbedPane.addTab("Macchinari Info", null, jpMI, null);
        tabbedPane.addTab("Esercitazione Info", null, jpEI, null);
        tabbedPane.addTab("Visite Info", null, jpVI, null);



        for(int i=0; i<22; i++) {
        	tabbedPane.setEnabledAt(i, false);
        }
        login.addActionListener(e->{
            if(nome.getText().equals("admin")&&passwordField.getText().equals("admin")){
            	 for(int i=0;i<22;i++){
                     tabbedPane.setEnabledAt(i, true);
               }
        		String message = "Benvenuti! Sei riuscito ad accedere con successo!";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        				JOptionPane.ERROR_MESSAGE);
        		tabbedPane.setEnabledAt(0, false);
            }
        });       
        
      this.setSize(1200, 1000);
      this.setVisible(true);
    }

    
    boolean check(String s){
          try{
              Integer.parseInt(s);
              return true;
          } catch(Exception e){
              return false;
          }
    }
	
	public static void main(String[] args) {
        new TabbedPane();

	}

}
