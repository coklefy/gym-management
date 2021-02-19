package model;

public class Settore {
	private Integer codiceSett;
	private String tipo;
	private Integer numeroMacchinari;
	private Integer area;
	 
	public Settore() {}
	
	public Integer getCodiceSett() {
		return this.codiceSett;
	}
	
	public void setCodiceSett(Integer codiceSett) {
		this.codiceSett = codiceSett;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Integer getNumeroMacchinari() {
		return this.numeroMacchinari;
	}

	public void setNumeroMacchinari(Integer numeroMacchinari) {
		this.numeroMacchinari = numeroMacchinari;
	}
	
	public Integer getArea() {
		return this.area;
	}
	
	public void setArea(Integer area) {
		this.area = area;
	}
}
