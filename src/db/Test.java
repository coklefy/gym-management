package db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import model.Abbonamento;
import model.Carta_Cliente;
import model.Cliente;
import model.Controllo_Macchinario;
import model.Fisioterapista;
import model.Gruppo;
import model.Istruttore;
import model.Macchinario;
import model.Metodo_Es;
import model.Reception;
import model.Scheda_Allenamento;
import model.Settore;
import model.Visita;

public class Test {
	
	public static String converUtilDateToSqlDate(java.util.Date utilDate) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String sqlDate = sdf.format(utilDate);
		return sqlDate;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args)  {
		
		/*
	
		Tabella_Cliente clT = new Tabella_Cliente();
		clT.dropAndCreateTable();
		
		Cliente cl =  new Cliente();
		cl.setCF("SOD97MDW017Z");
		cl.setNome("Sokol");
		cl.setCognome("Guri");
		cl.setClienteID(125631);
		cl.setCittà("Cesena");
		cl.setVia("IX Febbraio");
		cl.setNrCivico(69);
		cl.setCAP(47521);
		

		
	   //prs.delete(p.getCF());
		
		Tabella_Istruttore it = new Tabella_Istruttore();
		it.dropAndCreateTable();
		
		Istruttore i =  new Istruttore();
		i.setCF("SOD97MDW017Z");
		i.setNome("Sokol");
		i.setCognome("Guri");
		i.setIstruttoreID(125631);
		i.setCittà("Cesena");
		i.setVia("IX Febbraio");
		i.setNrCivico(69);
		i.setCAP(47521);
		it.persist(i);
		
		Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
		ft.dropAndCreateTable();
		
		Fisioterapista f =  new Fisioterapista();
		f.setCF("SOD97MDW017Z");
		f.setNome("Sokol");
		f.setCognome("Guri");
		f.setFisioterapistaID(125631);
		f.setCittà("Cesena");
		f.setVia("IX Febbraio");
		f.setNrCivico(69);
		f.setCAP(47521);
		ft.persist(f);
		
		Tabella_Reception rt = new Tabella_Reception();
		rt.dropAndCreateTable();
		
		Reception r =  new Reception();
		r.setCF("SOD97MDW017Z");
		r.setNome("Sokol");
		r.setCognome("Guri");
		r.setReceptionID(125631);
		r.setCittà("Cesena");
		r.setVia("IX Febbraio");
		r.setNrCivico(69);
		r.setCAP(47521);
		rt.persist(r);
		
		 Tabella_Abbonamento at = new Tabella_Abbonamento();
		at.dropAndCreateTable();
		

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = new java.util.Date(02/05/1998);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());

		Abbonamento a = new Abbonamento();


		
		Tabella_Settore st = new Tabella_Settore();
		st.dropAndCreateTable();
		Settore s = new Settore();
		s.setCodiceSett(50);
		s.setTipo("muscol");
		s.setNumeroMacchinari(10);
		s.setArea(100);
		st.persist(s);
		
		s.setCodiceSett(50);
		s.setTipo("wda");
		s.setNumeroMacchinari(140);
		s.setArea(1040);
		st.update(s);
		
		Tabella_Macchinario mt = new Tabella_Macchinario();
		mt.dropAndCreateTable();
		
		Macchinario m = new Macchinario();
		m.setCodiceMac(1001);
		m.setNome("varicarrin");
		m.setGrado("a");
		m.setAnno(2018);
		m.setCodiceSet(10001);
		mt.persist(m);
		
		m.setCodiceMac(1001);
		m.setNome("varicadwadwarrin");
		m.setGrado("b");
		m.setAnno(2018);
		m.setCodiceSet(10001);
		mt.update(m);
		mt.delete(1001);
		
		
		Tabella_Scheda sat = new Tabella_Scheda();
		sat.dropAndCreateTable();
		
		Scheda_Allenamento sa = new Scheda_Allenamento();
		sa.setCodiceScheda(10302);
		sa.setCFc("SOD97MDW017Z");
		sa.setCFi("fwwqa");
		sa.setAltezza(32);
		sa.setPeso(81);
		sa.setGrado("a");
		sa.setTipo("muscul");
		sat.persist(sa);
		
		
		Tabella_Gruppo gt = new Tabella_Gruppo();
		gt.dropAndCreateTable();
		
		Gruppo g = new Gruppo();
		g.setCodiceGr(54);
		g.setNrMembri(10);
		g.setTipo("musc");
		g.setCFi("fwa");
		gt.persist(g);
		
		g.setCodiceGr(54);
		g.setNrMembri(110);
		g.setTipo("musc");
		g.setCFi("fwa");
		gt.update(g);
		
		//gt.delete(54);
		
		Tabella_CartaCliente cct = new Tabella_CartaCliente();
		cct.dropAndCreateTable();
		
		Tabella_MetodoEsercitazione met = new Tabella_MetodoEsercitazione();
		met.dropAndCreateTable();
		
		Metodo_Es mes = new Metodo_Es();
		
		Tabella_EsGruppo est = new Tabella_EsGruppo();
		est.dropAndCreateTable();
		
		Tabella_Fisioterapista ft = new Tabella_Fisioterapista();
		ft.dropAndCreateTable();
	
		
		Tabella_Istruttore it = new Tabella_Istruttore();
		it.dropAndCreateTable();
		
		Tabella_Gruppo gt = new Tabella_Gruppo();
		gt.dropAndCreateTable();
		*/
		/*
		Tabella_Abbonamento at = new Tabella_Abbonamento();
		at.dropAndCreateTable();
	
		
		Tabella_Scheda sct = new Tabella_Scheda();
		sct.dropAndCreateTable();
	
		Tabella_Settore st = new Tabella_Settore();
		st.dropAndCreateTable();
		
		Tabella_CompilaCC ccct = new Tabella_CompilaCC();
		ccct.dropAndCreateTable();
			*/
	}
	
}
