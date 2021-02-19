package model;

public class Gruppo {
	private Integer codiceGr;
	private Integer numeroMembri;
	private String tipo;
	private String CFi;

	public Gruppo() {}
	
	public Integer getCodiceGr() {
		return this.codiceGr;
	}
	
	public void setCodiceGr(Integer codiceGr) {
		this.codiceGr = codiceGr;
	}
	
	public Integer getNrMembri() {
		return this.numeroMembri;
	}
	
	public void setNrMembri(Integer nr) {
		this.numeroMembri = nr;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCFi() {
		return this.CFi;
	}
	
	public void setCFi(String CF) {
		this.CFi = CF;
	}
}
