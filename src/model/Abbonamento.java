package model;

import java.sql.Date;

public class Abbonamento {
	private Integer   IDabbonamento;
	private String   CFcliente;
	private Integer  durata;
	private Date 	 data;
	private String   tipo;
	private Integer  prezzo;

	public Abbonamento() {}
	
	public String getCFcliente() {
		return this.CFcliente;
	}
	
	public void setCFcliente(String CFcliente) {
		this.CFcliente = CFcliente;
	}
	
	public Integer getIDabbonamento() {
		return this.IDabbonamento;
	}
	
	public void setIDabbonamento(Integer IDabbonamento) {
		this.IDabbonamento = IDabbonamento;
	}
	
	public Integer getDurata() {
		return this.durata;
	}
	
	public void setDurata(Integer durata) {
		this.durata = durata;
	}
	
	public Date getData() {
		return this.data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Integer getPrezzo() {
		return this.prezzo;
	}
	
	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}
	
	
}

