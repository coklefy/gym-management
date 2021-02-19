package model;

import java.sql.Date;

public class Visita {
	private Integer codiceVisita;
	private String CFfisioterapista;
	private String CFcliente;
	private Date data;
	private String esito;
	
	public Visita() {}
	
	public Integer getCodiceVisita() {
		return this.codiceVisita;
	}
	
	public void setCodiceVisita(Integer codiceV) {
		this.codiceVisita = codiceV;
	}
	
	public String getCFf() {
		return this.CFfisioterapista;
	}
	
	public void setCFf(String CF) {
		this.CFfisioterapista = CF;
	}
	
	public String getCFc() {
		return this.CFcliente;
	}
	
	public void setCFc(String CF) {
		this.CFcliente = CF;
	}
		
	public Date getData() {
		return this.data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getEsito() {
		return this.esito;
	}
	
	public void setEsito(String esito) {
		this.esito = esito;
	}
}
