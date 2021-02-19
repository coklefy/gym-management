package model;

public class Carta_Cliente {
	private Integer codiceCarta;
	private String CF;
	private Integer punti;
	private String CFr;
	
	public Carta_Cliente() {}

	public Integer getCodiceCarta() {
		return this.codiceCarta;
	}
	
	public void setCodiceCarta(Integer codice) {
		this.codiceCarta = codice;
	}
	
	public String getCF() {
		return this.CF;
	}
	
	public void setCF(String cf) {
		this.CF = cf;
	}
	
	public Integer getPunti() {
		return this.punti;
	}
	
	public void setPunti(Integer points) {
		this.punti = points;
	}
	
}
