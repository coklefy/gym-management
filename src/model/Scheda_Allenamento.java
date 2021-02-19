package model;

public class Scheda_Allenamento {
	private Integer codiceScheda;
	private String CF_Cliente;
	private String CF_Istruttore;
	private String tipo;
	private Integer altezza;
	private Integer peso;
	private String grado;
	
	public Scheda_Allenamento() {}
	
	public Integer getCodiceScheda() {
		return this.codiceScheda;
	}
	
	public void setCodiceScheda(Integer codice) {
		this.codiceScheda = codice;
	}
	
	public String getCFc() {
		return this.CF_Cliente;
	}
	
	public void setCFc(String CFc) {
		this.CF_Cliente = CFc;
	}
	
	public String getCFi() {
		return this.CF_Istruttore;
	}
	
	public void setCFi(String CFi) {
		this.CF_Istruttore = CFi;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Integer getAltezza() {
		return this.altezza;
	}
	
	public void setAltezza(Integer alt) {
		this.altezza = alt;
	}
	
	public Integer getPeso() {
		return this.peso;
	}
	
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	public String getGrado() {
		return this.grado;
	}
	
	public void setGrado(String grado) {
		this.grado = grado;
	}
}
